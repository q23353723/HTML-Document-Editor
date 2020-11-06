package handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;


import main.WindowDemo;
import model.CustomJTabbedPaneUI;
import model.DialogWindow;
import model.MainWindow;


import pattern.HtmlStrategy;
import pattern.PlainStrategy;


public class MenuActionHandler implements ActionListener {
	private CustomJTabbedPaneUI tabPane = null;
	private MainWindow window;
	
	public MenuActionHandler(MainWindow window) {
		this.tabPane = window.getTabPane();
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent ev) {
		JTextPane Jtp = (JTextPane)((JScrollPane)tabPane.getSelectedComponent()).getViewport().getView();
		String event = ev.getActionCommand();
		switch(event) {
			case "HTML":
				window.setStrategy(new PlainStrategy());
				window.getStrategy().setTextPane(Jtp);
				window.show();
				break;
			case "文字":
				window.setStrategy(new HtmlStrategy());
				window.getStrategy().setTextPane(Jtp);
				window.show();
				window.add(Jtp);
				break;
			case "關於":
				DialogWindow dialog = new DialogWindow(WindowDemo.imp);
				dialog.showDialog("你現在正運行在" + window.getSystemName() + "系統\n目前編輯器版本為:1.0.0", "關於");
				break;
		}
    }
}
