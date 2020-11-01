package model;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.StyledEditorKit.ItalicAction;
import javax.swing.text.StyledEditorKit.UnderlineAction;

import handler.MenuActionHandler;
import handler.TabActionHandler;
import parser.HTMLparser;
import pattern.CountVisitor;
import pattern.GrayStyleWidgetFactory;
import pattern.HtmlStrategy;
import pattern.ShowStrategy;
import pattern.WidgetFactory;
import pattern.WidgetFactoryProducer;
import pattern.WinWindowImp;
import pattern.WindowImp;

public class MainWindow extends Window{
	private JFrame frame;
	//Create Factory
	WidgetFactory widgetfactory = WidgetFactoryProducer.getFactory(this.getSystemName());
	
	CustomJTabbedPaneUI tabPane = new CustomJTabbedPaneUI(null);
	JTextPane textPane = new JTextPane();
	
	MenuActionHandler menuActionHandler = new MenuActionHandler(this);
	//Strategy
	private ShowStrategy strategy = new HtmlStrategy();
	
	HTMLparser parser = new HTMLparser();
	
	JLabel status = new JLabel();
	
	public MainWindow(WindowImp imp) {
		super(imp);
		//Create Frame
		frame = this.drawFrame();
		frame.setTitle("HTML Editor");
		frame.setBounds(100, 100, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setJMenuBar(this.createMenuBar());
		textPane.setContentType("text/html");
		
		//Add ScrollBar
		JScrollPane scrollBar1 = new JScrollPane(textPane);
				
		//debug用
		JTextArea textArea = new JTextArea();
				
		
        frame.getContentPane().add(this.createTabPane(scrollBar1), BorderLayout.CENTER); //將tabPane加進Frame中
		
		//textPane actionEvent
		add(textPane,textArea);
		
		status.setText("字數: 0");
		frame.getContentPane().add(status, BorderLayout.AFTER_LAST_LINE);
	}
	
	
	//Create MenuBar
	public MenuBar createMenuBar() {
		//Create Menu
		MenuBar menuBar = widgetfactory.createMenuBar();

		Menu menu = widgetfactory.createMenu();
		menu.setMenuText("字體");

		MenuItem menuItem1 = widgetfactory.createMenuItem();
		menuItem1.setMenuItemText("粗體");
		menuItem1.addActionListener(new StyledEditorKit.BoldAction());

		MenuItem menuItem2 = widgetfactory.createMenuItem();
		menuItem2.setMenuItemText("斜體");
		menuItem2.addActionListener(new StyledEditorKit.ItalicAction());
		
		MenuItem menuItem3 = widgetfactory.createMenuItem();
		menuItem3.setMenuItemText("底線");
		menuItem3.addActionListener(new StyledEditorKit.UnderlineAction());
		MenuItem menuItem4 = widgetfactory.createMenuItem();
        menuItem4.setMenuItemText("HTML");
		
        menuItem4.addActionListener(menuActionHandler);
        
		menu.add(menuItem1);
		menu.add(menuItem2);
		menu.add(menuItem3);
						
		Menu menu2 = widgetfactory.createMenu();
		menu2.setMenuText("切換");
		
		menu2.add(menuItem4);
		
		Menu menu3 = widgetfactory.createMenu();
		menu3.setMenuText("幫助");
		
		MenuItem menuItem5 = widgetfactory.createMenuItem();
		menuItem5.setMenuItemText("關於");
		menuItem5.addActionListener(menuActionHandler);
		
		menu3.add(menuItem5);
		
		menuBar.add(menu);
		menuBar.add(menu2);
		menuBar.add(menu3);
		
		//add toolbar
		frame.getContentPane().add(this.createToolBar(), BorderLayout.BEFORE_FIRST_LINE);
		
		return menuBar;
	}
	
	
	
	public JPopupMenu createPopupMenu(CustomJTabbedPaneUI tabPane) {
		//設置 增加頁籤 與 關閉全部頁籤 之 Menu
        JPopupMenu popMenu = new JPopupMenu();
        popMenu.add(new TabActionHandler("增加頁籤", tabPane, this));
        popMenu.addSeparator();
        popMenu.add(new TabActionHandler("關閉全部頁籤", tabPane, this));
        return popMenu;
	}
	
	public CustomJTabbedPaneUI createTabPane(JScrollPane scrollBar1) {
		//設置 頁籤內容
        tabPane.addTab("EditPage", scrollBar1);

		//設置 增加頁籤 與 關閉全部頁籤 之 Menu
        tabPane.setComponentPopupMenu(this.createPopupMenu(tabPane));
        
        tabPane.setSelectedIndex(0);                 // 預設顯示
        tabPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);      // 頁籤滿時，多出往前往後之按鈕，以防頁籤變成上下擠之樣貌
        
        return tabPane;
	}
	
	public JToolBar createToolBar() {
		JToolBar toolBar = new JToolBar();
        
		ImageIcon bold = new ImageIcon("C:\\Users\\a2335\\git\\HTML-Document-Editor\\WindowBuilder\\src\\main\\bold.png");
		bold.setImage(bold.getImage().getScaledInstance(15, 15,Image.SCALE_DEFAULT));
		
		JButton boldButton = new JButton(bold);
		boldButton.addActionListener(new StyledEditorKit.BoldAction());
		
		ImageIcon italic = new ImageIcon("C:\\Users\\a2335\\git\\HTML-Document-Editor\\WindowBuilder\\src\\main\\italic.png");
		italic.setImage(italic.getImage().getScaledInstance(15, 15,Image.SCALE_DEFAULT));
		JButton ItalicButton = new JButton(italic);
		ItalicButton.addActionListener(new StyledEditorKit.ItalicAction());
		
		ImageIcon underlined = new ImageIcon("C:\\Users\\a2335\\git\\HTML-Document-Editor\\WindowBuilder\\src\\main\\underlined.png");
		underlined.setImage(underlined.getImage().getScaledInstance(15, 15,Image.SCALE_DEFAULT));
		JButton underlinedButton = new JButton(underlined);
		underlinedButton.addActionListener(new StyledEditorKit.UnderlineAction());
		
        toolBar.add(boldButton);
        toolBar.add(ItalicButton);
        toolBar.add(underlinedButton);
        
        return toolBar;
	}
	
	//set Strategy
	public void setStrategy(ShowStrategy st) {
		this.strategy = st;
	}
	
	//get Strategy
	public ShowStrategy getStrategy() {
		return this.strategy;
	}
	
	public CustomJTabbedPaneUI getTabPane() {
		return this.tabPane;
	}
	
	public void add(JTextPane t, JTextArea j) {
    	t.getDocument().addDocumentListener(new DocumentListener() {

	        @Override
	        public void removeUpdate(DocumentEvent e) {
	        	print(e);
	        }

	        @Override
	        public void insertUpdate(DocumentEvent e) {
	        	CountVisitor countvisitor = new CountVisitor();
				parser.getString(t.getText());
				parser.print();
				parser.getGlyphs().accept(countvisitor);
				status.setText("字數: " + Integer.toString(countvisitor.getcharCount()));
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
	
	public JFrame getFrame() {
		return this.frame;
	}
}
