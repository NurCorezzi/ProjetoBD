package GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Persistencia.Database;
import Persistencia.DatabaseResponse;

public class MainFrame extends JFrame{
	
	private MenuBar menuBar;
	private Table table;
	private TabbedForm tabbedForm;
	
	private Database db;
	
	public MainFrame(String appName) {
		super(appName);
		setLayout(new BorderLayout());
		
		menuBar = new MenuBar();
		table = new Table();
		tabbedForm = new TabbedForm();
		
		menuBar.setHideActionListener(new HideActionListener() {
			@Override
			public void actionPerformed(boolean arg0) {
				tabbedForm.setVisible(arg0);
			}
		});
		
		add(menuBar, BorderLayout.NORTH);
		add(tabbedForm, BorderLayout.CENTER);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension minScrnSize = new Dimension(750,500);
		setMinimumSize(minScrnSize);
		setSize(minScrnSize);
		setVisible(true);
		
		setDatabase();
	}
	
	private void setDatabase() {
		
		db = new Database();
		try {
			db.connect();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "No database connection");
			e.printStackTrace();
		}
		
		tabbedForm.setDatabaseActionListener(new DatabaseActionListener() {
			@Override
			public DatabaseResponse queryRequested(String query) {
				return db.query(query);
			}
		});
		
	}
}
