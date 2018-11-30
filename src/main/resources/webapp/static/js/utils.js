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

function shouldDisable(editable, editMode) {
  if (editable === Editable.NEVER || editMode === EditMode.VIEW) {
    return true;
  }

  return (editable > editMode);
}

function apiRoot() {
  return location.protocol + "//" + location.host + "/ModellBahn/api/";
}

function siteRoot() {
  return location.protocol + "//" + location.host + "/ModellBahn/static/";
}

function fetchUrl(dataType) {
  let fetchUrl = apiRoot() + dataType;
  let searchParams = new URLSearchParams(location.search);

  if (searchParams.has("self")) {
    fetchUrl = searchParams.get("self");
  }

  return fetchUrl;
}

function removeChildren(node) {
  while (node.firstChild) {
    node.removeChild(node.firstChild);
  }
}

function reportError(error) {
  alert("error: " + error.toString());
}

function addButton(cell, lnk, action) {
  removeChildren(cell);

  if (lnk) {
    cell.appendChild(getButton(lnk.href, lnk.rel, action));
  }
}

function getLink(links, rel) {
  return links.find((lnk) => {
    return lnk.rel === rel;
  });
}

function getFieldId(rowId, binding) {
  return rowId + "_" + binding;
}

function getImg(action) {
  let img = document.createElement("img");

  img.alt = action;
  img.src = "img/" + action + ".png";

  return img;
}

function getButton(value, alt, action) {
  let btn = document.createElement("button");

  btn.setAttribute("value", value);
  btn.setAttribute("onclick", action);
  btn.className = "nav-button";

  let img = getImg(alt);
  img.className = "nav-button";

  btn.appendChild(img);

  return btn;
}

function addText(cell, text) {
  cell.appendChild(document.createTextNode(text));
}

function getButtonLink(href, alt, action) {
  let a = document.createElement("a");

  a.setAttribute("href", href);
  a.className = "nav-button";
  a.appendChild(getImg(action));

  return a;
}

class Column {
  constructor(heading, binding, editable, required, length) {
    this.heading = heading;
    this.binding = binding;
    this.editable = editable ? editable : Editable.NEVER;
    this.required = required ? required : false;
    this.length = Math.max(length ? length : heading.length, heading.length);
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
    return entity[this.binding];
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

class TextColumn extends Column {
  constructor(heading, binding, editable, required, length) {
    super(heading, binding, editable, required, length);
  }

  createControl() {
    let ctl = super.createControl();
    ctl.type = "text";
    ctl.maxLength = this.length;
    return ctl;
  }
}

class IMGColumn extends Column {
  constructor(heading, binding, onChange, editable, required) {
    super(heading, binding, editable, required);
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
}

class PhoneColumn extends Column {
  constructor(heading, binding, editable, required) {
    super(heading, binding, editable, required);
  }

  createControl() {
    let ctl = super.createControl();
    ctl.type = "tel";
    return ctl;
  }
}

class PDFColumn extends Column {
  constructor(heading, binding, onChange, editable, required) {
    super(heading, binding, editable, required);
    this.onChange = onChange;
  }

  createControl() {
    let ctl = super.createControl();
    ctl.type = "file";
    ctl.accept = "application/pdf";
    ctl.multiple = false;
    if (this.onChange) ctl.change = this.onChange;
    return ctl;
  }
}

class URLColumn extends Column {
  constructor(heading, binding, editable, required) {
    super(heading, binding, editable, required);
  }

  createControl() {
    let ctl = super.createControl();
    ctl.type = "url";
    return ctl;
  }
}

class NumberColumn extends Column {
  constructor(heading, binding, editable, required, max, min, step) {
    max = max ? max : 255;
    super(heading, binding, editable, required, Math.max(max.toString().length, 5));
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

class BoolColumn extends Column {
  constructor(heading, binding, editable, required) {
    super(heading, binding, editable, required, heading.length);
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
  constructor(heading, binding, editable, required) {
    super(heading, binding, editable, required, 12);
  }

  createControl() {
    let ctl = super.createControl();
    ctl.type = "date";
    return ctl;
  }
}

class SelectColumn extends Column {
  constructor(heading, binding, dropDown, editable, required) {
    super(heading, binding, editable, required, dropDown.length);
    this.dropDown = dropDown;
  }

  createControl() {
    let ctl = document.createElement("select");
    this.dropDown.addOptions(ctl, 1);
    return ctl;
  }

  getControlValue(select) {
    return select.options[select.selectedIndex].value;
  }

  getLength() {
    return Math.max(this.dropDown.length, this.heading.length);
  }

  setValue(ctl, value) {
    for (let i = 0; i < ctl.options.length; i++) {
      if (ctl.options[i].value === value) {
        ctl.selectedIndex = i;
        return;
      }
    }
  }
}

class HeaderLinkage {
  constructor(alt, method) {
    this.alt = alt;
    this.method = method;

    this.img = getImg(alt);
  }

  getButton() {
    return getButton(undefined, this.alt, this.method);
  }
}

class FunctionLinkage extends HeaderLinkage {
  constructor(alt, method) {
    super(alt, method);
  }

  extractLink(entity, cell) {
    let lnk = getLink(entity.links, this.alt);

    if (lnk) {
      this.value = cell.id.replace("_buttons", "");

      return true;
    }

    return false;
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

    let col = this;
    if (this.headLinkage) {
      this.headLinkage.forEach(linkage => {
        let btn = linkage.getButton();
        btn.id = col.tableName + "_" + btn.id;
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

    let ctl = document.createElement("div");

    if (editMode && this.btnLinkage) {
      this.btnLinkage.forEach(linkage => {
        if (entity && linkage.extractLink(entity, cell)) {
          let btn = linkage.getButton();
          btn.id = cell.id + "_" + btn.id;
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

function about() {
  modal("license", "About ModellBahn", siteRoot() + "LICENSE");
}

function setActiveTab(event, tabName) {
  let tabContents = document.getElementsByClassName("tabContent");
  let tabLinks = document.getElementsByClassName("tabLinks");

  tabContents.forEach(tab => {
    tab.style.display = (tab.id === tabName) ? "block" : "none";
  });

  let linkName = tabName.replace("Tab", "Link");
  tabLinks.forEach(link => {
    link.className = (link.id === linkName) ? "tabLinks active" : "tabLinks";
  });
}

function addRow(elementName) {
  return new HeaderLinkage("add", elementName + ".addRow()");
}

function deleteRow(elementName) {
  return new FunctionLinkage("delete", elementName + ".deleteRow(this.value)");
}

function editRow(elementName) {
  return new FunctionLinkage("update", elementName + ".editRow(this.value)");
}

function updateRow(elementName) {
  return new FunctionLinkage("update", elementName + ".updateRow(this.value)");
}

function gridButtonColumn(elementName) {
  return new ButtonColumn([addRow(elementName)],
    [updateRow(elementName), deleteRow(elementName)]);
}

async function uploadFile(url, inputCtl) {
  let formData = new FormData();

  let file = inputCtl.files[0];

  formData.append("FileName", file.name);
  formData.append("FileType", file.type);
  formData.append("FileData", file);

  await fetch(url, {method: "PUT", body: formData})
    .then(response => checkResponse(response))
    .catch(error => reportError(error));
}
