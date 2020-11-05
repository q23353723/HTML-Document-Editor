package model;


import org.jsoup.nodes.Attributes;

import pattern.Iterator;
import pattern.Visitor;

public abstract class Glyph {

	public void add(Glyph g) {
		throw new UnsupportedOperationException();
	}
    public void remove(int i){
    	throw new UnsupportedOperationException();
    }
    public String getString() {
    	throw new UnsupportedOperationException();
    }
    public Attributes getAttributes() {
    	throw new UnsupportedOperationException();
    }
    public void setAttributes(Attributes attributes) {
    	throw new UnsupportedOperationException();
    }
    public Glyph getChild(int i) {
    	throw new UnsupportedOperationException();
    }
    public int getChildSize() {
    	throw new UnsupportedOperationException();
    }
    public Iterator getIterator() {
    	throw new UnsupportedOperationException();
	}
    public void accept(Visitor visitor) {
    	throw new UnsupportedOperationException();
    } 
    
}
