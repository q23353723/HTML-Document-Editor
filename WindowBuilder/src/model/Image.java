package model;

import pattern.Visitor;

public class Image extends Glyph {
	private String attribute;
	
	public Image(String atr) {
		this.attribute = atr;
	}
		
	@Override
	public String getAttribute() {
		return this.attribute;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
