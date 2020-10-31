package model;

import pattern.Visitor;

public class Image extends Glyph {
	String url;
	
	public Image(String u) {
		this.url = u;
	}
	
	@Override
	public String getString() {
		return this.url;
	}
	
	@Override
	public void print() {
		System.out.print("<img src=\"" + url + ">");
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
