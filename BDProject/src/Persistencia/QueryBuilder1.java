package Persistencia;

public class QueryBuilder1 {

	DatabaseActionListener dbListener;
	
	//Passar informacoes por meio de argumentos no construtor
	public QueryBuilder1(DatabaseActionListener dbListener) {
		this.dbListener = dbListener;
	}
	
	public DatabaseResponse buildQuery(String regiao, String sexo, String orderBy) {
		
		String query =  "select DS_ESCOLARIDADE_DOCENTE as ESCOLARIDADE, "+
				 			   "count(*) as DOCENTES, " +
				 			   "avg(NU_IDADE_DOCENTE) as 'IDADE_MEDIA', " +
				 			   "(select DS_COR_RACA_DOCENTE " + 
				 			   "	from (select DC.DS_COR_RACA_DOCENTE, " + 
				 			   "				 DC.DS_ESCOLARIDADE_DOCENTE, "+	
				 			   "				 count(*) CONTA " + 
				 			   "		   from docente as DC join ies as IE on DC.CO_IES = IE.CO_IES " + 
				 			   "           where IE.NO_REGIAO_IES = '" +regiao+ "' " + 
				 			   "				 and DC.DS_SEXO_DOCENTE ='" + sexo+ "' " + 
				 			   "		   group by DC.DS_COR_RACA_DOCENTE, DS_ESCOLARIDADE_DOCENTE) as tempTab " + 
				 			   "		   where tempTab.DS_ESCOLARIDADE_DOCENTE = D.DS_ESCOLARIDADE_DOCENTE " +
				 			   "		   order by tempTab.CONTA desc limit 1) as RACA " +
				"from docente as D join ies as I on D.CO_IES = I.CO_IES " +
				"where I.NO_REGIAO_IES = '"+regiao+"' " +
					  "and D.DS_SEXO_DOCENTE = '"+sexo+"' " +
				"group by D.DS_ESCOLARIDADE_DOCENTE ";
	
		if(!orderBy.isEmpty())
			query += "order by " + orderBy;
	
		return dbListener.queryRequested(query);
	}
}
