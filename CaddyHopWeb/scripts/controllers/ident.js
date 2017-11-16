'use strict';

/**
 * @ngdoc function
 * @name app1App.controller:DeckBuilderCtrl
 * @description
 * # DeckBuilderCtrl
 * Controller of the app1App
 */
angular.module('app1App')
  .controller('identCtrl', function($scope, $rootScope,  $http){
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
	
	$scope.testfonct = "pelooodecombat";
	
	$rootScope.user = {};
	
	$scope.toggle = false;
	$scope.hideButton = true;	
	
	$scope.toggleFilterConnect = function(loginlog, mdplog) {
		$rootScope.user.login = loginlog;
		$rootScope.user.mdp = mdplog;
		$http.get("http://162.243.106.178:8080/register/" +loginlog+"/"+mdplog).success(function(data) {
			$rootScope.user.id = data;
			if(data != -1){
				$scope.toggle = $scope.toggle === false ? true : false;
				console.log($rootScope.user);
				$scope.envoyCookie("login", $rootScope.user.login);
				$scope.envoyCookie("mdp", $rootScope.user.mdp);
				$scope.envoyCookie("id", $rootScope.user.id);
				alert("Bienvenu sur Caddy'Hop "+loginlog+" !");
			}
			else{
				alert("Login ou Mot de passe incorecte !");
			}
		});
	};
	
	
	$scope.toggleFilterDisconnect = function() {
		$scope.toggle = $scope.toggle === false ? true : false;
		$rootScope.user.login = null;
		$rootScope.user.mdp = null;
		$scope.envoyCookie("id", -1);
	};
	
	$scope.envoyCookie = function(name, cvalue){
		//function setCookie(cname, cvalue, exdays) {
		var d = new Date();
		d.setTime(d.getTime() + (100*24*60*60*1000));
		var expires = "expires="+ d.toUTCString();
		document.cookie = name + "=" + cvalue + "; " + 100; 
	};
	
	
	
  });