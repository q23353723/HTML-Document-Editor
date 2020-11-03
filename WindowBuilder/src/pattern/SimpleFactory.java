package pattern;

import model.Body;
import model.Glyph;
import model.Image;
import model.Paragraph;
import model.Span;


public class SimpleFactory {
	
	public static Glyph createGlyph(String tag) {
		switch(tag) {
			case "p":
				return new Paragraph();
			case "body":
				return new Body();
			default:
				return new Span();
		}
	}
}
