'use strict';

/**
 * @ngdoc function
 * @name app1App.controller:CartesCtrl
 * @description
 * # CartesCtrl
 * Controller of the app1App
 */
angular.module('app1App')
  .controller('coursesCtrl', function ($scope, $http, $rootScope) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];	
	
	$scope.userId = -1;
	$scope.listId = -1;
	$scope.AllListUser = [];
	
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
	console.log($scope.userId);
	console.log($scope.listId);
	
	$http.get("http://162.243.106.178:8080/users/" + $scope.userId + "/lists/").success(function(data) {
			$scope.AllListUser = data
			console.log($scope.AllListUser);
	});
	
	$scope.newListForUser = function(listname){
		$http.post("http://162.243.106.178:8080/users/" + $scope.userId + "/lists/"+ listname).success(function(data) {
			$scope.listId = data;
		});
		$http.get("http://162.243.106.178:8080/users/" + $scope.userId + "/lists/").success(function(data) {
			$scope.AllListUser = data
			console.log($scope.AllListUser);
		});
	};
	
	$scope.deleteListForUser = function(idlis){
		$http.delete("http://162.243.106.178:8080/users/" + $scope.userId + "/lists/" + idlis).success(function(datass) {
		});
		$http.get("http://162.243.106.178:8080/users/" + $scope.userId + "/lists/").success(function(data) {
			$scope.AllListUser = data
			console.log($scope.AllListUser);
		});
	};
	
	$scope.modifyList = function(idlis){
		$scope.listId = idlis;
		console.log("listId set");
		$http.get("http://162.243.106.178:8080/users/" + $scope.userId + "/lists/" + $scope.listId + "/").success(function(data) {
				$scope.list = data.articleList;
				if($scope.list != null){
					$scope.list.forEach(additionPrixGlobal);
				}
				console.log("refresh list");
		});
		$scope.prixTotal = 0;
		price = 0;
	};
	
	$scope.list = null;
	$scope.listTosend;
	$scope.articleMagasin = [];
	$scope.prixTotal = 0;
	var price = 0;
	$scope.isIn = false;
	
	$scope.getListByUserAndId = function() {
		$scope.prixTotal = 0;
		price = 0;
		$http.get("http://162.243.106.178:8080/users/" + $scope.userId + "/lists/" + $scope.listId + "/").success(function(data) {
				$scope.list = data.articleList;
				if($scope.list != null){
					$scope.list.forEach(additionPrixGlobal);
				}
				console.log($scope.list);
		});
	};
	
	$scope.getListByUserAndId();
	
	$scope.modifyListOnServer = function() {
		console.log($scope.list.nom);
	$scope.listTosend = {"articleList":$scope.list,"id":$scope.listId}
	$http.put("http://162.243.106.178:8080/users/" + $scope.userId + "/lists/" + $scope.listId, $scope.listTosend).success(function(data) {
		});
	};
	
	$scope.getListMagasin = function() {
	$http.get("http://162.243.106.178:8080/magasin/0").success(function(data) {
			$scope.articleMagasin = data;
		});
	};
	
	function additionPrixGlobal(element, index, array) {
		price = price + (element.prix * element.quantite);
		
		$scope.prixTotal = price;
	}
	
	function isInList(element, index, array) {
		if(codeBarre == element.codeBarre){
			return true;
		}
		else{
			return false;
		}
	}	
	
	/*var magasin = [{ value: 'Casino', data: 'Cas' },{ value: 'Carrefour', data: 'Car' }];

	$('#autocomplete').autocomplete({
		lookup: magasin,
		onSelect: function (suggestion) {
		    alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
		}
	});*/
	
	$scope.getListMagasin();
	
	$scope.suppressionArticle = function(article) {
		var index = $scope.list.indexOf(article);
		$scope.prixTotal = $scope.prixTotal - (article.prix * article.quantite);
		$scope.list.splice(index, 1);
		$scope.modifyListOnServer($scope.userId, $scope.listId);
	};
	
	$scope.add = function(article) {
		article.quantite = article.quantite + 1;
		$scope.prixTotal = $scope.prixTotal + article.prix;
		$scope.modifyListOnServer($scope.userId, $scope.listId);
	};
	
	$scope.del = function(article) {
		if (article.quantite == 1) {
			$scope.suppressionArticle(article);
			$scope.modifyListOnServer($scope.userId, $scope.listId);
		} 
		else{
			article.quantite = article.quantite - 1;
			$scope.prixTotal = $scope.prixTotal - article.prix;
			$scope.modifyListOnServer($scope.userId, $scope.listId);
		}
	};
	
	$scope.checkInList = function(article) {
		angular.forEach($scope.list, function(value, key) {
			if(value.codeBarre == article.codeBarre) {
				$scope.isIn = true;
			}
		});	
	};
	
	$scope.modifyQuantity = function(article) {
		angular.forEach($scope.list, function(value, key) {
			if(value.codeBarre == article.codeBarre) {
				value.quantite = value.quantite + 1;
				$scope.prixTotal = $scope.prixTotal + article.prix;
				$scope.modifyListOnServer($scope.userId, $scope.listId);
			}
		});	
	};

	
	$scope.addArticle = function(nomArticle) {
		$scope.checkInList(nomArticle);
		if($scope.isIn){
			$scope.modifyQuantity(nomArticle);
			$scope.isIn = false;
		}
		else{
			$scope.list.push({'codeBarre':nomArticle.codeBarre,'nom':nomArticle.nom,'prix':nomArticle.prix,'quantite':1});
			$scope.prixTotal = ($scope.prixTotal + nomArticle.prix);
			$scope.modifyListOnServer($scope.userId, $scope.listId);
			
		}
	};
	
  });

 
