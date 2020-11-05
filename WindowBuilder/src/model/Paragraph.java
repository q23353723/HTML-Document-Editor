package model;

import java.util.ArrayList;


import pattern.GlyphListIterator;
import pattern.Iterator;
import pattern.Visitor;

public class Paragraph extends Glyph{
	private ArrayList<Glyph> Glyphs = new ArrayList<Glyph>();
	
	@Override
	public void add(Glyph g) {
		Glyphs.add(g);
	}
	
	@Override
	public void remove(int i) {
		Glyphs.remove(i);
	}
	
	@Override
	public Glyph getChild(int i) {
		return Glyphs.get(i);
	}
	
	@Override
	public int getChildSize() {
		return Glyphs.size();
	}
	
	@Override
	public String getString() {
		return "";
	}
	
	@Override
	public Iterator getIterator() {
		return new GlyphListIterator(this);
	}
	
	@Override
	public void accept(Visitor visitor) {
        Iterator iterator = this.getIterator();
        while (iterator.hasNext()) {
        	iterator.next().accept(visitor);
        }
        visitor.visit(this);
    }
}
