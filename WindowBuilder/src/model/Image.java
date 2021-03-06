package model;

import org.jsoup.nodes.Attributes;

import pattern.Visitor;

public class Image extends Glyph {
	private Attributes attributes;
	
	public Image(Attributes atr) {
		this.attributes = atr;
	}
		
	@Override
	public Attributes getAttributes() {
		return this.attributes;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
