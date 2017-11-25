package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;

import Persistencia.DatabaseActionListener;
import Persistencia.DatabaseResponse;
import Persistencia.QueryBuilder3;

public class Form3 extends Form{
	
	private JButton okButton;
	private JTextField nameField;
	private JComboBox regiaoComboBox;
	
	private DatabaseActionListener dbListener;
	private TableActionListener tableListener;
	private ReorderTableListener reorderListener;
	
	public Form3() {
		setPreferredSize(new Dimension(350, getSize().height));
		
		okButton = new JButton("Ok");
		nameField = new JTextField(10);
		regiaoComboBox = new JComboBox();
		
		reorderListener = new ReorderTableListener() {
			@Override
			public void onActionPerformed(String reorderBy) {
				
				performQuery(reorderBy);
			}
		};
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				performQuery("");
			}
		});
		
		Border outter = BorderFactory.createTitledBorder("Consulta"); 
		Border inner = BorderFactory.createLineBorder(Color.BLACK); 
//		setBorder(BorderFactory.createCompoundBorder(outter, inner));
		
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.fill = GridBagConstraints.NONE;
		
		/**************FIRST ROW******************/
		constraints.gridy = 0;
		
		constraints.gridx = 1;
		constraints.weighty = 0.1;
		constraints.weightx = 1;
		constraints.anchor  = GridBagConstraints.LINE_START;
		
		JTextPane titleText = new JTextPane();
		titleText.setPreferredSize(new Dimension(250,80));
		titleText.setText("Quantidade de alunos ingressantes, cursando e formandos das instituições por regiões ");
		titleText.setEditable(false);
		titleText.setBorder(BorderFactory.createCompoundBorder(inner, outter));
		add( titleText, constraints);
		
		/**************NEXT ROW******************/
		constraints.gridy++;
		
		constraints.gridx = 0;
		constraints.weighty = 0.1;
		constraints.weightx = 1;
		constraints.anchor  = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Regiao: "), constraints);
		
		constraints.gridx = 1;
		constraints.weighty = 0.1;
		constraints.weightx = 1;
		constraints.anchor  = GridBagConstraints.FIRST_LINE_START;
		add(regiaoComboBox, constraints);
		
		/**************NEXT ROW******************/
		constraints.gridy++;
		
		constraints.gridx = 1;
		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.anchor  = GridBagConstraints.FIRST_LINE_START;
		add(okButton, constraints);
		
		
	}
	
	public void performQuery(String orderBy) {
		if(dbListener != null) {
			
			QueryBuilder3 queryBuilder = new QueryBuilder3(dbListener);
			String regiao = regiaoComboBox.getSelectedItem().toString();
			
			DatabaseResponse dataResp = queryBuilder.buildQuery(regiao ,orderBy);
			
			if(dataResp == null)
				JOptionPane.showMessageDialog(Form3.this.getParent(), "Query not valid", "Error", JOptionPane.ERROR_MESSAGE);
			else
				tableListener.updateTable(dataResp);
		}
		else
			System.out.println("null listener");
	}
	
	private void setRegiaoComboBox() {
		if(dbListener != null) {
			
			DatabaseResponse dataResp = dbListener.queryRequested("select distinct (NO_REGIAO_IES) from ies order by NO_REGIAO_IES");
			
			if(dataResp != null)
			{
				DefaultComboBoxModel uniModel = new DefaultComboBoxModel();
				for(int i = 1; i < dataResp.getData().size(); i++)
					uniModel.addElement(dataResp.getData().get(i).get(0));
				
				Dimension size = new Dimension(250, regiaoComboBox.getPreferredSize().height);
				regiaoComboBox.setSize(size);
				regiaoComboBox.setPreferredSize(size);
				regiaoComboBox.setModel(uniModel);	
				regiaoComboBox.setSelectedItem(regiaoComboBox.getItemAt(0));
			}
		}
	}
	
	public ReorderTableListener getReorderTableListener() {
		return reorderListener;
	}
	
	public void setTableActionListener(TableActionListener tableListener) {
		this.tableListener = tableListener;
	}
	
	public void setDatabaseActionListener(DatabaseActionListener dbListener) {
		this.dbListener = dbListener;
		setRegiaoComboBox();
	}
	
}
