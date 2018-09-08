// module "utils.js"
"use strict"

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

function addButton(lnk, cell, tableName, action) {
	removeChildren(cell);

	if (lnk) {
		var btn = document.createElement("button");
		btn.setAttribute("value", lnk.href);
		btn.setAttribute("onclick", tableName + "." + action);
		btn.style.width = "65px";
		cell.appendChild(btn);
		
		var img = document.createElement("img");
		img.alt = lnk.rel;
		img.src = "img/" + lnk.rel + ".png";
		img.style.height = "18px";
		img.style.width = "18px";
		btn.appendChild(img);
	} 
}

function addText(cell, text) { 
	cell.appendChild(document.createTextNode(text));
}

class Column {
	constructor(heading, binding, inputType, select) {
		this.heading = heading;
		this.binding = binding;
		this.inputType = inputType;
		this.select = select;
	}
}