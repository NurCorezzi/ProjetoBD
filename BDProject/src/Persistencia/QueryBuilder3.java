package Persistencia;

public class QueryBuilder3 {

	//Passar informacoes por meio de argumentos no construtor
	public QueryBuilder3() {
		
	}
	
	public String buildQuery() {
		return "select count(*) from curso";
	}
}
