package GUI;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Persistencia.DatabaseActionListener;
import Persistencia.DatabaseResponse;
import Persistencia.QueryBuilder3;

public class Form3 extends JPanel{
	
	private JButton okButton;
	private JTextField nameField;
	
	private DatabaseActionListener dbListener;
	private TableActionListener tableListener;
	
	public Form3() {
		setPreferredSize(new Dimension(250, getSize().height));
		
		okButton = new JButton("Ok");
		nameField = new JTextField(10);
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(dbListener != null) {
					
					QueryBuilder3 queryBuilder = new QueryBuilder3();
					DatabaseResponse dataResp = dbListener.queryRequested(queryBuilder.buildQuery());
					
					if(dataResp == null)
						JOptionPane.showMessageDialog(Form3.this.getParent(), "Query not valid", "Error", JOptionPane.ERROR_MESSAGE);
					else
						tableListener.updateTable(dataResp);
				}
				else
					System.out.println("null listener");
			}
		});
		
//		Border outter = BorderFactory.createTitledBorder("Info"); 
//		Border inner = BorderFactory.createEmptyBorder(30,30,30,30); 
//		setBorder(BorderFactory.createCompoundBorder(outter, inner));
		
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		/**************FIRST ROW******************/
		constraints.gridy = 0;
		
		constraints.gridx = 0;
		constraints.weighty = 0.1;
		constraints.weightx = 1;
		constraints.anchor  = GridBagConstraints.LINE_END;
		add(new JLabel("Nome: "), constraints);
		
		constraints.gridx = 1;
		constraints.weighty = 0.1;
		constraints.weightx = 1;
		constraints.anchor  = GridBagConstraints.LINE_START;
		add(nameField, constraints);
		
		/**************NEXT ROW******************/
		/**************NEXT ROW******************/
		/**************NEXT ROW******************/
		constraints.gridy++;
		
		constraints.gridx = 1;
		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.anchor  = GridBagConstraints.FIRST_LINE_START;
		add(okButton, constraints);
		
		
		constraints.fill = GridBagConstraints.NONE;
	}
	
	public void setTableActionListener(TableActionListener tableListener) {
		this.tableListener = tableListener;
	}
	
	public void setDatabaseActionListener(DatabaseActionListener dbListener) {
		this.dbListener = dbListener;
	}
	
}
