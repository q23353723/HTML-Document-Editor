package pattern;

import java.util.ArrayList;
import java.util.Stack;

import model.Body;
import model.Bold;
import model.Decorator;
import model.Image;
import model.Italic;
import model.Paragraph;
import model.Span;
import model.UnderLine;
import model.character;

public class PrintVisitor implements Visitor {
	String html = "";
	String decoratee = "";
	Stack<String> st = new Stack<String>();
	ArrayList<String> list = new ArrayList<String>();
	
	public void visit(Body body) {
		System.out.print("<body>");
		for(String item : list) {
			System.out.print(item);
		}
		System.out.print("</body>");
	}
	public void visit(character c) {
		html += c.getString();
		//st.push(c.getString());
	}
	public void visit(Paragraph paragraph) {
		decoratee = decoratee + html;
		list.add("<p>" + html + "p");
		html = "";
	}
	public void visit(Span span) {
		list.add(decoratee);
	}
	public void visit(Italic italic) {
		list.add("<i>" + html + "</i>");
		html = "";
	}
	public void visit(Bold bold) {
		list.add("<b>" + html + "</b>");
		html = "";
	}
	public void visit(UnderLine underline) {
		list.add("<u>" + html + "</u>");
		html = "";
	}
	public void visit(Image image) {
	}
	
	public void parsetoHTML() {
		
	}
}
