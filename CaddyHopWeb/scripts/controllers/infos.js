'use strict';


/**
 * @ngdoc function
 * @name app1App.controller:DecksCtrl
 * @description
 * # DecksCtrl
 * Controller of the app1App
 */
angular.module('app1App')
  .controller('infosCtrl', function($scope, $rootScope,  $http){
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

	$scope.yourname = "Vou devez etre connecté";
	$scope.yourlogin = "Vou devez etre connecté";
	$scope.yourmail = "Vou devez etre connecté";
	$scope.yournick = "Vou devez etre connecté";
	
	
	$scope.userId = 0;
	
	$scope.utilisateur = $rootScope.user;
	console.log($scope.utilisateur);
	
	$scope.getCookie = function(cname) {
		var name = cname + "=";
		var ca = document.cookie.split(';');
		for(var i = 0; i <ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0)==' ') {
				c = c.substring(1);
			}
			if (c.indexOf(name) == 0) {
				$scope.userId = c.substring(name.length,c.length);
			}
		}
		return "";
	};
	
	$scope.getCookie("id");
	
	$http.get("http://162.243.106.178:8080/users/" + $scope.userId).success(function(data) {
			console.log(data);
			$scope.yourname = data.nom;
			$scope.yourlogin = data.login;
			$scope.yourmail = data.email;
			$scope.yournick = data.prenom;
	});
		
  });
