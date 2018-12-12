// module "dropdown.js"
"use strict";

class Option {
  constructor(display, value) {
    this.display = display;
    this.value = value;
  }
}

class DropDown {
  constructor(apiQuery, valueColumn, displayColumn) {
    this.apiQuery = apiQuery;
    this.valueColumn = valueColumn;
    this.displayColumn = displayColumn;
    this.length = 10;
    this.options = [];
  }

  addOptions(select, dropSize, initial) {
    while (select.length) {
      select.remove(0);
    }

    select.size = dropSize;

    let i = 0;
    this.options.forEach(option => {
      let opt = document.createElement("option");
      opt.value = option.value;
      opt.text = option.display;

      select.add(opt);

      if (initial && initial === option.display) {
        select.selectedIndex = i;
      }

      i++;
    });
  }

  loadOptions(jsonData) {
    let select = this;
    let entities = jsonData.entities ? jsonData.entities : jsonData;

    entities.forEach(entity => {
      let display = entity[this.displayColumn];
      let value = entity[this.valueColumn];

      select.length = Math.max(select.length, display.length);
      select.options.push(new Option(display, value));
    });
  }

  async init() {
    let select = this;
    await fetch(select.apiQuery, {method: "get", headers: {"Content-type": "application/json"}})
      .then(response => checkResponse(response))
      .then(jsonData => select.loadOptions(jsonData))
      .catch(error => reportError(error));
  }
}