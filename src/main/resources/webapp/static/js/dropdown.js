// module "dropdown.js"
"use strict";

class Option {
  constructor(display, value, image) {
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

    let options = entities.reduce((options, entity) => {
      let display = entity[dropDown.columns(0, 0)];
      dropDown.length = Math.max(display.length, dropDown.length);
      (options[key] = options[key] || []).push(entity[dropDown.columns(0, 1)]);
      return options;
      }, {});

    options.forEach((option) => {
      dropDown.options.push(new Option(display, values, dropDown.imageColumn(entity)));
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