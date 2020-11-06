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

public interface Visitor {
	void visit(Body body);
	void visit(Character c);
	void visit(Paragraph paragraph);
	void visit(Span span);
	void visit(Italic italic);
	void visit(Bold bold);
	void visit(UnderLine underline);
	void visit(Font font);
	void visit(Image image);
	
}
