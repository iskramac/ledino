var ledinoControllers = angular.module('ledinoControllers', []);

ledinoControllers.controller('menuController', function($scope) {
	$scope.pages = [ {
		name : "Dashboard",
		url : "#/dashboard"
	}, {
		name : "Manual mode",
		url : "#/manual-mode"
	}, {
		name : "Scheduler mode",
		url : "#/scheduler-mode"
	}, ];
});

ledinoControllers.controller('pageController', [ '$rootScope', '$http', 'ArduinoService', 'ColorConverterService', 'LayoutService',
		'WebSocketService', 'config', function($rootScope, $http, ArduinoService, ColorConverterService, LayoutService, WebSocketService, config) {

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


ledinoControllers.controller('schedulerModeController', [ '$scope', function($scope) {
		$scope.init = function() {

			console.log("Scheduler mode initialized");
		};


		$scope.init();
	} ]);


ledinoControllers.controller('dashboardController', [ '$scope', 'ArduinoService', 'ColorConverterService', 'LayoutService','AnimationService',
		function($scope, ArduinoService, ColorConverterService, LayoutService,AnimationService) {
			$scope.init = function() {
				console.log("Dashboard initialized");
			}
			var intervalId = 0;

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
			$scope.discoAnimation = function(){
				AnimationService.startAnimation("disco");	
			}
			$scope.fadeInAnimation = function(){
				AnimationService.startAnimation("fadeIn");	
			}
			$scope.fadeOutAnimation = function(){
				AnimationService.startAnimation("fadeOut");	
			}
			$scope.stopAllAnimations = function(){
				AnimationService.stopAllAnimations();
			}
			

			$scope.init();
		} ]);
