package handler;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import main.WindowDemo;
import model.CustomJTabbedPaneUI;
import model.Factory;
import model.JTextPaneFactory;

public class TabActionHandler extends AbstractAction {
	CustomJTabbedPaneUI tabPane = null;
	Factory factory = null;

	public TabActionHandler(String label, CustomJTabbedPaneUI tabPane) {
		super(label);
		this.tabPane = tabPane;
	}
	
		@Override 
		public void actionPerformed(ActionEvent e) {
			String event = e.getActionCommand();
			switch(event) {
				case "增加頁籤"://增加頁籤 之 Action
					if(tabPane != null) {
						factory = new JTextPaneFactory();
						JTextPane textPane = factory.createProduct();
						JTextArea textArea = new JTextArea();
						WindowDemo.add(textPane, textArea);
						
						JScrollPane scrollBar1 = new JScrollPane(textPane);
						JScrollPane scrollBar2 = new JScrollPane(textArea);

						tabPane.addTab("newEdit" + (int)(tabPane.getTabCount() + 1), scrollBar1);
						tabPane.addTab("newHtmlEdit" + (int)(tabPane.getTabCount() + 1), scrollBar2);
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
