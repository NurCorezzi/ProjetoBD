package Persistencia;

public class QueryBuilder0 {

	//Passar informacoes por meio de argumentos no construtor
	public QueryBuilder0() {
		
	}
	
	public String buildQuery() {
		return "select NO_IES ,count(*) CURSOS from curso group by CO_IES order by count(*)";
	}
}
