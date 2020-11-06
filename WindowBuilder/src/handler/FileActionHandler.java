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
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.html.HTML;


import model.CustomJTabbedPaneUI;
import model.MainWindow;


public class FileActionHandler implements ActionListener {
	CustomJTabbedPaneUI tabPane = null;
	MainWindow window;
	File file;
	String text;
	
	public FileActionHandler(MainWindow window) {
		this.window = window;
		this.tabPane = window.getTabPane();
	}
	
	public void actionPerformed(ActionEvent ev) {
		String event = ev.getActionCommand();
		switch(event) {
			case "開檔":
				JFileChooser openChooser = new JFileChooser();
				openChooser.setFileFilter(new FileNameExtensionFilter("HTML", "html", "htm"));
				openChooser.addChoosableFileFilter(new FileNameExtensionFilter("純文字", "txt"));
				
                if (openChooser.showOpenDialog(window.getFrame()) == JFileChooser.APPROVE_OPTION) {
                	file = openChooser.getSelectedFile(); // do something
                	
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
			case "存檔":
				JFileChooser saveChooser = new JFileChooser();
				saveChooser.setFileFilter(new FileNameExtensionFilter("HTML", "*.html", "*.htm"));
				saveChooser.addChoosableFileFilter(new FileNameExtensionFilter("純文字", "*.txt"));
				
				if (saveChooser.showOpenDialog(window.getFrame()) == JFileChooser.APPROVE_OPTION) {
					try {
						File file = saveChooser.getSelectedFile();
						BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
						writer.write(window.getTextPane().getText());
						writer.close();
						JOptionPane.showMessageDialog(window.getFrame(), "The Message was Saved Successfully!","Success!", JOptionPane.INFORMATION_MESSAGE);
				    } catch (IOException e) {
				    	JOptionPane.showMessageDialog(window.getFrame(), "The Text could not be Saved!","Error!", JOptionPane.INFORMATION_MESSAGE);
				    }
				}
				break;
			case "圖片":
				JFileChooser imageChooser = new JFileChooser();
		        imageChooser.setFileFilter(new FileNameExtensionFilter("JPEG files (*.png)", "png"));
		        imageChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		        
		        
		        if (imageChooser.showOpenDialog(window.getFrame()) == JFileChooser.APPROVE_OPTION) {
		        	try {
		        		Document doc = ((JTextPane)((JScrollPane)window.getTabPane().getSelectedComponent()).getViewport().getView()).getDocument();
		        		SimpleAttributeSet attr = new SimpleAttributeSet();
		                attr.addAttribute(HTML.Attribute.SRC, "file:///"+ imageChooser.getSelectedFile().getAbsolutePath());
		                attr.addAttribute(HTML.Attribute.WIDTH, "600");
		                MutableAttributeSet outerattr = new SimpleAttributeSet();
		        		outerattr.addAttribute(HTML.Tag.IMG, attr);
		        		//kit.insertHTML(doc, window.getTextPane().getSelectionStart(), "<img src=\"file:///" + imageChooser.getSelectedFile().getAbsolutePath() + "\" width=\"500\">" , 0, 0, HTML.Tag.IMG);
		        		//doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()), "<img src=\"file:///" + imageChooser.getSelectedFile().getAbsolutePath() + "\" width=\"500\">");
			            doc.insertString(((JTextPane)((JScrollPane)window.getTabPane().getSelectedComponent()).getViewport().getView()).getCaretPosition(), " ", outerattr);
		        	}
		        	catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		        break;
		}
	}
}
