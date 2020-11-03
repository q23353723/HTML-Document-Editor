package pattern;

import java.util.Stack;

import model.Body;
import model.Bold;
import model.Image;
import model.Italic;
import model.Paragraph;
import model.Span;
import model.UnderLine;
import model.character;

public class PrintVisitor implements Visitor {
	String html = "";
	Stack<String> st = new Stack<String>();
	
	public void visit(Body body) {
		for(int i = 1; i <= body.getChildSize(); i++) {
			html = st.pop() + html;
		}
		System.out.print("<body>" + html + "</body>");
	}
	public void visit(character c) {
		st.push(c.getString());
	}
	public void visit(Paragraph paragraph) {
		for(int i = 1; i <= paragraph.getChildSize(); i++) {
			html = st.pop() + html;
		}
		st.push("<p>" + html + "</p>");
		html = "";
	}
	public void visit(Span span) {
		for(int i = 1; i <= span.getChildSize(); i++) {
			html = st.pop() + html;
		}
		st.push("<span>" + html + "</span>");
		html = "";
	}
	public void visit(Italic italic) {
		st.push("<i>" + st.pop() + "</i>");
	}
	public void visit(Bold bold) {
		st.push("<b>" + st.pop() + "</b>");
	}
	public void visit(UnderLine underline) {
		st.push("<u>" + st.pop() + "</u>");
	}
	public void visit(Image image) {
		st.push("<img src=\"" + image.getAttribute() + " \">");
	}
	
	public String getHTML() {
		return html;
	}
}

//<p>
//	ab<b><span>c</span></b>defg
//</p>