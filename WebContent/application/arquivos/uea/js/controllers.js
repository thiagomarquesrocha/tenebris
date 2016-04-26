app.

controller('Latest', function ($scope) {
	$scope.add = function(){
		console.log("Carrega mais...");
		var last = $scope.articles.length;
		for(var i=0; i<4; i++)
			$scope.articles[last+i] = last+i;
	};
}).


controller('HomeCtrl', function ($rootScope, $scope, $location) {
	$rootScope.activetab = $location.path();

	console.log($rootScope.activetab);
}).

controller('PerfilCtrl', function ($rootScope, $scope, $location) {
	$rootScope.activetab = $location.path();

	console.log($rootScope.activetab);
});