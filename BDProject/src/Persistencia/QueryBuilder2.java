package Persistencia;

public class QueryBuilder2 {

	DatabaseActionListener dbListener;
	
	//Passar informacoes por meio de argumentos no construtor
	public QueryBuilder2(DatabaseActionListener dbListener) {
		this.dbListener = dbListener;
	}
	
	public DatabaseResponse buildQuery(String campus, String uni, String orderBy) {
		
		String requestUni = "select CO_IES from ies where NO_IES = '"+uni+"' ";
		uni = dbListener.queryRequested(requestUni).getData().get(1).get(0).toString();
		
		String requestCampus = "select CO_LOCAL_OFERTA_IES from local_oferta where NO_LOCAL_OFERTA = '"+campus+"' and CO_IES = '"+uni+"' " ;
		campus = dbListener.queryRequested(requestCampus).getData().get(1).get(0).toString();
		
		String query = 	"select " +
						"NO_CURSO, " + 
						"sum(QT_INGRESSO_CURSO) INGRESSANTES, " + 
						"sum(QT_MATRICULA_CURSO) CURSANDO, " + 
						"sum(QT_CONCLUINTE_CURSO) CONCLUINTES " + 
						"from curso " +
						"where CO_IES =" + uni + " " +
						"and CO_LOCAL_OFERTA = " + campus + " " +
						"group by NO_CURSO ";
	
		if(!orderBy.isEmpty())
			query += "order by " + orderBy;
	
		return dbListener.queryRequested(query);
	}
}
