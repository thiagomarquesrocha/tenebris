app = angular.module('uea', ['ngRoute']).

config(function($routeProvider, $locationProvider){

	//remove o # da url
	// $locationProvider.html5Mode(true);

	$routeProvider

	//Para a rota '/', carregamos o template home.html e o controller 'HomeCtrl'
	.when('/', {
		templateUrl : 'views/home.html',
		controller  : 'HomeCtrl'
	})

	.when('/perfil', {
		templateUrl : 'views/perfil.html',
		controller  : 'PerfilCtrl'
	})

	// Caso não seja nenhum desses, redirecione para a rota '/'
	.otherwise({ redirectTo : '/' });
});