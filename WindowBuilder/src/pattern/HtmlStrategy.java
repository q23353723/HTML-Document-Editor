package pattern;

import javax.swing.JTextPane;

public class HtmlStrategy implements ShowStrategy{
	private JTextPane TextPane;
	
	public void setTextPane(JTextPane tp) {
		this.TextPane = tp;
	}
	
	public void show() {
		String st = TextPane.getText();
		TextPane.setContentType("text/html");
		TextPane.setText(st);
	}
}
