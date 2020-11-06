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
		
		System.out.println("--------------------");
		System.out.println(body);
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
			System.out.print("�o�O" + ((Element)e).tagName() + "����");
			Glyph glyphs = SimpleFactory.createGlyph(((Element)e).tagName(),((Element)e).attributes()); //Create Glyph by tag name of Element
			for(Node child: e.childNodes()) { //foreach node child
				if(child instanceof TextNode && child != null) { // if child is textnode
					//System.out.println(((TextNode)child).text());
					System.out.println("���B�z:" + ((TextNode)child).toString() + "����");
					System.out.println("�w�B�z:" + eliminateBlank(((TextNode)child).toString()) + "����");
					for(String c: eliminateBlank(((TextNode)child).toString()).split("")) {
						System.out.print(c);
						if(c.equals(" ")) {
							System.out.println("����");
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
		return str.replaceAll(" ","").replaceAll("(&nbsp;)|(&#160;)|(&amp;nbsp;)", " ");
	}
}
