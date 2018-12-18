// module "dropdown.js"
"use strict";

class Option {
  constructor(display, values, image) {
    this.display = display;
    this.values = values;
    this.image = image;
  }

  getDisplay() {
    return this.display;
  }

  getValues() {
    return this.values;
  }

  getImage() {
    return this.image;
  }
}

class DropDown {
  constructor(apiQuery, columns, imageColumn) {
    this.apiQuery = apiQuery;
    this.columns = columns;
    this.imageColumn = imageColumn;
    this.length = 10;
    this.options = [];
  }

  loadOptions(jsonData) {
    let entities = jsonData.entities ? jsonData.entities : jsonData;
    let dropDown = this;
    
    let menu = entities.reduce((choices, entity) => {
      let displayExtractor = dropDown.columns[0][0];
      let display = displayExtractor(entity);
      let valueExtractor = dropDown.columns[0][1];
      let value = valueExtractor(entity);
      dropDown.length = Math.max(display.length, dropDown.length);
      (choices[display] = choices[display] || []).push(value);
      return choices;
      }, {});

    Object.keys(menu).forEach((display) => {
      dropDown.options.push(new Option(display, menu[display]));
    });
  }

  getOptions() {
    return options;
  }

  async init() {
    let select = this;
    await fetch(select.apiQuery, {method: "get", headers: {"Content-type": "application/json"}})
      .then(response => checkResponse(response))
      .then(jsonData => select.loadOptions(jsonData))
      .catch(error => reportError(error));
  }
}