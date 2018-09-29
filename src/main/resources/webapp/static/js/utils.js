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

  if (editable <= editMode) {
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
  btn.className = "nav-button";

  var img = getImg(alt);
  img.className = "nav-button";
  
  btn.appendChild(img);

  return btn;
}

function addText(cell, text) {
  cell.appendChild(document.createTextNode(text));
}

function getButtonLink(href, alt, action) {
    var a = document.createElement("a");

    a.setAttribute("href", href);
    a.className = "nav-button";
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
    }
    
    if (value || entity) {
      ctl.disabled = shouldDisable(this.editable, editMode);
    } else if (editMode == EditMode.ADD && this.editable != Editable.NEVER) {
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
  constructor(heading, binding, editable, length) {
    super(heading, binding, editable);
    this.length = length ? length : 50;
  }

  createControl() {
    var ctl = document.createElement("input");
    ctl.type = "text";
    return ctl;
  }
}

class NumberColumn extends Column {
  constructor(heading, binding, editable, max, min) {
    super(heading, binding, editable);
    this.max = max ? max : 255;
    this.min = min ? min : 0;
  }

  createControl() {
    var ctl = document.createElement("input");
    ctl.type = "number";
    if (this.min) ctl.min = this.min;
    if (this.max) ctl.max = this.max;
    return ctl;
  }
}

class BoolColumn extends Column {
  constructor(heading, binding, editable) {
    super(heading, binding, editable);
  }

  createControl() {
    var ctl = document.createElement("input");
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

  setTableName(tableName) {
	this.tableName = tableName;
  }

  getHeading() {
    var td = document.createElement("div");
    td.className = "table-heading-btn";

    var col = this;
    if (this.headLinkage) {
      this.headLinkage.forEach(function(linkage) {
    	var btn = linkage.getButton();
    	btn.id = col.tableName + "_" + btn.id;
        td.append(btn);
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
          var btn = linkage.getButton();
          btn.id = cell.id + "_" + btn.id;
          ctl.append(btn);
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