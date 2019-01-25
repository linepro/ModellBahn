// module "dropdown.js"
"use strict";

class DropOption {
  constructor(value, display, tooltip, image) {
    this.display = display;
    this.value = value;
    this.tooltip = tooltip;
    this.image = image;
  }

  getDisplay() {
    return this.display;
  }

  getValue() {
    return this.value;
  }

  getTooltip() {
    return this.tooltip;
  }

  getImage() {
    return this.image;
  }
  
  getLength() {
	return this.getDisplay().length;
  }
}

class DropDown {
  constructor(apiQuery, optionExtractor) {
    this.apiQuery = apiQuery;
    this.optionExtractor = optionExtractor;
    this.length = 0;
    this.options = [];
  }

  loadOptions(jsonData) {
    let entities = jsonData.entities ? jsonData.entities : jsonData;
    let dropDown = this;
    let length = 0;
    
    entities.forEach(entity => {
      let opt = dropDown.optionExtractor(entity);

      dropDown.options.push(opt);

      length = Math.max(opt.getLength(), length);
    });
    
    dropDown.length = length;
  }

  getOptions() {
    return this.options;
  }

  async init() {
    let select = this;
    await fetch(select.apiQuery, {
      method: "GET",
      headers: {"Content-type": "application/json"}
    })
    .then(response => checkResponse(response))
    .then(jsonData => select.loadOptions(jsonData))
    .catch(error => reportError(error));
  }
  
  getLength() {
	 return this.length;
  }
}