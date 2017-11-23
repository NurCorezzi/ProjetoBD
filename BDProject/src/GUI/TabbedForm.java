package GUI;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Persistencia.DatabaseActionListener;

public class TabbedForm extends JPanel{
	
	private JTabbedPane tabbedPanel;
	
	private Form0 form0;
	private Form1 form1;
	private Form2 form2;
	private Form3 form3;
	private Form4 form4;
	
	private Table table0;
	private Table table1;
	private Table table2;
	private Table table3;
	private Table table4;
	
	public TabbedForm() {
		tabbedPanel = new JTabbedPane();
		
		table0 = new Table();
		table1 = new Table();
		table2 = new Table();
		table3 = new Table();
		table4 = new Table();
			
		form0 = new Form0(); form0.setTableActionListener(table0.getTableActionListener());
		form1 = new Form1(); form1.setTableActionListener(table1.getTableActionListener());
		form2 = new Form2(); form2.setTableActionListener(table2.getTableActionListener());
		form3 = new Form3(); form3.setTableActionListener(table3.getTableActionListener());
		form4 = new Form4(); form4.setTableActionListener(table4.getTableActionListener());

		table0.setReorderTableListener(form0.getReorderTableListener());
		table1.setReorderTableListener(form1.getReorderTableListener());
		table2.setReorderTableListener(form2.getReorderTableListener());
		table3.setReorderTableListener(form3.getReorderTableListener());
		table4.setReorderTableListener(form4.getReorderTableListener());
		
		tabbedPanel.addTab("Consulta 0", new TableForm(form0, table0));
		tabbedPanel.addTab("Consulta 1", new TableForm(form1, table1));
		tabbedPanel.addTab("Consulta 2", new TableForm(form2, table2));
		tabbedPanel.addTab("Consulta 3", new TableForm(form3, table3));
		tabbedPanel.addTab("Consulta 4", new TableForm(form4, table4));
		
		setLayout(new BorderLayout());
		add(tabbedPanel, BorderLayout.CENTER);
	}
	
	public void setDatabaseActionListener(DatabaseActionListener dbListener) {
		form0.setDatabaseActionListener(dbListener);
		form1.setDatabaseActionListener(dbListener);
		form2.setDatabaseActionListener(dbListener);
		form3.setDatabaseActionListener(dbListener);
		form4.setDatabaseActionListener(dbListener);
	}
	
	public void changeFormVisibility()
	{
		TableForm cur = (TableForm)tabbedPanel.getSelectedComponent();
		cur.changeFormVisibility();
	}
	
	public void performQuery()
	{
		TableForm cur = (TableForm)tabbedPanel.getSelectedComponent();
		cur.performQuery();
	}
}
