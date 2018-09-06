class Column {
	constructor(heading, binding, inputType) {
		this.heading = heading;
		this.binding = binding;
		this.inputType = inputType;
	}
}

class ItemGrid {
	constructor(pageSize, apiRoot, collection, tableName, columns, paged, deleteButtons, editButtons, updateLink, addLink, editable, children) {
		this.pageSize = pageSize;
		this.current = apiRoot + (paged ? "?pageNumber=0&pageSize=" + pageSize : "");
		this.collection = collection;
		this.tableName = tableName;
		this.columns = columns;
		this.paged = paged;
		this.deleteButtons = deleteButtons;
		this.editButtons = editButtons;
		this.updateLink = updateLink;
		this.addLink = addLink;
		this.editable = editable;
		this.children = children;
		this.initialized = false;
	}

	init() {
		this.loadData();
	}

	initGrid(pageSize) {
		var columns = this.columns;
		var columnCount = this.columns.length + (this.deleteButtons + this.editButtons + this.addLink ? 1 : 0);
		var deleteButtons = this.deleteButtons;
		var editButtons = this.editButtons
		var paged = this.paged;
		var pageSize = paged ? this.pageSize : pageSize;
		var tableName = this.tableName;
		
		var table = document.getElementById(this.tableName);
		table.innerHtml = "";
		table.className = "table";

		var header = document.createElement('div');
		header.className = "thead";
		table.append(header);

		var headRow = document.createElement('div');
		headRow.className = "table-row";
		headRow.id = tableName + "Head";
		header.append(headRow);

		var i;
		for (i = 0; i < columnCount; i++) {
			var th = document.createElement('div');
			th.className = "table-heading";

			if (!deleteButtons || i < (columnCount-1)) {
				th.append(document.createTextNode(columns[i].heading));
			}
			
			headRow.append(th);
		}

		var body = document.createElement('div');
		body.className = "tbody";
		table.append(body);

		var p;
		for (p = 0; p < pageSize; p++) {
			var tr = document.createElement('div');
			tr.className = "table-row";
			tr.id = tableName + p;
			body.append(tr);

			var i;
			for (i = 0; i < columnCount; i++) {
				var td = document.createElement('div');
				const delCell = deleteButtons && (i == (columnCount-1));
				td.className = delCell ? "table-del" : "table-cell";
				if (!delCell) { td.id = tableName + "_" + columns[i].binding + p };
				td.appendChild(document.createTextNode(""));
				tr.append(td);
			}
		}
		
		var footer = document.createElement('div');
		footer.className = "tfoot";
		table.append(footer);

		if (paged) {
			var navRow = document.createElement('div');
			navRow.className = "table-row";
			navRow.id = tableName + "Foot";
			footer.append(navRow);

			for (i = 0; i < columnCount; i++) {
				var tf = document.createElement('div');

				if (i == 0) {
					tf.className = "table-prev";
					tf.id = tableName + "Prev";
				} else if (i == (columnCount-1)) {
					tf.className = "table-next";
					tf.id = tableName + "Next";
				} else {
					tf.className = "table-add";
					if (i == Math.trunc(columnCount/2)) {
						tf.id = tableName + "Add";
					}
				}

				tf.appendChild(document.createTextNode(""));
				navRow.append(tf);
			}
		}
		
		this.initialized = true;
	}

	loadData() {
		if (this.current) {
			this.getData(this.current);
		}
	}

	deleteRow(deleteUrl) {
		var grid = this;
		$.ajax( { url: deleteUrl, type: 'DELETE', success: function( data, textStatus, jqXHR ) { grid.loadData(); } } )
			.fail(function( jqXHR, textStatus, errorThrown ) { grid.reportError(jqXHR, textStatus, errorThrown); });
	}
	
	editRow(editUrl)
	{
		var grid = this;
		$.ajax( { url: editUrl, type: 'EDIT'', success: function( data, textStatus, jqXHR ) { grid.loadData(); } } )
			.fail(function( jqXHR, textStatus, errorThrown ) { grid.reportError(jqXHR, textStatus, errorThrown); });
	}

	addDeleteButton(lnk, cell) {
		var tableName = this.tableName;

		if (cell.childNodes[0]) {
			cell.removeChild(cell.childNodes[0]);
		}

		if (lnk) {
			var btn = document.createElement("button");
			btn.setAttribute("value", lnk.href);
			btn.setAttribute("onclick", tableName + ".deleteRow(this.value)");
			btn.style.width = "65px";
			cell.appendChild(btn);
			
			var img = document.createElement("img");
			img.alt = lnk.rel;
			img.src = "img/" + lnk.rel + ".png";
			img.style.height = "18px";
			btn.appendChild(img);
		} 
	}
	
	addEditButton(lnk, cell)
	{
		var tableName = this.tableName;
		if (cell.childNodes[0])
		{
			cell.removeChild(cell.childNodes[0]);
		}
		
		if (lnk)
		{
			var btn = document.createElement("button");
			btn.setAttribute("value", lnk.href);
			btn.setAttribute("onclick", tableName + ".editRow(this.value)");
			btn.style.width = "65px";
			cell.appendChild(btn);
			
			var img = document.createElement("img");
			img.alt = lnk.rel;
			img.src = "img/" + lnk.rel + ".png";
			img.style.height = "18px";
			btn.appendChild(img);
		}
	}
	addLinkButton(lnk, cell) {
		var tableName = this.tableName;

		if (cell.childNodes[0]) {
			cell.removeChild(cell.childNodes[0]);
		}

		if (lnk) {
			var btn = document.createElement("button");
			btn.setAttribute("value", lnk.href);
			btn.setAttribute("onclick", tableName + ".getData(this.value)");
		    btn.style.width = "65px";
			cell.appendChild(btn);
		    
			var img = document.createElement("img");
			img.alt = lnk.rel;
			img.src = "img/" + lnk.rel + ".png";
			img.style.height = "18px";
			btn.appendChild(img);
		} 
	}

	renderData(jsonData) {
		var columns = this.columns;
		var columnCount = this.columns.length + (this.deleteButtons + this.editButtons + this.addLink ? 1 : 0);
		var deleteButtons = this.deleteButtons;
		var editable = this.editable;
		var entities = (this.collection ? jsonData[this.collection] : jsonData.entities ? jsonData.entities : [ jsonData ]);
		var paged = this.paged;
		var pageSize = paged ? this.pageSize : pageSize;
		var tableName = this.tableName;
		var updateLink = this.updateLink;

		if (!this.initialized) {
			this.initGrid(entities.length);
		}

		var table = document.getElementById(tableName);

		var p;
		for (p = 0; p < pageSize; p++) {
			var tr = document.getElementById(tableName + p);

			var c;
			for (c = 0; c < columnCount; c++) {
				var td = tr.childNodes[c];

				if (p < entities.length) {
					if (c == (columnCount-1) && deleteButtons) {
						this.addDeleteButton(entities[p].links.find(function(e) { return e.method == "DELETE"; }), td);
					} else {
						td.removeChild(td.childNodes[0]);

						var ctl;
						var data = entities[p][columns[c].binding];

						if (editable && columns[c].inputType && !(columns[c].inputType == "select")) {
							ctl = document.createElement("input");
							ctl.type = columns[c].inputType;
							ctl.value = data;
						} else {
							ctl = document.createTextNode(data);
						}
						
						if (c == 0 && updateLink) {
							var item = entities[p].links.find(function(e) { return e.rel == "self"; })
							if (item) {
								var a = document.createElement('a');
								var ref = updateLink + "?self=" + item.href;
								a.setAttribute('href', ref);
								a.appendChild(ctl);
								td.appendChild(a);
							}
						} else {
							td.appendChild(ctl);
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
			var children = this.children;
			var paged = this.paged;
			var tableName = this.tableName;

			if (children) {
				var i;
				for (i = 0 ; i < children.length ; i++) {
					children[i].renderData(jsonData);
				}
			}

			if (paged) {
				var prev = document.getElementById(tableName + "Prev");

				this.addLinkButton(jsonData.links.find(function(e) { return e.rel == "previous"; }), prev);

				var next = document.getElementById(tableName + "Next");

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