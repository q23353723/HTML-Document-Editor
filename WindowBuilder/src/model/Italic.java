package model;

import pattern.Visitor;

public class Italic extends Decorator {
	
	public Italic(Glyph d){
		super(d);
	}
	
	public String getString() {
		return decoratee.getString();
	}

	@Override
	public void print() {
		System.out.print("<i>");
		decoratee.print();
		System.out.print("</i>");
	}
	
	@Override
	public void accept(Visitor visitor) {
		super.accept(visitor);
		visitor.visit(this);
	}
}
