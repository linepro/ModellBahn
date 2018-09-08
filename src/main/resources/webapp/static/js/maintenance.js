// module "maintenance.js"
"use strict"

class ItemGrid {
	constructor(pageSize, apiRoot, collection, tableName, columns, paged, deleteButtons, updateLink, addLink, editable, children) {
		this.pageSize = pageSize;
		this.current = apiRoot + (paged ? "?pageNumber=0&pageSize=" + pageSize : "");
		this.collection = collection;
		this.tableName = tableName;
		this.columns = columns;
		this.paged = paged;
		this.deleteButtons = deleteButtons;
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
		var columnCount = this.columns.length + (this.deleteButtons ? 1 : 0);
		var deleteButtons = this.deleteButtons;
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
				addText(th, columns[i].heading);
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
				addText(td, "");
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

	renderData(jsonData) {
		var columns = this.columns;
		var columnCount = this.columns.length + (this.deleteButtons ? 1 : 0);
		var deleteButtons = this.deleteButtons;
		var editable = this.editable;
		var entities = (this.collection ? jsonData[this.collection] : jsonData.entities ? jsonData.entities : [ jsonData ]);
		var paged = this.paged;
		var pageSize = paged ? this.pageSize : Math.max(1, entities.length);
		var tableName = this.tableName;
		var updateLink = this.updateLink;

		if (!this.initialized) {
			this.initGrid(pageSize);
		}

		var table = document.getElementById(tableName);

		var p;
		for (p = 0; p < pageSize; p++) {
			var tr = document.getElementById(tableName + p);

			var c;
			for (c = 0; c < columnCount; c++) {
				var td = tr.childNodes[c];

				if (p < entities.length) {
					var entity = entities[p];

					if (c == (columnCount-1) && deleteButtons) {
						var lnk = entity.links.find(function(e) { return e.method == "DELETE"; });
						addButton(lnk, td, tableName, "deleteRow(this.value)");
					} else {
						removeChildren(td);

						var ctl;
						var data = entity[columns[c].binding];

						if (editable && columns[c].inputType) {
							if (columns[c].inputType == "select" && columns[c].select) {
								ctl = document.createElement("select");
								columns[c].select.addOptions(ctl, 1, data);
							} else {
								ctl = document.createElement("input");
								ctl.type = columns[c].inputType;
								ctl.value = data;
							}
						} else {
							ctl = document.createTextNode(data);
						}
						
						if (c == 0 && updateLink) {
							// Add a link to the page where this row can be edited passing the item href in the link (should be a post??)
							var lnk = entity.links.find(function(e) { return e.rel == "self"; })

							if (lnk) {
								var a = document.createElement('a');
								var ref = updateLink + "?self=" + lnk.href;
								a.setAttribute('href', ref);
								a.appendChild(ctl);

								ctl = a;
							}
						}

						td.appendChild(ctl);
					}
				} else {
					removeChildren(td);
					addText(td, "");
				}
			}
		}
	}

	renderJson(jsonData, textStatus, jqXHR, restUrl) {
		if (jqXHR.status == 200) {
			var children = this.children;
			var paged = this.paged;
			var tableName = this.tableName;

			this.renderData(jsonData);

			if (children) { children.forEach(function(child){child.renderData(jsonData);}) };

			if (paged) {
				var prev = document.getElementById(tableName + "Prev");
				var lnk = jsonData.links.find(function(e) { return e.rel == "previous"; });

				addButton(lnk, prev, tableName, "getData(this.value)");

				var next = document.getElementById(tableName + "Next");
				lnk = jsonData.links.find(function(e) { return e.rel == "next"; });

				addButton(lnk, next, tableName, "getData(this.value)");
			}

			this.current = restUrl;
		}
	}
	
	getData(restUrl) {
		var grid = this;
		$.getJSON(restUrl, function( data, textStatus, jqXHR ) { grid.renderJson(data, textStatus, jqXHR, restUrl) })
			.fail( function( jqXHR, textStatus, errorThrown ) { reportError(  jqXHR, textStatus, errorThrown  ); });
	}
}