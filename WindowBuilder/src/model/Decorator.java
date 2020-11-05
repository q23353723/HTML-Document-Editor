package model;

import pattern.Visitor;

public abstract class Decorator extends Glyph{
	Glyph decoratee;
	
	Decorator(Glyph decoratee){
		this.decoratee = decoratee;
	}
	
	@Override
	public String getString() {
		return decoratee.getString();
	}
	
	@Override
	public void accept(Visitor visitor) {
		decoratee.accept(visitor);
	}
}
