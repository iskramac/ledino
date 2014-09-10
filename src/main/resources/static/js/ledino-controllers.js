var ledinoControllers = angular.module('ledinoControllers', []);

ledinoControllers.controller('menuController', function($scope) {
	$scope.pages = [ {
		name : "Dashboard",
		url : "#/dashboard"
	}, {
		name : "Manual mode",
		url : "#/mode/manual"
	}, {
		name : "Scheduler mode",
		url : "#/mode/scheduler"
	}, ];
});

ledinoControllers.controller('pageController', [ '$scope', '$http',
		'ManualModeService', 'ColorHelper', 'JQueryHelper',
		function($scope, $http, ManualModeService, ColorHelper, JQueryHelper) {
			ManualModeService.getState(function(data) {

				var color = ColorHelper.jsonToRgb(data.ledLevelMap);
				console.log("Initialized page, current color:" + color)
				JQueryHelper.setPageColor(color);
			});
		} ]);

ledinoControllers.controller('manualModeController', [ '$scope', '$http',
		'ManualModeService', 'ColorHelper', 'JQueryHelper',
		function($scope, $http, ManualModeService, ColorHelper, JQueryHelper) {
			ManualModeService.getState(function(data) {
				$scope.state = data;
				$scope.color = {};
				$scope.color = data.ledLevelMap;
				$scope.colorpickerValue = ColorHelper.jsonToRgb($scope.color);
				JQueryHelper.setPageColor($scope.colorpickerValue)
			});

			$scope.colorpickerChangeHandler = function(e) {
				JQueryHelper.setPageColor($scope.colorpickerValue)
				$scope.color = ColorHelper.rgbToJson($scope.colorpickerValue)
				$scope.sync()
			}

			$scope.sync = function() {
				ManualModeService.setColors($scope.color, function(data) {
					$scope.color = data.ledLevelMap;
				});
			}
		} ]);

ledinoControllers.controller('dashboardController', [
		'$scope',
		'ManualModeService',
		'ColorHelper',
		'JQueryHelper',
		function($scope, ManualModeService, ColorHelper, JQueryHelper) {
			var intervalId = 0;
			$scope.demoIsOn = false;
			$scope.ledOff = function() {
				var color = {
					RED : 0,
					GREEN : 0,
					BLUE : 0
				};
				JQueryHelper.setPageColor(ColorHelper.jsonToRgb(color));
				ManualModeService.setColors(color);
			}
			$scope.ledOn = function() {
				var color = {
					RED : 255,
					GREEN : 255,
					BLUE : 255
				};
				JQueryHelper.setPageColor(ColorHelper.jsonToRgb(color));
				ManualModeService.setColors(color);
			}
			$scope.toogleDemo = function() {
				if ($scope.demoIsOn == false) {
					console.log("Starting demo");
					var red = 0;
					var green = 0;
					var blue = 0;
					this.intervalId = setInterval(
							function() {
								red = (red + 1) % 512
								green = (green + 2) % 512;
								blue = (blue + 3) % 512;
								var color = {
									RED : red < 256 ? red : 512 - red,
									GREEN : green < 256 ? green : 512 - green,
									BLUE : blue < 256 ? blue : 512 - blue,
								};
								JQueryHelper.setPageColor(ColorHelper
										.jsonToRgb(color));
								ManualModeService.setColors(color);
							}, 200);
				} else {
					console.log("Stopping demo");
					clearInterval(this.intervalId);
				}

				$scope.demoIsOn = !$scope.demoIsOn;

			}
		} ]);
