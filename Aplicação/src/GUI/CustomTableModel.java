package GUI;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.sql.rowset.CachedRowSet;
import javax.swing.table.AbstractTableModel;

import Persistencia.DatabaseResponse;

public class CustomTableModel extends AbstractTableModel{

	DatabaseResponse dataRes;
	
	public void setResult(DatabaseResponse dataRes) {
		this.dataRes = dataRes;
	}
	
	@Override
	public String getColumnName(int column) {
		
		if(dataRes == null || column < 0) return "null";
		
		return dataRes.getData().get(0).get(column).toString();
	}

	@Override
	public int getColumnCount() {

		if(dataRes == null) return 1;
		
		return dataRes.getData().get(0).size();
	}

	@Override
	public int getRowCount() {
		
		if(dataRes == null) return 0;
		
		return dataRes.getData().size() - 1;
	}

	@Override
	public Object getValueAt(int i, int j) {
		
		if(dataRes == null) return null;
		
		// Primeira linha sao nomes das colunas
		i++;
		
		return dataRes.getData().get(i).get(j);
	}
}
