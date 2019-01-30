// module 'maintenance.js'
'use strict';

const Paged = {
  FORM: 0, 
  PAGED: 1, 
  EXPAND: 2
};

const defaultRowSetter = (rowId, columns) => {
  let data = {};

  if (document.getElementById(getKeyId(rowId))) {
    columns.forEach(column => {
      if (column.setter) {
        let value = column.getValue(document.getElementById(getFieldId(rowId, column.fieldName)));

        if (value) {
          column.setter(data, value, column.fieldName);
        }
      }
    });
  }

  return data;
};


class ItemGrid {
  constructor(pageSize = 10, apiUrl, tableName, columns, paged, editMode = EditMode.VIEW, children = undefined, editForm = undefined, rowSetter = defaultRowSetter) {
    this.pageSize = pageSize;
    this.rowCount = pageSize;
    this.apiUrl = apiUrl;
    this.tableName = tableName;
    this.columns = columns;
    this.paged = paged;
    this.editMode = editMode;
    this.children = children;
    this.editForm = editForm;
    this.rowSetter = rowSetter;

    this.current = this.apiUrl;

    if (this.apiUrl && !this.parent) {
      let search = new URLSearchParams(location.search);

      if (search.has('new')) {
        this.editMode = EditMode.ADD;
        search.delete('new');
      }

      if (search.has('self')) {
        this.current = search.get('self');
        search.delete('self');
      }

      if (paged === Paged.PAGED) {
        search.set('pageNumber', '0');
        search.set('pageSize', pageSize.toString());
      }

      let searchString = search.toString();

      this.current = this.current + (searchString.length ? '?' + searchString : '');
    }

    this.initialized = false;

    let grid = this;
    if (this.children) {
      this.children.forEach(child => {
        child.setParent(grid);
      });
    }
  }

  setParent(parent) {
    this.parent = parent;
    this.apiUrl = ((parent && parent.apiUrl) ? parent.apiUrl + '/' : '') + this.apiUrl;
    this.editMode = parent.editMode;
  }

  async init() {
    let grid = this;
    grid.loadData();
  }

  initGrid(rowCount) {
    let grid = this;

    if (!grid.initialized || (rowCount > grid.pageSize)) {
      grid.rowCount = rowCount;

      let columns = grid.columns;
      let paged = grid.paged;
      let pageSize = grid.pageSize;
      let tableName = grid.tableName;

      let totalWidth = 0;
      let maxLabel = 0;

      columns.forEach(column => {
        column.setTableName(tableName);
        totalWidth += column.getLength();
      });

      columns.forEach(column => {
        column.setWidth(Math.floor((column.getLength() * 100) / totalWidth) + '%');
        maxLabel = Math.max(column.getHeaderLength(), maxLabel);
      });

      let place = document.getElementById(grid.tableName+'Site');
      removeChildren(place);

      let table = document.createElement(paged === Paged.FORM ? 'div' : 'table'); 
      table.id = grid.tableName;
      table.className = 'table';
      place.appendChild(table);

      initHeader(tableName, table, columns, paged);

      initBody(tableName, table, pageSize, columns, paged, rowCount, maxLabel);

      initFooter(tableName, table, columns, paged);

      grid.initialized = true;
    }
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
          child.editMode = editMode;
          child.initGrid(child.rowCount);
          child.addRow();
        });
      }
    } else {
      grid.getData(grid.current);
    }
  }

  renderRow(rowId, entity, columns, editMode) {
    columns.forEach(column => {
      let cell = document.getElementById(getCellId(rowId, column));

      removeChildren(cell);

      if (this.parent && editMode === EditMode.ADD) {
        blankControl(cell);
      } else if (entity || column.isButtons()) {
        column.getControl(cell, entity, editMode);
      } else {
        blankControl(cell);
      }
    });
  }

  renderUpdate(jsonData, rowId) {
    let grid = this;

    renderRow(rowId, jsonData, grid.columns, grid.editMode);

    if (grid.editMode === EditMode.ADD) {
      let self = getLink(entity.links, 'self').href;
      grid.current = self;
      grid.editMode = EditMode.UPDATE;
    }
  }
  
  renderData(jsonData) {
    let grid = this;
    let columns = grid.columns;
    let editMode = grid.editMode;
    let entities = (grid.parent ? jsonData[grid.tableName] : jsonData.entities ? jsonData.entities : [jsonData]);
    let tableName = grid.tableName;

    if (!entities) entities = {};
    
    let rowCount = grid.paged === Paged.PAGED ? grid.pageSize : Math.max(grid.pageSize, entities.length);

    grid.initGrid(rowCount);

    for (let row = 0; row < rowCount; row++) {
      let rowId = getRowId(tableName, row);

      let entity = (entities.length > 0 && row < entities.length) ? entities[row] : undefined;

      let key = document.getElementById(getKeyId(rowId));
      key.value = entity ? getLink(entity.links, 'self').href : '';

      this.renderRow(rowId, entity, columns, editMode);
    }

    let prev = document.getElementById(tableName + 'Prev');

    if (prev) {
      removeChildren(prev);

      let prevLnk = getLink(jsonData.links, 'previous');

      if (prevLnk) {
        addButton(prev, prevLnk, Function(tableName + '.getData(this.value)'));
      } else {
        addText(prev, '');
      }
    }

    let next = document.getElementById(tableName + 'Next');

    if (next) {
      removeChildren(next);

      let nextLnk = getLink(jsonData.links, 'next');

      if (nextLnk) {
        addButton(next, nextLnk, Function(tableName + '.getData(this.value)'));
      } else {
        addText(next, '');
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

    fetch(restUrl, {
      method: 'get', 
      headers: {'Content-type': 'application/json'}
    })
    .then(response => checkResponse(response))
    .then(jsonData => grid.renderJson(jsonData, restUrl))
    .catch(error => reportError(error));
  }

  rowData(rowId) {
    let grid = this;
    let data = grid.rowSetter(rowId, grid.columns);

    if (Object.entries(data).length) {
      grid.childData(grid, data);
    }

    return data;
  }

  childData(grid, data) {
    if (grid.children && grid.editMode !== EditMode.ADD) {
      grid.children.forEach(child => {
        let childData = child.gridData();

        if (Object.entries(childData).length) {
          data[child.tableName] = child.gridData();
        }
      });
    }
  }

  gridData() {
    let grid = this;
    let data = [];

    for (let row = 0; row < grid.rowCount; row++) {
      let rowData = grid.rowData(getRowId(grid.tableName, row));

      if (Object.entries(rowData).length) {
        data.push(rowData);
      }
    }

    return data;
  }

  getFreeRow(tableName, rowCount) {
    for (let row = 0; row < rowCount; row++) {
      let rowId = getRowId(tableName, row);

      if (!getKeyValue(rowId)) {
        return row;
      }
    }
    
    return rowCount;
  }

  addRow() {
    let grid      = this;
    let columns   = grid.columns;
    let rowCount  = grid.rowCount;
    let paged     = grid.paged;
    let tableName = grid.tableName;
    let rowNum    = this.getFreeRow(tableName, rowCount);

    if (paged === Paged.EXPAND) {
      if (rowNum === rowCount) {
        // Add a row at end
        grid.rowCount = rowCount + 1;
        rowNum = grid.rowCount;

        let body = document.getElementById(tableName + '_tbody');

        initRow(tableName, rowNum, body, paged, columns);
      }
    } else if (paged === Paged.PAGED) {
      if (rowNum === rowCount) {
        // the page is full, new page and add at start
        rowNum = 0;
        for (let row = 1; row < rowCount; row++) {
          this.renderRow(getRowId(tableName, row), undefined, columns, EditMode.NEVER);
        }
          
        let prev = document.getElementById(tableName + 'Prev');

        if (prev) {
          removeChildren(prev);

          addButton(prev, grid.current, Function(tableName + '.getData(this.value)'));
        }

        let next = document.getElementById(tableName + 'Next');

        if (next) {
          removeChildren(next);
          
          addText(next, '');
        }
      }
    } else {
      // Don't care just use first row.
      rowNum = 0;
    }

    let rowId = getRowId(tableName, rowNum);

    let key = document.getElementById(getKeyId(rowId));
    key.value = '';

    let focusCtl = undefined;
    columns.forEach(column => {
      let td = document.getElementById(getCellId(rowId, column));

      removeChildren(td);

      let ctl = column.getControl(td, undefined, EditMode.ADD);
      if (!focusCtl) {
        focusCtl = ctl;
        ctl.focus();
      }
    });

    let td = document.getElementById(getCellId(rowId, 'buttons'));
    let save = getButton(rowId, 'save', Function(tableName + '.saveRow(' + rowId + '.id)'));
    td.appendChild(save);
    let del = getButton(rowId, 'delete', Function(tableName + '.removeRow(' + rowId + '.id)'));
    td.appendChild(del);
    
  }

  async deleteRow(rowId) {
    let grid = this;
    let deleteUrl = getKeyValue(rowId);
    if (deleteUrl) {
      await fetch(deleteUrl, {method: 'DELETE', headers: {'Content-type': 'application/json'}})
      .then(response => checkResponse(response))
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
      window.location.href = grid.editForm + '?self=' + selfUrl;
    }
  }

  newRow() {
    let grid = this;
    window.location.href = grid.editForm + '?new=true';
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
      await fetch(saveUrl, {
        method: 'POST', 
        headers: {'Content-type': 'application/json'}, 
        body: jsonData
      })
      .then(response => checkResponse(response))
      .then(jsonData => grid.renderUpdate(jsonData, rowId))
      .catch(error => reportError(error));
    }
  }

  async updateRow(rowId) {
    let grid = this;
    let updateUrl = getKeyValue(rowId);
    let data = grid.rowData(rowId);
    let jsonData = JSON.stringify(data);

    if (data) {
      await fetch(updateUrl, {
        method: 'PUT', 
        headers: {'Content-type': 'application/json'}, 
        body: jsonData
      })
      .then(response => checkResponse(response))
      .then(jsonData => grid.renderUpdate(jsonData, rowId))
      .catch(error => reportError(error));
    }
  }
}

class ListEditGrid extends ItemGrid {
  constructor(pageSize, dataType, elementName, columns) {
    super(pageSize, fetchUrl(dataType), elementName, columns.concat([gridButtonColumn()]), Paged.PAGED, EditMode.UPDATE, undefined);
    this.dataType = dataType;
  }

  async init() {
    super.init();

    let h1 = document.getElementById('heading');

    if (h1) {
      h1.text = this.dataType;
    }
  }
}

class NamedItemGrid extends ListEditGrid {
  constructor(dataType, elementName) {
    super(10, dataType, elementName, [NAMEN(), BEZEICHNUNG()]);
  }
}

