// module "utils.js"
"use strict"

function apiRoot() {
  return window.location.protocol + "//" + window.location.host + "/ModellBahn/api/";
}

function siteRoot() {
  return window.location.protocol + "//" + window.location.host + "/ModellBahn/static/";
}

function removeChildren(node) {
  while (node.firstChild) {
    node.removeChild(node.firstChild);
  }
}

function reportError( jqXHR, textStatus, error ) {
  alert( "error:    " + error +
         "\njqXHR:  " + jqXHR +
         "\nstatus: " + textStatus);
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

function getButtonLink(href, action) {
    var a = document.createElement("a");

    a.setAttribute("href", href);
    a.className = "btn btn-info btn-sm";
    a.appendChild(getImg(action));

    return a;
}

function addButtonLink(element, href, action) {
  if (href) {
    element.appendChild(getButtonLink(href, action));
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

class TextColumn {
  constructor(heading, binding, mutable) {
    this.heading    = heading;
    this.binding    = binding;
    this.readOnly   = mutable ? false : true;
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

  getControl(cell, entity, editMode) {
    var ctl;

    ctl = document.createElement("input");
    ctl.type = "text";

    if (entity) {
    	ctl.value = entity[this.binding];
    	ctl = addLink(ctl, entity[this.linkBinding]);
        ctl.disabled = this.readOnly || !editMode;
    } else {
        ctl.disabled = false;
    }

    return ctl;
  }

  getValue(cell) {
	    var input = cell.firstChild;
    if (input && input.nodeName == "INPUT") {
      return input.value;
    }
  }
}

class NumberColumn {
  constructor(heading, binding, mutable, maxBinding, minBinding) {
    this.heading    = heading;
    this.binding    = binding;
    this.readOnly   = mutable ? false : true;
    this.maxBinding = maxBinding;
    this.minBinding = minBinding;
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

  getControl(cell, entity, editMode) {
    var ctl;
  
    ctl = document.createElement("input");
    ctl.type = "number";
    
    if (entity) {
    	ctl.min = this.minBinding ? entity[this.minBinding] : undefined;
    	ctl.max = this.maxBinding ? entity[this.maxBinding] : undefined;
    	ctl.value = entity[this.binding];
        ctl.disabled = this.readOnly || !editMode;
    } else {
        ctl.disabled = false;
    }

    return ctl;
  }

  getValue(cell) {
	    var input = cell.firstChild;
    if (input && input.nodeName == "INPUT") {
      return input.value;
    }
  }
}

class BoolColumn {
  constructor(heading, binding, mutable) {
    this.heading   = heading;
    this.binding   = binding;
    this.readOnly  = mutable ? false : true;
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

  getControl(cell, entity, editMode) {
    var ctl;

    ctl = document.createElement("input");
    ctl.type = "checkbox";
    
    if (entity) {
        ctl.checked = entity[this.binding];
        ctl.disabled = this.readOnly || !editMode;
    } else {
        ctl.disabled = false;
    }

    return ctl;
  }

  getValue(cell) {
	    var input = cell.firstChild;
    if (input && input.nodeName == "INPUT") {
      return input.checked;
    }
  }
}

class DateColumn {
  constructor(heading, binding, mutable) {
    this.heading   = heading;
    this.binding   = binding;
    this.readOnly  = mutable ? false : true;
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

  getControl(cell, entity, editMode) {
    var ctl;

    ctl = document.createElement("input");
    ctl.type = "date";
    
    if (entity) {
    	ctl.value = entity[this.binding];
        ctl.disabled = this.readOnly || !editMode;
    } else {
        ctl.disabled = false;
    }
  
    return ctl;
  }

  getValue(cell) {
    var input = cell.firstChild;
    if (input && input.nodeName == "INPUT") {
      return input.value;
    }
  }
}

class SelectColumn {
  constructor(heading, binding, dropDown, mutable) {
    this.heading   = heading;
    this.binding   = binding;
    this.dropDown  = dropDown;
    this.readOnly  = mutable ? false : true;
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

  getControl(cell, entity, editMode) {
    var ctl;
    var value;
    
    if (entity) value = entity[this.binding];

    ctl = document.createElement("select");
  
    this.dropDown.addOptions(ctl, 1, value);

    if (value) {
      ctl.disabled = this.readOnly || !editMode;
    } else {
      ctl.disabled = false;
    }

    return ctl;
  }

  getValue(cell) {
    var select = input;
    if (select && select.nodeName == "SELECT") {
      return select.options[select.selectedIndex].value;
    }
  }
}

class PageLinkage {
  constructor(page, alt, action) {
    this.page      = page;
    this.alt       = alt;
    this.img       = getImg(alt);
    this.action    = action;
  }
  
  setTableName(tableName) {
    this.tableName = tableName;
  }

  extractLink(entity, cell) {
    return true;
  }

  getButton() {
    return getButtonLink(this.page, this.action);
  }
}

class FormLinkage {
  constructor(page, alt, rel) {
    this.page      = page;
    this.alt       = alt;
    this.img       = getImg(alt);
    this.rel       = rel;
  }
  
  setTableName(tableName) {
    this.tableName = tableName;
  }

  extractLink(entity, cell) {
    var lnk = getLink(entity.links, this.rel);
  
    if (lnk) {
      this.url    = this.page + "?" + lnk.rel + "=" + lnk.href;

      return true;
    }

    return false;
  }

  getButton() {
    return getButtonLink(this.url, this.alt);
  }
}

class RestLinkage {
  constructor(alt, rel, action) {
    this.alt       = alt;
    this.img       = getImg(alt);
    this.rel       = rel;
    this.action    = action;
  }
  
  setTableName(tableName) {
    this.tableName = tableName;
  }

  extractLink(entity, cell) {
    var linkage = this;
    var lnk = getLink(entity.links, linkage.rel);
  
    if (lnk) {
      this.url    = lnk.href;
      this.method = lnk.method;

      return true;
    }

    return false;
  }

  getButton() {
    return getButton(this.url, this.rel, this.tableName +"." + this.action + "(this.value);");
  }
}

class FunctionLinkage {
  constructor(alt, action) {
    this.alt       = alt;
    this.img       = getImg(alt);
    this.action    = action;
  }
  
  setTableName(tableName) {
    this.tableName = tableName;
  }

  extractLink(entity, cell) {
    this.cellId = cell.id.replace("_buttons", "");
    return true;
  }

  getButton() {
    return getButton(this.cellId, this.alt, this.action);
  }
}

class ButtonColumn {
  constructor(headLinkage, btnLinkage) {
    this.headLinkage = headLinkage;
    this.btnLinkage  = btnLinkage;
    }

  setTableName(tableName) {
    this.tableName = tableName;
    
    this.headLinkage.forEach(function(l) { l.setTableName(tableName);});
    this.btnLinkage.forEach(function(l) { l.setTableName(tableName);});
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