package model;

import javax.swing.JMenuItem;

public abstract class MenuItem extends JMenuItem {
	
	public void setMenuItemText(String s) {
		this.setText(s);
		this.setPreferredSize(this.getPreferredSize());
	}
}
