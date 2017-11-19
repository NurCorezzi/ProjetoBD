package GUI;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Persistencia.DatabaseResponse;

public class Table extends JPanel{

	private JTable table;
	private CustomTableModel tableModel;
	private TableActionListener tableListener;
	private ReorderTableListener reorderListener;
	
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
			
		table.getTableHeader().addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(reorderListener != null) {
					
					if(e.getButton() == MouseEvent.BUTTON1){
					    int col = table.columnAtPoint(e.getPoint());
					    reorderListener.onActionPerformed(table.getColumnName(col) + " asc");
					}
					if(e.getButton() == MouseEvent.BUTTON3){
					    int col = table.columnAtPoint(e.getPoint());
					    reorderListener.onActionPerformed(table.getColumnName(col) + " desc");
					}
					
				}
			}
		});
		
		table.setModel(tableModel);
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	public void setReorderTableListener(ReorderTableListener reorderListener) {
		this.reorderListener = reorderListener;
	}
	
	public TableActionListener getTableActionListener() {
		return tableListener;
	}
	
}
