var ledinoServices = angular.module('ledinoServices', [ 'ngResource' ]);

ledinoServices.service('ArduinoService',[ "WebSocketService","config", function(WebSocketService,config) {
	var stateChangeHandlers = [];
	
	var init = function() {
		WebSocketService.connect(function(e) {
			// onSuccess
			WebSocketService.subscribe("/topic/ledino-state-changed", function(message) {
				console.log("Received new ledino state:'" + message.body + "',executing event handlers");
				for (var i = 0; i < stateChangeHandlers.length; i++) {
					stateChangeHandlers[i](JSON.parse(message.body));
				}
			});
		});
	}

	var setColors = function(colors, callback) {
		console.log("Sending color change request: " + JSON.stringify(colors));
		WebSocketService.send("/ledino/set-led-state", JSON.stringify(colors));
	}

	var addStateChangeHandler = function(handler) {
		stateChangeHandlers.push(handler);
	}
	
	var rootScopeVariablesUpdateChangeHandler = function(applicationConfig){
		config.arduinoState= applicationConfig;
		console.log("Updated ledino config")
	}

	init();
	addStateChangeHandler(rootScopeVariablesUpdateChangeHandler);

	return {
		setColors : setColors,
		addStateChangeHandler : addStateChangeHandler
	}
} ]);

ledinoServices.service('ColorConverterService', [ function() {
	return {
		rgbToJson : function(color) {
			var matchColors = /rgb\((\d{1,3}), (\d{1,3}), (\d{1,3})\)/;
			var match = matchColors.exec(color);
			return {
				red : match[1],
				green : match[2],
				blue : match[3]
			}
		},
		jsonToRgb : function(color) {
			return 'rgb(' + color.red + ', ' + color.green + ', ' + color.blue + ')';
		}
	}
} ]);

ledinoServices.service('LayoutService', [ 'ColorConverterService', function(ColorConverterService) {
	return {
		body : $("body"),
		setPageColor : function(color) {
			console.log("Updating layout color to: " + JSON.stringify(color))
			this.body.css("background-color", ColorConverterService.jsonToRgb(color))
		}
	}
} ]);

ledinoServices.service('WebSocketService', ['config', function(config) {
	return {
		client : null,
		connect : function(callback) {
			var url = "ws://" + config.applicationHost + "/websocket";
			console.log("Connecting to websocket at url:" + url)
			this.client = Stomp.over(new WebSocket(url));
			this.client.connect({}, callback);
			this.client.debug = false;
		},
		subscribe : function(destination, onMessageHandler) {
			this.client.subscribe(destination, onMessageHandler)
		},
		send : function(destination, payload, callback) {
			this.client.send(destination, {}, payload);
		}
	}
} ]);

ledinoServices.service('RestService', [ "$resource", function($resource) {
	return {
		getApplicationConfiguration : function(callback) {
			return $resource('/rest/application/configuration').get(callback);
		}
	}
} ]);