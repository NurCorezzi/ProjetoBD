package Persistencia;

public class QueryBuilder0 {

	DatabaseActionListener dbListener;
	
	//Passar informacoes por meio de argumentos no construtor
	public QueryBuilder0(DatabaseActionListener dbListener) {
		this.dbListener = dbListener;
	}
	
	public DatabaseResponse makeQuery() {
		
		String query = "select NO_IES ,count(*) CURSOS from curso group by CO_IES order by count(*)";
		
		DatabaseResponse resp = dbListener.queryRequested(query);
		
		return resp;
	}
}
