/**
	Lista de codigos retornados durante a requisicao
	{ "code" : (String) }
	Ex : code : SUCESS_001, ERRO_001
**/
var Code = {
	OPERACAO_COMPLETADA : "SUCESS_001",
	User : {
		LOGIN_EM_USO : "USER_002",
		NAO_FOI_POSSIVEL_CADASTRAR_USUARIO : "USER_003",
		FALTA_PARAMETROS_PARA_CADASTRAR : "USER_004",
		NENHUM_USUARIO_ENCONTRADO : "USER_008",
		NAO_FOI_POSSIVEL_COMPLETAR_ACAO : "USER_009",
		PREFERENCIA_EM_USO : "USER_010"
	}
};

var Version = {
	current : "1.0",
	build : "3",
	version : "6"
};

var SITE = "/tenebris2016/";
var MAIN = "/tenebris2016/application/";

/**
	URL de ações dentro do Tenebris
**/
var Actions = {
	"user" : 
	{
		"profile"   : SITE + "usuario/perfil",
		"login"     : SITE + "usuario/login",
		"logout" 	: SITE + "usuario/sair",
		"institutions" 	: SITE + "instituicao",
		"add" : {
			"interest" : SITE + "usuario/adicionar/interesse",
			"user" : SITE + "usuario/adicionar"
		},
		"update" : {
			"area" : SITE + "usuario/atualizar/area"
		}
 	},
	"work" : 
	{
		"recommend" :
		{
			"rate" 	: SITE + "obra/recomendacao/avaliacao",
			"hibryd" 	: SITE + "obra/recomendacao"
		},
		"rate" : SITE + "obra/avaliar",
		"view" : SITE + "obra",
		"new" : SITE + "obra/cadastrar",
		"list" : SITE + "obra/listar",
		"update" : SITE + "obra/alterar",
		"remove" : SITE + "obra/remover",
		"recents" : SITE + "obra/recentes",
		"types" : SITE + "obra/tipos"
	},
	"preferences" :{
		"add_interest" : 2,
		"add_institution" : 3,
		"add_auth" : 4,
		"interest" : 2,
		"institution" : 3,
		"auth" : 4
	}
};

// Flag para ativar o modo debug, onde todos os 'console.log' sao ativados
var DEBUG = true;