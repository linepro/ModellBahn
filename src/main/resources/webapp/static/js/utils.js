// module "utils.js"
"use strict"
function apiRoot() {
  return window.location.protocol + "//" + window.location.host + "/ModellBahn/api/";
};

function siteRoot() {
  return window.location.protocol + "//" + window.location.host + "/ModellBahn/static/";
};

function removeChildren(node) {
  while (node.firstChild) {
    node.removeChild(node.firstChild);
  }
};

function reportError( jqXHR, textStatus, error ) {
  alert( "error:    " + error +
       "\njqXHR:  " + jqXHR +
       "\nstatus: " + textStatus);
};

function getImg(action) {
  var img = document.createElement("img");

  img.alt = action;
  img.src = "img/" + action + ".png";

  return img;
};

function getButton(href, alt, action) {
  var btn = document.createElement("button");

  btn.setAttribute("value", href);
  btn.setAttribute("onclick", action);

  btn.appendChild(getImg(alt));

  return btn;
};

function addButton(cell, lnk, action) {
  removeChildren(cell);

  if (lnk) {
    cell.appendChild(getButton(lnk.href, lnk.rel, action));
  }
};

function addText(cell, text) {
  cell.appendChild(document.createTextNode(text));
};

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
};

function addLink(element, href) {
  if (href) {
    var a = document.createElement("a");

    a.setAttribute("href", href);
    a.appendChild(element);

    return a;
  }

  return element;
};

function addHeader(tableName, table, columns) {
  var header = document.createElement("div");
  header.className = "thead";
  table.append(header);

  var headRow = document.createElement("div");
  headRow.className = "table-row";
  headRow.id = tableName + "Head";
  header.append(headRow);

  columns.forEach(function(column) {
    var th = document.createElement("div");
    th.className = "table-heading";
    headRow.append(column.getHeading());
  });
};

function addFooter(tableName, table, columns, paged) {
  if (paged) {
    var footer = document.createElement("div");
    footer.className = "tfoot";
    table.append(footer);

    var navRow = document.createElement("div");
    navRow.className = "table-row";
    navRow.id = tableName + "Foot";
    footer.append(navRow);

    var i;
    for (i = 0; i < columns.length; i++) {
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
};

class TextColumn {
  constructor(heading, binding, readOnly) {
    this.heading    = heading;
    this.binding    = binding;
    this.readOnly   = readOnly ? readOnly : true;
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
    var value = entity[this.binding];

    ctl = document.createElement("input");
    ctl.type = "text";
    ctl.disabled = this.readOnly || !editMode;
    ctl.value = value;

    ctl = addLink(ctl, entity[this.linkBinding]);

    return ctl;
  }
};

class NumberColumn {
  constructor(heading, binding, readOnly, maxBinding, minBinding) {
    this.heading    = heading;
    this.binding    = binding;
    this.readOnly   = readOnly ? readOnly : true;
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
    var value = entity[this.binding];
    var max = this.maxBinding ? entity[this.maxBinding] : undefined;
    var min = this.minBinding ? entity[this.minBinding] : undefined;
  
    ctl = document.createElement("input");
    ctl.type = "number";
    ctl.disabled = this.readOnly || !editMode;
    ctl.min = min;
    ctl.max = max;
    ctl.value = value;

    return ctl;
  }
};

class BoolColumn {
  constructor(heading, binding, readOnly) {
    this.heading   = heading;
    this.binding   = binding;
    this.readOnly  = readOnly ? readOnly : true;
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
    var value = entity[this.binding];

    ctl = document.createElement("input");
    ctl.type = "checkbox";
    ctl.disabled = this.readOnly || !editMode;
    ctl.checked = value;

    return ctl;
  }
};

class DateColumn {
  constructor(heading, binding, readOnly) {
    this.heading   = heading;
    this.binding   = binding;
    this.readOnly  = readOnly ? readOnly : true;
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
    var value = entity[this.binding];

    ctl = document.createElement("input");
    ctl.type = "text";
    ctl.disabled = this.readOnly || !editMode;
    ctl.value = value;

    return ctl;
  }
};

class SelectColumn {
  constructor(heading, binding, dropDown, readOnly) {
    this.heading   = heading;
    this.binding   = binding;
    this.dropDown  = dropDown;
    this.readOnly  = readOnly ? readOnly : true;
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
    var value = entity[this.binding];

    ctl = document.createElement("select");
  
    this.dropDown.addOptions(ctl, 1, value);

    ctl.disabled = this.readOnly || !editMode;

    return ctl;
  }
};

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

  extractLink(entity) {
    return true;
  }

  getButton() {
    return getButtonLink(this.page, this.action);
  }
};

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

  extractLink(entity) {
    var linkage = this;
    var lnk = entity.links.find(function(e) { return e.rel == linkage.rel; });
  
    if (lnk) {
      this.url    = this.page + "?" + lnk.rel + "=" + lnk.href;
      this.method = lnk.method;

      return true;
    }

    return false;
  }

  getButton() {
    return getButtonLink(this.url, this.alt);
  }
};

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

  extractLink(entity) {
    var linkage = this;
    var lnk = entity.links.find(function(e) { return e.rel == linkage.rel; });
  
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
};

class FunctionLinkage {
  constructor(alt, rel, action) {
    this.alt       = alt;
    this.img       = getImg(alt);
    this.rel       = rel;
    this.action    = action;
  }
  
  setTableName(tableName) {
    this.tableName = tableName;
  }

  extractLink(entity) {
    var linkage = this;
    var lnk = entity.links.find(function(e) { return e.rel == linkage.rel; });
  
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
};

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
        if (linkage.extractLink(entity)) {
          ctl.append(linkage.getButton());
        }
      });
    } else {
      addText(ctl, "");
    }

    return ctl;
  }
};