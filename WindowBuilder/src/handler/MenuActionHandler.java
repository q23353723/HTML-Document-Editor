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
			case "��r":
				window.setStrategy(new HtmlStrategy());
				window.getStrategy().setTextPane(Jtp);
				window.show();
				window.add(Jtp);
				break;
			case "����":
				DialogWindow dialog = new DialogWindow(WindowDemo.imp);
				dialog.showDialog("�A�{�b���B��b" + window.getSystemName() + "�t��\n�ثe�s�边������:1.0.0", "����");
				break;
		}
    }
}
