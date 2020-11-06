package handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;

public class KeyActionHandler implements KeyListener {
	private JTextPane textPane;
	
	public KeyActionHandler(JTextPane textPane) {
		this.textPane = textPane;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			try {
				textPane.getDocument().insertString(textPane.getCaretPosition(),"&nbsp;", null);
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(textPane.getContentType().equals("text/html")) {
				HTMLDocument doc = (HTMLDocument) textPane.getDocument();
				try {
					doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()), "<p> </p>");
				} catch (BadLocationException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}
