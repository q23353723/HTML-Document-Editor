package parser;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import model.Bold;
import model.Font;
import model.Glyph;
import model.Italic;
import model.UnderLine;

import model.character;
import pattern.SimpleFactory;

public class HTMLparser {
	private String html;
	private Glyph Glyphs;
	
	public HTMLparser(String html) {
		this.html = html;
	}
	
	public void parseToGlyph() {
		Document doc = Jsoup.parse(html);
		Element body = doc.body();
		
		Glyphs = iteration(body, "");
		
		System.out.println("");
	}
	
	public Glyph getGlyphs() {
		return Glyphs;
	}
		
	//用simple factory來造Glyph
	//由tag name來決定要造哪一個Glyph
	//為node建標籤
	//foreach小孩
	//  標籤insert小孩的標籤
	//return 標籤
	
	public Glyph iteration(Node e, String spacer) {
		if(e instanceof Element) { //if node is Element
			Glyph glyphs = SimpleFactory.createGlyph(((Element)e).tagName(),((Element)e).attributes()); //Create Glyph by tag name of Element
			for(Node child: e.childNodes()) { //foreach node child
				if(child instanceof TextNode && child != null) { // if child is textnode
					//System.out.println(((TextNode)child).text());
					for(String c: eliminateBlank(((TextNode)child).text()).split("")) {
						System.out.print(c);
						if(c.equals(" ")) {
							glyphs.add(new character("&nbsp;"));
						}
						else glyphs.add(new character(c));
					}
					//glyphs.add(new character(((TextNode)child).text())); // insert character into Glyph
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
			
			if(((Element)e).tagName().equals("font")){ //<b> Decorator
				glyphs = new Font(glyphs);
				glyphs.setAttributes(((Element)e).attributes());
			}
			
			return glyphs;
		}
		return null;
	}
	
	public String eliminateBlank(String str) {
		str = str.replaceAll(" ","").replaceAll("&nbsp;", " ");
		System.out.print(str);
		return str;
	}
}
