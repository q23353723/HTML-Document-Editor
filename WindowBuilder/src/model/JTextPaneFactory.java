package model;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class JTextPaneFactory extends Factory{
	public JTextPane factoryMethod() {
		
		//ScrollBar
		JScrollPane scrollBar1 = new JScrollPane();
		scrollBar1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JTextPane textPane = new JTextPane();
		textPane.setContentType("text/html");
		//textPane.setText("<p><img src='https://ichef.bbci.co.uk/news/800/cpsprodpb/172FF/production/_110957949_27aeb6ba-73c3-4c71-86bf-e885d7711975.jpg'></p>");
		
		return textPane;
	}
}
