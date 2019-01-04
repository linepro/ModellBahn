// module "dropdown.js"
"use strict";

class Option {
  constructor(display, value) {
    this.display = display;
    this.values = value;
  }

  getDisplay() {
    return this.display;
  }

  addValue(value) {
    this.values.push(value);
  }

  getValues() {
    return this.values;
  }

  getImage() {
    return this.image;
  }

  setImage(image) {
    this.image = image;
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
      let display = dropDown.keyExtractor(entity);
      let option = dropDown.optionExtractor(entity);
      dropDown.length = Math.max(display.length, dropDown.length);

      let current = dropDown.options.find(o => o.getDisplay() === display);

      if (!current) {
        dropDown.options.push(option);
      } else {
        option.getValues().forEach(o => {
          current.addValue(0)
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