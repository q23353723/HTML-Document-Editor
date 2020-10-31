package pattern;

import javax.swing.JTextPane;

public class HtmlStrategy implements ShowStrategy{
	
	
	public void show(JTextPane tp) {
		String st = tp.getText();
		tp.setContentType("text/html");
		tp.setText(st);
	}
}
