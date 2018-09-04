class Column {
	constructor(heading, binding) {
		this.heading = heading;
		this.binding = binding;
	}
}

class ItemGrid {
	constructor(pageSize, apiRoot, collection, tableName, columns, paged, deleteButtons, updateLink, addLink, children) {
		this.pageSize = pageSize;
		this.current = apiRoot + (paged ? "?pageNumber=0&pageSize=" + pageSize : "");
		this.collection = collection;
		this.tableName = tableName;
		this.columns = columns;
		this.paged = paged;
		this.deleteButtons = deleteButtons;
		this.addLink = addLink;
		this.updateLink = updateLink;
		this.columnCount = this.columns.length + (this.deleteButtons ? 1 : 0);
		this.children = children;
		this.initialized = false;
	}

	init() {
		this.loadData();
	}

	initGrid(pageSize) {
		this.pageSize = this.paged ? this.pageSize : pageSize;

		var table = document.getElementById(this.tableName);
		table.innerHtml = "";
		table.className = "table";

		var header = document.createElement('div');
		header.className = "thead";
		table.append(header);

		var headRow = document.createElement('div');
		headRow.className = "table-row";
		headRow.id = this.tableName + "Head";
		header.append(headRow);

		var i;
		for (i = 0; i < this.columnCount; i++) {
			var th = document.createElement('div');
			th.className = "table-heading";

			if (!this.deleteButtons || i < (this.columnCount-1)) {
				th.append(document.createTextNode(this.columns[i].heading));
			}
			
			headRow.append(th);
		}

		var body = document.createElement('div');
		body.className = "tbody";
		table.append(body);

		var p;
		for (p = 0; p < this.pageSize; p++) {
			var tr = document.createElement('div');
			tr.className = "table-row";
			tr.id = this.tableName + p;
			body.append(tr);

			var i;
			for (i = 0; i < this.columnCount; i++) {
				var td = document.createElement('div');
				td.className = (this.deleteButtons && (i == (this.columnCount-1)) ? "table-del" : "table-cell");
				
				td.appendChild(document.createTextNode(""));
				tr.append(td);
			}
		}
		
		var footer = document.createElement('div');
		footer.className = "tfoot";
		table.append(footer);

		if (this.paged) {
			var navRow = document.createElement('div');
			navRow.className = "table-row";
			navRow.id = this.tableName + "Foot";
			footer.append(navRow);

			for (i = 0; i < this.columnCount; i++) {
				var tf = document.createElement('div');

				if (i == 0) {
					tf.className = "table-prev";
					tf.id = this.tableName + "Prev";
				} else if (i == (this.columnCount-1)) {
					tf.className = "table-next";
					tf.id = this.tableName + "Next";
				} else {
					tf.className = "table-add";
					if (i == Math.trunc(this.columnCount/2)) {
						tf.id = this.tableName + "Add";
					}
				}

				tf.appendChild(document.createTextNode(""));
				navRow.append(tf);
			}
		}
		
		this.initialized = true;
	}

	loadData() {
		this.getData(this.current);
	}

	deleteRow(deleteUrl) {
		var grid = this;
		$.ajax( { url: deleteUrl, type: 'DELETE', success: function( data, textStatus, jqXHR ) { grid.loadData(); } } )
			.fail(function( jqXHR, textStatus, errorThrown ) { grid.reportError(jqXHR, textStatus, errorThrown); });
	}

	addDeleteButton(lnk, cell) {
		if (cell.childNodes[0]) {
			cell.removeChild(cell.childNodes[0]);
		}

		if (lnk) {
			var btn = document.createElement("button");
			btn.setAttribute("value", lnk.href);
			btn.setAttribute("onclick", this.tableName + ".deleteRow(this.value)");
			btn.style.width = "65px";
			cell.appendChild(btn);
			
			var img = document.createElement("img");
			img.alt = lnk.rel;
			img.src = "img/" + lnk.rel + ".png";
			btn.appendChild(img);
		} 
	}
	
	addLinkButton(lnk, cell) {
		if (cell.childNodes[0]) {
			cell.removeChild(cell.childNodes[0]);
		}

		if (lnk) {
			var btn = document.createElement("button");
			btn.setAttribute("value", lnk.href);
			btn.setAttribute("onclick", this.tableName + ".getData(this.value)");
		    btn.style.width = "65px";
			cell.appendChild(btn);
		    
			var img = document.createElement("img");
			img.alt = lnk.rel;
			img.src = "img/" + lnk.rel + ".png";
			btn.appendChild(img);
		} 
	}

	renderData(jsonData) {
		var entities = (this.collection ? jsonData[this.collection] : jsonData.entities ? jsonData.entities : [ jsonData ]);

		if (!this.initialized) {
			this.initGrid(entities.length);
		}

		var table = document.getElementById(this.tableName);

		var p;
		for (p = 0; p < this.pageSize; p++) {
			var tr = document.getElementById(this.tableName + p);

			var c;
			for (c = 0; c < this.columnCount; c++) {
				var td = tr.childNodes[c];

				if (p < entities.length) {
					if (c == (this.columnCount-1) && this.deleteButtons) {
						this.addDeleteButton(entities[p].links.find(function(e) { return e.method == "DELETE"; }), td);
					} else {
						td.removeChild(td.childNodes[0]);
						
						var txt = document.createTextNode(entities[p][this.columns[c].binding]);
						
						if (c == 0 && this.updateLink) {
							var item = entities[p].links.find(function(e) { return e.rel == "self"; })
							if (item) {
								var a = document.createElement('a');
								var ref = this.updateLink + "?self=" + item.href;
								a.setAttribute('href', ref);
								a.appendChild(txt);
								td.appendChild(a);
							}
						} else {
							td.appendChild(txt);
						}
					}
				} else {
					td.removeChild(td.childNodes[0]);
					td.appendChild(document.createTextNode(""));
				}
			}
		}
	}

	renderJson(jsonData, textStatus, jqXHR, restUrl) {
		if (jqXHR.status == 200) {
			this.renderData(jsonData);

			if (this.children) {
				var i;
				for (i = 0 ; i < this.children.length ; i++) {
					this.children[i].renderData(jsonData);
				}
			}

			if (this.paged) {
				var prev = document.getElementById(this.tableName + "Prev");

				this.addLinkButton(jsonData.links.find(function(e) { return e.rel == "previous"; }), prev);

				var next = document.getElementById(this.tableName + "Next");

				this.addLinkButton(jsonData.links.find(function(e) { return e.rel == "next"; }), next);
			}

			this.current = restUrl;
		}
	}
	
	reportError( jqxhr, textStatus, error ) {
		alert( "error: " + error );
	}
	
	getData(restUrl) {
		var grid = this;
		$.getJSON(restUrl, function( data, textStatus, jqXHR ) { grid.renderJson(data, textStatus, jqXHR, restUrl) })
			.fail( function( jqXHR, textStatus, errorThrown ) { grid.reportError(  jqXHR, textStatus, errorThrown  ); });
	}
};