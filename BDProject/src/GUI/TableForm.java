package GUI;
import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

class TableForm extends JPanel{
	
	private Form form;
	private Table table;
	
	public TableForm(Form form, Table table) {
		
		this.form = form;
		this.table = table;
		
		setLayout(new BorderLayout());
		add(form, BorderLayout.WEST);
		add(table, BorderLayout.CENTER);
	}
	
	public void changeFormVisibility()
	{
		form.setVisible( !form.isVisible() );	
	}
	
	public void performQuery()
	{
		form.performQuery("");
	}
}