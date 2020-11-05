package handler;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JEditorPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTML;

public class BackgroundColorAction extends StyledEditorKit.StyledTextAction {

    private Color color;

    public BackgroundColorAction(Color color) {
        super(StyleConstants.Background.toString());
        this.color = color;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JEditorPane editor = getEditor(ae);
        if (editor == null) {
            return;
        }
        //Add span Tag
        String htmlStyle = "background-color:" + this.getHTMLColor(color);
        SimpleAttributeSet attr = new SimpleAttributeSet();
        attr.addAttribute(HTML.Attribute.STYLE, htmlStyle);
        MutableAttributeSet outerAttr = new SimpleAttributeSet();
        outerAttr.addAttribute(HTML.Tag.SPAN, attr);
        //Next line is just an instruction to editor to change color
        StyleConstants.setBackground(outerAttr, this.color);
        setCharacterAttributes(editor, outerAttr, false);
    }
    
    public String getHTMLColor(Color color) {
        if (color == null) {
            return "#000000";
        }
        return "#" + Integer.toHexString(color.getRGB()).substring(2).toUpperCase();
    }
}