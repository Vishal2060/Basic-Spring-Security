var myApp = angular.module('myApp', []);

myApp.controller('myController', function($scope, $http) {

	$scope.error = false;
	$scope.logout = false;

	alert(window.location.search);

	if (window.location.search.match("error")) {
		$scope.error = true;
	}

	if (window.location.search.match("logout")) {
		$scope.logout = true;
	}

	$scope.login = function() {

		$http.post(
				"login",
				$scope.user,
				{
					headers : {
						"content-type" : "application/x-www-form-urlencoded"
					},
					transformRequest : function(obj) {
						var str = [];
						for ( var p in obj)
							str.push(encodeURIComponent(p) + "="
									+ encodeURIComponent(obj[p]));
						return str.join("&");
					}
				}).then(function(response) {
			alert("Success");
		}, function(response) {
		});
	};
});
