package pattern;

import model.Body;
import model.Bold;
import model.Image;
import model.Italic;
import model.Paragraph;
import model.Span;
import model.UnderLine;
import model.character;

public class CountVisitor implements Visitor {
	private int charCount = 0;
	
	public void visit(Body body) {
	}
	public void visit(character c) {
		if(!c.getString().equals(" ")) {
			//System.out.println("這是debug " + c.getString() + " 這是結束");
			this.charCount++;
		}
	}
	public void visit(Paragraph paragraph) {
		
	}
	public void visit(Span span) {
	}
	public void visit(Italic italic) {
	}
	public void visit(Bold bold) {
	}
	public void visit(UnderLine underline) {
	}
	public void visit(Image image) {
	}
	public int getcharCount() {
		return charCount;
	}
}
