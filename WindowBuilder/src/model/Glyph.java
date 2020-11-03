package model;


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
    public String getAttribute() {
    	throw new UnsupportedOperationException();
    }
    public void setAttribute(String atr) {
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
    public void print() {
    	throw new UnsupportedOperationException();
    } 
    public void accept(Visitor visitor) {
    	throw new UnsupportedOperationException();
    } 
    
}
