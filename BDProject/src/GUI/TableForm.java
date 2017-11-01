package GUI;
import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

class TableForm extends JPanel{
	
	private JPanel form;
	private Table table;
	
	public TableForm(JPanel form0, Table table) {
		
		this.form = form0;
		this.table = table;
		
		setLayout(new BorderLayout());
		add(form0, BorderLayout.WEST);
		add(table, BorderLayout.CENTER);
	}
}