package Persistencia;

public class QueryBuilder3 {

	DatabaseActionListener dbListener;
	
	//Passar informacoes por meio de argumentos no construtor
	public QueryBuilder3(DatabaseActionListener dbListener) {
		this.dbListener = dbListener;
	}
	
	public DatabaseResponse buildQuery(String regiao, String orderBy) {
	
		String query = "(select I.NO_IES, "+
								"sum(QT_INGRESSO_CURSO) as INGRESSANTES, "+
								"sum(QT_MATRICULA_CURSO) as MATRICULADOS, "+
								"sum(QT_CONCLUINTE_CURSO) as CONCLUINTES, " +
								"sum(QT_INGRESSO_CURSO) + sum(QT_MATRICULA_CURSO) + sum(QT_CONCLUINTE_CURSO) as TOTAL " +
					   "from curso as C join ies as I on C.CO_IES_CURSO = I.CO_IES " +
					   "where NO_REGIAO_IES = '"+regiao+"'" +
					   "group by  I.CO_IES)"+
					   "union"+
					   "(select I.NO_REGIAO_IES, "+
					    		"sum(QT_INGRESSO_CURSO) as INGRESSANTES, " +
					    		"sum(QT_MATRICULA_CURSO) as MATRICULADOS, " +
					    		"sum(QT_CONCLUINTE_CURSO) as CONCLUINTES, "+
								"sum(QT_INGRESSO_CURSO) + sum(QT_MATRICULA_CURSO) + sum(QT_CONCLUINTE_CURSO) as TOTAL " +
					   "from curso as C join ies as I on C.CO_IES_CURSO = I.CO_IES " +
					   "where NO_REGIAO_IES = '"+regiao+"'" +
					   "group by  I.NO_REGIAO_IES)";
	

		if(!orderBy.isEmpty())
			query += "order by " + orderBy;
		
		return dbListener.queryRequested(query);
	}
}
