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

ledinoControllers.controller('pageController', [ '$rootScope', 'RestService', '$http', 'ArduinoService', 'ColorConverterService', 'LayoutService',
		'WebSocketService', 'config', function($rootScope, RestService, $http, ArduinoService, ColorConverterService, LayoutService, WebSocketService, config) {

			LayoutService.setPageColor(config.arduinoState.ledColor);
			ArduinoService.addStateChangeHandler(function(state) {
				LayoutService.setPageColor(state.ledColor);
			});
		} ]);

ledinoControllers.controller('manualModeController', [ '$scope', '$rootScope', '$http', 'ArduinoService', 'ColorConverterService', 'LayoutService',
		'WebSocketService', 'config', function($scope, $rootScope, $http, ArduinoService, ColorConverterService, LayoutService, WebSocketService, config) {
			$scope.init = function() {
				$scope.colorpickerValue = ColorConverterService.jsonToRgb(config.arduinoState.ledColor);
				console.log(config.arduinoState.ledColor)
				console.log("Manual mode initialized");
			};

			$scope.colorpickerChangeHandler = function(e) {
				WebSocketService.send("/ledino/set-led-state", JSON.stringify(ColorConverterService.rgbToJson($scope.colorpickerValue)));
			}
			$scope.init();
		} ]);

ledinoControllers.controller('dashboardController', [ '$scope', 'ArduinoService', 'ColorConverterService', 'LayoutService',
		function($scope, ArduinoService, ColorConverterService, LayoutService) {
			$scope.init = function() {
				console.log("Dashboard initialized");
			}
			var intervalId = 0;
			$scope.demoIsOn = false;
			$scope.ledOff = function() {
				var color = {
					red : 0,
					green : 0,
					blue : 0
				};
				ArduinoService.setColors(color);
			}
			$scope.ledOn = function() {
				var color = {
					red : 255,
					green : 255,
					blue : 255
				};
				ArduinoService.setColors(color);
			}
			$scope.toogleDemo = function() {
				if ($scope.demoIsOn == false) {
					console.log("Starting demo");
					var red = 0;
					var green = 0;
					var blue = 0;
					this.intervalId = setInterval(function() {
						red = (red + 1) % 512
						green = (green + 2) % 512;
						blue = (blue + 3) % 512;
						var color = {
							red : red < 256 ? red : 512 - red,
							green : green < 256 ? green : 512 - green,
							blue : blue < 256 ? blue : 512 - blue,
						};
						ArduinoService.setColors(color);
					}, 200);
				} else {
					console.log("Stopping demo");
					clearInterval(this.intervalId);
				}

				$scope.demoIsOn = !$scope.demoIsOn;

			}

			$scope.init();
		} ]);
