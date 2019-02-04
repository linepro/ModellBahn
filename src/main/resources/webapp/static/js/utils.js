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

const NavMenu = {
  BACK: 0,
  REF_DATA: 1,
  INVENTORY: 2,
  HOME: 3
};

const DATE_PATTERN = '^(18[2-9][0-9]|19[0-9]{2}|2[0-9]{3})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[0-1])$';

const URL_PATTERN = '^(?:(http[s]?):\\/\\/)?(\\w+(?:\\.\\w+)*)(?::(\\d+))?(?:\\/(\\w+(?:\\/|\\.\\w+)?))?$';

window.onerror = function (msg, url, lineNo, columnNo, error) {
  if (msg.toLowerCase().includes('script error')){
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

const addToStart = (element) => {
  let docBody = document.getElementsByTagName('BODY')[0];
  docBody.insertBefore(element, docBody.firstChild);
};

const reportError = (error) => {
  console.log(error);
  
  let alert = document.getElementById('alert-box');
  
  if (!alert) {
    alert = document.createElement('div');
    alert.id = 'alert-box';
    alert.className = 'alert';

    let closer = document.createElement('span');
    closer.className = 'closebtn';
    closer.onclick = (e) => { alert.style.display = 'none' };
    addText(closer, 'x');
    alert.appendChild(closer);

    let message = document.createElement('span');
    message.id = 'alert-message';
    alert.appendChild(message);

    let content = document.getElementById('data');
    content.insertBefore(alert, content.firstChild);
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

const getRowId = (tableId, i) => {
  return tableId + '_' + i;
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

const boxSize = (length) => {
  return Math.ceil(length/5)*5;
};

const blankControl = (cell) => {
  let ctl = document.createElement('input');

  ctl.type = 'text';
  ctl.disabled = 'true';
  ctl.readOnly = 'true';
  ctl.required = false;

  cell.appendChild(ctl);
};

class Column {
  constructor(heading, fieldName, getter, setter, editable, required, length) {
    this.heading   = heading;
    this.fieldName = fieldName;
    this.getter    = getter;
    this.setter    = setter;
    this.editable  = editable ? editable : Editable.NEVER;
    this.required  = required ? required : false;
    this.length    = Math.max(length ? length : heading.length, heading.length + 1);
    this.width     = 0;
  }

  setContext(grid, table) {
    this.grid  = grid;
    this.table = table;
  }

  getHeading(tagName) {
    let td = document.createElement(tagName);
    td.className = 'table-heading';
    addText(td, this.heading);
    return td;
  }

  entityValue(entity) {
    return this.getter(entity, this.fieldName);
  }

  getLength() {
    return boxSize(this.length);
  }

  getDisplayLength() {
    return this.getLength()+'ch';
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
    let inp = document.createElement('input');
    inp.autocomplete = 'off';
    return inp;
  }

  getControl(cell, entity, editMode) {
    let inp = this.createControl();

    let value;

    if (entity) {
      value = this.entityValue(entity);
    }

    if (value) {
      inp.setAttribute('data-value', value);
    }

    this.setValue(inp, value);

    if (value || entity) {
      inp.disabled = shouldDisable(this.editable, editMode);
    } else {
      inp.disabled = !(editMode === EditMode.ADD && this.editable !== Editable.NEVER);
    }

    inp.readOnly = inp.disabled;
    inp.required = this.required;

    cell.appendChild(inp);

    return inp;
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
    if (value) {
      ctl.value = value;
    }
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
    let chk = super.createControl();
    chk.type = 'checkbox';
    return chk;
  }

  getControlValue(chk) {
    return chk.checked;
  }

  setValue(chk, value) {
    chk.checked = value;
  }
}

class NumberColumn extends Column {
  constructor(heading, fieldName, getter, setter, editable, required, max = 255, min = 0, places = 0) {
    super(heading, fieldName, getter, setter, editable, required, Math.max(max.toString().length, heading.length));
    this.max = max;
    this.min = min;
    this.places = places;
  }

  createControl() {
    let num = super.createControl();
    num.type = 'number';
    num.min = this.min;
    num.max = this.max;
    num.step = this.places > 0 ? 1 / this.places : 1;
    return num;
  }

  getControlValue(num) {
    return num.value;
  }

  setValue(num, value) {
    if (value) {
      num.value = parseFloat(value).toFixed(this.places);
    }
  }
}

class PhoneColumn extends Column {
  constructor(heading, fieldName, getter, setter, editable, required) {
    super(heading, fieldName, getter, setter, editable, required, 10);
  }

  getControlValue(tel) {
    return tel.value;
  }

  createControl() {
    let tel = super.createControl();
    tel.type = 'tel';
    return tel;
  }
}

class TextColumn extends Column {
  constructor(heading, fieldName, getter, setter, editable, required, length, pattern) {
    super(heading, fieldName, getter, setter, editable, required, length);
    this.pattern = pattern;
  }

  createControl() {
    let txt = super.createControl();
    txt.type = 'text';
    txt.maxLength = this.length;
    txt.pattern = this.pattern;
    return txt;
  }
}

class URLColumn extends TextColumn {
  constructor(heading, fieldName, getter, setter, editable, required) {
    super(heading, fieldName, getter, setter, editable, required, 60, URL_PATTERN);
  }

  createControl() {
    let rul = super.createControl();
    rul.type = 'url';
    rul.class = 'table-url';
    rul.addEventListener('click', (e) => {
      if (rul.value) {
        window.open(rul.value, '_blank');
      }
    }, false);
    return rul;
  }
}

class DateColumn extends TextColumn {
  constructor(heading, fieldName, getter, setter, editable, required) {
    super(heading, fieldName, getter, setter, editable, required, 12, DATE_PATTERN);
  }

  createControl() {
    let dte = super.createControl();
    dte.addEventListener('click', (e) => {
      // TODO pop up date picker.
    }, false);
    return dte;
  }

}

class FileColumn extends Column {
  constructor(heading, fieldName, getter, mask, editable, required) {
    super(heading, fieldName, getter, undefined, editable, required, 20);
    this.mask = mask;
  }

  getControl(cell, entity, editMode) {
    let img = super.getControl(cell, entity, editMode);
    img.className = 'img-display';
    img.id = cell.id + '_img';

    if (entity) {
      img.addEventListener('click', (e) => {
    	  let img = e.target;
    	  let file = img.getAttribute('data-value');
    	  if (file) {
    		  this.showContent(file);
    		  }
    	  }, false);

      let bar = document.createElement('div');
      bar.className = 'img-button';
      cell.append(bar);
      
	    let add = getLink(entity.links, 'update-' + this.fieldName);
	    let grid = this.grid;
      let rowId = getCellRowId(cell);

	    if (add) {
	      let btn = getButton(add.href, 'add', (e) => { this.select(e, grid, rowId); });
	      btn.className = 'img-button';
	      btn.id = cell.id + '_add';
	      btn.firstChild.className = 'img-button';
	      bar.appendChild(btn);
	    }
	
	    let remove = getLink(entity.links, 'delete-' + this.fieldName);
	
	    if (remove) {
	      let btn = getButton(remove.href, 'delete', (e) => { this.remove(e, grid, rowId); });
	      btn.className = 'img-button';
        btn.id = cell.id + '_delete';
	      btn.firstChild.className = 'img-button';
	      btn.disabled = !img.getAttribute('data-value');
	      bar.appendChild(btn);
        this.delBtn = btn;
	    }
	
	    if (cell.firstChild) {
	      cell.removeChild(img);
	      cell.insertBefore(img, cell.firstChild);
	    }
    }
    
    return img;
  }

  createControl() {
    return document.createElement('img');
  }

  getControlValue(img) {
    return img.getAttribute('data-value');
  }

  defaultImage() {}

  setValue(img, value) {
    img.src = value ? value : this.defaultImage();
  }

  getDisplayLength() {
    return 'auto';
  }

  select(e, grid, rowId) {
    let btn = e.target;
    if (btn.tagName === 'IMG') {
      btn = btn.parentElement;
    }
    let link = btn.value;
    let bar = btn.parentElement;
    let cell = bar.parentElement;
    let file = document.createElement('input');
    file.type = 'file';
    file.accept = this.mask;
    file.multiple = false;
    file.setAttribute('data-update', link);
    cell.appendChild(file);
    file.style.display = 'none';
    file.click();
    file.addEventListener('change', (e) => { this.update(e, grid, rowId); }, false);
    file.addEventListener('click', (e) => { this.update(e, grid, rowId); }, false);
    file.addEventListener('blur', (e) => { this.update(e, grid, rowId); }, false);
  }

  remove(e, grid, rowId) {
    let btn = e.target;
    if (btn.tagName === 'IMG') {
      btn = btn.parentElement;
    }
    let link = btn.value;
    if (link) {
      removeFile(link, grid, rowId);
    }
  }

  update(e, grid, rowId) {
    let file = e.target;
    let fileData = file.files[0];
    let link = file.getAttribute('data-update');
    let cell = file.parentElement;
    cell.removeChild(file);
    if (fileData && link) {
      readFile(link, fileData, grid, rowId);
    }
  }

  showContent(file) {
    // Do nothimg.
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

  defaultImage() {
    return siteRoot() + getImgSrc('add-picture');
  }
}

class PDFColumn extends FileColumn {
  constructor(heading, fieldName, getter, editable, required) {
    super(heading, fieldName, getter, 'application/pdf', editable, required);
  }

  defaultImage() {
    return siteRoot() + getImgSrc('add-document');
  }

  showContent(pdf) {
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

    viewer.load(pdf);
    
    showModal(div, true);
  }
}

const closeAutoLists = (elmnt = document) => {
  let autoComp = elmnt.getElementsByClassName( 'autocomplete-list');
  for (let i = 0; i < autoComp.length; i++) {
    removeChildren(autoComp[i]);
    autoComp[i].parentNode.removeChild(autoComp[i]);
  }
};

document.addEventListener('click', (e) => { closeAutoLists() }, false);

class SelectColumn extends Column {
  constructor(heading, fieldName, getter, setter, dropDown, editable, required, length, dropSize = 5) {
    super(heading, fieldName, getter, setter, editable, required, length);
    this.dropDown = dropDown;
    this.dropSize = dropSize;
  }

  getControl(cell, entity, editMode) {
    let sel = super.getControl(cell, entity, editMode);

    if (!sel.disabled) {
      sel.addEventListener('click', (e) => { this.open(e); }, false);
      sel.addEventListener('input', (e) => { this.open(e); }, false);
      sel.addEventListener('keydown', (e) => { this.keydown(e); }, false);
      sel.classList.add('autocomplete');
    }

    return sel;
  }

  getControlValue(sel) {
    return sel.getAttribute('data-value');
  }

  setValue(sel, value) {
    if (value) {
      this.dropDown.options.forEach((o) => {
        if (o.value === value) {
          sel.value = o.display;
        }
      });
    }
  }

  getLength() {
    return Math.max(this.dropDown.getLength(), this.getHeaderLength());
  }

  caption(txt, o) {
    return o.display;
  }

  options(txt) {
    // Filter only for AutoComplete...
    return this.dropDown.options;
  }

  open(e) {
    let sel = this;
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
    sel.options(inp.value).forEach((o) => {
      let autoItem = document.createElement('div');
      autoItem.setAttribute('data-value', o.value);
      autoItem.className = 'autocomplete-items';
      autoItem.style.top = (dims.value * i) + dims.units;
      addText(autoItem, sel.caption(inp.value, o));
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
    inp.setAttribute('data-value', opt.getAttribute('data-value'));

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

  addOptions(select, dropDown) {
    if (!this.required) {
      addOption(select, undefined, '(nicht benötigt)');
    }

    dropDown.options.forEach(opt => {
      addOption(select, opt.getValue(), opt.getDisplay());
    });
  }

  createControl() {
    let sel = document.createElement('select');
    sel.size = 1;
    this.addOptions(sel, this.dropDown);
    sel.selectedIndex = -1;
    return sel;
  }

  getControlValue(select) {
    return select.selectedIndex >= 0 ? select.options[select.selectedIndex].value : undefined;
  }

  getLength() {
    return boxSize(Math.max(this.dropDown.getLength() + 3, this.getHeaderLength()));
  }

  setValue(sel, value) {
    if (value) {
      sel.value = value;
      for (let i = 0; i < sel.options.length; i++) {
        let opt = sel.options[i];
        if (opt.value === value) {
          opt.selected = true;
          return;
        }
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

  setContext(grid, table) {
    this.grid = grid;
    this.table = table;
  }

  getHeading(tagName) {
    let grid  = this.grid;
    let td    = document.createElement(tagName);

    td.className = 'table-heading-btn';

    if (this.headLinkage) {
      this.headLinkage.forEach(linkage => {
        let btn = linkage(grid);
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
    let rowId = getCellRowId(cell);
    let grid  = this.grid;

    let ctl = document.createElement('div');

    if (editMode && this.btnLinkage) {
      this.btnLinkage.forEach(linkage => {
        if (entity) {
          let btn = linkage(grid, rowId);
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
  let clone = response.clone();
  if (200 <= response.status && response.status <= 202) {
    return response.json();
  } else if (response.status === 204) {
    return {entities: [], links: []};
  } else if (response.status === 400 || response.status === 500) {
    let errorMessage = response.status + ': ' + response.statusText;

    try {
      let jsonData = await response.json();

      errorMessage = jsonData.errorCode + ': ' + jsonData.userMessage;
    } catch(err) {
      errorMessage = await clone.text();
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
  let modal = addModal();

  let contents = document.getElementById('modal-content');
  //contents.style.height = big ? '90%' : '40rem';
  //contents.style.width = big ? '80%': '60rem';
  removeChildren(contents);

  //let closer = document.createElement('span');
  //closer.className = 'closebtn';
  //closer.onclick = (e) => { modal.style.display = 'none' };
  //addText(closer, 'x');
  //contents.appendChild(closer);

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

  resizeTables();

  let linkName = tabName.replace('Tab', 'Link');
  for (let i = 0; i < tabLinks.length; i++) {
    tabLinks[i].className = (tabLinks[i].id === linkName) ? 'tabLinks active' : 'tabLinks';
  }
};

const addRow = (grid) => {
  return getButton(undefined, 'add', (e) => { grid.addRow() });
};

const deleteRow = (grid, rowId) => {
  return getButton(rowId, 'delete', (e) => { grid.deleteRow(rowId) });
};

const editRow = (grid, rowId) => {
  return getButton(rowId, 'update', (e) => { grid.editRow(rowId) });
};

const newRow = (grid) => {
  return getButton(undefined, 'add', (e) => { grid.newRow() });
};

const updateRow = (grid, rowId) => {
  return getButton(rowId, 'save', (e) => { grid.updateRow(rowId) });
};

const gridButtonColumn = () => {
  return new ButtonColumn([addRow], [updateRow, deleteRow]);
};

const setWidths = (element, width) => {
  element.width = width;  
  //element.maxWidth = width;  
  element.style.width = width;  
  element.style.maxWidth = width;  
};

const initializeForm = (grid, table) => {
  let maxLabel = 10;

  grid.columns.forEach(column => {
    column.setContext(grid, table);
    maxLabel = Math.max(column.getHeaderLength(), maxLabel);
  });

  grid.maxlabel = maxLabel;
};

const initializeFormHeader = (grid, table) => {
  let header = document.createElement('div');
  header.id = table.id + '_thead';
  header.className = 'fhead';
  table.append(header);

  let headRow = document.createElement('div');
  headRow.className = 'form-head';
  headRow.id = table.id + 'Head';
  header.append(headRow);

  grid.columns.forEach(column => {
    if (column.isButtons()) {
      let th = document.createElement('div');
      th.id = getCellId(getRowId(table.id, 0), column);
      th.className = 'form-heading-btn';
      headRow.append(th);
    }
  });
};

const initializeFormBody = (grid, table, maxLabel) => {
  let body = document.createElement('div');
  body.id = table.id + '_tbody';
  body.className = 'flex-container';
  table.append(body);

  let maxRow = Math.max(grid.rowCount, grid.pageSize);
  for (let rowNum = 0; rowNum < maxRow; rowNum++) {
    initializeFormRow(grid, table); 
  }
};

const initializeFormRow = (grid, table) => {
  let body = document.getElementById(table.id + '_tbody');

  let tr = document.createElement('div');
  let rowId = getRowId(table.id, 0);
  tr.className = 'flex-container';
  tr.id = rowId;
  body.append(tr);
  
  let key = document.createElement('input');
  key.type = 'hidden';
  key.id = getKeyId(rowId);
  key.className = 'flex-control';
  tr.append(key);
  
  grid.columns.forEach(column => {
    let td = document.createElement('div');
  
    if (!column.isButtons()) {
      td.className = 'flex-item';

      // TODO: change to label
      let th = column.getHeading('div');
      th.className = 'flex-label';
      setWidths(th, grid.maxLabel + 'ch');

      td.append(th);
  
      let tc = document.createElement('div');
      tc.id = getCellId(rowId, column);
      tc.className = 'flex-control';
      setWidths(tc, column.getDisplayLength());
  
      addText(tc, ' ');
  
      td.append(tc);
      tr.append(td);
    }
  });
};

const initializeFormFooter = (grid, table) => {
  let footer = document.createElement('div');
  footer.id = table.id + '_tfoot';
  footer.className = 'ffoot';
  table.append(footer);

  let navRow = document.createElement('div');
  navRow.className = 'form-foot';
  navRow.id = table.id + 'Foot';
  footer.append(navRow);

  let tf = document.createElement('div');
  tf.className = 'form-footer';
  setWidths(tf, '100%');

  addText(tf, ' ');

  navRow.append(tf);
};

const resizeGroup = (element, newWidths, tableWidth, rowWidth) => {
  setWidths(element, tableWidth);
  let childTag = element.tagName === 'THEAD' ? 'TH' : 'TD';
  let rows = element.getElementsByTagName('TR');
  for (let i = 0; i < rows.length; i++) {
    setWidths(rows[i], rowWidth);
    let cells = rows[i].getElementsByTagName(childTag);
    for (let j = 0; j < cells.length; j++) {
      setWidths(cells[j], newWidths[j]);
    }
  }
};

const resizeTables = (element = document) => {
  let tables = element.getElementsByTagName('TABLE');
  for (let i = 0; i < tables.length; i++) {
    let rect = tables[i].getBoundingClientRect();
    setWidths(tables[i].getElementsByTagName('TBODY')[0], rect.width+'px');
  }
};

window.addEventListener('resize', (e) => { resizeTables() }, true);

const initializeTable = (grid, table) => {
  if (grid.name && !grid.parent) {
    let caption = document.createElement('caption');
    addText(caption, grid.name);
    table.appendChild(caption);
  }

  setWidths(table, '100%');

  let colGroup = document.createElement('colgroup');
  setWidths(colGroup, '100%');

  let totalLength = 0;
  grid.columns.forEach(column => {
    column.setContext(grid, table);
    totalLength += column.getLength();
  });

  grid.columns.forEach(column => {
    let col = document.createElement('col');
    let colWidth = Math.floor((column.getLength() * 100) / totalLength)+'%';
    column.setWidth(colWidth);
    setWidths(col, column.getWidth());
    colGroup.append(col);
  });

  table.appendChild(colGroup);
};

const initializeTableHeader = (grid, table) => {
  let header = document.createElement('thead');
  header.id = table.id + '_thead';
  header.className = 'thead';
  table.append(header);

  let headRow = document.createElement('tr');
  headRow.id = table.id + 'Head';
  headRow.className = 'table-head';
  header.append(headRow);

  grid.columns.forEach(column => {
    let th = column.getHeading('th');
    th.className = column.isButtons() ? 'table-heading-btn' : 'table-heading';
    setWidths(th, column.getWidth());

    headRow.append(th);
  });
};

const initializeTableBody = (grid, table) => {
  let body = document.createElement('tbody');
  let rect = table.getBoundingClientRect();
  body.id = table.id + '_tbody';
  body.className = 'tbody';
  setWidths(body, rect.width+'px');
  table.append(body);

  let maxRow = Math.max(grid.rowCount, grid.pageSize);
  for (let rowNum = 0; rowNum < maxRow; rowNum++) {
    initializeTableRow(grid, table);
  }
};

const initializeTableRow = (grid, table) => {
  let body = table.getElementsByTagName('TBODY')[0];
  let rows = body.getElementsByTagName('TR');

  let tr = document.createElement('tr');
  let rowId = getRowId(table.id, rows.length);
  tr.className = 'table-row';
  tr.id = rowId;
  body.append(tr);
  
  let key = document.createElement('input');
  key.type = 'hidden';
  key.id = getKeyId(rowId);
  key.className = 'table-cell';
  tr.append(key);
  
  grid.columns.forEach(column => {
    let td = document.createElement('td');
    td.id = getCellId(rowId, column);
    td.className = column.isButtons() ? 'table-cell-btn' : 'table-cell';
    setWidths(td, column.getWidth());

    addText(td, ' ');

    tr.append(td);
  });
};

const initializeTableFooter = (grid, table) => {
  let footer = document.createElement('tfoot');
  footer.id = table.id + '_tfoot';
  footer.className = 'tfoot';
  table.append(footer);

  let navRow = document.createElement('tr');
  navRow.id = table.id + 'Foot';
  navRow.className = 'table-foot';
  footer.append(navRow);

  grid.columns.forEach(column => {
    let tf = document.createElement('td');
    tf.className = 'table-footer';
    tf.width = column.getWidth();
    setWidths(tf, column.getWidth());

    addText(tf, ' ');

    navRow.append(tf);
  });

  navRow.firstChild.className = 'table-prev';
  navRow.firstChild.id = table.id + 'Prev';
  
  navRow.lastChild.className = 'table-next';
  navRow.lastChild.id = table.id + 'Next';
};

const initializeGrid = (grid, table) => {
  if (grid.paged === Paged.FORM) {
    let maxLabel = initializeForm(grid, table);

    grid.columns.forEach(column => {
      maxLabel = Math.max(column.getHeaderLength(), maxLabel);
    });
    
    initializeFormHeader(grid, table);

    initializeFormBody(grid, table, maxLabel);

    initializeFormFooter(grid, table);
  } else {
    initializeTable(grid, table);

    initializeTableHeader(grid, table);

    initializeTableBody(grid, table);

    initializeTableFooter(grid, table);
  }
};

async function removeFile(deleteUrl, grid, rowId) {
  await fetch(deleteUrl, {
    method: 'DELETE', 
    headers: {'Content-type': 'application/json'}
  })
  .then(response => checkResponse(response))
  .then(jsonData => grid.renderUpdate(jsonData, rowId))
  .catch(error => reportError(error));
}

async function uploadFile(e, uploadUrl, fileData, grid, rowId) {
  let body = new FormData();

  body.append('file', fileData);

  await fetch(uploadUrl, {
    method: 'PUT', 
    body: body
  })
  .then(response => checkResponse(response))
  .then(jsonData => grid.renderUpdate(jsonData, rowId))
  .catch(error => reportError(error));
}

const readFile = (uploadUrl, fileData, grid, rowId) => {
  const reader = new FileReader();

  reader.onload = (e) => {
    uploadFile(e, uploadUrl, fileData, grid, rowId)
  };

  reader.onerror = (e) => {
    reader.abort();

    reportError('Problem beim Lesen der Datei ' + fileData + ': ' + e);
  };

  reader.readAsDataURL(fileData);
};

const navLink = (ul, title, href, action, id) => {
  if (document.location.href === href) return;

  let li = document.createElement('li');

  let a = document.createElement('a');
  if (id) a.id = id;
  a.className = 'nav-button';
  a.href = href;
  if (action) a.addEventListener('click', action);
  addText(a, title);
  li.appendChild(a);

  ul.appendChild(li);

  return li;
};

const addNavBar = (menuStyle) => {
  let header = document.getElementsByTagName('HEADER')[0];
  removeChildren(header);

  let nav = document.createElement('nav');
  header.appendChild(nav);

  let ul = document.createElement('ul');
  ul.className = 'nav';
  nav.appendChild(ul);

  if (menuStyle !== NavMenu.HOME) {
    navLink(ul, 'Home', siteRoot().replace('/static/', '/index.html'));
    navLink(ul, 'Back', '#', (e) => { history.back() });
  }

  if (menuStyle === NavMenu.REF_DATA || menuStyle === NavMenu.HOME) {
    navLink(ul, 'Aufbau', siteRoot()+'aufbauten.html');
    navLink(ul, 'Antrieb', siteRoot()+'antrieben.html');
    navLink(ul, 'Bahnverwaltung', siteRoot()+'bahnverwaltungen.html');
    navLink(ul, 'Decoder Typ', siteRoot()+'decoderTypen.html');
    navLink(ul, 'Hersteller', siteRoot()+'herstellern.html');
    navLink(ul, 'Katogorie', siteRoot()+'kategorien.html');
    navLink(ul, 'Kupplung', siteRoot()+'kupplungen.html');
    navLink(ul, 'Land', siteRoot()+'lander.html');
    navLink(ul, 'Licht', siteRoot()+'lichten.html');
    navLink(ul, 'Maßstab', siteRoot()+'massstaben.html');
    navLink(ul, 'Motor Typ', siteRoot()+'motorTypen.html');
    navLink(ul, 'Protokoll', siteRoot()+'protokollen.html');
    navLink(ul, 'SonderModell', siteRoot()+'sonderModellen.html');
    navLink(ul, 'Spurwiete', siteRoot()+'spurweiten.html');
    navLink(ul, 'Steuerung', siteRoot()+'steuerungen.html');
    navLink(ul, 'Wahrung', siteRoot()+'wahrungen.html');
    navLink(ul, 'Zug Typ', siteRoot()+'zugtypen.html');
  }

  if (menuStyle === NavMenu.INVENTORY || menuStyle === NavMenu.HOME) {
    navLink(ul, 'Produkt', siteRoot()+'produkten.html');
    navLink(ul, 'Vorbild', siteRoot()+'vorbilder.html');
    navLink(ul, 'Artikel', siteRoot()+'artikelen.html');
    navLink(ul, 'Decoder', siteRoot()+'decoderen.html');
    navLink(ul, 'Zug', siteRoot()+'zugen.html');
  }

  let hr = document.createElement('hr');
  hr.className = 'highlight';
  nav.appendChild(hr);
};

const addFooter = () => {
  let div = document.getElementsByTagName('FOOTER')[0];
  removeChildren(div);

  let hr = document.createElement('hr');
  hr.className = 'highlight';
  div.appendChild(hr);

  let ul = document.createElement('ul');
  ul.className = 'footer';
  div.appendChild(ul);

  let li = document.createElement('li');
  addText(li, 'Copyright © 2018, 2019 John & Andrew Goff');
  ul.appendChild(li);

  navLink(ul,'Über', '#', about, 'license');
};

const createStyle = () => {
  
}