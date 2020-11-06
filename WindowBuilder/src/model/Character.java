package model;


import pattern.Visitor;

public class Character extends Glyph {
	String c;
	
	public Character(String c) {
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
