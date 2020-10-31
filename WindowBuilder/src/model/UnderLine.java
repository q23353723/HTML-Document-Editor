package model;

import pattern.Visitor;

public class UnderLine extends Decorator {
	
	public UnderLine(Glyph d) {
		super(d);
	}
	
	public String getString() {
		return decoratee.getString();
	}
	
	@Override
	public void print() {
		System.out.print("<u>");
		decoratee.print();
		System.out.print("</u>");
	}
	
	@Override
	public void accept(Visitor visitor) {
		super.accept(visitor);
		visitor.visit(this);
	}
}
