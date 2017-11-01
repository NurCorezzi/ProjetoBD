package Persistencia;

public class QueryBuilder2 {

	//Passar informacoes por meio de argumentos no construtor
	public QueryBuilder2() {
		
	}
	
	public String buildQuery() {
		return "select NO_CURSO,count(*) from curso group by NO_CURSO";
	}
}
