
app = angular.module('App', ['ngRoute', 'ngProgress'])

.run([function () {
	// Mostra a aplicação apenas quando todos os modulos estiverem carregados
	angular.element("#top").removeClass("loading");
}])

.filter('unique', function() {

  return function (arr, field) {
    var o = {}, i, l = arr.length, r = [];
    for(i=0; i<l;i+=1) {
      o[arr[i][field]] = arr[i];
    }
    for(i in o) {
      r.push(o[i]);
    }
    return r;
  };
})

// File directive for inputs
.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}])

.config(function ($routeProvider, $locationProvider){

	//remove o # da url
	// $locationProvider.html5Mode(true);

	$routeProvider

	//Para a rota '/', carregamos o template home.html e o controller 'HomeCtrl'
	.when('/', {
		templateUrl : 'views/home.html',
		controller  : 'WorksCtrl'
	})

  .when('/obra/recomendada', {
		templateUrl : 'views/obras_recomendadas.html',
		controller  : 'WorksCtrl'
	})

  .when('/time', {
		templateUrl : 'views/time.html',
    controller  : 'TeamCtrl'
	})

	.when('/perfil', {
		templateUrl : 'views/perfil.html',
		controller  : 'ProfileCtrl'
	})

	.when('/obra/cadastrar', {
		templateUrl : 'views/obra_cadastrar.html',
		controller  : 'WorkCtrl'
	})

  .when('/obra/editar/:obraId', {
		templateUrl : 'views/obra_editar.html',
		controller  : 'WorkCtrl'
	})
  
  .when('/obra/listar', {
		templateUrl : 'views/obra_listar.html',
		controller  : 'WorkCtrl'
	})
  
  .when('/obra/:obraId', {
		templateUrl : 'views/obra.html',
		controller  : 'ViewWorkCtrl'
	})

	// Caso não seja nenhum desses, redirecione para a rota '/'
	.otherwise({ redirectTo : '/' });
})

.factory('sharedService', function($rootScope) {
  var sharedService = {};

  sharedService.message = '';

  sharedService.broadcast = function(msg) {
    this.message = msg;
    this.broadcastItem();
  };

  sharedService.broadcastItem = function() {
    $rootScope.$broadcast('handleBroadcast');
  };

  return sharedService;
});