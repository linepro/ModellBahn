// module "utils.js"
"use strict";

var Editable = {
  NEVER: 0,
  UPDATE: 1,
  ADD: 2,
}

var EditMode = {
  VIEW: 0,
  UPDATE: 1,
  ADD: 2,
}

function shouldDisable(editable, editMode) {
  if (editable === Editable.NEVER || editMode === EditMode.VIEW) {
    return true;
  }

  if (editable < editMode) {
    return false;
  }

  return true;
}

function apiRoot() {
  return location.protocol + "//" + location.host + "/ModellBahn/api/";
}

function siteRoot() {
  return location.protocol + "//" + location.host + "/ModellBahn/static/";
}

function fetchUrl(dataType) {
  var fetchUrl = apiRoot() + dataType;
  var searchParams  = new URLSearchParams(location.search);

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

function reportError( error ) {
  alert( "error: " + error.toString() );
}

function getLink(links, rel) {
  return links.find(function(lnk) { return lnk.rel == rel; });
}

function getImg(action) {
  var img = document.createElement("img");

  img.alt = action;
  img.src = "img/" + action + ".png";

  return img;
}

function getButton(value, alt, action) {
  var btn = document.createElement("button");

  btn.setAttribute("value", value);
  btn.setAttribute("onclick", action);

  btn.appendChild(getImg(alt));

  return btn;
}

function addText(cell, text) {
  cell.appendChild(document.createTextNode(text));
}

function getButtonLink(href, alt, action) {
    var a = document.createElement("a");

    a.setAttribute("href", href);
    a.className = "btn btn-info btn-sm";
    a.appendChild(getImg(action));

    return a;
}

function addButtonLink(element, href, action) {
  if (href) {
    element.appendChild(getButtonLink(href, action, action));
  }

  return element;
}

function addLink(element, href) {
  if (href) {
    var a = document.createElement("a");

    a.setAttribute("href", href);
    a.appendChild(element);

    return a;
  }

  return element;
}

class Column {
  constructor(heading, binding, editable, required) {
    this.heading  = heading;
    this.binding  = binding;
    this.editable = editable ? editable : Editable.NEVER;
    this.required = required;
  }

  setTableName(tableName) {
    this.tableName = tableName;
  }
 
  getHeading() {
    var td = document.createElement("div");
    td.className = "table-heading";
    addText(td, this.heading);
    return td;
  }

  entityValue(entity) {
    return entity[this.binding];
  }
  
  getControl(cell, entity, editMode) {
    var ctl = this.createControl();
    var value;

    if (entity) value = this.entityValue(entity);

    if (value) {
      this.setValue(ctl, value);
      ctl.disabled = shouldDisable(this.editable, editMode);
    } else if (this.editable != Editable.NEVER && editMode == EditMode.ADD){
      ctl.disabled = false;
    } else {
        ctl.disabled = true;
    }

    return ctl;
  }

  getValue(cell) {
    var ctl = cell.firstChild;
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
  constructor(heading, binding, editable) {
    super(heading, binding, editable);
  }

  createControl() {
    var ctl = document.createElement("input");
    ctl.type = "text";
    return ctl;
  }
}

class NumberColumn extends Column {
  constructor(heading, binding, editable, maxBinding, minBinding) {
    super(heading, binding, editable);
    this.maxBinding = maxBinding;
    this.minBinding = minBinding;
  }

  createControl() {
    var ctl = document.createElement("input");
    ctl.type = "number";
    return ctl;
  }
}

class BoolColumn extends Column {
  constructor(heading, binding, editable) {
    super(heading, binding, editable);
  }

  createControl() {
    var ctl = document.createElement("div");
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
  constructor(heading, binding, editable) {
    super(heading, binding, editable);
  }

  createControl() {
    var ctl = document.createElement("input");
    ctl.type = "date";
    return ctl;
  }
}

class SelectColumn extends Column {
  constructor(heading, binding, dropDown, editable) {
    super(heading, binding, editable);
    this.dropDown  = dropDown;
  }

  createControl() {
    var ctl = document.createElement("select");
    this.dropDown.addOptions(ctl, 1);
    return ctl;
  }
  
  getControlValue(select) {
    return select.options[select.selectedIndex].value;
  }
 
  setValue(ctl, value) {
   var i;
   for (i = 0; i < ctl.options.length; i++) {
     if (ctl.options[i].value == value) {
      ctl.selectedIndex = i;
      return;
     }
   }
  }
}

class HeaderLinkage {
  constructor(alt, method) {
    this.alt    = alt;
    this.method = method;

    this.img    = getImg(alt);
  }
  
  getButton() {
    return getButton(this.value, this.alt, this.method);
  }
}

class FunctionLinkage extends HeaderLinkage {
  constructor(alt, method) {
    super(alt, method);
  }

  extractLink(entity, cell) {
    var lnk = getLink(entity.links, this.alt);
    
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
    this.btnLinkage  = btnLinkage;
    }

  setTableName() {
  }

  getHeading() {
    var td = document.createElement("div");
    td.className = "table-heading-btn";

    if (this.headLinkage) {
      this.headLinkage.forEach(function(linkage) {
        td.append(linkage.getButton());
      });
    } else {
      addText(td, "");
    }

    return td;
  }

  getControl(cell, entity, editMode) {
    cell.className = "table-btn";

    var ctl = document.createElement("div");

    if (editMode && this.btnLinkage) {
      this.btnLinkage.forEach(function(linkage) {
        if (entity && linkage.extractLink(entity, cell)) {
          ctl.append(linkage.getButton());
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
		return response.json();
	}

	throw new Error(response.statusText);
}