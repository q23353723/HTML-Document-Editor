package model;

import pattern.Visitor;

public class UnderLine extends Decorator {
	
	public UnderLine(Glyph decoratee) {
		super(decoratee);
	}
	
	@Override
	public String getString() {
		return decoratee.getString();
	}
	
	@Override
	public void accept(Visitor visitor) {
		super.accept(visitor);
		visitor.visit(this);
	}
}
