package model;

public class JSONOut {
	
	public static String CODE = "code";
	public static String DATA = "data";

	// CODE : USER_{000}
	public static class User{
		private static String BASE = "USER_";
		public static final String LOGIN_EM_USO = BASE + "002";
		public static final String NAO_FOI_POSSIVEL_CADASTRAR_USUARIO = BASE + "003";
		public static final String FALTA_PARAMETROS_PARA_CADASTRAR = BASE + "004";
		public static final String NAO_AUTENTICADO = BASE + "005";
		public static final String NAO_FOI_POSSIVEL_ENCONTRAR_ESTE_USUARIO = BASE + "006";
		public static final String LOGIN_OU_SENHA_NAO_INFORMADO = BASE + "007";
		public static final String NENHUM_USUARIO_ENCONTRADO = BASE + "008";
		public static final String NAO_FOI_POSSIVEL_COMPLETAR_ACAO = BASE + "009";
		public static final String PREFERENCIA_EM_USO = BASE + "010";
		public static final String NAO_FOI_POSSIVEL_CADASTRAR_PREFERENCIA = BASE + "011";
		public static final String NAO_FOI_POSSIVEL_LISTAR_AS_PREFERENCIAS = BASE + "012";
	}
	
	// CODE : WORK_{000}
	public static class Work{
		private static String BASE = "WORK_";
		public static final String NAO_FOI_POSSIVEL_ENCONTRAR_ESTA_OBRA = BASE  + "001";
		public static final String NENHUMA_OBRA_ENCONTRADA = BASE  + "002";
		public static final String NAO_FOI_POSSIVEL_DELETAR_ESTA_OBRA = BASE + "003";
	}
	
	// CODE : KEYWORDS_{000}
	public static class Keywords{
		private static String BASE = "KEYWORDS_";
		public static final String NENHUMA_PALAVRACHAVE_ENCONTRADA = BASE  + "001";
	}
	
	// CODE : SUCESS_{000}
	public static class Sucess{
		private static String BASE = "SUCESS_";
		public static final String COMPLETADA = BASE + "001";
	}
	
	// CODE : ERRO_{000}
	public static class Erro{
		private static final String BASE = "ERRO_";
		public static final String OCORREU_ALGUM_ERRO = BASE + "001";
	}
}
