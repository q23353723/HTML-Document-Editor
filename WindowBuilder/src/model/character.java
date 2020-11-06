package model;


import pattern.Visitor;

public class character extends Glyph {
	String c;
	
	public character(String c) {
		this.c = c;
	}
	
	@Override
	public String getString() {
		return this.c;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
