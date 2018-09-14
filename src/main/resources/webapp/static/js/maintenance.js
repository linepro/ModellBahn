// module "maintenance.js"
"use strict"

class ItemGrid {
  constructor(pageSize, restRoot, collection, tableName, columns, paged, editable, children) {
    this.pageSize = pageSize;
    this.current = restRoot + (paged ? "?pageNumber=0&pageSize=" + pageSize : "");
    this.collection = collection;
    this.tableName = tableName;
    this.columns = columns;
    this.paged = paged;
    this.editable = editable;
    this.children = children;
    this.initialized = false;

    this.columns.forEach(function(column) { column.setTableName(tableName);});
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
  
    addHeader(tableName, table, columns);

    var body = document.createElement("div");
    body.className = "tbody";
    table.append(body);

    var p;
    for (p = 0; p < pageSize; p++) {
      var tr = document.createElement("div");
      tr.className = "table-row";
      tr.id = getRowId(tableName, p);
      body.append(tr);

      var key = document.createElement("input");
      key.type = "hidden";
      key.id = getFieldId(tableName, p, "key");
      tr.append(key);

      columns.forEach(function(column) {
        var td = document.createElement("div");
        td.id = getCellId(tableName, p, column);
        td.className = "table-cell";
        addText(td, "");
        tr.append(td);
      });
    }

    addFooter(tableName, table, columns, paged);

    this.initialized = true;
  }

  loadData() {
    if (this.current) {
      this.getData(this.current);
    } else {
      // Add mode
      this.initGrid(this.pageSize);
    }
  }

  renderData(jsonData) {
    var columns = this.columns;
    var columnCount = this.columns.length + (this.deleteButtons ? 1 : 0);
    var editable = this.editable;
    var entities = (this.collection ? jsonData[this.collection] : jsonData.entities ? jsonData.entities : [ jsonData ]);
    this.pageSize = this.paged ? this.pageSize : Math.max(1, entities.length);
    var pageSize = this.pageSize;
    var tableName = this.tableName;
    var updateLink = this.updateLink;

    if (!this.initialized) {
      this.initGrid(pageSize);
    }

    var table = document.getElementById(tableName);

    var p;
    for (p = 0; p < pageSize; p++) {
      var tr = document.getElementById(tableName + p);

      var entity;

      var key = document.getElementById(getFieldId(tableName, p, "key"));

      if (p < entities.length) {
        entity = entities[p]
        var lnk = getLink(entity.links, "self");
        key.value = lnk.href;
      } else {
        key.value = "";
      }

      columns.forEach(function(column) {
    	var td = document.getElementById(getCellId(tableName, p, column));

        removeChildren(td);

        if (entity) {
          var ctl = column.getControl(td, entity, editable, tableName);
          td.appendChild(ctl);
        } else {
          addText(td, "");
        }
      });
      
    }
  }

  renderJson(jsonData, textStatus, jqXHR, restUrl) {
    if (jqXHR.status == 200) {
      var children = this.children;
      var tableName = this.tableName;

      this.renderData(jsonData);

      if (children) { children.forEach(function(child){child.renderData(jsonData);}) };

      var prev    = document.getElementById(tableName + "Prev");

      if (prev) {
        var prevLnk = getLink(jsonData.links, "previous");

        addButton(prev, prevLnk, tableName + ".getData(this.value)");
      }
    
      var next    = document.getElementById(tableName + "Next");
    
      if (next) {
        var nextlnk = getLink(jsonData.links, "next");

        addButton(next, nextlnk, tableName + ".getData(this.value)");
      }

      this.current = restUrl;
    }
  }

  getData(restUrl) {
    var grid = this;
    $.getJSON(restUrl, function( data, textStatus, jqXHR ) { grid.renderJson(data, textStatus, jqXHR, restUrl) })
      .fail( function( jqXHR, textStatus, errorThrown ) { reportError(  jqXHR, textStatus, errorThrown  ); });
  }

  rowData() {
    var data = [];
    var tableName = this.tableName;
    
    for (p = 0 ; p < this.pageSize; p++) {
    	var row = rowData(getRowId(tableName, p));
    	if (row.length) {
    		data.push(row);
    	}
      }

    return data;
  }

  rowData(rowId) {
    var data = {};
    var tableName = this.tableName;

    var key = document.getElementById(getFieldId(tableName, p, "key"));
	  if (key) {
	    this.columns.forEach(function(column) {
	      if (column.binding) {
	        var td = document.getElementById(getFieldId(tableName, p, column.binding));
	        data[column.binding] = td.value;
	      }
	    });
	
	    this.children.forEach(function(child) {
	    	data[child.collection] = child.rowData();
	    	});
	  }

    return data;
  }

  addRow(addUrl) {
    window.location.href = addUrl;
  }

  deleteRow(deleteUrl) {
    var grid = this;
    $.ajax( { url: deleteUrl, type: "DELETE", success: function( data, textStatus, jqXHR ) { grid.loadData(); } } )
      .fail(function( jqXHR, textStatus, errorThrown ) { grid.reportError(jqXHR, textStatus, errorThrown); });
  }

  editRow(editUrl, rowRef) {
    window.location.href = editUrl + rowRef;
  }

  updateRow(updateUrl, rowId) {
    var grid = this;
    var data = grid.rowData(rowId);
    $.ajax( { url: updateUrl, type: "PUT", data: $.param(data), success: function( data, textStatus, jqXHR ) { grid.loadData(); } } )
      .fail(function( jqXHR, textStatus, errorThrown ) { grid.reportError(jqXHR, textStatus, errorThrown); });
  }
}