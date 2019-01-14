// module "maintenance.js"
"use strict";

const Paged = {
  FORM: 0,
  PAGED: 1,
  EXPAND: 2
};

class ItemGrid {
  constructor(pageSize, apiUrl, tableName, columns, paged, editMode, children, editForm) {
    this.pageSize = pageSize ? pageSize : 10;
    this.rowCount = pageSize;
    this.rowsFilled = 0;
    this.apiUrl = apiUrl;
    this.tableName = tableName;
    this.columns = columns;
    this.paged = paged;
    this.editMode = editMode ? editMode : EditMode.VIEW;
    this.children = children;
    this.editForm = editForm;
    this.current = this.apiUrl;

    if (this.apiUrl) {
      let search = new URLSearchParams(location.search);

      if (search.has("new")) {
        this.editMode = EditMode.ADD;
        search.delete("new");
      }

      if (search.has("self")) {
        this.current = search.get("self");
        search.delete("self");
      }

      if (paged === Paged.PAGED) {
        search.set("pageNumber", "0");
        search.set("pageSize", pageSize);
      }

      let searchString = search.toString();

      this.current = this.current + (searchString.length ? "?" + searchString : "");
    }

    this.initialized = false;

    let grid = this;
    if (this.children) this.children.forEach(child => {
      child.setParent(grid);
    });
  }

  setParent(parent) {
    this.parent = parent;
    this.apiUrl = parent.apiUrl;
  }

  async init() {
    let grid = this;
    grid.loadData();
  }

  initGrid(rowCount) {
    let grid = this;
    grid.rowCount = rowCount;

    let columns = grid.columns;
    let paged = grid.paged;
    let pageSize = grid.pageSize;
    let tableName = grid.tableName;

    let totalWidth = 0;
    let maxLabel   = 0;

    columns.forEach(column => {
      column.setTableName(tableName);
      totalWidth += column.getLength();
    });

    columns.forEach(column => {
      column.setWidth(Math.floor((column.getLength() * 100) / totalWidth) + "%");
      maxLabel = Math.max(column.heading ? column.heading.length : maxLabel,  maxLabel);
    });

    let table = document.getElementById(grid.tableName);
    removeChildren(table);
    table.className = "table";
    grid.maxLabel = maxLabel;

    addHeader(tableName, table, columns, paged, rowCount);

    addBody(tableName, table, pageSize, columns, paged, rowCount, maxLabel);

    addFooter(tableName, table, columns, paged, rowCount);

    grid.initialized = true;
  }

  async loadData() {
    let grid = this;
    if (grid.parent) {
      grid.parent.loadData();
    } else if (grid.editMode === EditMode.ADD) {
      grid.initGrid(grid.rowCount);
      grid.addRow();

      if (grid.children) {
        grid.children.forEach(child => {
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
    let grid = this;
    let columns = grid.columns;
    let editMode = grid.editMode;
    let entities = (grid.parent ? jsonData[grid.tableName] : jsonData.entities ? jsonData.entities : [jsonData]);
    let tableName = grid.tableName;

    let rowCount = grid.paged === Paged.PAGED ? grid.pageSize : Math.max(grid.pageSize, entities.length);

    if (!grid.initialized || (rowCount > grid.pageSize)) {
      grid.initGrid(rowCount);
    }

    let row;
    grid.rowsFilled = 0;
    for (row = 0; row < rowCount; row++) {
      let rowId = getRowId(tableName, row);
      let entity;

      let key = document.getElementById(getKeyId(rowId));

      if (entities.length > 0 && row < entities.length) {
        entity = entities[row];
        let lnk = getLink(entity.links, "self");
        key.value = lnk.href;
        grid.rowsFilled++;
      } else {
        entity = undefined;
        key.value = "";
      }

      columns.forEach(column => {
        let cell = document.getElementById(getCellId(rowId, column));

        removeChildren(cell);

        let ctl;
        if (entity || cell.id.endsWith("_buttons")) {
          ctl = column.getControl(cell, entity, editMode);
        } else {
          cell.style.width = column.getLength() + "em";
          cell.style.maxWidth = this.getLength() + "em";
          ctl = document.createElement("input");
          ctl.type = "text";
          ctl.disabled = "true";
          ctl.readOnly = "true";
          ctl.required = false;
        }

        cell.appendChild(ctl);
      });
    }

    let prev = document.getElementById(tableName + "Prev");

    if (prev) {
      removeChildren(prev);

      let prevLnk = getLink(jsonData.links, "previous");

      if (prevLnk) {
        addButton(prev, prevLnk, tableName + ".getData(this.value)");
      } else {
        addText(prev, "");
      }
    }

    let next = document.getElementById(tableName + "Next");

    if (next) {
      removeChildren(next);

      let nextLnk = getLink(jsonData.links, "next");

      if (nextLnk) {
        addButton(next, nextLnk, tableName + ".getData(this.value)");
      } else {
        addText(next, "");
      }
    }
  }

  renderJson(jsonData, restUrl) {
    let grid = this;
    let children = grid.children;

    grid.renderData(jsonData);

    if (children) {
      children.forEach(child => {
        child.editMode = grid.editMode;
        child.renderData(jsonData);
      });
    }

    grid.current = restUrl;
  }

  async getData(restUrl) {
    let grid = this;

    fetch(restUrl, {method: "get", headers: {"Content-type": "application/json"}})
      .then(response => checkResponse(response))
      .then(jsonData => grid.renderJson(jsonData, restUrl))
      .catch(error => reportError(error));
  }

  gridData() {
    let grid = this;
    let data = [];

    for (let row = 0; row < grid.rowCount; row++) {
      let row = grid.rowData(getRowId(grid.tableName, row));
      if (row.length) {
        data.push(row);
      }
    }

    return data;
  }

  rowData(rowId) {
    let grid = this;
    let data = {};

    let key = document.getElementById(getKeyId(rowId));
    if (key) {
      grid.columns.forEach(column => {
        if (column.binding) {
          let value = column.getValue(document.getElementById(getFieldId(rowId, column.binding)));
          if (value) {
            data[column.binding] = value;
          }
        }
      });

      if (grid.children) {
        grid.children.forEach(child => {
          if (data && data.length) {
            data[child.tableName] = child.gridData();
          }
        });
      }
    }

    return data;
  }

  addRow() {
    let grid = this;
    let rowNum = grid.rowsFilled === grid.rowCount ? grid.rowCount - 1 : grid.rowsFilled; //TODO: add row for non paged if not exists
    let rowId = getRowId(grid.tableName, rowNum);

    let key = document.getElementById(getKeyId(rowId));
    key.value = "";

    grid.columns.forEach(column => {
      let td = document.getElementById(getCellId(rowId, column));

      removeChildren(td);

      let ctl = column.getControl(td, undefined, EditMode.ADD);
      td.appendChild(ctl);
    });

    let td = document.getElementById(getCellId(rowId, "buttons"));
    let save = getButton(rowId, "save", grid.tableName + ".saveRow(" + rowId + ".id)");
    td.appendChild(save);
    let del = getButton(rowId, "delete", grid.tableName + ".removeRow(" + rowId + ".id)");
    td.appendChild(del);
  }

  async deleteRow(rowId) {
    let grid = this;
    let deleteUrl = getKeyValue(rowId);
    if (deleteUrl) {
      await fetch(deleteUrl, {method: "DELETE", headers: {"Content-type": "application/json"}})
        .then(response => {
          if (!response.ok) {
            throw new Error(response.statusText);
          }
        })
        .catch(error => reportError(error));

      grid.loadData();
    } else {
      grid.removeRow(rowId);
    }
  }

  editRow(rowId) {
    let grid = this;
    let selfUrl = getKeyValue(rowId);
    if (selfUrl) {
      window.location.href = grid.editForm + "?self=" + selfUrl;
    }
  }

  newRow() {
    let grid = this;
    window.location.href = grid.editForm + "?new=true";
  }

  removeRow(rowId) {
    let grid = this;
    grid.loadData();
  }

  async saveRow(rowId) {
    let grid = this;
    let saveUrl = grid.apiUrl;
    let data = grid.rowData(rowId);
    let jsonData = JSON.stringify(data);
    if (data) {
      await fetch(saveUrl, {method: "POST", headers: {"Content-type": "application/json"}, body: jsonData})
        .then(response => checkResponse(response))
        .then(jsonData => grid.loadData())
        .catch(error => reportError(error));
    }
  }

  async updateRow(rowId) {
    let grid = this;
    let updateUrl = getKeyValue(rowId);
    let data = grid.rowData(rowId);
    let jsonData = JSON.stringify(data);

    if (data) {
      await fetch(updateUrl, {method: "PUT", headers: {"Content-type": "application/json"}, body: jsonData})
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
      Paged.PAGED, EditMode.UPDATE, undefined);
    this.dataType = dataType;
  }

  async init() {
    super.init();

    let h1 = document.getElementById("heading");

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

