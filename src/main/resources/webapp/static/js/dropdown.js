// module 'utils.js'
'use strict'

class Option {
	constructor(display,value) {
		this.display = display;
		this.value = value;
	}
}

class DropDown {
	constructor(apiQuery, valueColumns, displayColumns) {
		this.apiQuery = apiQuery;
		this.valueColumns = valueColumns;
		this.displayColumns = displayColumns;
		this.options = new Array();
	}

	addOptions(select, dropSize, initial) {
		while(select.length) {
			select.remove(0);
		}

		select.size = dropSize;

		var i = 0;
		this.options.forEach(function(option) {
			var opt = document.createElement('option');
			opt.value = option.value;
			opt.text  = option.display;
			select.add(opt);
			
			if (initial && initial == option.display) {
				select.selectedIndex = i;
			}
			
			i++;
		});
	}

	loadOptions(jsonData, textStatus, jqXHR) {
		if (jqXHR.status == 200) {
			var select = this;
			var entities = jsonData.entities ? jsonData.entities : jsonData; 
			entities.forEach(function(entity) {
				var display = '';
				var value;

				select.displayColumns.forEach(function(name) {
					display = display + ' ' + entity[name];
				});

				select.valueColumns.forEach(function(name) {
					value = (value ? '' : value + ', ') + name + ': ' + entity[name];
				});

				display = display.trim();
				value =  '{' + value.trim() + '}';

				select.options.push(new Option(display,value));
			});
		}
	}

	init() {
		var select = this;
		$.getJSON(this.apiQuery, function( jsonData, textStatus, jqXHR ) { select.loadOptions(jsonData, textStatus, jqXHR);})
		 .fail( function( jqXHR, textStatus, errorThrown ) { reportError(  jqXHR, textStatus, errorThrown  );});
	}
}