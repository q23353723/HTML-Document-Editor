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
import model.MainWindow;
import model.MenuItem;

import pattern.HtmlStrategy;
import pattern.PlainStrategy;
import pattern.ShowStrategy;

public class MenuActionHandler implements ActionListener {
	CustomJTabbedPaneUI tabPane = null;
	MainWindow window;
	
	public MenuActionHandler(CustomJTabbedPaneUI tabPane, MainWindow window) {
		this.tabPane = tabPane;
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent ev) {
		System.out.print(ev.getActionCommand());
  	  Component[] comps = tabPane.getComponents();
  	  for(Component cmp : comps) {
  		  if(cmp instanceof JScrollPane) {
  			  JViewport vp = ((JScrollPane)cmp).getViewport();
  			  JTextPane Jtp = (JTextPane)(vp).getView();
  			  System.out.print("test");
  			  if(((JTextPane) Jtp).getContentType().equals("text/html")) {  				  
  				  window.setStrategy(new PlainStrategy());
  				  window.getStrategy().show((JTextPane)Jtp);
  				  System.out.print(((JTextPane) Jtp).getContentType());
  			  }
  			  else if(((JTextPane) Jtp).getContentType().equals("text/plain")) {
  				  window.setStrategy(new HtmlStrategy());
  				  window.getStrategy().show((JTextPane)Jtp);
  				  WindowDemo.add((JTextPane) Jtp, new JTextArea());
  				  System.out.print(((JTextPane) Jtp).getContentType());
  			  }
  		  }
  	  }
    }
}
