package pattern;

import java.util.Stack;

import org.jsoup.nodes.Attributes;

import model.Body;
import model.Bold;
import model.Font;
import model.Image;
import model.Italic;
import model.Paragraph;
import model.Span;
import model.UnderLine;
import model.Character;

public class PrintVisitor implements Visitor {
	private String html = "";
	private Stack<String> st = new Stack<String>();
	
	public void visit(Body body) {
		for(int i = 1; i <= body.getChildSize(); i++) {
			html = st.pop() + html;
		}
		System.out.print("<body>" + html + "</body>");
	}
	
	public void visit(Character c) {
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
		if(span.getAttributes().get("style").equals("")) {
			st.push("<span>" + html + "</span>");
		}
		else st.push("<span style=\"" + span.getAttributes().get("style") + "\">" + html + "</span>");
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
		st.push("<img src=\"" + image.getAttributes().get("src") + "\">");
	}
	
	public void visit(Font font) {
		Attributes attributes = font.getAttributes();
		if(attributes.get("color").equals("")) {
			if(attributes.get("size").equals("")) {
				st.push("<font face=\"" + attributes.get("face") + "\">" + st.pop() + "</font>");
			}
			else if(attributes.get("face").equals("")) {
				st.push("<font size=\"" + attributes.get("size") + "\">" + st.pop() + "</font>");
			}
			else st.push("<font size=\"" + attributes.get("size") + "\" face=\"" + attributes.get("face") + "\">" + st.pop() + "</font>");
		}
		else if(attributes.get("size").equals("")) {
			if(attributes.get("face").equals("")) {
				st.push("<font color=\"" + attributes.get("color") + "\">" + st.pop() + "</font>");
			}
			else if(attributes.get("color").equals("")) {
				st.push("<font face=\"" + attributes.get("face") + "\">" + st.pop() + "</font>");
			}
			else st.push("<font color=\"" + attributes.get("color") + "\" face=\"" + attributes.get("face") + "\">" + st.pop() + "</font>");
		}
		else if(attributes.get("face").equals("")) {
			if(attributes.get("color").equals("")) {
				st.push("<font size=\"" + attributes.get("size") + "\">" + st.pop() + "</font>");
			}
			else if(attributes.get("size").equals("")) {
				st.push("<font =\"" + attributes.get("face") + "\">" + st.pop() + "</font>");
			}
			else st.push("<font color=\"" + attributes.get("color") + "\" size=\"" + attributes.get("size") + "\">" + st.pop() + "</font>");
		}
		else st.push("<font color=\"" + attributes.get("color") + "\" size=\"" + attributes.get("size") + "\" face=\"" + attributes.get("face") + "\">" + st.pop() + "</font>");
	}
	
	public String getHTML() {
		return html;
	}
}

//<p>
//	ab<b><span>c</span></b>defg
//</p>