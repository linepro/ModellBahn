// module "dropdown.js"
"use strict";

class Option {
  constructor(value, display, image) {
    this.display = display;
    this.value = value;
    this.image = image;
    this.options = [];
  }

  getDisplay() {
    return this.display;
  }

  getValue(value) {
    return this.value;
  }

  getImage() {
    return this.image;
  }

  addOption(value) {
    this.options.push(value);
  }

  getOptions() {
    return this.options;
  }
}

class DropDown {
  constructor(apiQuery, keyExtractor, optionExtractor) {
    this.apiQuery = apiQuery;
    this.keyExtractor = keyExtractor;
    this.optionExtractor = optionExtractor;
    this.length = 10;
    this.options = [];
  }

  loadOptions(jsonData) {
    let entities = jsonData.entities ? jsonData.entities : jsonData;
    let dropDown = this;

    entities.forEach(entity => {
      let value = dropDown.keyExtractor(entity);
      let option = dropDown.optionExtractor(entity);

      dropDown.length = Math.max(option.getDisplay().length, dropDown.length);

      let current = dropDown.options.find(o => o.getValue() === value);

      if (!current) {
        dropDown.options.push(option);
      } else {
        option.getOptions().forEach(o => {
          current.addOption(o)
        });
      }
    });
  }

  getOptions() {
    return this.options;
  }

  async init() {
    let select = this;
    await fetch(select.apiQuery, {method: "get", headers: {"Content-type": "application/json"}})
      .then(response => checkResponse(response))
      .then(jsonData => select.loadOptions(jsonData))
      .catch(error => reportError(error));
  }
}