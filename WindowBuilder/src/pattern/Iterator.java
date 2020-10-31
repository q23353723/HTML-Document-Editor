package pattern;

import model.Glyph;

public interface Iterator {
	boolean hasNext();
	Glyph next();
	void remove();
}
