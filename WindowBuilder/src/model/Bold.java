package model;

import pattern.Visitor;

public class Bold extends Decorator {
	
	public Bold(Glyph d){
		super(d);
	}
	
	@Override
	public String getString() {
		return super.getString();
	}
	
	@Override
	public void print() {
		System.out.print("<b>");
		decoratee.print();
		System.out.print("</b>");
	}
	
	@Override
	public void accept(Visitor visitor) {
		super.accept(visitor);
		visitor.visit(this);
	}
}
