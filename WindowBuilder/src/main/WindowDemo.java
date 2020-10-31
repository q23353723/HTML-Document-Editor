package main;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.Component;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.text.StyledEditorKit;

import model.JTextPaneFactory;
import model.Menu;
import model.MenuBar;
import model.MenuItem;
import parser.HTMLparser;
import pattern.BlackStyleWidgetFactory;
import pattern.GrayStyleWidgetFactory;
import pattern.HtmlStrategy;
import pattern.PlainStrategy;
import pattern.ShowStrategy;
import pattern.WidgetFactory;
import model.BlackMenu;
import model.BlackMenuBar;
import model.BlackMenuItem;
import model.CustomJTabbedPaneUI;
import model.Factory;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class WindowDemo {

	private JFrame frame;
	private ShowStrategy st;
//	String[] imgPath = {"/test2/Res/btnClose.png", "/test2/Res/btnClose_Entered.png", "/test2/Res/btnClose_Pressed.png"};//有圖片版本
	String[] imgPath = null;//無圖片版本
	
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowDemo window = new WindowDemo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WindowDemo() {
		initialize();
	}
	
	public static void add(JTextPane t, JTextArea j) {
		HTMLparser parser = new HTMLparser();
    	t.getDocument().addDocumentListener(new DocumentListener() {

	        @Override
	        public void removeUpdate(DocumentEvent e) {
	        	print(e);
	        }

	        @Override
	        public void insertUpdate(DocumentEvent e) {
				parser.getString(t.getText());
				parser.print();
				print(e);
	        }

	        @Override
	        public void changedUpdate(DocumentEvent e) {
	        	print(e);
	        }
	        
	        private void print(DocumentEvent e) {
                j.replaceRange(t.getText(), 0, j.getDocument().getLength());
            }
	    });
    }

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void setStrategy(ShowStrategy st) {
		this.st = st;
	}
	
	private void initialize() {
		//Create Frame
		frame = new JFrame();
		frame.setTitle("HTML Editor");
		frame.setBounds(100, 100, 592, 512);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Create Factory
		WidgetFactory widgetfactory = new GrayStyleWidgetFactory();
		//Create Menu
		MenuBar menuBar = widgetfactory.createMenuBar();

		Menu menu = widgetfactory.createMenu();
		menu.setMenuText("字體");

		menuBar.add(menu);

		MenuItem menuItem1 = widgetfactory.createMenuItem();
		menuItem1.setMenuItemText("粗體");
		menuItem1.addActionListener(new StyledEditorKit.BoldAction());

		MenuItem menuItem2 = widgetfactory.createMenuItem();
		menuItem2.setMenuItemText("斜體");
		menuItem2.addActionListener(new StyledEditorKit.ItalicAction());
		MenuItem menuItem3 = widgetfactory.createMenuItem();
		menuItem3.setMenuItemText("底線");
		menuItem3.addActionListener(new StyledEditorKit.UnderlineAction());
		
		menu.add(menuItem1);
		menu.add(menuItem2);
		menu.add(menuItem3);
		
		Menu menu2 = widgetfactory.createMenu();
		menu2.setMenuText("切換");
		menuBar.add(menu2);
		
		
		frame.setJMenuBar(menuBar);
		
		//Factory建造JTextPane
		Factory factory = new JTextPaneFactory();
		JTextPane textPane = factory.createProduct();
		
		//Add ScrollBar
		JScrollPane scrollBar1 = new JScrollPane(textPane);
		
		JTextArea textArea = new JTextArea();
		
		JScrollPane scrollBar2 = new JScrollPane(textArea);
		
		File imageCheck = new File("C:\\\\Users\\\\a2335\\\\git\\\\HTML-Document-Editor\\\\WindowBuilder\\\\src\\\\main\\\\bold.png");

		if(imageCheck.exists()) 
		    System.out.println("Image file found!");
		else 
		    System.out.println("Image file not found!");
		
		
		//Open file button	
		
		ImageIcon bold = new ImageIcon("C:\\Users\\a2335\\git\\HTML-Document-Editor\\WindowBuilder\\src\\main\\bold.png");
		bold.setImage(bold.getImage().getScaledInstance(10, 10,Image.SCALE_DEFAULT));
		
		JButton openButton = new JButton(bold);
		
		
		
		//open button listener
        
        JToolBar toolBar = new JToolBar();
        
        toolBar.add(openButton);
        frame.getContentPane().add(toolBar, BorderLayout.BEFORE_FIRST_LINE);
		
		//設置 頁籤內容
        CustomJTabbedPaneUI tabPane = new CustomJTabbedPaneUI(imgPath);
        tabPane.addTab("EditPage", scrollBar1);
        
        JViewport vp = scrollBar2.getViewport();//Debug
        JTextArea Jta = (JTextArea)vp.getView();//Debug
        tabPane.addTab("HtmlPage", Jta);
        tabPane.setSelectedIndex(0);                 // 預設顯示
        tabPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);      // 頁籤滿時，多出往前往後之按鈕，以防頁籤變成上下擠之樣貌
        frame.getContentPane().add(tabPane, BorderLayout.CENTER); //將tabPane加進Frame中
        
        MenuItem menuItem4 = widgetfactory.createMenuItem();
        menuItem4.setMenuItemText("HTML");
		menuItem4.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ev) {
		    	  Component[] comps = tabPane.getComponents();
		    	  for(Component cmp : comps) {
		    		  if(cmp instanceof JScrollPane) {
		    			  JViewport vp = ((JScrollPane)cmp).getViewport();
		    			  JTextPane Jtp = (JTextPane)(vp).getView();
		    			  System.out.print("test");
		    			  if(((JTextPane) Jtp).getContentType().equals("text/html")) {
		    				  setStrategy(new PlainStrategy());
			    			  st.show((JTextPane)Jtp);
			    			  System.out.print(((JTextPane) Jtp).getContentType());
		    			  }
		    			  else if(((JTextPane) Jtp).getContentType().equals("text/plain")) {
		    				  setStrategy(new HtmlStrategy());
			    			  st.show((JTextPane)Jtp);
			    			  WindowDemo.add((JTextPane) Jtp, textArea);
			    			  System.out.print(((JTextPane) Jtp).getContentType());
		    			  }
		    		  }
		    	  }
		      }
		    });
		menu2.add(menuItem4);
        
        //設置 增加頁籤 與 關閉全部頁籤 之 Menu
        JPopupMenu popMenu = new JPopupMenu();
        popMenu.add(new NewTabAction("增加頁籤", tabPane));
        popMenu.addSeparator();
        popMenu.add(new CloseAllAction("關閉全部頁籤", tabPane));
        tabPane.setComponentPopupMenu(popMenu);
		
        
		//textPane actionEvent
		add(textPane,textArea);
		
		
		//Window Look-And-Feel
		//try { 
	    //    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	    //    SwingUtilities.updateComponentTreeUI(tabPane);
	    //} catch(Exception ignored){}
		
		openButton.addActionListener(new ActionListener() { //Open file
        	File file;
        	String text;
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                	file = chooser.getSelectedFile(); // do something
                	
                	FileReader fr = null;
					try {
						fr = new FileReader(file);
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
                	BufferedReader reader = new BufferedReader(fr);
                	String line;
                	try {
                		JTextPane textPane = factory.createProduct();
						JTextArea textArea = new JTextArea();
						WindowDemo.add(textPane, textArea);
						while ((line = reader.readLine()) != null)
						{
						    if (!line.startsWith(">"))
						    {
								textArea.append(line + "\n");
						    }
						}
						textPane.setText(textArea.getText());
						JScrollPane scrollBar1 = new JScrollPane(textPane);
						JScrollPane scrollBar2 = new JScrollPane(textArea);
						tabPane.addTab("newEdit" + (int)(tabPane.getTabCount() + 1), scrollBar1);
						tabPane.addTab("newHtmlEdit" + (int)(tabPane.getTabCount() + 1), scrollBar2);
						tabPane.setSelectedIndex(tabPane.getTabCount() - 1);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
            }
        });
	}
}

//增加頁籤 之 Action
class NewTabAction extends AbstractAction {
	CustomJTabbedPaneUI tabPane = null;
	Factory factory = null;
 
	protected NewTabAction(String label, CustomJTabbedPaneUI tabPane) {
		super(label);
		this.tabPane = tabPane;
	}
 
	@Override 
 	public void actionPerformed(ActionEvent e) {
		if(tabPane != null) {
			factory = new JTextPaneFactory();
			JTextPane textPane = factory.createProduct();
			JTextArea textArea = new JTextArea();
			WindowDemo.add(textPane, textArea);
			
			JScrollPane scrollBar1 = new JScrollPane(textPane);
			JScrollPane scrollBar2 = new JScrollPane(textArea);

			tabPane.addTab("newEdit" + (int)(tabPane.getTabCount() + 1), scrollBar1);
			tabPane.addTab("newHtmlEdit" + (int)(tabPane.getTabCount() + 1), scrollBar2);
			tabPane.setSelectedIndex(tabPane.getTabCount() - 1);
		}
	}
}

//關閉全部頁籤 之 Action
class CloseAllAction extends AbstractAction {
	CustomJTabbedPaneUI tabPane = null;
 
	protected CloseAllAction(String label, CustomJTabbedPaneUI tabPane) {
		super(label);
		this.tabPane = tabPane;
	}
 
	@Override 
	public void actionPerformed(ActionEvent e) {
		if(tabPane != null) {
			tabPane.removeAll();
		}
	}
}

