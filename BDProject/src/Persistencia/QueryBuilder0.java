package Persistencia;

public class QueryBuilder0 {

	DatabaseActionListener dbListener;
	
	//Passar informacoes por meio de argumentos no construtor
	public QueryBuilder0(DatabaseActionListener dbListener) {
		this.dbListener = dbListener;
	}
	
	public DatabaseResponse buildQuery(String cat, String orderBy) {
	
		dbListener.queryRequested("call update_ies_table()");
		
		String query =  "select "+
						"NO_IES as NOME, " +
						"DS_CATEGORIA_ADMINISTRATIVA as CATEGORIA, "+
						"QT_TEC_TOTAL as TECNICOS, "+
						"QT_DOCENTE_TOTAL as DOCENTES, " +
						"QT_ALUNO_TOTAL as ALUNOS, " +
						"QT_TEC_TOTAL + QT_DOCENTE_TOTAL + QT_ALUNO_TOTAL as TOTAL "+
						"from ies "+
						"where DS_CATEGORIA_ADMINISTRATIVA = '"+cat+"' ";
		
		if(!orderBy.isEmpty())
			query += "order by " + orderBy;
	
		return dbListener.queryRequested(query);
	}
}
