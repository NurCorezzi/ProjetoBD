package GUI;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Persistencia.DatabaseActionListener;
import Persistencia.DatabaseResponse;
import Persistencia.QueryBuilder0;

public class Form0 extends JPanel{
	
	private JButton okButton;
	private JTextField nameField;
	private JComboBox uniComboBox;
	
	private DatabaseActionListener dbListener;
	private TableActionListener tableListener;
	
	public Form0() {
		setPreferredSize(new Dimension(350, getSize().height));
		
		okButton = new JButton("Ok");
		nameField = new JTextField(10);
		uniComboBox = new JComboBox();
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				performQuery();
			}
		});
		
//		Border outter = BorderFactory.createTitledBorder("Campos vao ficar aqui"); 
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
		constraints.gridy ++;
		
		constraints.gridx = 0;
		constraints.weighty = 0.1;
		constraints.weightx = 1;
		constraints.anchor  = GridBagConstraints.LINE_END;
		add(new JLabel("Univerisdade: "), constraints);
		
		constraints.gridx = 1;
		constraints.weighty = 0.1;
		constraints.weightx = 1;
		constraints.anchor  = GridBagConstraints.LINE_START;
		add(uniComboBox, constraints);
		
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
	
	private void performQuery() {
		if(dbListener != null) {
			
			QueryBuilder0 queryBuilder = new QueryBuilder0(dbListener);
			DatabaseResponse dataResp = queryBuilder.makeQuery();
			
			if(dataResp == null)
				JOptionPane.showMessageDialog(Form0.this.getParent(), "Query not valid", "Error", JOptionPane.ERROR_MESSAGE);
			else
				tableListener.updateTable(dataResp);
		}
		else
			System.out.println("null listener");
	}
	
	private void setUniComboBox() {
		
		if(dbListener != null) {
			
			DatabaseResponse dataResp = dbListener.queryRequested("select distinct NO_IES from ies order by NO_IES");
			
			if(dataResp != null)
			{
				DefaultComboBoxModel uniModel = new DefaultComboBoxModel();
				for(int i = 1; i < dataResp.getData().size(); i++)
					uniModel.addElement(dataResp.getData().get(i).get(0));
				
				Dimension size = new Dimension(250, uniComboBox.getPreferredSize().height);
				uniComboBox.setSize(size);
				uniComboBox.setPreferredSize(size);
				uniComboBox.setModel(uniModel);	
			}
		}
	}
	
	public void setTableActionListener(TableActionListener tableListener) {
		this.tableListener = tableListener;
	}
	
	public void setDatabaseActionListener(DatabaseActionListener dbListener) {
		this.dbListener = dbListener;
		setUniComboBox();
	}
	
}
