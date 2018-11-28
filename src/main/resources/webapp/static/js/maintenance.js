// module "maintenance.js"
"use strict"

class ItemGrid {
  constructor(pageSize, apiUrl, tableName, columns, paged, editMode, children, editForm) {
    this.pageSize = pageSize ? pageSize : 10;
    this.rowCount = pageSize;
    this.apiUrl = apiUrl;
    this.tableName = tableName;
    this.columns = columns;
    this.paged = paged;
    this.editMode = editMode ? editMode : EditMode.VIEW;
    this.children = children;
    this.editForm = editForm;

    this.current = this.apiUrl;

    if (this.apiUrl) {
      var search = new URLSearchParams(location.search);

      if (search.has("new")) {
        this.editMode = EditMode.ADD;
        search.delete("new");
      }

      if (search.has("self")) {
        this.current = search.get("self");
        search.delete("self");
      }
      
      if (paged) {
        search.set("pageNumber", 0);
        search.set("pageSize", pageSize);
      }

      var searchString = search.toString();
      
      this.current = this.current + ( searchString.length ? "?" + searchString : "" );
    }
  
    this.initialized = false;

    var grid = this;
    if (this.children) this.children.forEach(function(child) { child.setParent(grid);});
  }

  setParent(parent) {
    this.parent = parent;
    this.apiUrl = parent.apiUrl;
  }
  
  async init() {
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
    header.id = tableName + "_thead"
    header.className = "thead";
    table.append(header);
  
    var headRow = document.createElement("div");
    headRow.className = "table-head";
    headRow.id = tableName + "Head";
    header.append(headRow);
  
    grid.columns.forEach(function(column) {
      var th = column.getHeading();
      th.style.width = column.width;
      th.style.maxWidth = column.width;
      
      headRow.append(th);
    });
  }
  
  addBody(tableName, table, columns, rowCount) {
    var grid = this;
    var body = document.createElement("div");
    body.id = tableName + "_tbody"
    body.className = "tbody";
    table.append(body);

    var row;
    var maxRow = Math.max(rowCount, grid.pageSize);
    for (row = 0; row < maxRow; row++) {
      var tr = document.createElement("div");
      var rowId = grid.getRowId(row);
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
        td.style.width = column.width;
        td.style.maxWidth = column.width;
        
        addText(td, "");
        
        tr.append(td);
      });
    }
  }
  
  addFooter(tableName, table, columns, paged) {
    var grid = this;

    if (paged) {
      var footer = document.createElement("div");
      footer.id = tableName + "_tfoot"
      footer.className = "tfoot";
      table.append(footer);
      
      var navRow = document.createElement("div");
      navRow.className = "table-foot";
      navRow.id = tableName + "Foot";
      footer.append(navRow);
      
      for (var i = 0; i < columns.length; i++) {
        var tf = document.createElement("div");
        if (i == 0) {
          tf.className = "table-prev";
          tf.id = tableName + "Prev";
        } else if (i == (columns.length-1)) {
          tf.className = "table-next";
          tf.id = tableName + "Next";
        } else {
          tf.className = "table-footer";
        }

        tf.style.width = columns[i].width;
        tf.style.maxWidth = columns[i].width;

        addText(tf, "");
        
        navRow.append(tf);
      }
    }
  }
  
  initGrid(rowCount) {
    var grid = this;
    grid.rowCount = rowCount;

    var columns = grid.columns;
    var columnCount = grid.columns.length;
    var paged = grid.paged;
    var tableName = grid.tableName;

    var totalWidth = 0;

    columns.forEach(function(column) { 
        column.setTableName(tableName);
        totalWidth += column.getLength();
        });

    columns.forEach(function(column) {
        column.setWidth(Math.round((column.getLength()*100)/totalWidth)+"%");
        });
    

    var table = document.getElementById(grid.tableName);
    removeChildren(table);
    table.className = "table";
  
    grid.addHeader(tableName, table, columns);

    grid.addBody(tableName, table, columns, rowCount);

    grid.addFooter(tableName, table, columns, paged);

    grid.initialized = true;
  }

  async loadData() {
    var grid = this;
    if (grid.parent) {
        grid.parent.loadData();
    } else if (grid.editMode === EditMode.ADD) {
       grid.initGrid(grid.rowCount);
       grid.addRow();

       if (grid.children) {
         grid.children.forEach(function(child) {
           child.editMode = grid.editMode;
             child.initGrid(child.rowCount);
             child.addRow();
           });
       }
    } else {
       grid.getData(grid.current);
    }
  }

  renderData(jsonData) {
    var grid = this;
    var columns = grid.columns;
    var columnCount = grid.columns.length;
    var editMode = grid.editMode;
    var entities = (grid.parent ? jsonData[grid.tableName] : jsonData.entities ? jsonData.entities : [ jsonData ]);
    var tableName = grid.tableName;
    var updateLink = grid.updateLink;

    var rowCount = grid.paged ? grid.pageSize : Math.max(grid.pageSize, entities.length);

    if (!grid.initialized || (rowCount > grid.pageSize)) {
      grid.initGrid(rowCount);
    }

    var table = document.getElementById(tableName);

    var row;
    var rowsFilled = 0;
    for (row = 0; row < rowCount; row++) {
      var rowId = grid.getRowId(row);
      var tr = document.getElementById(rowId);

      var entity;

      var key = document.getElementById(grid.getKeyId(rowId));

      if (entities.length > 0 && row < entities.length) {
        entity = entities[row];
        var lnk = getLink(entity.links, "self");
        key.value = lnk.href;
        rowsFilled++;    
      } else {
        entity = undefined;
        key.value = "";
      }

      columns.forEach(function(column) {
        var cell = document.getElementById(grid.getCellId(rowId, column));

        removeChildren(cell);

    var ctl;
    if (entity || cell.id.endsWith("_buttons")) {
          ctl = column.getControl(cell, entity, editMode);
    } else {
      cell.style.width = column.width;
          cell.style.maxWidth = column.width;

      ctl = document.createElement("input");
          ctl.type = "text";
          ctl.disabled = true;
          ctl.required = false;
    }

        cell.appendChild(ctl);
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
  }

  renderJson(jsonData, restUrl) {
    var grid = this;
    var children = grid.children;
    var tableName = grid.tableName;

    grid.renderData(jsonData);

    if (children) {
      children.forEach(function(child) { 
        child.editMode = grid.editMode;
        child.renderData(jsonData);
      });
    }

    grid.current = restUrl;
  }

  async getData(restUrl) {
    var grid = this;

    fetch(restUrl, { method: "get", headers: { "Content-type": "application/json" }})
      .then(response => checkResponse(response))
      .then(jsonData => grid.renderJson(jsonData, restUrl))
      .catch(error => reportError(error));
  }

  rowData() {
    var grid = this;
    var data = [];
    var tableName = grid.tableName;
    
    for (row = 0 ; row < grid.rowCount; row++) {
      var row = rowData(grid.getRowId(row));
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
    var rowNum = grid.rowCount == grid.rowCount ? grid.rowCount - 1 : grid.rowCount; //TODO: add row for non paged if not exists
    var rowId = grid.getRowId(rowNum);

    var key = document.getElementById(grid.getKeyId(rowId));
    key.value = "";

    grid.columns.forEach(function(column){
      var td = document.getElementById(grid.getCellId(rowId, column));

      removeChildren(td);

      var ctl = column.getControl(td, undefined, EditMode.ADD);
      td.appendChild(ctl);
    });
    
    var td = document.getElementById(grid.getCellId(rowId, "buttons"));
    var save = getButton(rowId, "save", grid.tableName + ".saveRow(this.value)");
    td.appendChild(save);
    var del = getButton(rowId, "delete", grid.tableName + ".removeRow(this.value)");
    td.appendChild(del);
  }

  async deleteRow(rowId) {
    var grid = this;
    var deleteUrl = grid.getKeyValue(rowId);
    if (deleteUrl) {
        var response = await fetch(deleteUrl, { method: "DELETE", headers: { "Content-type": "application/json" } } )
                                  .then(response => { if (!response.ok) { throw new Error(response.statusText); } } )
                                .catch(error => reportError(error));
        grid.loadData();
    } else {
      grid.removeRow(rowId);
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
    window.location.href = grid.editForm + "?new=true";
  }

  removeRow(rowId) {
    //TODO: remove grid row for non paged if exists
  var grid = this;  

    var key = document.getElementById(grid.getKeyId(rowId));
    key.value = "";

    grid.columns.forEach(function(column){
      var td = document.getElementById(grid.getCellId(rowId, column));

      removeChildren(td);

      addText(td, "");
    });
  }
  
  async saveRow(rowId) {
    var grid = this;
    var saveUrl = grid.apiUrl;
    var data = grid.rowData(rowId);
    var jsonData = JSON.stringify(data);
    if (data) {
      await fetch(saveUrl, { method: "POST", headers: { "Content-type": "application/json" }, body: jsonData } )
        .then(response => checkResponse(response))
        .then(jsonData => grid.loadData())
        .catch(error => reportError(error));
    }
  }

  async updateRow(rowId) {
    var grid = this;
    var updateUrl = grid.getKeyValue(rowId);
    var data = grid.rowData(rowId);
    var jsonData = JSON.stringify(data);
    
    if (data) {
      await fetch(updateUrl, { method: "PUT", headers: { "Content-type": "application/json" }, body: jsonData } )
        .then(response => checkResponse(response))
        .then(jsonData => grid.loadData())
        .catch(error => reportError(error));
    }
  }
}

class ListEditGrid extends ItemGrid {
  constructor(pageSize, dataType, elementName, columns) {
    super(pageSize, fetchUrl(dataType), elementName, 
      columns.concat([gridButtonColumn(elementName)]),
      true, EditMode.UPDATE, undefined);
    this.dataType = dataType;
  }
    
  async init() {
    super.init();
      
    var h1 = document.getElementById("heading");

    if (h1) {
      h1.text = this.dataType;
    }
  }
}

class NamedItemGrid extends ListEditGrid {
  constructor(dataType, elementName) {
    super(10, dataType, elementName, [NAMEN, BEZEICHNUNG]);
  }
}

