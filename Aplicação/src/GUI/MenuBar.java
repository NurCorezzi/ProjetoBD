package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuBar extends JMenuBar{

	private JMenu optionsMenu;
	private JMenuItem queryItem;
	private JMenuItem hideFormsItem;
	private JMenuItem exitItem;
	private HideActionListener hideListener;
	private QueryActionListener queryListener;
	
	public MenuBar() {
		
		optionsMenu = new JMenu("Actions");
		exitItem = new JMenuItem("Exit");
		queryItem = new JMenuItem("Make query");
		hideFormsItem = new JMenuItem("Hide/Show forms");
		
		hideFormsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		hideFormsItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				hideListener.actionPerformed();
			}
		});
		
		queryItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		queryItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				queryListener.actionPerformed();
			}
		});
		
		
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		

		optionsMenu.add(queryItem);
		optionsMenu.add(hideFormsItem);
		optionsMenu.addSeparator();
		optionsMenu.add(exitItem);
		add(optionsMenu);
	}
	
	public void setHideActionListener(HideActionListener actionListener) {
		hideListener = actionListener;
	}

	public void setQueryActionListener(QueryActionListener actionListener) {
		queryListener = actionListener;
	}
}
