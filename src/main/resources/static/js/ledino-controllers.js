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

ledinoControllers.controller('manualModeController', [
		'$scope',
		'$http',
		function($scope, $http) {
			$http.get('/arduino/state').success(function(data) {
				$scope.state = data;
				console.log(data);
			});
			$scope.colorRange = (function() {
				var ranges = [];
				for (i = 0; i < 255; i++) {
					ranges[i] = i;
				}
				return ranges;
			})();
			$scope.color = {
				'red' : 0,
				'green' : 0,
				'blue' : 0,
				'rgb' : function() {
					return 'rgb(' + $scope.color.red + ',' + $scope.color.green
							+ ',' + $scope.color.blue + ')';
				}
			}

		} ]);
ledinoControllers.controller('dashboardController', function($scope) {
	$scope.phones = [ {
		'name' : 'Nexus S',
		'snippet' : 'Fast just got faster with Nexus S.'
	}, {
		'name' : 'Motorola XOOM™ with Wi-Fi',
		'snippet' : 'The Next, Next Generation tablet.'
	}, {
		'name' : 'MOTOROLA XOOM™',
		'snippet' : 'The Next, Next Generation tablet.'
	} ];
	$scope.pageName = "dashboard"
});
