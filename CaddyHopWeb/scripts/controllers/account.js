'use strict';

/**
 * @ngdoc function
 * @name app1App.controller:DeckBuilderCtrl
 * @description
 * # DeckBuilderCtrl
 * Controller of the app1App
 */
angular.module('app1App')
  .controller('accountCtrl', function($scope, $rootScope,  $http){
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
	
	$scope.id = -1;
		
	$scope.NewUserSubscription = function(pseudo, mdp, mail, nom, prenom) {
		$scope.listTosend = {"nom":nom,"prenom":prenom,"email":mail,"login":pseudo,"mdp":mdp}
		console.log($scope.listTosend);
		$http.post("http://162.243.106.178:8080/users", $scope.listTosend).success(function(data) {
			$scope.id = data;
		});
		alert("Inscription réussie, vous pouvez vous connecter !");
	};
	
  });