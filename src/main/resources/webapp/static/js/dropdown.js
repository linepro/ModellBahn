// module "dropdown.js"
"use strict";

class Option {
  constructor(display, value) {
    this.display = display;
    this.value = value;
  }
}

class DropDown {
  constructor(apiQuery, valueColumns, displayColumns, compound) {
    this.apiQuery = apiQuery;
    this.valueColumns = valueColumns;
    this.displayColumns = displayColumns;
    this.compound = compound;
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
      let display = "";
      let value = select.compound ? [] : "";

      select.displayColumns.forEach(name => {
        display = display + " " + entity[name];
      });

      select.valueColumns.forEach(name => {
        if (select.compound) {
          value[name] = entity[name];
        } else {
          value = entity[name];
        }
      });

      display = display.trim();
      value = value.trim();

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