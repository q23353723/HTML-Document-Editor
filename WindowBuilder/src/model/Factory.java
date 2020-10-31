package model;

import javax.swing.JTextPane;

public abstract class Factory {
	public JTextPane createProduct() {
		return this.factoryMethod();
	}
	public abstract JTextPane factoryMethod();
}
