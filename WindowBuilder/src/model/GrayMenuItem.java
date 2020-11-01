package model;

import java.awt.Color;

public class GrayMenuItem extends MenuItem{
	public GrayMenuItem() {
		this.setOpaque(true);
		this.setBackground(Color.GRAY);
		this.setForeground(Color.WHITE);
	}
}
