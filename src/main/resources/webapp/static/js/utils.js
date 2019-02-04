// module 'utils.js'
'use strict';

const NavMenu = {
  BACK: 0,
  REF_DATA: 1,
  INVENTORY: 2,
  HOME: 3
};

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
  
const apiRoot = () => {
  return location.protocol + '//' + location.host + '/ModellBahn/api/';
};

const siteRoot = () => {
  return location.protocol + '//' + location.host + '/ModellBahn/static/';
};

const getMessage = (message, substitutions) => {
  return message; // browser.i18n.getMessage(message.toUpperCase(), substitutions);
}

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

  btn.value = getMessage(value.toUpperCase());
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
    addText(title, getMessage('ABOUT'));
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

const setWidths = (element, width) => {
  element.width = width;  
  //element.maxWidth = width;  
  element.style.width = width;  
  element.style.maxWidth = width;  
};

const resizeTables = (element = document) => {
  let tables = element.getElementsByTagName('TABLE');
  for (let i = 0; i < tables.length; i++) {
    let rect = tables[i].getBoundingClientRect();
    setWidths(tables[i].getElementsByTagName('TBODY')[0], rect.width+'px');
  }
};

window.addEventListener('resize', (e) => { resizeTables() }, true);

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

    reportError(getMessage('BADFILE', [fileData, e]));
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
    navLink(ul, getMessage('HOME'), siteRoot().replace('/static/', '/index.html'));
    navLink(ul, getMessage('BACK'), '#', (e) => { history.back() });
  }

  if (menuStyle === NavMenu.REF_DATA || menuStyle === NavMenu.HOME) {
    navLink(ul, getMessage('AUFBAU'), siteRoot()+'aufbauten.html');
    navLink(ul, getMessage('ANTRIEB'), siteRoot()+'antrieben.html');
    navLink(ul, getMessage('BAHNVERWALTUNG'), siteRoot()+'bahnverwaltungen.html');
    navLink(ul, getMessage('DECODER_TYP'), siteRoot()+'decoderTypen.html');
    navLink(ul, getMessage('HERSTELLER'), siteRoot()+'herstellern.html');
    navLink(ul, getMessage('KATOGORIE'), siteRoot()+'kategorien.html');
    navLink(ul, getMessage('KUPPLUNG'), siteRoot()+'kupplungen.html');
    navLink(ul, getMessage('LAND'), siteRoot()+'lander.html');
    navLink(ul, getMessage('LICHT'), siteRoot()+'lichten.html');
    navLink(ul, getMessage('MASSSTAB'), siteRoot()+'massstaben.html');
    navLink(ul, getMessage('MOTOR_TYP'), siteRoot()+'motorTypen.html');
    navLink(ul, getMessage('PROTOKOLL'), siteRoot()+'protokollen.html');
    navLink(ul, getMessage('SONDERMODELL'), siteRoot()+'sonderModellen.html');
    navLink(ul, getMessage('SPURWIETE'), siteRoot()+'spurweiten.html');
    navLink(ul, getMessage('STEUERUNG'), siteRoot()+'steuerungen.html');
    navLink(ul, getMessage('WAHRUNG'), siteRoot()+'wahrungen.html');
    navLink(ul, getMessage('ZUG_TYP'), siteRoot()+'zugtypen.html');
  }

  if (menuStyle === NavMenu.INVENTORY || menuStyle === NavMenu.HOME) {
    navLink(ul, getMessage('PRODUKT'), siteRoot()+'produkten.html');
    navLink(ul, getMessage('VORBILD'), siteRoot()+'vorbilder.html');
    navLink(ul, getMessage('ARTIKEL'), siteRoot()+'artikelen.html');
    navLink(ul, getMessage('DECODER'), siteRoot()+'decoderen.html');
    navLink(ul, getMessage('ZUG'), siteRoot()+'zugen.html');
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
  addText(li, getMessage('COPYRIGHT'));
  ul.appendChild(li);

  navLink(ul, getMessage('ABOUT'), '#', about, 'license');
};

const createStyle = () => {
  
}