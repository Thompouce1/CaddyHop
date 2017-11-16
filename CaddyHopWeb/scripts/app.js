'use strict';

/**
 * @ngdoc overview
 * @name app1App
 * @description
 * # app1App
 *
 * Main module of the application.
 */
angular
  .module('app1App', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/courses', {
        templateUrl: 'views/courses.html',
        controller: 'coursesCtrl',
        controllerAs: 'courses'
      })
	  .when('/ident', {
        templateUrl: 'views/ident.html',
        controller: 'identCtrl',
        controllerAs: 'ident'
      })
	  .when('/infos', {
        templateUrl: 'views/infos.html',
        controller: 'infosCtrl',
        controllerAs: 'infos'
      })
	  .when('/mylists', {
        templateUrl: 'views/mylists.html',
        controller: 'mylistsCtrl',
        controllerAs: 'mylists'
      })
	  .when('/account', {
        templateUrl: 'views/account.html',
        controller: 'accountCtrl',
        controllerAs: 'account'
      })
      .otherwise({
        redirectTo: '/404.html'
      });
  });
