package model;

import java.awt.Font;

import javax.swing.JMenu;

public abstract class Menu extends JMenu{
	public void setMenuText(String s) {
		this.setText(s);
		this.setFont(new Font("�L�n������", Font.BOLD, 20));
	}
}
