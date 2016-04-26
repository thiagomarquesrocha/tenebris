app

.controller('ProfileCtrl', function ($rootScope, $scope, $http, $location, $timeout, $filter, sharedService) {

  $rootScope.activetab = $location.path();

  // Configura os parametros 
  User
    .setScope($scope)
    .setRootScope($rootScope)
    .setFilter($filter)
    .setHttp($http)
    .setService(sharedService);

  sharedService.broadcast("mudar view");
  sharedService.broadcast("usuario esta logado");

  $scope.$on('handleBroadcast', function() {
    if(sharedService.message == 'perfil carregado'){
      $scope.area = $scope.profile.user.area;
      return;
    }
    if(sharedService.message != "usuario esta logado") return;
    User.isLoggin();
  }); // ON

  function send(action, type, field, value){
    $http({
        method: 'POST',
        url: Actions.user.add.interest,
        data : { action: action, "type" : type, "field" : field, "value" : value.toLowerCase(), user : User.getId() },
    }).then(function successCallback(response) {
        var data = response.data;
        console.log("Data :", data);
        var msg = "";
        switch(data.code){
          case Code.User.PREFERENCIA_EM_USO :
            msg = "A prefência já existe";
            break;
          case Code.OPERACAO_COMPLETADA :
            msg = "Cadastrada com sucesso";
            break;
          default :
            msg = "Não foi possível cadastrar o interesse";
            break;
        }
        Materialize.toast(msg, 4000);
    }, function errorCallback(response) {
        
    });
  }

  this.logout = function(){
      $http({
        method: 'GET',
        url: Actions.user.logout,
      }).then(function successCallback(response) {
           document.location.href = SITE;
      });
  }

  $scope.getName = function(profile){
    profile.user.namePublic = (profile.user.nome && profile.user.nome != '')? profile.user.nome : profile.user.login;
  }

  $scope.items = ['menu1', 'menu2', 'menu3', 'menu4'];
  $scope.selection = $scope.items[0];

  $scope.openPhotoWindow = function(){
    console.log("Abrir a foto");
    $scope.selection = $scope.items[0];
    $timeout(function(){
    angular.element('#fotoPerfil').trigger('click');
    }, 10);
  };

  $scope.addTag = function(tag){
    createList('tags');
    addItem('tags', tag);
    $("#icon_prefix_interesse").val('');
    send(Actions.preferences.add_interest, 'add', 'tag', tag);
  };

  $scope.addInstituition = function(instituition){
    console.log(instituition);
    if(!instituition) return;
    createList('instituicoes');
    addItem('instituicoes', instituition);
    $("#icon_prefix_instituicao").val('');
    send(Actions.preferences.add_institution, 'add', 'instituicao', instituition);
  };

  $scope.listInstitutions = function(){
    User.getInstitutions(function(data){
        //console.log(data);
        $scope.institutions = data;
        $timeout(function(){
          $('select').material_select();
        }, 200);
    });
  }

  $scope.addArea = function(area){
    if(area == '') return;
    var user = $scope.profile.user;
    console.log(user);
    $http({
        method: 'POST',
        url: Actions.user.update.area,
        data : { id : user.id, area : area.toLowerCase(), areaId : user.areaid },
    }).then(function successCallback(response) {
        var data = response.data;
        if(!data.data) return;
        data = data.data;
        if(data.id)
          user.areaid = data.id;
        console.log("Area response : ", data);
    });
    Materialize.toast("Area atualizada com sucesso", 4000);
  };

  $scope.addAuth = function(auth){
    createList('autores');
    addItem('autores', auth);
    send(Actions.preferences.add_auth, 'add', 'autor', auth);
  };

  $scope.isActivatedMenu = function(value){
    return ($scope.selection == value)? 'active' : '';
  }

  function createList(field){
    if(!$scope.profile.user[field])
      $scope.profile.user[field] = [];
  }

  function addItem(name, item){
    var list = $scope.profile.user[name];

    var found = false;

    for(index in list){
      var _this = list[index];
      if(_this.nome.toLowerCase() == item.toLowerCase()){
        found = true;
        break;
      }
    }

    if(!found)
      list.unshift({ nome : item });
  }

  console.log($rootScope.activetab);
})

.controller("TabCtrl", function ($rootScope, $scope, $location, sharedService, ngProgressFactory) {
  $rootScope.activetab = $location.path();

  $rootScope.$on("$routeChangeStart", function(){
    	createProgress($rootScope, ngProgressFactory);
  		if(DEBUG)
  			console.log("$routeChangeStart...");
  		$rootScope.progressbar.start();
  });

  $rootScope.$on("$routeChangeSuccess", function(){
  		if(DEBUG)
  			console.log("$routeChangeSuccess...");
  		$rootScope.progressbar.complete();
  });

  $rootScope.$on("$routeChangeError", function(){
  		if(DEBUG)
  			console.log("$routeChangeError...");
  		$rootScope.progressbar.complete();
  });

  $scope.$on('handleBroadcast', function() {
    if(sharedService.message != "mudar view") return;
    window.scrollTo(0, 0);
  });
})

.controller('WorkCtrl', function ($rootScope, $scope, $location, $http, $filter, sharedService) {

  $rootScope.activetab = $location.path();
  $scope.grid = [12];
  // Configura os parametros 
  User.setScope($scope).setRootScope($rootScope).setFilter($filter).setHttp($http).setService(sharedService);

  $scope.$on('handleBroadcast', function() {
    if(sharedService.message != "perfil carregado") return;
    User.getWorks();
  }); // ON

  $scope.view = function(id){
    $location.path('/obra/' + id);
  }

	$scope.add = function(){
		console.log("Carrega mais...");
		var last = $scope.works.length;
		for(var i=0; i<4; i++)
			$scope.works[last+i] = last+i;
	};

  $scope.getGrid = function(i){
    return 'm' + $scope.grid[i % $scope.grid.length];
  }

  function getWork(work){
    var index = $scope.works.indexOf(work);
    var found = $scope.works[index];
    return found;
  }

  sharedService.broadcast("mudar view");
  sharedService.broadcast("usuario esta logado");

})

.controller('ViewWorkCtrl', function($rootScope, $scope, $http, $timeout, $routeParams, sharedService){

  User.setScope($scope).setRootScope($rootScope);

  console.log($routeParams);

  var work = parseInt($routeParams.obraId);

  $scope.rating = function(found, rate){
    console.log(found);
    found.isSubmiting = true;
    found.preavaliacao = rate + 1;
    //found.avaliacao = rate+1;
  }

  $scope.submitRating = function(work){
    work.avaliacao = work.preavaliacao;
    work.submited = true;
    work.preavaliacao = 0;
    console.log("Envia a avaliação da obra ", work);

    var userId = User.getId();

    // Envia para o servidor a avaliacao da obra
    $http({
        method: 'POST',
        url: Actions.work.rate,
        data : { "itemId" : work.id, "userId" : userId, "rating" : work.avaliacao },
    }).then(function successCallback(response) {
        var data = response.data;
        console.log("Data :", data);

    }, function errorCallback(response) {
        
    });
  }

  $scope.$on('handleBroadcast', function() {
    if(sharedService.message != "perfil carregado") return;
      // Envia para o servidor a avaliacao da obra
      $http({
          method: 'POST',
          url: Actions.work.view,
          data : { "id" : work, "user" : User.getId() },
      }).then(function successCallback(response) {
          var data = response.data.data;
          console.log("Obra :", data);
          if(!data || !data[0]){
            console.error("Nao existe essa obra");
            return;
          } 
          // Mostra a lista de trabalhos recomendados
          $scope.work = data[0];
          // Redireciona o scroll da tela para o topo
          $(window).scrollTop(0);
      }, function errorCallback(response) {
          
      });
  }); // ON

  sharedService.broadcast("usuario esta logado");

})

.controller('HomeCtrl', function ($rootScope, $scope, $location) {
	$rootScope.activetab = $location.path();

	console.log($rootScope.activetab);
});

function createProgress($rootScope, ngProgressFactory){
  if($rootScope.progressbar) return;
  $rootScope.progressbar = ngProgressFactory.createInstance();
  $rootScope.progressbar.setHeight('5px');
}

/**
  Classe para estruturas angular
**/
Angular = {
  setService : function(sharedService){
    this.sharedService = sharedService;
    return this;
  },
  setRootScope : function($rootScope){
    this.$rootScope = $rootScope;
    return this;
  },
  setFilter : function($filter){
    this.$filter = $filter;
    return this;
  },
  setScope : function($scope){
    this.$scope = $scope;
    return this;
  },
  setHttp : function($http){
    this.$http = $http;
    return this;
  }
};

/**
  Operacoes usuario
**/
User = (function(){

  function getId(){
    //console.log(this);
    return (this.$scope.profile && this.$scope.profile.user)? this.$scope.profile.user.id : 0;
  }

  function getWorks(){
    var userId = this.getId();
    if(userId == 0) return;

    var _this = this;
    // Carrega a lista de recomendacoes para o usuario
    this.$http({
        method: 'POST',
        url: Actions.work.recommend.hibryd,
        data : { "userId" : userId, qtd : 20 },
    }).then(function successCallback(response) {
        var data = response.data;
        if(!data || !data.recommend) return;
        _this.$scope.works = _this.$filter('unique')(data.recommend, 'titulo');
        console.log("Recomendação :", _this.$scope.works);

    }, function errorCallback(response) {
        console.error("Erro ao carregar as obras recomendadas ");
    });
  }

  function getInstitutions(callback){

    var _this = this;
    // Carrega a lista de recomendacoes para o usuario
    this.$http({
        method: 'GET',
        url: Actions.user.institutions,
    }).then(function successCallback(response) {
        var data = response.data;
        if(!data.data){
          callback(null);
          return;
        } 
        data = data.data;
        callback(data);
        console.log("Lista de instituicoes :", data);

    }, function errorCallback(response) {
        console.error("Erro ao carregar as instituicoes");
    });
  }

  function isLoggin(){
    if( this.$scope.profile.user ){
        console.info("Usuario esta logado");
        this.sharedService.broadcast("perfil carregado");
        return;
    } 

      if(DEBUG)
        console.log("Mensagem broadcast recebida em ProfileCtrl");

      var _this = this;

      // Verifica se o usuario esta logado
      this.$http({
          method: 'GET',
          url: Actions.user.profile,
      }).then(function successCallback(response) {
        try{
          _this.$rootScope.progressbar.complete();
          var data = response.data;
          console.log("Data :", data);

          if(data.code == Code.OPERACAO_COMPLETADA ){
            Materialize.toast("Bem vindo ao Tenebris", 4000);
            _this.$scope.profile.user = data.data[0];
            _this.$scope.getName(_this.$scope.profile);
            _this.sharedService.broadcast("perfil carregado");
          }else{
            Materialize.toast("Redicionando para o site...", 4000);
            document.location.href = SITE;
          }
          
        }catch(e){
          //document.location.href = SITE;
          console.log(e);
        }

      }, function errorCallback(response) {
        _this.$rootScope.progressbar.complete();
        // called asynchronously if an error occurs
        // or server returns response with an error status.
      });
  }

  var obj = Object.create(Angular, {
    isLoggin: {
      value: isLoggin
    },
    getWorks: {
      value: getWorks
    },
    getId: {
      value: getId
    },
    getInstitutions : {
      value : getInstitutions
    }
  });

  return obj;
})();