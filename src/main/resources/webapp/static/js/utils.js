// module 'utils.js'
'use strict';

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

const DATE_PATTERN = '^(18[2-9][0-9]|19[0-9]{2}|2[0-9]{3})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[0-1])$';

const URL_PATTERN = '^(?:(http[s]?):\\/\\/)?(\\w+(?:\\.\\w+)*)(?::(\\d+))?(?:\\/(\\w+(?:\\/|\\.\\w+)?))?$';

window.onerror = function (msg, url, lineNo, columnNo, error) {
  if (msg.toLowerCase().includes("script error")){
    reportError('Script Error: See Browser Console for Detail');
  } else {
    const message = [
      'Message: ' + msg, 
      'URL: ' + url, 
      'Line: ' + lineNo, 
      'Column: ' + columnNo, 
      'Error object: ' + error.toString()
    ].join(' - ');

    reportError(message);
  }

  return false;
};
  
const shouldDisable = (editable, editMode) => {
  if (editable === Editable.NEVER || editMode === EditMode.VIEW) {
    return true;
  }

  return (editable > editMode);
};

const apiRoot = () => {
  return location.protocol + '//' + location.host + '/ModellBahn/api/';
};

const siteRoot = () => {
  return location.protocol + '//' + location.host + '/ModellBahn/static/';
};

const fetchUrl = (dataType) => {
  let fetchUrl = apiRoot() + dataType;
  let searchParams = new URLSearchParams(location.search);

  if (searchParams.has('self')) {
    fetchUrl = searchParams.get('self');
  }

  return fetchUrl;
};

const removeChildren = (node) => {
  while (node.firstChild) {
    node.removeChild(node.firstChild);
  }
};

const addToEnd = (element) => {
  let docBody = document.getElementsByTagName('BODY')[0];
  docBody.appendChild(element);
};

const reportError = (error) => {
  console.log(error);
  
  let alert = document.getElementById('alert-box');
  
  if (!alert) {
    alert = document.createElement('div');
    alert.className = 'alert';

    let closer = document.createElement('span');
    closer.className = 'closebtn';
    closer.onclick = (e) => {alert.style.display = 'none'; };
    addText(closer, 'x');
    alert.appendChild(closer);

    let message = document.createElement('span');
    message.id = 'alert-message';
    alert.appendChild(message);

    addToEnd(alert);
  }

  let message = document.getElementById('alert-message');
  message.innerText = error;
  alert.style.display = 'inline-block';
};

const addButton = (cell, lnk, action) => {
  removeChildren(cell);

  if (lnk) {
    cell.appendChild(getButton(lnk.href, lnk.rel, action));
  }
};

const getLink = (links, rel) => {
  if (!links) {
    return;
  }
  return links.find((lnk) => {
    return lnk.rel === rel;
  });
};

const getFieldId = (rowId, fieldName) => {
  return rowId + '_' + fieldName;
};

const getKeyId = (rowId) => {
  return getFieldId(rowId, 'key');
};

const getKeyValue = (rowId) => {
  let keyField = document.getElementById(getKeyId(rowId));
  return keyField.value;
};

const getRowId = (tableName, i) => {
  return tableName + '_' + i;
};

const getCellId = (rowId, column) => {
  if (column.fieldName) {
    return getFieldId(rowId, column.fieldName);
  } else {
    return getFieldId(rowId, 'buttons');
  }
};

const getCellRowId = (cell) => {
  return cell.id.substring(0, cell.id.lastIndexOf('_'));
};

const getImgSrc = (image) => {
  return 'img/' + image + '.png';
};

const getImg = (action) => {
  let img = document.createElement('img');

  img.alt = action;
  img.src = getImgSrc(action);

  return img;
};

const getButton = (value, alt, action) => {
  let btn = document.createElement('button');

  btn.setAttribute('value', value);
  if (action) btn.addEventListener('click', action);
  btn.className = 'nav-button';

  let img = getImg(alt);
  img.className = 'nav-button';

  btn.appendChild(img);

  return btn;
};

const addText = (cell, text) => {
  let txt = document.createTextNode(text);
  if (cell.firstChild) {
    cell.insertBefore(txt, cell.firstChild);
  } else {
    cell.appendChild(txt);
  }
  return txt;
};

const addOption = (select, value, text) => {
  let opt = document.createElement('option');
  opt.value = value;
  opt.text = text;
  select.add(opt);
};

const valueAndUnits = (cssSize) => {
  let dims = /^(\d+)([^\d]+)$/.exec(cssSize);
  return {
    value: dims[1], 
    units: dims[2]
  };
};

const setCtlValue = (jsonData, ctl, img) => {
  if (jsonData) {
    ctl.setValue(img, ctl.getter(jsonData));
  }
};

const boxSize = (length) => {
  return Math.ceil(length/5)*5;
};

const blankControl = (cell, column) => {
  let ctl = document.createElement('input');

  ctl.type = 'text';
  ctl.disabled = 'true';
  ctl.readOnly = 'true';
  ctl.required = false;

  cell.appendChild(ctl);
};

class Column {
  constructor(heading, fieldName, getter, setter, editable, required, length) {
    this.heading = heading;
    this.fieldName = fieldName;
    this.getter = getter;
    this.setter = setter;
    this.editable = editable ? editable : Editable.NEVER;
    this.required = required ? required : false;
    this.length = Math.max(length ? length : heading.length, heading.length + 1);
    this.width = 0;
  }

  setTableName(tableName) {
    this.tableName = tableName;
  }

  getHeading() {
    let td = document.createElement('div');
    td.className = 'table-heading';
    addText(td, this.heading);
    return td;
  }

  entityValue(entity) {
    return this.getter(entity);
  }

  getLength() {
    return boxSize(this.length);
  }

  getHeaderLength() {
    return boxSize(this.heading.length);
  }

  getWidth() {
    return this.width;
  }

  setWidth(width) {
    this.width = width;
  }

  createControl() {
    let ctl = document.createElement('input');
    ctl.autocomplete = 'off';
    return ctl;
  }

  getControl(cell, entity, editMode) {
    let ctl = this.createControl();

    let value;

    if (entity) {
      value = this.entityValue(entity);
    }

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

    cell.appendChild(ctl);

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

  isButtons() {
    return false;
  }
}

class BoolColumn extends Column {
  constructor(heading, fieldName, getter, setter, editable, required) {
    super(heading, fieldName, getter, setter, editable, required, heading.length);
  }

  createControl() {
    let ctl = super.createControl();
    ctl.type = 'checkbox';
    return ctl;
  }

  getControlValue(ctl) {
    return ctl.checked;
  }

  setValue(ctl, value) {
    ctl.checked = value;
  }
}

class NumberColumn extends Column {
  constructor(heading, fieldName, getter, setter, editable, required, max, min, places) {
    super(heading, fieldName, getter, setter, editable, required, (max ? max : 255).toString().length, heading.length);
    this.max = max ? max : 255;
    this.min = min ? min : 0;
    this.places = places ? places : 0;
  }

  createControl() {
    let ctl = super.createControl();
    ctl.type = 'number';
    ctl.min = this.min;
    ctl.max = this.max;
    ctl.step = this.places > 0 ? 1 / this.places : 1;
    return ctl;
  }

  setValue(ctl, value) {
    ctl.value = parseFloat(value).toFixed(this.places);
  }
}

class PhoneColumn extends Column {
  constructor(heading, fieldName, getter, setter, editable, required) {
    super(heading, fieldName, getter, setter, editable, required);
  }

  createControl() {
    let ctl = super.createControl();
    ctl.type = 'tel';
    return ctl;
  }
}

class TextColumn extends Column {
  constructor(heading, fieldName, getter, setter, editable, required, length, pattern) {
    super(heading, fieldName, getter, setter, editable, required, length);
    this.pattern = pattern;
  }

  createControl() {
    let ctl = super.createControl();
    ctl.type = 'text';
    ctl.maxLength = this.length;
    ctl.pattern = this.pattern;
    return ctl;
  }
}

class URLColumn extends TextColumn {
  constructor(heading, fieldName, getter, setter, editable, required) {
    super(heading, fieldName, getter, setter, editable, required, 60, URL_PATTERN);
  }

  createControl() {
    let ctl = super.createControl();
    ctl.type = 'url';
    ctl.class = 'table-url';
    ctl.addEventListener('click', (e) => {
      if (ctl.value) {
        window.open(ctl.value, '_blank');
      }
    }, false);
    return ctl;
  }
}

class DateColumn extends TextColumn {
  constructor(heading, fieldName, getter, setter, editable, required) {
    super(heading, fieldName, getter, setter, editable, required, 12,
        DATE_PATTERN);
  }

  createControl() {
    let ctl = super.createControl();
    ctl.addEventListener('click', (e) => {
    }, false);
    return ctl;
  }

}

class FileColumn extends Column {
  constructor(heading, fieldName, getter, mask, editable, required) {
    super(heading, fieldName, getter, undefined, editable, required);
    this.mask = mask;
  }

  getControl(cell, entity, editMode) {
    let ctl = super.getControl(cell, entity, editMode);
    ctl.className = 'img-display';

    if (entity) {
      ctl.addEventListener('click', (e) => {
    	  let img = e.target;
    	  let file = img.getAttribute('data-file');
    	  if (file) {
    		  this.showContent(file);
    		  }
    	  }, false);

	    let add = getLink(entity.links, 'update-' + this.fieldName);
	
	    if (add) {
	      let btn = getButton(add.href, 'add', (e) => { this.select(e); });
	      btn.className = 'img-button';
	      btn.firstChild.className = 'img-button';
	      cell.appendChild(btn);
	    }
	
	    let remove = getLink(entity.links, 'delete-' + this.fieldName);
	
	    if (remove) {
	      let btn = getButton(remove.href, 'delete', (e) => { this.remove(e, ctl); });
	      btn.className = 'img-button';
	      btn.firstChild.className = 'img-button';
	      cell.appendChild(btn);
	    }
	
	    if (cell.firstChild) {
	      cell.removeChild(ctl);
	      cell.insertBefore(ctl, cell.firstChild);
	    }
    }
    
    return ctl;
  }

  createControl() {
    return getImg('none');
  }

  getControlValue(ctl) {
    return ctl.getAttribute('data-file');
  }

  setValue(ctl, value) {
    ctl.src = value ? value : 'img/none.png';
    ctl.setAttribute('data-file', value);
  }

  select(e) {
    let btn = e.target;
    if (btn.tagName === 'IMG') {
      btn = btn.parentElement;
    }
    let link = btn.value;
    let cell = btn.parentElement;
    let file = document.createElement('input');
    file.type = 'file';
    file.accept = this.mask;
    file.multiple = false;
    file.setAttribute('data-update', link);
    cell.appendChild(file);
    file.style.display = 'none';
    file.click();
    file.addEventListener('change', (e) => { this.update(e); }, false);
    file.addEventListener('click', (e) => { this.update(e); }, false);
    file.addEventListener('blur', (e) => { this.update(e); }, false);
  }

  remove(e, img) {
    let btn = e.target;
    if (btn.tagName === 'IMG') {
      btn = btn.parentElement;
    }
    let cell = btn.parentElement;
    let link = btn.value;
    if (link) {
      removeFile(link)
        .then(() => this.setValue(img, undefined));
    }
  }

  update(e) {
    let file = e.target;
    let fileData = file.files[0];
    let link = file.getAttribute('data-update');
    let cell = file.parentElement;
    cell.removeChild(file);
    if (fileData && link) {
      let ctl = cell.getElementsByClassName('img-display')[0];
      readFile(link, fileData, this, ctl);
    }
  }

  showContent(file) {
  }
}

class IMGColumn extends FileColumn {
  constructor(heading, fieldName, getter, editable, required) {
    super(heading, fieldName, getter, 'image/*', editable, required);
  }

  showContent(file) {
    let img = document.createElement('img');
    img.className = 'display-img';
    img.src = file;
    showModal(img);
  }
}

class PDFColumn extends FileColumn {
  constructor(heading, fieldName, getter, editable, required) {
    super(heading, fieldName, getter, 'application/pdf', editable, required);
  }

  setValue(ctl, value) {
    ctl.src = value ? 'img/pdf.png' : 'img/none.png';
    ctl.setAttribute('data-file', value);
  }

  showContent(file) {
    let div = document.createElement('div');
    div.className = 'display-pdf-container';

    let canvas = document.createElement('canvas');
    canvas.className = 'display-pdf-canvas';
    div.appendChild(canvas);

    let bar = document.createElement('div');
    bar.className = 'display-pdf-bar';
    div.appendChild(bar);

    let viewer = new PDFViewer(canvas);

    let prev = getButton('Vorig', 'previous', (e) => { viewer.prevPage(); });
    prev.style.cssFloat = 'left';
    bar.appendChild(prev);

    let next = getButton('Nächste', 'next', (e) => { viewer.nextPage(); });
    next.style.cssFloat = 'right';
    bar.appendChild(next);

    viewer.load(file);
    
    showModal(div, true);
  }
}

const closeAutoLists = (elmnt) => {
  let autoComp = (elmnt ? elmnt : document).getElementsByClassName( 'autocomplete-list');
  for (let i = 0; i < autoComp.length; i++) {
    removeChildren(autoComp[i]);
    autoComp[i].parentNode.removeChild(autoComp[i]);
  }
};

document.addEventListener('click', (e) => { closeAutoLists(); }, false);

class SelectColumn extends Column {
  constructor(heading, fieldName, getter, setter, dropDown, editable, required, length, dropSize) {
    super(heading, fieldName, getter, setter, editable, required, length);
    this.dropDown = dropDown;
    this.dropSize = dropSize ? dropSize : 5;
  }

  getControl(cell, entity, editMode) {
    let ctl = super.getControl(cell, entity, editMode);

    if (!ctl.disabled) {
      ctl.addEventListener('click', (e) => { this.open(e); }, false);
      ctl.addEventListener('input', (e) => { this.open(e); }, false);
      ctl.addEventListener('keydown', (e) => { this.keydown(e); }, false);
      ctl.classList.add('autocomplete');
    }

    return ctl;
  }

  getControlValue(ctl) {
    return ctl.getAttribute('data-name');
  }

  setValue(ctl, value) {
    ctl.setAttribute('data-name', value);
    this.dropDown.options.forEach((o) => {
      if (o.value === value) {
        ctl.value = o.display;
      }
    });
  }

  getLength() {
    return Math.max(this.dropDown.getLength(), this.getHeaderLength());
  }

  caption(txt, o) {
    return o.display;
  }

  options(txt) {
    return this.dropDown.options;
  }

  open(e) {
    let ctl = this;
    let inp = e.target;
    let div = inp.parentNode;

    e.stopPropagation();
    closeAutoLists();

    let rect = div.getBoundingClientRect();

    let autoComp = document.createElement('div');
    autoComp.className = 'autocomplete-list';
    autoComp.style.top = rect.y + rect.height;
    div.appendChild(autoComp);
    let dims = valueAndUnits(getComputedStyle(autoComp).lineHeight);

    let i = 0;
    ctl.options(inp.value).forEach((o) => {
      let autoItem = document.createElement('div');
      autoItem.setAttribute('data-name', o.value);
      autoItem.className = 'autocomplete-items';
      autoItem.style.top = (dims.value * i) + dims.units;
      addText(autoItem, ctl.caption(inp.value, o));
      autoItem.addEventListener('click', (e) => { this.click(e); }, false);
      autoComp.appendChild(autoItem);
      i++;
    });
  }

  keydown(e) {
    let ctl = this;
    let div = e.target.parentNode;

    let autoComps = div.getElementsByClassName('autocomplete-list');

    if (!autoComps || !autoComps.length) {
      return;
    }

    let autoComp = autoComps[0];

    if (e.keyCode === 40) {
      ctl.addActive(ctl, autoComp, true);
    } else if (e.keyCode === 38) {
      ctl.addActive(ctl, autoComp, false);
    } else if (e.keyCode === 13) {
      e.preventDefault();
      ctl.selectActive(autoComp);
    }
  }

  click(e) {
    let opt = e.target;
    let autoComp = opt.parentNode;
    let div = autoComp.parentNode;
    let inp = div.getElementsByClassName('autocomplete')[0];

    inp.value = opt.innerText;
    inp.setAttribute('data-name', opt.getAttribute('data-name'));

    closeAutoLists();
  };

  addActive(ctl, autoComp, up) {
    let items = autoComp.getElementsByClassName('autocomplete-items');
    let curr = -1;
    for (let i = 0; i < items.length; i++) {
      if (items[i].classList.contains('autocomplete-active')) {
        if (curr === -1) {
          curr = i;
        }
        items[i].classList.remove('autocomplete-active');
      }
    }

    let next = curr + (up ? 1 : -1);
    if (0 <= next && next <= items.length) {
      items[next].classList.add(
          'autocomplete-active');
    }
  }

  selectActive(autoComp) {
    let active = autoComp.getElementsByClassName('autocomplete-active');
    if (active.length) {
      active[0].click();
    }
  }
}

class AutoCompleteColumn extends SelectColumn {
  constructor(heading, fieldName, getter, setter, dropDown, editable, required, length, dropSize) {
    super(heading, fieldName, getter, setter, dropDown, editable, required, length, dropSize);
  }

  options(txt) {
    return this.dropDown.getOptions().filter((o) => {
      return o.display.toLowerCase().includes(txt.toLowerCase());
    }).slice(0, this.dropSize);
  }

  caption(txt, o) {
    return o.display.replace(/inp.value/i, 
        '<strong>' + inp.value + '</strong>');
  }

  open(e) {
    let inp = e.target;

    if (!inp.value) {
      return false;
    }

    return super.open(e);
  }
}

class DropDownColumn extends SelectColumn {
  constructor(heading, fieldName, getter, setter, dropDown, editable, required, length, dropSize) {
    super(heading, fieldName, getter, setter, dropDown, editable, required, length + 3.5, dropSize);
  }

  getControl(cell, entity, editMode) {
    let ctl = this.createControl();

    let value;

    if (entity) {
      value = this.entityValue(entity);
    }

    if (value) {
      this.setValue(ctl, value);
    }

    if (value || entity) {
      ctl.disabled = shouldDisable(this.editable, editMode);
    } else {
      ctl.disabled = !(editMode === EditMode.ADD && this.editable !== Editable.NEVER);
    }

    ctl.required = this.required;

    cell.appendChild(ctl);

    return ctl;
  }

  addOptions(select, dropDown) {
    if (!this.required) {
      addOption(select, undefined, '(nicht benötigt)');
    }

    dropDown.options.forEach(opt => {
      addOption(select, opt.getValue(), opt.getDisplay());
    });
  }

  createControl() {
    let ctl = document.createElement('select');
    ctl.size = 1;
    this.addOptions(ctl, this.dropDown);
    return ctl;
  }

  getControlValue(select) {
    return select.selectedIndex >= 0 ? select.options[select.selectedIndex].value : undefined;
  }

  getLength() {
    return boxSize(Math.max(this.dropDown.getLength() + 3, this.getHeaderLength()));
  }

  setValue(ctl, value) {
    ctl.value = value;
    for (let i = 0; i < ctl.options.length; i++) {
      let opt = ctl.options[i];
      if (opt.value === value) {
        opt.selected = true;
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
    this.width = 0;
  }

  setTableName(tableName) {
    this.tableName = tableName;
  }

  getHeading() {
    let td = document.createElement('div');
    td.className = 'table-heading-btn';

    let tableName = this.tableName;

    if (this.headLinkage) {
      this.headLinkage.forEach(linkage => {
        let btn = linkage(tableName);
        td.appendChild(btn);
      });
    } else {
      addText(td, ' ');
    }

    return td;
  }

  getLength() {
    return boxSize(this.length);
  }

  getHeaderLength() {
    return this.length;
  }

  getWidth() {
    return this.width;
  }

  setWidth(width) {
    this.width = width;
  }

  getControl(cell, entity, editMode) {
    cell.className = 'table-btn';

    let rowId = getCellRowId(cell);
    let tableName = this.tableName;

    let ctl = document.createElement('div');

    if (editMode && this.btnLinkage) {
      this.btnLinkage.forEach(linkage => {
        if (entity) {
          let btn = linkage(tableName, rowId);
          ctl.appendChild(btn);
        }
      });
    } else {
      addText(ctl, ' ');
    }

    cell.appendChild(ctl);

    return ctl;
  }

  isButtons() {
    return true;
  }
}

async function checkResponse(response) {
  if (200 <= response.status && response.status <= 202) {
    return response.json();
  } else if (response.status === 204) {
    return {entities: [], links: []};
  } else if (response.status === 400 || response.status === 500) {
    let errorMessage = response.status + ": " + response.statusText;

    try {
      let jsonData = await response.json();

      errorMessage = jsonData.errorCode + ": " + jsonData.userMessage;
    } catch(err) {
      errorMessage = await response.text();
    }

    console.log(errorMessage);

    throw new Error(errorMessage);
  } else {
    console.log(response.statusText);

    throw new Error(response.statusText);
  }
}

const addModal = () => {
  let modal = document.getElementById('modal');

  if (!modal) {
    modal = document.createElement('div');
    modal.id = 'modal';
    modal.className = 'modal';

    let content = document.createElement('div');
    content.id = 'modal-content';
    content.className = 'modal-content';

    modal.appendChild(content);
    addToEnd(modal);

    window.addEventListener('click', (e) => {
      if (e.target === modal) {
        modal.style.display = 'none';
      }
    }, false);
  }

  return modal;
};

const showModal = (content, big) => {
  let modal = addModal(big);

  let contents = document.getElementById('modal-content');
  contents.style.height = big ? '90%' : '40rem';
  contents.style.width = big ? '80%': '60rem';
  removeChildren(contents);

  contents.appendChild(content);
  modal.style.display = 'block';
};

const about = () => {
  fetch(siteRoot() + 'LICENSE')
  .then(response => response.text())
  .then(text => {
    let about = document.createElement('div');
    about.className = 'about';

    let heading = document.createElement('div');
    heading.className = 'about-header';
    about.appendChild(heading);

    let title = document.createElement('h2');
    addText(title, 'Über die ModellBahn');
    heading.appendChild(title);

    let body = document.createElement('div');
    body.className = 'about-body';
    about.appendChild(body);

    let area = document.createElement('textarea');
    area.value = text;
    area.readOnly = true;
    area.disabled = true;
    body.appendChild(area);

    let footer = document.createElement('div');
    footer.className = 'about-footer';
    about.appendChild(footer);

    showModal(about);
  })
  .catch(error => reportError(error));
};

const setActiveTab = (event, tabName) => {
  let tabContents = document.getElementsByClassName('tabContent');
  let tabLinks = document.getElementsByClassName('tabLinks');

  for (let i = 0; i < tabContents.length; i++) {
    tabContents[i].style.display = (tabContents[i].id === tabName) ? 'block' : 'none';
  }

  let linkName = tabName.replace('Tab', 'Link');
  for (let i = 0; i < tabLinks.length; i++) {
    tabLinks[i].className = (tabLinks[i].id === linkName) ? 'tabLinks active' : 'tabLinks';
  }
};

const addRow = (tableName) => {
  return getButton(undefined, 'add', Function(tableName + '.addRow()'));
};

const deleteRow = (tableName, row) => {
  return getButton(row, 'delete', Function(tableName + '.deleteRow(' + row + '.id)'));
};

const editRow = (tableName, row) => {
  return getButton(row, 'update', Function(tableName + '.editRow(' + row + '.id)'));
};

const newRow = (tableName) => {
  return getButton(undefined, 'add', Function(tableName + '.newRow()'));
};

const updateRow = (tableName, row) => {
  return getButton(row, 'save', Function(tableName + '.updateRow(' + row + '.id)'));
};

const gridButtonColumn = () => {
  return new ButtonColumn([addRow], [updateRow, deleteRow]);
};

const initHeader = (tableName, table, columns, paged) => {
  let isForm = paged === Paged.FORM;
  let header = document.createElement('div');
  header.id = tableName + '_thead';
  header.className = 'thead';
  table.append(header);

  let headRow = document.createElement('div');
  headRow.className = isForm ? 'form-head' : 'table-head';
  headRow.id = tableName + 'Head';
  header.append(headRow);

  columns.forEach(column => {
    if (isForm) {
      if (column.isButtons()) {
        let th = document.createElement('div');
        th.id = getCellId(getRowId(tableName, 0), column);
        th.className = 'table-btn';

        headRow.append(th);
      }
    } else {
      let th = column.getHeading();
      th.style.width = column.width;
      th.style.maxWidth = column.width;

      headRow.append(th);
    }
  });
};

const initRow = (tableName, row, body, paged, columns) => { 
  let isForm = paged === Paged.FORM;
  let tr = document.createElement('div');
  let rowId = getRowId(tableName, row);
  tr.className = isForm ? 'flex-container' : 'table-row';
  tr.id = rowId;
  body.append(tr);
  
  let key = document.createElement('input');
  key.type = 'hidden';
  key.id = getKeyId(rowId);
  key.className = isForm ? 'flex-control' : 'table-cell';
  tr.append(key);
  
  columns.forEach(column => {
    let td = document.createElement('div');
  
    if (!isForm || !column.isButtons()) {
      if (isForm) {
        td.className = 'flex-item';
  
        // TODO: change to label
        let th = column.getHeading();
        th.className = 'flex-label';
        th.style.width = maxLabel + 'ch';
        th.style.maxWidth = maxLabel + 'ch';
  
        td.append(th);
      }
  
      let tc = document.createElement('div');
      tc.id = getCellId(rowId, column);
      tc.className = isForm ? 'flex-control' : 'table-cell';
      tc.style.width = isForm ? column.getLength() + 'ch' : column.getWidth();
      tc.style.maxWidth = tc.style.width;
  
      addText(tc, ' ');
  
      if (isForm) {
        td.append(tc);
        tr.append(td);
      } else {
        tr.append(tc);
      }
    }
  });
};

const initBody = (tableName, table, pageSize, columns, paged, rowCount, maxLabel) => {
  let isForm = paged === Paged.FORM;
  let body = document.createElement('div');
  body.id = tableName + '_tbody';
  body.className = isForm ? 'flex-container' : 'tbody';
  table.append(body);

  let row;
  let maxRow = Math.max(rowCount, pageSize);
  for (row = 0; row < maxRow; row++) {
	initRow(tableName, row, body, paged, columns);
  }
};


const initFooter = (tableName, table, columns, paged) => {
  let isForm = paged === Paged.FORM;
  let footer = document.createElement('div');
  footer.id = tableName + '_tfoot';
  footer.className = 'tfoot';
  table.append(footer);

  let navRow = document.createElement('div');
  navRow.className = isForm ? 'form-foot' : 'table-foot';
  navRow.id = tableName + 'Foot';
  footer.append(navRow);

  if (isForm) {
    let tf = document.createElement('div');
    tf.className = 'table-footer';
    tf.style.width = '100%';
    tf.style.maxWidth = '100%';

    addText(tf, ' ');

    navRow.append(tf);
  } else {
    for (let i = 0; i < columns.length; i++) {
      let tf = document.createElement('div');
      if (i === 0) {
        tf.className = 'table-prev';
        tf.id = tableName + 'Prev';
      } else if (i === (columns.length - 1)) {
        tf.className = 'table-next';
        tf.id = tableName + 'Next';
      } else {
        tf.className = 'table-footer';
      }

      tf.style.width = columns[i].getHeaderLength() + 'ch';
      tf.style.maxWidth = columns[i].getHeaderLength() + 'ch';

      addText(tf, ' ');

      navRow.append(tf);
    }
  }
};

async function removeFile(deleteUrl) {
  await fetch(deleteUrl, {
    method: 'DELETE', 
    headers: {'Content-type': 'application/json'}
  })
  .then(response => checkResponse(response))
  .catch(error => reportError(error));
}

async function uploadFile(e, uploadUrl, fileData, ctl, img) {
  let body = new FormData();

  body.append('file', fileData);

  await fetch(uploadUrl, {
    method: 'PUT', 
    body: body
  })
  .then(response => checkResponse(response))
  .then(jsonData => setCtlValue(jsonData, ctl, img))
  .catch(error => reportError(error));
}

function readFile(uploadUrl, fileData, ctl, img) {
  const reader = new FileReader();
  reader.onload = (e) => {
    uploadFile(e, uploadUrl, fileData, ctl, img)
  };
  reader.onerror = (e) => {
    reader.abort();

    reportError('Problem beim Lesen der Datei ' + fileData + ': ' + e);
  };

  reader.readAsDataURL(fileData);
}
