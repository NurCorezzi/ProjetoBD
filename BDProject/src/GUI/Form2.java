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
import javax.swing.JTextPane;

import Persistencia.DatabaseActionListener;
import Persistencia.DatabaseResponse;
import Persistencia.QueryBuilder2;

public class Form2 extends JPanel{
	
	private JButton okButton;
	private JTextField nameField;
	private JComboBox uniComboBox;
	private JComboBox campusComboBox;
	
	private DatabaseActionListener dbListener;
	private TableActionListener tableListener;
	private ReorderTableListener reorderListener;
	
	public Form2() {
		setPreferredSize(new Dimension(350, getSize().height));
		
		okButton = new JButton("Ok");
		nameField = new JTextField(10);
		uniComboBox = new JComboBox();
		campusComboBox = new JComboBox();
		
		uniComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(dbListener != null)
					setCampusComboBox();
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
		
//		Border outter = BorderFactory.createTitledBorder("Quantidade de alunos ingressantes, cursando e formandos por instituição"); 
//		Border inner = BorderFactory.createEmptyBorder(30,30,30,30); 
//		setBorder(BorderFactory.createCompoundBorder(outter, inner));
		
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.fill = GridBagConstraints.NONE;
		
		/**************FIRST ROW******************/
		constraints.gridy = 0;
		
		constraints.gridx = 0;
		constraints.weighty = 0.1;
		constraints.weightx = 1;
		constraints.anchor  = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Consulta: "), constraints);
		
		constraints.gridx = 1;
		constraints.weighty = 0.1;
		constraints.weightx = 1;
		constraints.anchor  = GridBagConstraints.FIRST_LINE_START;
		
		JTextPane title = new JTextPane();
		title.setPreferredSize(new Dimension(170,75));
		title.setText("Quantidade de alunos ingressantes, cursando e concluintes de cada curso ofertado pela instituicao");
		title.setEditable(false);
		add(title, constraints);
		
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
		add(new JLabel("Campus: "), constraints);
		
		constraints.gridx = 1;
		constraints.weighty = 0.1;
		constraints.weightx = 1;
		constraints.anchor  = GridBagConstraints.FIRST_LINE_START;
		add(campusComboBox, constraints);
		
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
			String uni = uniComboBox.getSelectedItem().toString();
			String campus = campusComboBox.getSelectedItem().toString();
			
			DatabaseResponse dataResp = queryBuilder.buildQuery( campus, uni ,orderBy);
			
			if(dataResp == null)
				JOptionPane.showMessageDialog(Form2.this.getParent(), "Query not valid", "Error", JOptionPane.ERROR_MESSAGE);
			else
				tableListener.updateTable(dataResp);
		}
		else
			System.out.println("null listener");
	}
	
	private void setCampusComboBox() {
		if(dbListener != null) {

			String uni = uniComboBox.getSelectedItem().toString();
			String requestUni = "select CO_IES from ies where NO_IES = '"+uni+"' ";
			String uniId = dbListener.queryRequested(requestUni).getData().get(1).get(0).toString();
			
			String campusQuery = "select distinct(NO_LOCAL_OFERTA) from local_oferta " + 
					"where CO_IES = "+uniId+" and CO_LOCAL_OFERTA_IES in " + 
					"(select distinct(CO_LOCAL_OFERTA) from curso " + 
					"where CO_IES = "+ uniId +") ";
			
			DatabaseResponse dataResp = dbListener.queryRequested(campusQuery);
			
			if(dataResp != null)
			{
				DefaultComboBoxModel uniModel = new DefaultComboBoxModel();
				for(int i = 1; i < dataResp.getData().size(); i++)
					uniModel.addElement(dataResp.getData().get(i).get(0));
				
				Dimension size = new Dimension(250, campusComboBox.getPreferredSize().height);
				campusComboBox.setSize(size);
				campusComboBox.setPreferredSize(size);
				campusComboBox.setModel(uniModel);	
				campusComboBox.setSelectedItem(campusComboBox.getItemAt(0));
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
