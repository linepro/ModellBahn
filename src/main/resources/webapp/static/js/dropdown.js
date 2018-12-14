// module "dropdown.js"
"use strict";

class Option {
  constructor(display, value, image) {
    this.values = values;
    this.image = image;
    this.display = display;
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
  constructor(apiQuery, valueColumns, displayColumns, imageColumn) {
    this.apiQuery = apiQuery;
    this.valueColumns = valueColumns;
    this.displayColumns = displayColumns;
    this.imageColumn = imageColumn;
    this.length = 10;
    this.options = [];
  }

  loadOptions(jsonData) {
    let entities = jsonData.entities ? jsonData.entities : jsonData;
    let dropDown = this;

    entities.forEach(entity => {
      let display = dropDown.displayColumns(entity);
      let values = dropDown.valueColumns(entity);

      let option =
      dropDown.length = Math.max(display.length, dropDown.length);
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