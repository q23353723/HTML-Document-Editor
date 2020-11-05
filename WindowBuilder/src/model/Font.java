package model;

import org.jsoup.nodes.Attributes;

import pattern.Visitor;

public class Font extends Decorator {
	private Attributes attributes;
	
	public Font(Glyph decoratee){
		super(decoratee);
	}
	
	@Override
	public void setAttributes(Attributes atr) {
		this.attributes = atr;
	}
	
	@Override
	public Attributes getAttributes() {
		return this.attributes;
	}
	
	@Override
	public String getString() {
		return super.getString();
	}
	
	@Override
	public void accept(Visitor visitor) {
		super.accept(visitor);
		visitor.visit(this);
	}
}
