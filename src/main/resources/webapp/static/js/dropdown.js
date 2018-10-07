// module "dropdown.js"
"use strict"

class Option {
  constructor(display,value) {
    this.display = display;
    this.value = value;
  }
}

class DropDown {
  constructor(apiQuery, valueColumns, displayColumns, wrapped) {
    this.apiQuery = apiQuery;
    this.valueColumns = valueColumns;
    this.displayColumns = displayColumns;
    this.wrapped = wrapped;
    this.length  = 10;
    this.options = new Array();
  }

  addOptions(select, dropSize, initial) {
    while(select.length) {
      select.remove(0);
    }

    select.size = dropSize;

    var i = 0;
    this.options.forEach(function(option) {
      var opt = document.createElement("option");
      opt.value = option.value;
      opt.text  = option.display;
      select.add(opt);
      
      if (initial && initial == option.display) {
        select.selectedIndex = i;
      }
      
      i++;
    });
  }

  loadOptions(jsonData) {
    var select = this;
    var entities = jsonData.entities ? jsonData.entities : jsonData; 
    entities.forEach(function(entity) {
      var display = "";
      var value = "";

      select.displayColumns.forEach(function(name) {
        display = display + " " + entity[name];
      });

      select.valueColumns.forEach(function(name) {
        if (select.wrapped) {
          value = (value ? "" : value + ", ") + name + ": " + entity[name];
        } else {
          value = entity[name];
        }
        });

      display = display.trim();
      value = value.trim();

      if (select.wrapped) {
        value = "{" + value.trim() + "}";
      }

      select.length = Math.max(select.length, display.length);
      select.options.push(new Option(display,value));
    });
  }

  async init() {
    var select = this;
    await fetch(select.apiQuery, { method: "get", headers: { "Content-type": "application/json" } })
      .then(response => checkResponse(response))
      .then(jsonData => select.loadOptions(jsonData))
      .catch(error => reportError(error));
  }
}