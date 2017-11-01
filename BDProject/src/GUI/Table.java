package GUI;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Persistencia.DatabaseResponse;

public class Table extends JPanel{

	private JTable table;
	private CustomTableModel tableModel;
	private TableActionListener tableListener;
	
	public Table() {
		table = new JTable();
		tableModel = new CustomTableModel();
		
		tableListener = new TableActionListener() {
			@Override
			public void updateTable(DatabaseResponse dataRes) {
				tableModel.setResult(dataRes);
				tableModel.fireTableStructureChanged();
				tableModel.fireTableDataChanged();
			}
		};
		
		table.setModel(tableModel);
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	public TableActionListener getTableActionListener() {
		return tableListener;
	}
	
}
