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

.controller('WorksCtrl', function ($rootScope, $scope, $location, $timeout, $http, $filter, sharedService) {

  $rootScope.activetab = $location.path();
  $scope.grid = [12];
  // Configura os parametros 
  User.setScope($scope).setRootScope($rootScope).setFilter($filter).setHttp($http).setService(sharedService);

  $scope.$on('handleBroadcast', function() {
    if(sharedService.message != "perfil carregado") return;
    User.getWorks();
  }); // ON

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

.controller('WorkCtrl', function($scope, $rootScope, $routeParams, $timeout, $location, $http, sharedService){
  
  // Canceled the click between blocks from nivel
  var isCanceled = false;
  var picker;
  var gettingMyWorks;

  $rootScope.activetab = $location.path();

  // Configura os parametros 
  Work.setHttp($http);
  // Work id passed by parameter
  var work = parseInt($routeParams.obraId);

  console.log($rootScope.activetab);
  
  // Init datepicker and others configs
  $timeout(function(){
     if($rootScope.activetab == "/") return;
     $(document).ready(function() {
      $("body").animate({ scrollTop : 0 }, 1000);
      Materialize.updateTextFields();
      // Interface input date 
      var $input = $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 15, // Creates a dropdown of 15 years to control year
        onSet: function(context) {
          console.log('Just set stuff:', context);
          var d = context.select;
          var day = d.date;
          var month = d.month;
          var year = d.year;
          if(typeof d === "number"){
            var dtmp = new Date(d);
            day = dtmp.getDate();
            month = dtmp.getMonth() + 1;
            year = dtmp.getFullYear();
          }
          if(typeof d !== "string")
            $scope.date = year + "-" + month + "-" + day;
          else
            $scope.date = d;
        }
      });
      picker = $input.pickadate("picker");
      setDate();
    });
  }, 200);

  $scope.$on('handleBroadcast', function() {
    if(sharedService.message != "perfil carregado") return;
    if($rootScope.activetab.match(/\/obra\/listar/gi)) // List works user
      $scope.myWorks();
    else if($rootScope.activetab.match(/\/obra\/editar\/[0-9]{1,}/gi)){ // Edit work
      if($scope.work) return;
      Work.get(work, User.getId(), onWorkReceived);
    } 
  }); // ON

  var onWorkReceived = function(data){
    $scope.work = data;
    if(!data) return;
    $scope.title = data.titulo;
    $scope.auth = data.autor;
    $scope.date = data.data;
    $scope.id = data.id;
    $scope.institution = data.instituicao;
    $scope.resume = data.resumo;
    $scope.area = data.area;
    $scope.file = data.path;
    setDate();
  }

  // Date
  function setDate(){
    if(picker && $scope.date)
      picker.set('select', $scope.date, { format: 'yyyy-mm-dd' });
  }

  $scope.myWorks = function(){
    if($scope.works || gettingMyWorks) return; // Already exist the list of works
    gettingMyWorks = true;
    var userId = User.getId();

    $http({
        method: 'POST',
        url: Actions.work.list,
        data : { "id" : userId, command : "listByUser" },
    }).then(function successCallback(response) {
        var data = response.data;
        console.log("Data :", data);

        if(data.works){
          $scope.works = data.works;
        }

    }, function errorCallback(response) {
        
    });
  }

  $scope.view = function(id, type){
    if(isCanceled) return;
    console.log("type ", type)
    switch(type){
      case "edit" :
        isCanceled = true;
        $location.path('/obra/editar/' + id);
        break;
      default:
        isCanceled = true;
        $location.path('/obra/' + id);
        break;
    }    

    cancel();
  }

  $scope.open = function(url){
    isCanceled = true;
    if(url){
      url = getPath(url, "\obras");
      window.open(url);
    }
    cancel();
  }

  $scope.getPath = function(url, end){
    var url = getPath(url, end);
    return url.replace('\\tenebris2016\\obras\\', '');
  }

  function getPath(url, end){
    if(!url) return "";
    //console.log(url);
    url = "\\tenebris2016\\" + url.substring(url.indexOf(end));
    console.log(url);
    return url;
  }

  function cancel(){
    $timeout(function(){
      isCanceled = false;
    }, 300);
  }

  $scope.isDefaultOption = function(institution){
      //console.log(institution);
      var isSelected = institution.id == 1;
      return isSelected; // EST
  }

  $scope.listInstitutions = function(){
    $timeout(function(){
       User.getInstitutions(function(data){
        //console.log(data);
        $scope.institutions = data;
        $timeout(function(){
          $scope.institution = 1; // EST
          $('select').material_select();
        }, 200);
      });
    }, 200);
  }

  $scope.save = function(update) {
        var user = User.getId();
        var d = $scope.date;
        var institution = $scope.institution;
        console.log(institution);
        if(!user){
          Materialize.toast("Você precisa está logado para realizar essa operação", 4000);
          return;
        }
        if(!institution || institution == ''){
          Materialize.toast("Você precisa selecionar a instituição", 4000);
          return;
        }
        if(!d){
          Materialize.toast("Você precisa selecionar a data da obra", 4000);
          return;
        }
        var file = $scope.file;
        var uploadUrl = (update)? Actions.work.update : Actions.work.new;
        var fd = new FormData();        
        fd.append('title', $scope.title.toLowerCase());
        // 1 - EST
        if(update)
          fd.append('id', $scope.id);
        fd.append('institution', institution);
        fd.append('auth', $scope.auth.toLowerCase());
        fd.append('area', $scope.area.toLowerCase());
        fd.append('date', $scope.date);
        fd.append('user', user);
        fd.append('resume', $scope.resume.toLowerCase());
        fd.append('file', file);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(data){
          console.log(data);
          if(data.code == Code.OPERACAO_COMPLETADA){
            Materialize.toast("Cadastrada com sucesso", 4000);
            $location.path('#/');
          }else{
            Materialize.toast("Não foi possível cadastrar a obra, por favor tente novamente", 4000);
          }
        })
        .error(function(){
        });
    }

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
 * Work Controller
 */
Work = (function(){

  // Get info about one work by WorkId and UserId
  function get(work, user, callback){
    this.$http({
          method: 'POST',
          url: Actions.work.view,
          data : { "id" : work, "user" : user },
      }).then(function successCallback(response) {
          var data = response.data.data;
          console.log("Obra :", data);
          if(callback != null){
            callback((data && data[0])? data[0] : null);
          }
      }, function errorCallback(response) {
          console.error("Nao foi possivel recuperar os dados da obra ", work);
      });
  }

  var obj = Object.create(Angular, {
    get : { value : get }
  })

  return obj;
})();

/**
  User Controller
**/
User = (function(){

  // Get ID from user
  function getId(){
    //console.log(this);
    return (this.$scope.profile && this.$scope.profile.user)? this.$scope.profile.user.id : 0;
  }

  // Recommendation for one user
  function getWorks(){
    if(this.$scope.works && this.$scope.works.length){
      console.info("As obras já foram carregadas");
      return;
    }
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

  // Get institutions avaible
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

  // Check if user is login in
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