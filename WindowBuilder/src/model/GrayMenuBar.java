package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Box;

public class GrayMenuBar extends MenuBar {
private Color bgColor;
	
	public GrayMenuBar() {
		this.bgColor = Color.GRAY;
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
