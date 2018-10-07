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
  constructor(heading, binding, editable, required, length) {
    this.heading  = heading;
    this.binding  = binding;
    this.editable = editable ? editable : Editable.NEVER;
    this.required = required ? required : false;
    this.length   = Math.max(length ? length : heading.length, heading.length);
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

  getLength() {
    return this.length;
  }

  setWidth(width) {
     this.width = width;
  }
  
  getControl(cell, entity, editMode) {
    var ctl = this.createControl();
    cell.style.width = this.width;
    cell.style.maxWidth = this.width;

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

    ctl.required = this.required;
    
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
  constructor(heading, binding, editable, required, length) {
    super(heading, binding, editable, required, length);
  }

  createControl() {
    var ctl = document.createElement("input");
    ctl.type = "text";
    ctl.maxLength = this.length;
    return ctl;
  }
}

class NumberColumn extends Column {
  constructor(heading, binding, editable, required, max, min) {
    max = max ? max : 255
    super(heading, binding, editable, required, Math.max(max.toString().length, 5));
    this.max = max;
    this.min = min ? min : 0;
  }

  createControl() {
    var ctl = document.createElement("input");
    ctl.type = "number";
    ctl.min = this.min;
    ctl.max = this.max;
    return ctl;
  }
}

class BoolColumn extends Column {
  constructor(heading, binding, editable, required) {
    super(heading, binding, editable, required, heading.length);
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
  constructor(heading, binding, editable, required) {
    super(heading, binding, editable, required, 12);
  }

  createControl() {
    var ctl = document.createElement("input");
    ctl.type = "date";
    return ctl;
  }
}

class SelectColumn extends Column {
  constructor(heading, binding, dropDown, editable, required) {
    super(heading, binding, editable, required, dropDown.length);
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

  getLength() {
      return Math.max(this.dropDown.length, this.heading.length);
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
    this.length = Math.max(headLinkage.length, btnLinkage.length)*8;
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

    var ctl = document.createElement("div");

    if (editMode && this.btnLinkage) {
      this.btnLinkage.forEach(function(linkage) {
        if (entity && linkage.extractLink(entity, cell)) {
          var btn = linkage.getButton();
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
		return response.json();
	}

	throw new Error(response.statusText);
}

async function modal(elementName, title, contentUrl) {
    
  var anchor = document.getElementById(elementName);
  var modal = document.getElementById("modal");
  
  if (anchor && !modal) {
      modal = document.createElement("div");
      modal.id="modal";
      modal.className="modal";

      var content = document.createElement("div");
      content.className="modal-content";

      var head = document.createElement("div");
      head.className="modal-header";

      var heading = document.createElement("h2");
      addText(heading, title);
      head.appendChild(heading);
      content.appendChild(head);

      var body = document.createElement("div");
      body.className="modal-body";
      
      var text = await fetch(contentUrl)
            .then(response => response.text())
            .catch(error => reportError(error));

      var area = document.createElement('textarea');
      area.value = text;
      area.color = 'black';
      area.height = "100%";
      area.width = "100%";
      area.readOnly = true;
      area.disabled = true;

      body.appendChild(area);
      content.appendChild(body);

      var foot = document.createElement("div");
      foot.className="modal-footer";

      content.appendChild(foot);
      modal.appendChild(content);

      anchor.appendChild(modal);
      anchor.href = "#";
      anchor.onclick = function() { modal.style.display = "block"; };

      window.onclick = function(event) {
          if (event.target == modal) {
              modal.style.display = "none";
          }
      }
   }
}

function about() {
    modal("license", "About ModellBahn", siteRoot() + "LICENSE");
}