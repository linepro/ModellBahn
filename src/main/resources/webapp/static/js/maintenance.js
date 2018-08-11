class Column {
	constructor(heading, binding) {
		this.heading = heading;
		this.binding = binding;
	}
}

class ItemGrid {
	constructor(pageSize, apiRoot, tableName, columns) {
		this.pageSize = pageSize;
		this.current = apiRoot + "?pageNumber=0&pageSize=" + pageSize;
		this.tableName = tableName;
		this.columns = columns;
	}

	init() {
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
			for (i = 0; i < this.columns.length; i++) {
				var td = tr.insertCell(i);
				td.appendChild(document.createTextNode(""));
				td.style.paddingBottom = "2px";
				td.style.paddingLeft = "2px";
				td.style.paddingRight = "2px";
				td.style.paddingTop = "2px";
			}
		}
		
		var header = table.createTHead();
		var headRow = header.insertRow(0);

		var i;
		for (i = 0; i < this.columns.length; i++) {
			var th = headRow.insertCell(i);
			th.style.height = "40px";
			th.append(document.createTextNode(this.columns[i].heading));
		}

		table.deleteTFoot();

		var footer = table.createTFoot();
		var navRow = footer.insertRow(0);

		for (i = 0; i < this.columns.length; i++) {
			var td = navRow.insertCell(i);
			td.style.height = "40px";
			td.style.textAlign = (i == 0 ? "left" : "right");
			td.appendChild(document.createTextNode(""));
		}

		this.loadData();
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

		cell.style.textAlign = "center";
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

	renderJson(jsonData, textStatus, jqXHR, restUrl) {
		if (jqXHR.status == 200) {
			var table = document.getElementById(this.tableName);

			var p;
			for (p = 0; p < this.pageSize; p++) {
				var tr = table.tBodies[0].rows[p];

				var c;
				for (c = 0; c < this.columns.length; c++) {
					var td = tr.cells[c];
					
					if (p < jsonData.entities.length) {
						if (c == (this.columns.length - 1)) {
							this.addDeleteButton(jsonData.entities[p].links.find(function(e) { return e.method == "DELETE"; }), td);
						} else {
							td.removeChild(td.childNodes[0]);
							td.appendChild(document.createTextNode(jsonData.entities[p][this.columns[c].binding]));
						}
					} else {
						td.removeChild(td.childNodes[0]);
						td.appendChild(document.createTextNode(""));
					}
				}
			}

			var tfoot = table.tFoot;

			var prev = tfoot.rows[0].cells[0];

			this.addLinkButton(jsonData.links.find(function(e) { return e.rel == "previous"; }), prev);

			var next = tfoot.rows[0].cells[tfoot.rows[0].cells.length-1];

			this.addLinkButton(jsonData.links.find(function(e) { return e.rel == "next"; }), next);

			this.current = restUrl;
		}
	}
	
	reportError( jqxhr, textStatus, error ) {
		alert( "error: " + error );
	}
	
	getData(restUrl) {
		var grid = this;
		$.getJSON(restUrl, function( data, textStatus, jqXHR ) { grid.renderJson(data, textStatus, jqXHR, restUrl) })
			.fail( function() { grid.reportError(  jqXHR, textStatus, errorThrown  ); });
	}
};