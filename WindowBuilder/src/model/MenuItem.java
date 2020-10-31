package model;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JMenuItem;

public abstract class MenuItem extends JMenuItem {
	
	public void setMenuItemText(String s) {
		this.setText(s);
		this.setFont(new Font("·L³n¥¿¶ÂÅé", Font.BOLD, 20));
		this.setPreferredSize(this.getPreferredSize());
	}
}
