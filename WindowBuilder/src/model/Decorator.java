package model;

import pattern.Visitor;

public abstract class Decorator extends Glyph{
	Glyph decoratee;
	
	Decorator(Glyph c){
		this.decoratee = c;
	}
	
	@Override
	public String getString() {
		return decoratee.getString();
	}
	
	@Override
	public void print() {
		decoratee.print();
	}
	
	public void accept(Visitor visitor) {
		decoratee.accept(visitor);
	}
}
