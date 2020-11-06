package model;

import javax.swing.JFrame;

import pattern.WindowImp;

public abstract class Window {
	private WindowImp windowimp;
	
	public Window(WindowImp imp) {
		this.windowimp = imp;
	}
	
	public JFrame drawFrame() {
		return this.windowimp.drawFrame();
	}
	
	public String getSystemName() {
		return this.windowimp.getSystemName();
	}
}
