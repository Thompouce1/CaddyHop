'use strict';

/**
 * @ngdoc function
 * @name app1App.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the app1App
 */
angular.module('app1App')
  .controller('MainCtrl', function ($scope, $http) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
});
