package Persistencia;

public class QueryBuilder4 {

	//Passar informacoes por meio de argumentos no construtor
	public QueryBuilder4() {
		
	}
	
	public String buildQuery() {
		return "select DS_CATEGORIA_ADMINISTRATIVA, count(*) from curso group by DS_CATEGORIA_ADMINISTRATIVA";
	}
}
