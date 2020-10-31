package pattern;

import model.Glyph;

public class GlyphListIterator implements Iterator {
	
	//body
	private Glyph GlyphComposite;
	private int Position = 0;
	
	public GlyphListIterator(Glyph g) {
		this.GlyphComposite = g;
	}
	
	public boolean hasNext() {
		if(Position >= GlyphComposite.getChildSize()) {
			return false;
		}
		else return true;
	}
	
	public Glyph next() {
		return GlyphComposite.getChild(Position++);
	}
	
	public void remove() {
		GlyphComposite.remove(Position--);
		Position--;
	}
	
}
