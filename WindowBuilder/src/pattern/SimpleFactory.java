package pattern;

import org.jsoup.nodes.Attributes;

import model.Body;
import model.Glyph;
import model.Image;
import model.Paragraph;
import model.Span;


public class SimpleFactory {
	
	public static Glyph createGlyph(String tag, Attributes atr) {
		switch(tag) {
			case "p":
				return new Paragraph();
			case "body":
				return new Body();
			case "img":
				return new Image(atr);
			default:
				return new Span(atr);
		}
	}
}
