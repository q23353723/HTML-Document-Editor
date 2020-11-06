package pattern;

import model.Body;
import model.Bold;
import model.Font;
import model.Image;
import model.Italic;
import model.Paragraph;
import model.Span;
import model.UnderLine;
import model.Character;

public class CountVisitor implements Visitor {
	private int charCount = 0;
	
	public void visit(Body body) {}
	public void visit(Character c) {
		if(!c.getString().equals(" ")) {
			this.charCount++;
		}
	}
	public void visit(Paragraph paragraph) {}
	public void visit(Span span) {}
	public void visit(Italic italic) {}
	public void visit(Bold bold) {}
	public void visit(UnderLine underline) {}
	public void visit(Image image) {}
	public void visit(Font font) {}

	public int getcharCount() {
		return charCount;
	}
}
