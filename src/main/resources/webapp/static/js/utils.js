// module "utils.js"
"use strict";

const Editable = {
  NEVER: 0,
  UPDATE: 1,
  ADD: 2
};

const EditMode = {
  VIEW: 0,
  UPDATE: 1,
  ADD: 2
};

const shouldDisable = (editable, editMode) => {
  if (editable === Editable.NEVER || editMode === EditMode.VIEW) {
    return true;
  }

  return (editable > editMode);
};

const apiRoot = () => {
  return location.protocol + "//" + location.host + "/ModellBahn/api/";
};

const siteRoot = () => {
  return location.protocol + "//" + location.host + "/ModellBahn/static/";
};

const fetchUrl = (dataType) => {
  let fetchUrl = apiRoot() + dataType;
  let searchParams = new URLSearchParams(location.search);

  if (searchParams.has("self")) {
    fetchUrl = searchParams.get("self");
  }

  return fetchUrl;
};

const removeChildren = (node) => {
  while (node.firstChild) {
    node.removeChild(node.firstChild);
  }
};

const reportError = (error) => {
  console.log(error);
  //alert("error: " + error.toString());
};

const addButton = (cell, lnk, action) => {
  removeChildren(cell);

  if (lnk) {
    cell.appendChild(getButton(lnk.href, lnk.rel, action));
  }
};

const getLink = (links, rel) => {
  return links.find((lnk) => {
    return lnk.rel === rel;
  });
};

const getFieldId = (rowId, binding) => {
  return rowId + "_" + binding;
};

const getKeyId = (rowId) => {
  return getFieldId(rowId, "key");
};

const getKeyValue = (rowId) => {
  let keyField = document.getElementById(getKeyId(rowId));
  return keyField.value;
};

const getRowId = (tableName, i) => {
  return tableName + "_" + i;
};

const getCellId = (rowId, column) => {
  if (column.binding) {
    return getFieldId(rowId, column.binding);
  } else {
    return getFieldId(rowId, "buttons");
  }
};

const getCellRowId = (cell) => {
 return cell.id.substring(0, cell.id.lastIndexOf("_"));
};

const getImg = (action) => {
  let img = document.createElement("img");

  img.alt = action;
  img.src = "img/" + action + ".png";

  return img;
};

const getButton = (value, alt, action) => {
  let btn = document.createElement("button");

  btn.setAttribute("value", value);
  btn.setAttribute("onclick", action);
  btn.className = "nav-button";

  let img = getImg(alt);
  img.className = "nav-button";

  btn.appendChild(img);

  return btn;
};

const addText = (cell, text) => {
  cell.appendChild(document.createTextNode(text));
};

const getButtonLink = (href, alt, action) => {
  let a = document.createElement("a");

  a.setAttribute("href", href);
  a.className = "nav-button";
  a.appendChild(getImg(action));

  return a;
};

const addOption = (select, value, text) => {
    let opt = document.createElement("option");
    opt.value = value;
    opt.text = text;
    select.add(opt);
};

class Column {
  constructor(heading, binding, getter, setter, editable, required, length) {
    this.heading = heading;
    this.binding = binding;
    this.getter = getter;
    this.setter = setter;
    this.editable = editable ? editable : Editable.NEVER;
    this.required = required ? required : false;
    this.length = Math.max(length ? length : heading.length, heading.length+1);
  }

  setTableName(tableName) {
    this.tableName = tableName;
  }

  getHeading() {
    let td = document.createElement("div");
    td.className = "table-heading";
    addText(td, this.heading);
    return td;
  }

  entityValue(entity) {
    return this.getter(entity);
  }

  getLength() {
    return this.length;
  }

  setWidth(width) {
    this.width = width;
  }

  createControl() {
    return document.createElement("input");
  }

  getControl(cell, entity, editMode) {
    let ctl = this.createControl();
    cell.style.width = this.width;
    cell.style.maxWidth = this.width;

    let value;

    if (entity) value = this.entityValue(entity);

    if (value) {
      this.setValue(ctl, value);
    }

    if (value || entity) {
      ctl.disabled = shouldDisable(this.editable, editMode);
    } else {
      ctl.disabled = !(editMode === EditMode.ADD && this.editable !== Editable.NEVER);
    }

    ctl.readOnly = ctl.disabled;
    ctl.required = this.required;

    return ctl;
  }

  getValue(cell) {
    let ctl = cell.firstChild;
    if (ctl) {
      return this.getControlValue(ctl);
    }
  }

  getControlValue(ctl) {
    return ctl.value;
  }

  setValue(ctl, value) {
    ctl.value = value;
  }
}

class BoolColumn extends Column {
  constructor(heading, binding, getter, setter, editable, required) {
    super(heading, binding, getter, setter, editable, required, heading.length);
  }

  createControl() {
    let ctl = super.createControl();
    ctl.type = "checkbox";
    return ctl;
  }

  getControlValue(ctl) {
    return ctl.checked;
  }

  setValue(ctl, value) {
    ctl.checked = value;
  }
}

class DateColumn extends Column {
  constructor(heading, binding, getter, setter, editable, required) {
    super(heading, binding, getter, setter, editable, required, 12);
  }

  createControl() {
    let ctl = super.createControl();
    ctl.type = "date";
    return ctl;
  }
}

class NumberColumn extends Column {
  constructor(heading, binding, getter, setter, editable, required, max, min, step) {
    max = max ? max : 255;
    super(heading, binding, getter, setter, editable, required, Math.max(max.toString().length, heading.length));
    this.max = max;
    this.min = min ? min : 0;
    this.step = step ? step : 1;
  }

  createControl() {
    let ctl = super.createControl();
    ctl.type = "number";
    ctl.min = this.min;
    ctl.max = this.max;
    ctl.step = this.step;
    return ctl;
  }
}

class PhoneColumn extends Column {
  constructor(heading, binding, getter, setter, editable, required) {
    super(heading, binding, getter, setter, editable, required);
  }

  createControl() {
    let ctl = super.createControl();
    ctl.type = "tel";
    return ctl;
  }
}

class TextColumn extends Column {
  constructor(heading, binding, getter, setter, editable, required, length) {
    super(heading, binding, getter, setter, editable, required, length);
  }

  createControl() {
    let ctl = super.createControl();
    ctl.type = "text";
    ctl.maxLength = this.length;
    return ctl;
  }
}

class URLColumn extends Column {
  constructor(heading, binding, getter, setter, editable, required) {
    super(heading, binding, getter, setter, editable, required);
  }

  createControl() {
    let ctl = super.createControl();
    ctl.type = "url";
    return ctl;
  }
}

class IMGColumn extends Column {
  constructor(heading, binding, getter, setter, onChange, editable, required) {
    super(heading, binding, getter, setter, editable, required);
    this.onChange = onChange;
  }

  createControl() {
    let ctl = super.createControl();
    ctl.type = "file";
    ctl.accept = "image/*";
    ctl.multiple = false;
    if (this.onChange) ctl.change = this.onChange;
    return ctl;
  }

  getControlValue(ctl) {
    return ctl.checked;
  }
}

class PDFColumn extends Column {
  constructor(heading, binding, getter, setter, onChange, editable, required) {
    super(heading, binding, getter, setter, editable, required);
    this.onChange = onChange;
  }

  getControl(cell, entity, editMode) {
    let ctl = this.createControl();
    cell.style.width = this.width;
    cell.style.maxWidth = this.width;

    let value;

    if (entity) value = this.entityValue(entity);

    if (value) {
      this.setValue(ctl, value);
    }

    if (value || entity) {
      ctl.disabled = shouldDisable(this.editable, editMode);
    } else {
      ctl.disabled = !(editMode === EditMode.ADD && this.editable !== Editable.NEVER);
    }

    ctl.required = this.required;

    return ctl;
  }

  createControl() {
    let ctl = super.createControl();
    ctl.type = "file";
    ctl.accept = "application/pdf";
    ctl.multiple = false;
    if (this.onChange) ctl.change = this.onChange;
    return ctl;
  }

  getControlValue(ctl) {
    return ctl.checked;
  }
}

class SelectColumn extends Column {
  constructor(heading, binding, getter, setter, dropDown, editable, required) {
    super(heading, binding, getter, setter, editable, required, dropDown.length);
    this.dropDown = dropDown;
    this.dropSize = 1;
  }

  getControl(cell, entity, editMode) {
    let ctl = this.createControl();
    cell.style.width = this.width;
    cell.style.maxWidth = this.width;

    let value;

    if (entity) value = this.entityValue(entity);

    if (value) {
      this.setValue(ctl, value);
    }

    if (value || entity) {
      ctl.disabled = shouldDisable(this.editable, editMode);
    } else {
      ctl.disabled = !(editMode === EditMode.ADD && this.editable !== Editable.NEVER);
    }

    ctl.required = this.required;

    return ctl;
  }

  addOptions(select, dropDown) {
  	if (!this.required) addOption(select, undefined, "(n/a)");

    dropDown.options.forEach(option => {
    	addOption(select, option.value, option.display);
    });
  }

  createControl() {
    let ctl = document.createElement("select");
    ctl.size = this.dropSize;
    this.addOptions(ctl, this.dropDown);
    return ctl;
  }

  getControlValue(select) {
    return select.options[select.selectedIndex].value;
  }

  getLength() {
    return Math.max(this.dropDown.length, this.heading.length);
  }

  setValue(ctl, value) {
    ctl.value = value;	  
    for (let i = 0; i < ctl.options.length; i++) {
      if (ctl.options[i].value === value) {
        ctl.selectedIndex = i;
        return;
      }
    }
  }
}

class ButtonColumn {
  constructor(headLinkage, btnLinkage) {
    this.headLinkage = headLinkage;
    this.btnLinkage = btnLinkage;
    this.length = Math.max(headLinkage.length, btnLinkage.length) * 8;
  }

  setTableName(tableName) {
    this.tableName = tableName;
  }

  getHeading() {
    let td = document.createElement("div");
    td.className = "table-heading-btn";
    
    let tableName = this.tableName;

    if (this.headLinkage) {
      this.headLinkage.forEach(linkage => {
        let btn = linkage(tableName);
        td.appendChild(btn);
      });
    } else {
      addText(td, "");
    }

    return td;
  }

  getLength() {
    return this.length;
  }

  setWidth(width) {
    this.width = width;
  }

  getControl(cell, entity, editMode) {
    cell.className = "table-btn";
    cell.style.width = this.width;
    cell.style.maxWidth = this.width;

    let rowId = getCellRowId(cell);
    let tableName = this.tableName;
    
    let ctl = document.createElement("div");

    if (editMode && this.btnLinkage) {
      this.btnLinkage.forEach(linkage => {
        if (entity) {
          let btn = linkage(tableName, rowId);
          ctl.appendChild(btn);
        }
      });
    } else {
      addText(ctl, "");
    }

    return ctl;
  }
}

async function checkResponse(response) {
  if (response.ok) {
    if (response.status !== 204) {
      return response.json();
    } else {
      return {entities: [], links: []};
    }
  }

  throw new Error(response.statusText);
}

async function modal(elementName, title, contentUrl) {
  let anchor = document.getElementById(elementName);
  let modal = document.getElementById("modal");

  if (anchor && !modal) {
    modal = document.createElement("div");
    modal.id = "modal";
    modal.className = "modal";

    let content = document.createElement("div");
    content.className = "modal-content";

    let head = document.createElement("div");
    head.className = "modal-header";

    let heading = document.createElement("h2");
    addText(heading, title);
    head.appendChild(heading);
    content.appendChild(head);

    let body = document.createElement("div");
    body.className = "modal-body";

    let text = await fetch(contentUrl)
      .then(response => response.text())
      .catch(error => reportError(error));

    let area = document.createElement('textarea');
    area.value = text;
    area.color = 'black';
    area.height = "100%";
    area.width = "100%";
    area.readOnly = true;
    area.disabled = true;

    body.appendChild(area);
    content.appendChild(body);

    let foot = document.createElement("div");
    foot.className = "modal-footer";

    content.appendChild(foot);
    modal.appendChild(content);

    anchor.appendChild(modal);
    anchor.href = "#";
    anchor.onclick = function () {
      modal.style.display = "block";
    };

    window.onclick = function (event) {
      if (event.target === modal) {
        modal.style.display = "none";
      }
    }
  }
}

const about = () => {
  modal("license", "About ModellBahn", siteRoot() + "LICENSE");
};

const setActiveTab = (event, tabName) => {
  let tabContents = document.getElementsByClassName("tabContent");
  let tabLinks = document.getElementsByClassName("tabLinks");

  for (let i = 0; i < tabContents.length; i++) {
    tabContents[i].style.display = (tabContents[i].id === tabName) ? "block" : "none";
  }

  let linkName = tabName.replace("Tab", "Link");
  for (let i = 0; i < tabLinks.length; i++) {
	  tabLinks[i].className = (tabLinks[i].id === linkName) ? "tabLinks active" : "tabLinks";
  }
};

const addRow = (tableName) => {
  return getButton(undefined, "add", tableName + ".addRow()");
};

const deleteRow = (tableName, row) => {
  return getButton(row, "delete", tableName + ".deleteRow(" + row + ".id)");
};

const editRow = (tableName, row) => {
  return getButton(row, "update", tableName + ".editRow(" +  row + ".id)");
};

const newRow = (tableName) => {
  return getButton(undefined, "update", tableName + ".newRow()");
};

const updateRow = (tableName, row) => {
  return getButton(row, "update", tableName + ".updateRow(" +  row + ".id)");
};

const gridButtonColumn = (elementName) => {
  return new ButtonColumn([addRow],
    [updateRow, deleteRow]);
};

const addHeader = (tableName, table, columns, paged, rowCount) => {
  let header = document.createElement("div");
  header.id = tableName + "_thead";
  header.className = "thead";
  table.append(header);

  let headRow = document.createElement("div");
  headRow.className = "table-head";
  headRow.id = tableName + "Head";
  header.append(headRow);

  columns.forEach(column => {
    let th = paged ? column.getHeading() : document.createElement("div");
    if (!paged) addText(th, "");
    th.style.width = column.width;
    th.style.maxWidth = column.width;

    headRow.append(th);
  });
};

const addBody = (tableName, table, pageSize, columns, paged, rowCount, maxLabel) => {
  let body = document.createElement("div");
  body.id = tableName + "_tbody";
  body.className = paged ? "tbody" : "flex-container";
  table.append(body);

  let row;
  let maxRow = Math.max(rowCount, pageSize);
  for (row = 0; row < maxRow; row++) {
    let tr = document.createElement("div");
    let rowId = getRowId(tableName, row);
    tr.className = paged ? "table-row" : "flex-container";
    tr.id = rowId;
    body.append(tr);
    
    let key = document.createElement("input");
    key.type = "hidden";
    key.id = getKeyId(rowId);
    key.className = paged ? "table-cell" : "flex-control";
    tr.append(key);

    columns.forEach(column => {
      let td = document.createElement("div");

      if (!paged) {
	    td.className = "flex-item";

	    let th = column.getHeading();
        th.className = "flex-label";
        th.style.width = maxLabel;
        th.style.maxWidth = maxLabel;
        
        td.append(th);
      }
      
      let tc = document.createElement("div");
      
      tc.id = getCellId(rowId, column);
      tc.className = paged ? "table-cell" : "flex-control";
      tc.style.width = column.width;
      tc.style.maxWidth = column.width;
      
      addText(tc, "");
      
      if (paged) {
        tr.append(tc);
      } else {
    	td.append(tc);  
        tr.append(td);
      } 
    });
  }
};

const addFooter = (tableName, table, columns, paged, rowCount) => {
  if (paged) {
    let footer = document.createElement("div");
    footer.id = tableName + "_tfoot";
    footer.className = "tfoot";
    table.append(footer);

    let navRow = document.createElement("div");
    navRow.className = "table-foot";
    navRow.id = tableName + "Foot";
    footer.append(navRow);

    for (let i = 0; i < columns.length; i++) {
      let tf = document.createElement("div");
      if (i === 0) {
        tf.className = "table-prev";
        tf.id = tableName + "Prev";
      } else if (i === (columns.length - 1)) {
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
};

async function uploadFile(url, inputCtl) {
  let formData = new FormData();

  let file = inputCtl.files[0];

  formData.append("FileName", file.name);
  formData.append("FileData", file);

  await fetch(url, {method: "PUT", body: formData})
    .then(response => checkResponse(response))
    .catch(error => reportError(error));
}
