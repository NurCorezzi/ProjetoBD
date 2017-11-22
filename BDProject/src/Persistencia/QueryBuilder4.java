package Persistencia;

public class QueryBuilder4 {

	DatabaseActionListener dbListener;
	
	//Passar informacoes por meio de argumentos no construtor
	public QueryBuilder4(DatabaseActionListener dbListener) {
		this.dbListener = dbListener;
	}
	
	public DatabaseResponse buildQuery(String regiao, String grau, String orderBy) {
	
		String query = "select SGL_UF_IES as UF, " + 
				"		sum(QT_INGRESSO_CURSO) as INGRESSANTES, " + 
				"		sum(QT_MATRICULA_CURSO) as CURSANDO, " + 
				"		sum(QT_CONCLUINTE_CURSO) as CONCLUINTES " + 
				"from curso as C join ies I on C.CO_IES = I.CO_IES " + 
				"where NO_REGIAO_IES = '"+regiao+"' " + 
				"	  and DS_GRAU_ACADEMICO = '"+grau+"' " + 
				"group by SGL_UF_IES ";

		if(!orderBy.isEmpty())
			query += "order by " + orderBy;
		
		return dbListener.queryRequested(query);
	}
}
