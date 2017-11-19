package Persistencia;

public class QueryBuilder2 {

	DatabaseActionListener dbListener;
	
	//Passar informacoes por meio de argumentos no construtor
	public QueryBuilder2(DatabaseActionListener dbListener) {
		this.dbListener = dbListener;
	}
	
	public DatabaseResponse buildQuery(String campus, String orderBy) {
		
		String codCursos = "(select CO_CURSO from local_oferta where NO_LOCAL_OFERTA = '"+campus+"')" ;
		
		String query = "select NO_CURSO, "+
							  "DS_MODALIDADE_ENSINO,  "+
							  "sum(QT_INGRESSO_CURSO) INGRESSANTES, "+
							  "sum(QT_MATRICULA_CURSO) CURSANDO, "+
							  "sum(QT_CONCLUINTE_CURSO) CONCLUINTES "+ 
					   "from curso "+
					   "where CO_CURSO in"+codCursos+
					   "group by NO_CURSO, DS_MODALIDADE_ENSINO ";
	
		if(!orderBy.isEmpty())
			query += "order by " + orderBy;
	
		return dbListener.queryRequested(query);
	}
}
