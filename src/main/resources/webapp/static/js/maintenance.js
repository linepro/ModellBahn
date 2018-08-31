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

		while (table.rows.length) {
			table.deleteRow(0);
		}

		var body = document.createElement('tbody');

		table.appendChild(body);

		var p;
		for (p = 0; p < this.pageSize; p++) {
			var tr = table.insertRow(0);

			var i;
			for (i = 0; i < this.columnCount; i++) {
				var td = tr.insertCell(i);
				td.appendChild(document.createTextNode(""));
				td.style.height = "40px";
				td.style.paddingBottom = "2px";
				td.style.paddingLeft = "2px";
				td.style.paddingRight = "2px";
				td.style.paddingTop = "2px";
			}
		}
		
		var header = table.createTHead();
		var headRow = header.insertRow(0);

		var i;
		for (i = 0; i < this.columnCount; i++) {
			var th = headRow.insertCell(i);
			th.style.height = "40px";
			if (!this.deleteButtons || i < (this.columnCount-1)) {
				th.append(document.createTextNode(this.columns[i].heading));
			}
		}

		table.deleteTFoot();

		var footer = table.createTFoot();
		
		if (this.paged) {
			var navRow = footer.insertRow(0);
	
			for (i = 0; i < this.columnCount; i++) {
				var td = navRow.insertCell(i);
				td.style.height = "40px";
				td.style.paddingBottom = "2px";
				td.style.paddingLeft = "5px";
				td.style.paddingRight = "5px";
				td.style.paddingTop = "2px";
				if (i == 0) {
					td.style.textAlign = "left";
					td.colspan = 1;
				} else if (i == this.columnCount-1) {
					td.style.textAlign = "right";
					td.colspan = 1;
				} else {
					td.style.textAlign = "center";
					td.colspan = this.columnCount-2;
				}
				td.appendChild(document.createTextNode(""));
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

		cell.style.textAlign = "right";
		cell.style.color = "white";
		cell.style.paddingBottom = "2px";
		cell.style.paddingLeft = "2px";
		cell.style.paddingRight = "2px";
		cell.style.paddingTop = "2px";
	
		if (lnk) {
			var btn = document.createElement("BUTTON");
			btn.appendChild(document.createTextNode(lnk.rel));
			btn.setAttribute("value", lnk.href);
			btn.setAttribute("onclick", this.tableName + ".deleteRow(this.value)");
			btn.style.width = "65px";
			cell.appendChild(btn);
		} 
	}
	
	addLinkButton(lnk, cell) {
		if (cell.childNodes[0]) {
			cell.removeChild(cell.childNodes[0]);
		}

		if (lnk) {
			var btn = document.createElement("BUTTON");
			btn.appendChild(document.createTextNode(lnk.rel));
			btn.setAttribute("value", lnk.href);
			btn.setAttribute("onclick", this.tableName + ".getData(this.value)");
			btn.style.width = "65px";
			cell.appendChild(btn);
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
			var tr = table.tBodies[0].rows[p];

			var c;
			for (c = 0; c < this.columnCount; c++) {
				var td = tr.cells[c];

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
				var tfoot = document.getElementById(this.tableName).tFoot;

				var prev = tfoot.rows[0].cells[0];

				this.addLinkButton(jsonData.links.find(function(e) { return e.rel == "previous"; }), prev);

				var next = tfoot.rows[0].cells[this.columnCount-1];

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