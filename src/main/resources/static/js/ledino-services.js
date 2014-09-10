var ledinoServices = angular.module('ledinoServices', [ 'ngResource' ]);

ledinoServices.service('ManualModeService', [ "$resource", function($resource) {
	return {
		getState : function(callback) {
			var result = null;
			return $resource('/arduino/state').get(function(data) {
				callback(data);
			});
		},
		setColors : function(colors, callback) {
			console.log("Setting colors to:")
			console.log(colors);
			$resource('/arduino/levels', colors).save(function(data) {
				if(callback != null){
					callback(data);	
				}
				
			});
		}
	}
} ]);

ledinoServices.service('ColorHelper', [ function() {
	return {
		rgbToJson : function(color) {
			var matchColors = /rgb\((\d{1,3}), (\d{1,3}), (\d{1,3})\)/;
			var match = matchColors.exec(color);
			return {
				RED : match[1],
				GREEN : match[2],
				BLUE : match[3]
			}
		},
		jsonToRgb : function(color) {
			return 'rgb(' + color.RED + ', ' + color.GREEN + ', ' + color.BLUE
					+ ')';
		}
	}
} ]);

ledinoServices.service('JQueryHelper', [ function() {
	return {
		body: $("body"),
		setPageColor : function(color) {
			this.body.css("background-color",color)	
		}
	}
} ]);