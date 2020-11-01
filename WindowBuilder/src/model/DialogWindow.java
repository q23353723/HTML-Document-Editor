package model;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import pattern.WindowImp;

public class DialogWindow extends Window{
	private JFrame frame;
	
	public DialogWindow(WindowImp imp) {
		super(imp);
		frame = super.drawFrame();
	}
	
	public void showDialog(String message, String title) {
		JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
}
