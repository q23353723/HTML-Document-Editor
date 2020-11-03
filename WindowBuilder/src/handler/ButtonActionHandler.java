package handler;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.crypto.dsig.spec.XPathType.Filter;

import main.WindowDemo;
import model.CustomJTabbedPaneUI;
import model.MainWindow;


public class ButtonActionHandler implements ActionListener {
	CustomJTabbedPaneUI tabPane = null;
	MainWindow window;
	File file;
	String text;
	
	public ButtonActionHandler(MainWindow window) {
		this.window = window;
		this.tabPane = window.getTabPane();
	}
	
	public void actionPerformed(ActionEvent ev) {
		String event = ev.getActionCommand();
		System.out.print(event);
		switch(event) {
			case "∂}¿…":
				JFileChooser openchooser = new JFileChooser();
				openchooser.setFileFilter(new FileNameExtensionFilter("HTML", "html", "htm"));
				openchooser.addChoosableFileFilter(new FileNameExtensionFilter("Ø¬§Â¶r", "txt"));
				
                if (openchooser.showOpenDialog(window.getFrame()) == JFileChooser.APPROVE_OPTION) {
                	file = openchooser.getSelectedFile(); // do something
                	
                	FileReader fr = null;
					try {
						fr = new FileReader(file);
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
                	BufferedReader reader = new BufferedReader(fr);
                	String line;
                	String text = "";
                	try {
                		JTextPane textPane = new JTextPane();
                		textPane.setContentType("text/html");
						window.add(textPane);
						while ((line = reader.readLine()) != null)
						{
						    if (!line.startsWith(">"))
						    {
								text = text + line + "\n";
						    }
						}
						textPane.setText(text);
						JScrollPane scrollBar1 = new JScrollPane(textPane);
						tabPane.addTab("newEdit" + (int)(tabPane.getTabCount() + 1), scrollBar1);
						tabPane.setSelectedIndex(tabPane.getTabCount() - 1);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
                break;
			case "¶s¿…":
				JFileChooser savechooser = new JFileChooser();
				BufferedWriter writer = null;
				
				if (savechooser.showOpenDialog(window.getFrame()) == JFileChooser.APPROVE_OPTION) {
					try {
						File file = savechooser.getSelectedFile();
						writer = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
						writer.write(window.getTextPane().getText());
						writer.close();
						JOptionPane.showMessageDialog(window.getFrame(), "The Message was Saved Successfully!","Success!", JOptionPane.INFORMATION_MESSAGE);
				    } catch (IOException e) {
				    	JOptionPane.showMessageDialog(window.getFrame(), "The Text could not be Saved!","Error!", JOptionPane.INFORMATION_MESSAGE);
				    }
				}
		}
	}
}
