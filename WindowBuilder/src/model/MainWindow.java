package model;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.StyledEditorKit;

import handler.CloseAllAction;
import handler.MenuActionHandler;
import handler.NewTabAction;
import handler.TabActionHandler;
import parser.HTMLparser;
import pattern.CountVisitor;
import pattern.GrayStyleWidgetFactory;
import pattern.HtmlStrategy;
import pattern.ShowStrategy;
import pattern.WidgetFactory;
import pattern.WinWindowImp;

public class MainWindow extends Window{
	private JFrame frame;
	//Create Factory
	WidgetFactory widgetfactory = new GrayStyleWidgetFactory();
	//Factory建造JTextPane
	Factory factory = new JTextPaneFactory();
	
	CustomJTabbedPaneUI tabPane = new CustomJTabbedPaneUI(null);
	
	//Strategy
	private ShowStrategy strategy = new HtmlStrategy();
	
	HTMLparser parser = new HTMLparser();
	
	JLabel status = new JLabel();
	
	public MainWindow() {
		super(new WinWindowImp());
		//Create Frame
		frame = windowimp.drawFrame();
		frame.setTitle("HTML Editor");
		frame.setBounds(100, 100, 592, 512);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setJMenuBar(this.createMenuBar());
		
		JTextPane textPane = factory.createProduct();
				
		//Add ScrollBar
		JScrollPane scrollBar1 = new JScrollPane(textPane);
				
		//debug用
		JTextArea textArea = new JTextArea();
				
		JScrollPane scrollBar2 = new JScrollPane(textArea);
		
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
		
        menuItem4.addActionListener(new MenuActionHandler(tabPane, this));
        
		menu.add(menuItem1);
		menu.add(menuItem2);
		menu.add(menuItem3);
						
		Menu menu2 = widgetfactory.createMenu();
		menu2.setMenuText("切換");
		
		menu2.add(menuItem4);
		
		menuBar.add(menu);
		menuBar.add(menu2);
		
		return menuBar;
	}
	
	
	
	public JPopupMenu createPopupMenu(CustomJTabbedPaneUI tabPane) {
		//設置 增加頁籤 與 關閉全部頁籤 之 Menu
        JPopupMenu popMenu = new JPopupMenu();
        popMenu.add(new TabActionHandler("增加頁籤", tabPane));
        popMenu.addSeparator();
        popMenu.add(new TabActionHandler("關閉全部頁籤", tabPane));
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
	
	//set Strategy
	public void setStrategy(ShowStrategy st) {
		this.strategy = st;
	}
	
	//get Strategy
	public ShowStrategy getStrategy() {
		return this.strategy;
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
