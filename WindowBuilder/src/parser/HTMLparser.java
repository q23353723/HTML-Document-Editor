package parser;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import model.Bold;
import model.Glyph;
import model.Italic;
import model.UnderLine;

import model.character;
import pattern.CountVisitor;
import pattern.Iterator;
import pattern.PrintVisitor;
import pattern.SimpleFactory;

public class HTMLparser {
	String html;
	
	public void getString(String t) {
		html = t;
	}
	
	public void print() {
		Document doc = Jsoup.parse(html);
		Element body = doc.body();
		//System.out.println("-----------------------");
		
		Glyph Glyphs = iteration(body, "");
		
		System.out.println("print");
		
		//Glyphs.print();
		
		CountVisitor countVisitor = new CountVisitor();
		Glyphs.accept(countVisitor);
		
		Glyphs.accept(new PrintVisitor());
		
		System.out.println(countVisitor.getcharCount());
		//System.out.print(countVisitor.getwordCount());
		
		
		System.out.println("");
	}
	
	public Glyph getGlyphs() {
		Document doc = Jsoup.parse(html);
		Element body = doc.body();
		return iteration(body, "");
	}
		
	//用simple factory來造Glyph
	//由tag name來決定要造哪一個Glyph
	//為node建標籤
	//foreach小孩
	//  標籤insert小孩的標籤
	//return 標籤
	
	public Glyph iteration(Node e, String spacer) {
		if(e instanceof Element) { //if node is Element
			Glyph glyphs = SimpleFactory.createGlyph(((Element)e).tagName()); //Create Glyph by tag name of Element
			for(Node child: e.childNodes()) { //foreach node child
				if(child instanceof TextNode && child != null) { // if child is textnode
					//System.out.println(((TextNode)child).text());
					for(String c: ((TextNode)child).text().split("")) {
						glyphs.add(new character(c));
					}
					//glyphs.add(new character(((TextNode)child).text())); // insert character into Glyph
				}
				else if(((Element)e).tagName().equals("img")) {
					System.out.println("讀到了");
					//System.out.println(((Element)e).text());
					//glyphs.add(new Image());
				}
				else {
					glyphs.add(iteration(child, " ")); 
				}
			}
			if(((Element)e).tagName().equals("b")){ //<b> Decorator
				glyphs = new Bold(glyphs);
			}
			
			if(((Element)e).tagName().equals("i")){ //<i> Decorator
				glyphs = new Italic(glyphs);
			}
			
			if(((Element)e).tagName().equals("u")){ //<u> Decorator
				glyphs = new UnderLine(glyphs);
			}
			return glyphs;
		}
		if(e instanceof TextNode) {
			return new character(((TextNode)e).text().toString());
		}
		return null;
	}
}
