package handler;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import model.CustomJTabbedPaneUI;

public class CloseAllAction extends AbstractAction {
	CustomJTabbedPaneUI tabPane = null;

	public CloseAllAction(String label, CustomJTabbedPaneUI tabPane) {
		super(label);
		this.tabPane = tabPane;
	}

	//������������ �� Action
	@Override 
	public void actionPerformed(ActionEvent e) {
		System.out.print(e.getActionCommand());
		if(tabPane != null) {
			tabPane.removeAll();
		}
	}
}
