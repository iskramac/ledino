var ledinoApp = angular.module('ledinoApp',
		[ 'ngRoute', 'ledinoControllers' ]);

ledinoApp.config(['$routeProvider',
                    function($routeProvider) {
                      $routeProvider.
                        when('/dashboard', {
                          templateUrl: '/html/pages/dashboard.html',
                          controller: 'dashboardController'
                        }).
                        when('/mode/manual', {
                          templateUrl: '/html/pages/manual-mode.html',
                          controller: 'manualModeController'
                        }).
                        otherwise({
                          redirectTo: '/dashboard'
                        });
                    }]);