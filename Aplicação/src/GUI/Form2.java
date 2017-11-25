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
import Persistencia.QueryBuilder2;

public class Form2 extends Form{
	
	private JButton okButton;
	private JTextField nameField;
	private JComboBox uniComboBox;
	private JComboBox localComboBox;
	
	private DatabaseActionListener dbListener;
	private TableActionListener tableListener;
	private ReorderTableListener reorderListener;
	
	public Form2() {
		setPreferredSize(new Dimension(350, getSize().height));
		
		okButton = new JButton("Ok");
		nameField = new JTextField(10);
		uniComboBox = new JComboBox();
		localComboBox = new JComboBox();
		
		uniComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(dbListener != null)
					setLocalComboBox();
			}
		});
		
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
		titleText.setText("Quantidade de alunos ingressantes, cursando e concluintes de cada curso ofertado pelas instituições");
		titleText.setEditable(false);
		titleText.setBorder(BorderFactory.createCompoundBorder(inner, outter));
		add( titleText, constraints);
		
		/**************NEXT ROW******************/
		/**************NEXT ROW******************/
		constraints.gridy++;
		
		constraints.gridx = 0;
		constraints.weighty = 0.1;
		constraints.weightx = 1;
		constraints.anchor  = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Instituicao: "), constraints);
		
		constraints.gridx = 1;
		constraints.weighty = 0.1;
		constraints.weightx = 1;
		constraints.anchor  = GridBagConstraints.FIRST_LINE_START;
		add(uniComboBox, constraints);
		
		/**************NEXT ROW******************/
		constraints.gridy++;
		
		constraints.gridx = 0;
		constraints.weighty = 0.1;
		constraints.weightx = 1;
		constraints.anchor  = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Local: "), constraints);
		
		constraints.gridx = 1;
		constraints.weighty = 0.1;
		constraints.weightx = 1;
		constraints.anchor  = GridBagConstraints.FIRST_LINE_START;
		add(localComboBox, constraints);
		
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
			
			QueryBuilder2 queryBuilder = new QueryBuilder2(dbListener);
			String campus = localComboBox.getSelectedItem().toString();
			
			DatabaseResponse dataResp = queryBuilder.buildQuery( campus ,orderBy);
			
			if(dataResp == null)
				JOptionPane.showMessageDialog(Form2.this.getParent(), "Query not valid", "Error", JOptionPane.ERROR_MESSAGE);
			else
				tableListener.updateTable(dataResp);
		}
		else
			System.out.println("null listener");
	}
	
	private void setLocalComboBox() {
		if(dbListener != null) {

			String uni = uniComboBox.getSelectedItem().toString();
			String requestUni = "select CO_IES from ies where NO_IES = '"+uni+"' ";
			String uniId = dbListener.queryRequested(requestUni).getData().get(1).get(0).toString();
			
			String campusQuery = "select distinct(NO_LOCAL_OFERTA) from local_oferta " + 
					"where CO_IES_LOCAL = "+uniId+ " ";
			
			DatabaseResponse dataResp = dbListener.queryRequested(campusQuery);
			
			if(dataResp != null)
			{
				DefaultComboBoxModel uniModel = new DefaultComboBoxModel();
				for(int i = 1; i < dataResp.getData().size(); i++)
					uniModel.addElement(dataResp.getData().get(i).get(0));
				
				Dimension size = new Dimension(250, localComboBox.getPreferredSize().height);
				localComboBox.setSize(size);
				localComboBox.setPreferredSize(size);
				localComboBox.setModel(uniModel);	
				localComboBox.setSelectedItem(localComboBox.getItemAt(0));
			}
		}
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
				uniComboBox.setSelectedItem(uniComboBox.getItemAt(0));
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
		setUniComboBox();
	}
	
}
