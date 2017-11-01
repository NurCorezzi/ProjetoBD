package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuBar extends JMenuBar{

	private JMenu optionsMenu;
	private JCheckBoxMenuItem hideFormsItem;
	private JMenuItem exitItem;
	private HideActionListener hideListener;
	
	public MenuBar() {
		
		optionsMenu = new JMenu("Options");
		exitItem = new JMenuItem("Exit");
		hideFormsItem = new JCheckBoxMenuItem("Hide forms");
		
		hideFormsItem.setState(false);
		hideFormsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		hideFormsItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				hideListener.actionPerformed(!hideFormsItem.getState());
			}
		});
		
		
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		
		optionsMenu.add(hideFormsItem);
		optionsMenu.addSeparator();
		optionsMenu.add(exitItem);
		add(optionsMenu);
	}
	
	public void setHideActionListener(HideActionListener actionListener) {
		hideListener = actionListener;
	}
}
