package handler;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JViewport;

import main.WindowDemo;
import model.CustomJTabbedPaneUI;
import model.DialogWindow;
import model.MainWindow;
import model.MenuItem;

import pattern.HtmlStrategy;
import pattern.PlainStrategy;
import pattern.ShowStrategy;

public class MenuActionHandler implements ActionListener {
	CustomJTabbedPaneUI tabPane = null;
	MainWindow window;
	
	public MenuActionHandler(MainWindow window) {
		this.tabPane = window.getTabPane();
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent ev) {
		String event = ev.getActionCommand();
		switch(event) {
			case "HTML":
				Component[] comps = tabPane.getComponents();
				for(Component cmp : comps) {
					if(cmp instanceof JScrollPane) {
						JViewport vp = ((JScrollPane)cmp).getViewport();
						JTextPane Jtp = (JTextPane)(vp).getView();
						if(((JTextPane) Jtp).getContentType().equals("text/html")) {
							window.setStrategy(new PlainStrategy());
							window.getStrategy().show((JTextPane)Jtp);
						}
						else if(((JTextPane) Jtp).getContentType().equals("text/plain")) {
							window.setStrategy(new HtmlStrategy());
							window.getStrategy().show((JTextPane)Jtp);
							window.add((JTextPane) Jtp, new JTextArea());
						}
					}
				}
				break;
			case "����":
				DialogWindow dialog = new DialogWindow(WindowDemo.imp);
				dialog.showDialog("�A�{�b���B��b" + window.getSystemName() + "�t��\n�ثe�s�边������:1.0.0", "����");
		}
    }
}
