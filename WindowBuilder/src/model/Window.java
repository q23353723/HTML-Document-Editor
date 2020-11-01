package model;

import pattern.WindowImp;

public abstract class Window {
	WindowImp windowimp;
	
	public Window(WindowImp imp) {
		this.windowimp = imp;
	}
}
