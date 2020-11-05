package model;

import pattern.Visitor;

public class Italic extends Decorator {
	
	public Italic(Glyph decoratee){
		super(decoratee);
	}
	
	public String getString() {
		return decoratee.getString();
	}
	
	@Override
	public void accept(Visitor visitor) {
		super.accept(visitor);
		visitor.visit(this);
	}
}
