package Persistencia;

public class QueryBuilder1 {

	//Passar informacoes por meio de argumentos no construtor
	public QueryBuilder1() {
		
	}
	
	public String buildQuery() {
		return "select * from curso where NO_IES = 'UNIVERSIDADE DE BRAS?LIA'";
	}
}
