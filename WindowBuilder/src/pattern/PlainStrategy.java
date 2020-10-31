package pattern;

import javax.swing.JTextPane;

public class PlainStrategy implements ShowStrategy{
	public void show(JTextPane tp) {
		String st = tp.getText();
		tp.setContentType("text/plain");
		tp.setText(st);
	}
}
