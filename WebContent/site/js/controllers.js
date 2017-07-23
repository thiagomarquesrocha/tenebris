var app = angular.module('App', ['ngProgress'])

// Capitalize
.filter('capitalize', function() {
    return function(input) {
      return (!!input) ? input.charAt(0).toUpperCase() + input.substr(1) : '';
    }
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
})

.controller('Version', function($scope){
	$scope.date = new Date();
	$scope.version = Version.current;
	$scope.build = Version.build;
})

.controller('ProfileCtrl', function ($rootScope, $scope, $http, sharedService) {
	$scope.$on('handleBroadcast', function() {
		if(sharedService.message != "usuario esta logado") return;

		if(DEBUG)
			console.log("Mensagem broadcast recebida em ProfileCtrl");
		// Verifica se o usuario esta logado
		$http({
				method: 'GET',
				url: Actions.user.profile + "?d=" + (new Date()).getTime(),
				cache: false,
		}).then(function successCallback(response) {
			$rootScope.progressbar.complete();
			var data = response.data;
			console.log(data);

			if(data.code == Code.OPERACAO_COMPLETADA )
			//console.log("Autenticado");
				document.location.href=MAIN;
		}, function errorCallback(response) {
			$rootScope.progressbar.complete();
			// called asynchronously if an error occurs
			// or server returns response with an error status.
		});
	});
})

.controller('WorksCtrl', function ($rootScope, $scope, $http, $location, $timeout, sharedService, ngProgressFactory) {
	$http({
        method: 'GET',
        url: Actions.work.recents,
    }).then(function successCallback(response) {
        var data = response.data;
        if(!data.data) return;
		var works = data.data;
		works = works.splice(1, 10);
        $scope.works = works;
    }, function errorCallback(response) {
        console.error("Erro ao carregar as obras recomendadas ");
    });
})

.controller('LoginCtrl', function ($rootScope, $scope, $http, $location, $timeout, sharedService, ngProgressFactory) {

	$rootScope.progressbar = ngProgressFactory.createInstance();

	$rootScope.progressbar.setHeight('5px');
	$rootScope.progressbar.start();

	this.user = {};
	this.action = "";
	this.enter = "Entrar";

	sharedService.broadcast("usuario esta logado");

	function listInstitutions(){

		$http({
	        method: 'GET',
	        url: Actions.user.institutions,
	    }).then(function successCallback(response) {
	        var data = response.data;
	        if(!data.data){
	          Materialize.toast("Não foi possível carregar as instituições", 4000);
	          return;
	        } 
	        data = data.data;
	        $scope.institutions = data;
	        $timeout(function(){
	          $('select').material_select();
	        }, 200);
	        console.log("Lista de instituicoes :", data);

	    }, function errorCallback(response) {
	        console.error("Erro ao carregar as instituicoes");
	    });
  	}

	this.submit = function(){

		var action = $scope.action;

		//console.log(action);

		if( $scope.institutionActive && action == 'entrar'){
			$scope.institutionActive = false;
			this.enter = "Entrar";
			return;
		}

		// Tentando se cadastrar
		if(!$scope.institutionActive && action != 'entrar'){
			$scope.institutionActive = true;
			this.enter = "Voltar";
			listInstitutions();
			return;
		}

		// Tentando se cadastrar sem selecionar uma instituicao
		if(!$scope.institution && action != 'entrar'){
			Materialize.toast("Você precisa selecionar uma instituição", 4000);
			return;
		}

		$rootScope.progressbar.start();

		$scope.user.institution = parseInt($scope.institution);

		// Simple GET request example:
		$http({
			method: 'POST',
			url: (action == 'entrar')? Actions.user.login : Actions.user.add.user,
			headers: {'Content-Type': 'application/json'},
			data : $scope.user,
		}).then(function successCallback(response) {
			$rootScope.progressbar.complete();
			// this callback will be called asynchronously
			// when the response is available
			var data = response.data;

			if( !data.code ){
				Materialize.toast("Estamos com problemas no servidor", 4000);
				return;
			}
			
			switch(action){
				case "entrar":
						var msg = "";
						if(data){
							if( data.code == Code.OPERACAO_COMPLETADA ){ // Autenticacao completada

								if(!data.data[0]){
									console.log(data);
									Materialize.toast("Nenhum usuário econtrado com esse login", 4000);
								}else{
									console.log(data);
									Materialize.toast("Bem vindo ao Tenebris!", 4000);
									msg = "Autenticado!";
									document.location.href = MAIN;
								}
							}else{ // Autenticacao nao foi completada
								switch(data.code){
									case Code.User.NENHUM_USUARIO_ENCONTRADO:
										msg = "Não existe nenhum usuário com esse login e senha";
										break;
									default:
										msg = "Não foi possível autenticar!";
										break;
								}
								Materialize.toast(msg, 4000);
							}

							if(DEBUG && data.data && !data.data[0])
								console.log("Autenticado!", data);
							//alert(msg);
						}
					break;
				default: // Cadastrar
					if(data){
						switch(data.code){
							case Code.User.LOGIN_EM_USO:
								Materialize.toast("Este login já está sendo usado por outro usuário", 4000);
								break;
							case Code.User.FALTA_PARAMETROS_PARA_CADASTRAR:
								Materialize.toast("Preencha todos os campos!", 4000);
								break;
							case Code.User.NAO_FOI_POSSIVEL_CADASTRAR_USUARIO:
								Materialize.toast("Nao foi possível completar o cadastro, por favor tente novamente!", 4000);
								break;
							default:
								Materialize.toast("Bem vindo ao Tenebris!", 4000);
								if(DEBUG)
									console.log("Cadastrar", data);
								document.location.href = MAIN;
								break;
						}
					}
					break;
			}
		}, function errorCallback(response) {
			$rootScope.progressbar.complete();
			// called asynchronously if an error occurs
			// or server returns response with an error status.
		});
	}
});