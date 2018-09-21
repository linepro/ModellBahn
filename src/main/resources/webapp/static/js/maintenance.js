// module "maintenance.js"
"use strict"

class ItemGrid {
  constructor(pageSize, fetchUrl, tableName, columns, paged, editable, children, editForm) {
    this.pageSize = pageSize;
    this.tableName = tableName;
    this.columns = columns;
    this.paged = paged;
    this.editable = editable;
    this.children = children;
    this.editForm = editForm;

    if (fetchUrl) {
      var parts = fetchUrl.split("?");
      this.restRoot     = parts[0];
      this.searchParams = parts[1]; 
      this.current      = fetchUrl + (paged ? "?pageNumber=0&pageSize=" + pageSize : "");
  	}
  
    this.initialized = false;
    this.rowCount = 0;

    this.columns.forEach(function(column) { column.setTableName(tableName);});
    
    var grid = this;
    if (this.children) this.children.forEach(function(child) { child.setParent(grid);});
  }

  setParent(parent) {
	  this.parent = parent;
  }
  
  init() {
    var grid = this;
    grid.loadData();
  }

  getRowId(i) {
    var grid = this;
    return grid.tableName + "_" + i;
  }
  
  getKeyId(rowId) {
    var grid = this;
    return grid.getFieldId(rowId, "key");
  }

  getKeyValue(rowId) {
    var grid = this;
    var keyField = document.getElementById(grid.getKeyId(rowId))
    return keyField.value;
  }
  
  getFieldId(rowId, binding) {
     return rowId + "_" + binding;
  }

  getCellId(rowId, column) {
    var grid = this;
    if (column.binding) {
      return grid.getFieldId(rowId, column.binding);
    } else {
      return grid.getFieldId(rowId, "buttons");
    }
  }

  addButton(cell, lnk, action) {
    removeChildren(cell);

    if (lnk) {
      cell.appendChild(getButton(lnk.href, lnk.rel, action));
    }
  }

  addHeader(tableName, table, columns) {
    var grid = this;
    var header = document.createElement("div");
    header.className = "thead";
    table.append(header);
  
    var headRow = document.createElement("div");
    headRow.className = "table-row";
    headRow.id = tableName + "Head";
    header.append(headRow);
  
    grid.columns.forEach(function(column) {
      var th = document.createElement("div");
      th.className = "table-heading";
      headRow.append(column.getHeading());
    });
  }
  
  addBody(tableName, table, columns, pageSize) {
    var grid = this;
    var body = document.createElement("div");
    body.className = "tbody";
      table.append(body);

  var p;
  for (p = 0; p < grid.pageSize; p++) {
    var tr = document.createElement("div");
    var rowId = grid.getRowId(p);
    tr.className = "table-row";
    tr.id = rowId;
    body.append(tr);
  
    var key = document.createElement("input");
    key.type = "hidden";
    key.id = grid.getKeyId(rowId);
    tr.append(key);

    columns.forEach(function(column) {
      var td = document.createElement("div");
      td.id = grid.getCellId(rowId, column);
      td.className = "table-cell";
      addText(td, "");
      tr.append(td);
      });
    }
  }
  
  addFooter(tableName, table, columns, paged) {
    var grid = this;

    if (paged) {
      var footer = document.createElement("div");
      footer.className = "tfoot";
      table.append(footer);
      
      var navRow = document.createElement("div");
      navRow.className = "table-row";
      navRow.id = tableName + "Foot";
      footer.append(navRow);
      
      var i;
      for (i = 0; i < grid.columns.length; i++) {
        var tf = document.createElement("div");
        if (i == 0) {
          tf.className = "table-prev";
          tf.id = tableName + "Prev";
      } else if (i == (columns.length-1)) {
        tf.className = "table-next";
        tf.id = tableName + "Next";
      } else {
        tf.className = "table-foot";
      }
        
        addText(tf, "");
        
        navRow.append(tf);
      }
    }
  }
  
  initGrid(pageSize) {
    var grid = this;
    var columns = grid.columns;
    var columnCount = grid.columns.length + (grid.deleteButtons ? 1 : 0);
    var deleteButtons = grid.deleteButtons;
    var paged = grid.paged;
    var pageSize = paged ? grid.pageSize : pageSize;
    var tableName = grid.tableName;

    var table = document.getElementById(grid.tableName);
    table.innerHtml = "";
    table.className = "table";
  
    grid.addHeader(tableName, table, columns);

    grid.addBody(tableName, table, columns, pageSize);

    grid.addFooter(tableName, table, columns, paged);

    grid.initialized = true;
  }

  loadData() {
    var grid = this;
    if (grid.searchParams == "?new") {
       // Add mode
       grid.initGrid(grid.pageSize);
       this.addRow();
    } else {
       grid.getData(grid.current);
    }
  }

  renderData(jsonData) {
    var grid = this;
    var columns = grid.columns;
    var columnCount = grid.columns.length + (grid.deleteButtons ? 1 : 0);
    var editable = grid.editable;
    var entities = (grid.parent ? jsonData[grid.tableName] : jsonData.entities ? jsonData.entities : [ jsonData ]);
    grid.pageSize = grid.paged ? grid.pageSize : Math.max(1, entities.length);
    var pageSize = grid.pageSize;
    var tableName = grid.tableName;
    var updateLink = grid.updateLink;

    if (!grid.initialized) {
      grid.initGrid(pageSize);
    }

    var table = document.getElementById(tableName);

    var p;
    grid.rowCount = 0;
    for (p = 0; p < pageSize; p++) {
      var rowId = grid.getRowId(p);
      var tr = document.getElementById(rowId);

      var entity;

      var key = document.getElementById(grid.getKeyId(rowId));

      if (p < entities.length) {
        entity = entities[p];
        var lnk = getLink(entity.links, "self");
        key.value = lnk.href;
        grid.rowCount++;    
      } else {
        entity = undefined;
        key.value = "";
      }

      columns.forEach(function(column) {
        var td = document.getElementById(grid.getCellId(rowId, column));

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
      var grid = this;
      var children = grid.children;
      var tableName = grid.tableName;

      grid.renderData(jsonData);

      if (children) {
    	  children.forEach(function(child) { 
    	      child.renderData(jsonData);
    	    });
      }

      var prev = document.getElementById(tableName + "Prev");

      if (prev) {
        removeChildren(prev);

        var prevLnk = getLink(jsonData.links, "previous");

        if (prevLnk) {
          grid.addButton(prev, prevLnk, tableName + ".getData(this.value)");
        } else {
          addText(prev, "");
        }
      }
    
      var next = document.getElementById(tableName + "Next");
    
      if (next) {
        removeChildren(next);

        var nextLnk = getLink(jsonData.links, "next");

        if (nextLnk) {
           grid.addButton(next, nextLnk, tableName + ".getData(this.value)");
        } else {
           addText(next, "");
        }
      }

      grid.current = restUrl;
    }
  }

  getData(restUrl) {
    var grid = this;
    $.getJSON(restUrl, function( data, textStatus, jqXHR ) { grid.renderJson(data, textStatus, jqXHR, restUrl) })
     .fail( function( jqXHR, textStatus, errorThrown ) { reportError(  jqXHR, textStatus, errorThrown  ); });
  }

  rowData() {
    var grid = this;
    var data = [];
    var tableName = grid.tableName;
    
    for (p = 0 ; p < grid.pageSize; p++) {
      var row = rowData(grid.getRowId(p));
      if (row.length) {
        data.push(row);
      }
    }

    return data;
  }

  rowData(rowId) {
    var grid = this;
    var data = {};
    var tableName = grid.tableName;

    var key = document.getElementById(grid.getKeyId(rowId));
    if (key) {
      grid.columns.forEach(function(column) {
        if (column.binding) {
          var value = column.getValue(document.getElementById(grid.getFieldId(rowId, column.binding)));
          if (value) {
            data[column.binding] = value;
          }
        }
      });
  
      if (grid.children) { 
        grid.children.forEach(function(child) {
          if (data && data.length) {
            data[child.tableName] = child.rowData();
          }
        });
      }
    }

    return data;
  }

  addRow(rowId) {
    var grid = this;
    var rowNum = grid.rowCount == grid.pageSize ? grid.pageSize - 1 : grid.rowCount; 
    var rowId = grid.getRowId(rowNum);

    var key = document.getElementById(grid.getKeyId(rowId));
    key.value = "";

    grid.columns.forEach(function(column){
    var td = document.getElementById(grid.getCellId(rowId, column));

    removeChildren(td);

    var ctl = column.getControl(td, undefined, true, grid.tableName);
    td.appendChild(ctl);
    });
    
    var td = document.getElementById(grid.getCellId(rowId, "buttons"));
    var save = getButton(rowId, "save", grid.tableName + ".saveRow(this.value)");
    td.appendChild(save);
  }

  deleteRow(rowId) {
    var grid = this;
    var deleteUrl = grid.getKeyValue(rowId);
    if (deleteUrl) {
      $.ajax( { url: deleteUrl, type: "DELETE", success: function( data, textStatus, jqXHR ) { grid.loadData(); } } )
       .fail(function( jqXHR, textStatus, errorThrown ) { reportError(jqXHR, textStatus, errorThrown); });
    }
  }

  editRow(rowId) {
    var grid = this;
    var selfUrl = grid.getKeyValue(rowId);
    if (selfUrl) {
      window.location.href = grid.editForm + "?self=" + selfUrl;
    }
  }

  newRow(rowId) {
    var grid = this;
    window.location.href = grid.editForm + "?new";
  }

  saveRow(rowId) {
    var grid = this;
    var saveUrl = grid.restRoot;
    var data = grid.rowData(rowId);
    var jsonData = JSON.stringify(data);
    if (data) {
      $.ajax( { url: saveUrl, type: "POST", data: jsonData,  contentType: "application/json; charset=utf-8", dataType: "json",
        success: function( data, textStatus, jqXHR ) { grid.loadData(); } } )
       .fail(function( jqXHR, textStatus, errorThrown ) { reportError(jqXHR, textStatus, errorThrown); });
    }
  }

  updateRow(rowId) {
    var grid = this;
    var updateUrl = grid.getKeyValue(rowId);
    var data = grid.rowData(rowId);
    var jsonData = JSON.stringify(data);
    if (data) {
      $.ajax( { url: updateUrl, type: "PUT", data: jsonData,  contentType: "application/json; charset=utf-8", dataType: "json",
      success: function( data, textStatus, jqXHR ) { grid.loadData(); } } )
       .fail(function( jqXHR, textStatus, errorThrown ) { reportError(jqXHR, textStatus, errorThrown); });
    }
  }
}

class EditableGrid extends ItemGrid {
  constructor(dataType, elementName) {
  super(10, apiRoot() + dataType, elementName, [
        new TextColumn("Name", "name"),
        new TextColumn("Description", "description", true),
        new ButtonColumn(
          [new HeaderLinkage("add", elementName + ".addRow()")],
          [new FunctionLinkage("update", elementName + ".updateRow(this.value)"), 
           new FunctionLinkage("delete", elementName + ".deleteRow(this.value)")]
          )], true, true, undefined);
    this.dataType = dataType;
  }
  
  init() {
    super.init();
    
    var h1 = document.getElementById("heading");

    if (h1) {
      h1.text = this.dataType;
    }
  }
}
