var ledinoApp = angular.module('ledinoApp', [ 'ngRoute', 'ledinoControllers',
		'ledinoServices', 'colorpicker.module', 'angularSpectrumColorpicker' ]);

ledinoApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/dashboard', {
		title : 'Ledino::Dashboard',
		templateUrl : '/html/pages/dashboard.html',
		controller : 'dashboardController'
	}).when('/mode/manual', {
		title : 'Ledino::Manual mode',
		templateUrl : '/html/pages/manual-mode.html',
		controller : 'manualModeController'
	}).otherwise({
		redirectTo : '/dashboard'
	});
} ]);

ledinoApp.run(['$location', '$rootScope', function($location, $rootScope) {
    $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
        $rootScope.title = current.$$route.title;
    });
}]);
