package handler;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import model.CustomJTabbedPaneUI;
import model.MainWindow;

public class TabActionHandler extends AbstractAction {
	CustomJTabbedPaneUI tabPane = null;
	MainWindow window;

	public TabActionHandler(String label, MainWindow window) {
		super(label);
		this.tabPane = window.getTabPane();
		this.window = window;
	}
	
		@Override 
		public void actionPerformed(ActionEvent e) {
			String event = e.getActionCommand();
			switch(event) {
				case "增加頁籤"://增加頁籤 之 Action
					if(tabPane != null) {
						JTextPane textPane = new JTextPane();
						textPane.setContentType("text/html");
						window.add(textPane);
						window.addKeyListener(textPane);
						
						JScrollPane scrollBar1 = new JScrollPane(textPane);

						tabPane.addTab("newEdit" + (int)(tabPane.getTabCount() + 1), scrollBar1);
						tabPane.setSelectedIndex(tabPane.getTabCount() - 1);
					}
					break;
				case "關閉全部頁籤"://關閉全部頁籤 之 Action
					if(tabPane != null) {
						tabPane.removeAll();
					}
					break;
			}

		}
}
