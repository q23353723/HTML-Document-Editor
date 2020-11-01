package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Box;

public class PurpleMenuBar extends MenuBar{
	private Color bgColor;
	
	public PurpleMenuBar() {
		this.bgColor = new java.awt.Color(193, 123, 185);
		this.add(Box.createRigidArea(new Dimension(0,20)));
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(bgColor);
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
	
	public void addMenu(Menu m) {
		this.add(m);
	}
}
