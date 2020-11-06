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
	
	//�NHTML parse to Glyph
	public void parseToGlyph() {
		Document doc = Jsoup.parse(html);
		Element body = doc.body();
		
		Glyphs = iteration(body, "");
		
		System.out.println("");
	}
	
	public Glyph getGlyphs() {
		return Glyphs;
	}
		
	//��simple factory�ӳyGlyph
	//��tag name�ӨM�w�n�y���@��Glyph
	//��node�ؼ���
	//foreach�p��
	//  ����insert�p�Ī�����
	//return ����
	
	public Glyph iteration(Node e, String spacer) {
		if(e instanceof Element) { //if node is Element
			Glyph glyphs = SimpleFactory.createGlyph(((Element)e).tagName(),((Element)e).attributes()); //Create Glyph by tag name of Element
			for(Node child: e.childNodes()) { //foreach node child
				if(child instanceof TextNode && child != null) { // if child is textnode
					for(String c: eliminateBlank(((TextNode)child).toString()).split("")) {
						if(c.equals(" ")) {
							glyphs.add(new character("&nbsp;"));
						}
						else glyphs.add(new character(c));
					}
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
	
	//�h���۰ʥͦ����ťաA�N�ϥΪ̴��J���ť�&nbsp;���Ҵ��������q�ť�
	public String eliminateBlank(String str) {
		return str.replaceAll(" ","").replaceAll("(&nbsp;)|(&#160;)|(&amp;nbsp;)", " ");
	}
}
