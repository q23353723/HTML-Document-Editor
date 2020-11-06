package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTMLDocument;

import handler.BackgroundColorAction;
import handler.FileActionHandler;
import handler.MenuActionHandler;
import handler.TabActionHandler;
import parser.HTMLparser;
import pattern.CountVisitor;

import pattern.PrintVisitor;
import pattern.ShowStrategy;
import pattern.WidgetFactory;
import pattern.WidgetFactoryProducer;
import pattern.WindowImp;

public class MainWindow extends Window{
	private JFrame frame;
	private static boolean Flag = true;
	//Create Abstract Factory
	WidgetFactory widgetfactory = WidgetFactoryProducer.getFactory(this.getSystemName());
	
	//初始化介面元件
	private CustomJTabbedPaneUI tabPane = new CustomJTabbedPaneUI(null);
	private JTextPane textPane = new JTextPane();
	private JLabel status = new JLabel();
	
	//建立操作監聽
	private MenuActionHandler menuActionHandler = new MenuActionHandler(this);
	private FileActionHandler fileActionHandler = new FileActionHandler(this);
	
	
	//Strategy
	private ShowStrategy showStrategy;
	
	public MainWindow(WindowImp imp) {
		super(imp);
		//由imp實作drawFrame並這邊只負責設定Frame
		frame = this.drawFrame();
		frame.setTitle("HTML Editor");
		frame.setBounds(100, 100, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//加上toolbar
		frame.getContentPane().add(this.createToolBar(), BorderLayout.BEFORE_FIRST_LINE);
		
		//設定MenuBar
		frame.setJMenuBar(this.createMenuBar());
		textPane.setContentType("text/html");
		
		//加上tabPane									
        frame.getContentPane().add(this.createTabPane(textPane), BorderLayout.CENTER); //將tabPane加進Frame中
		
        this.addKeyListener(textPane);
        
        //加上Label
		status.setText("字數: 0");
		frame.getContentPane().add(status, BorderLayout.AFTER_LAST_LINE);
		
		//Test
		textPane.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	HTMLDocument doc = (HTMLDocument) textPane.getDocument();
		    	  try {
		    		  //doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()), "&nbsp;");
		    		  textPane.getDocument().insertString(textPane.getCaretPosition(),"&nbsp;", null);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
            }
        });
	}
	
	
	
	//Create MenuBar
	public MenuBar createMenuBar() {
		//Create Menu
		MenuBar menuBar = widgetfactory.createMenuBar();
		
		Menu fileMenu = widgetfactory.createMenu();
		fileMenu.setMenuText("檔案");
		
		MenuItem openFile = widgetfactory.createMenuItem();
		openFile.setText("開檔");
		openFile.addActionListener(fileActionHandler);
		
		MenuItem saveFile = widgetfactory.createMenuItem();
		saveFile.setText("存檔");
		saveFile.addActionListener(fileActionHandler);
		
		fileMenu.add(openFile);
		fileMenu.add(saveFile);
		
		Menu fontMenu = widgetfactory.createMenu();
		fontMenu.setMenuText("字型");
		
		MenuItem fontMenuItem1 = widgetfactory.createMenuItem();
		fontMenuItem1.setMenuItemText("新細明體");
		fontMenuItem1.addActionListener(new StyledEditorKit.FontFamilyAction("新細明體", "新細明體"));
		
		MenuItem fontMenuItem2 = widgetfactory.createMenuItem();
		fontMenuItem2.setMenuItemText("微軟正黑體");
		fontMenuItem2.addActionListener(new StyledEditorKit.FontFamilyAction("微軟正黑體", "微軟正黑體"));
		
		fontMenu.add(fontMenuItem1);
        fontMenu.add(fontMenuItem2);
		
        Menu textMenu = widgetfactory.createMenu();
		textMenu.setMenuText("字體");
        
        MenuItem textMenuItem1 = widgetfactory.createMenuItem();
        textMenuItem1.setMenuItemText("粗體");
        textMenuItem1.addActionListener(new StyledEditorKit.BoldAction());

		MenuItem textMenuItem2 = widgetfactory.createMenuItem();
		textMenuItem2.setMenuItemText("斜體");
		textMenuItem2.addActionListener(new StyledEditorKit.ItalicAction());
		
		MenuItem textMenuItem3 = widgetfactory.createMenuItem();
		textMenuItem3.setMenuItemText("底線");
		textMenuItem3.addActionListener(new StyledEditorKit.UnderlineAction());
        
		textMenu.add(textMenuItem1);
		textMenu.add(textMenuItem2);
		textMenu.add(textMenuItem3);
		
		Menu sizeMenu = widgetfactory.createMenu();
		sizeMenu.setMenuText("字體大小");
        
		MenuItem sizeMenuItem1 = widgetfactory.createMenuItem();
        sizeMenuItem1.setMenuItemText("12");
        sizeMenuItem1.addActionListener(new StyledEditorKit.FontSizeAction("12", 12));
		
        MenuItem sizeMenuItem2 = widgetfactory.createMenuItem();
        sizeMenuItem2.setMenuItemText("14");
        sizeMenuItem2.addActionListener(new StyledEditorKit.FontSizeAction("14", 14));

        MenuItem sizeMenuItem3 = widgetfactory.createMenuItem();
        sizeMenuItem3.setMenuItemText("16");
        sizeMenuItem3.addActionListener(new StyledEditorKit.FontSizeAction("16", 16));
		
        MenuItem sizeMenuItem4 = widgetfactory.createMenuItem();
        sizeMenuItem4.setMenuItemText("20");
        sizeMenuItem4.addActionListener(new StyledEditorKit.FontSizeAction("20", 20));
        
        MenuItem sizeMenuItem5 = widgetfactory.createMenuItem();
        sizeMenuItem5.setMenuItemText("28");
        sizeMenuItem5.addActionListener(new StyledEditorKit.FontSizeAction("28", 28));
        
        sizeMenu.add(sizeMenuItem1);
        sizeMenu.add(sizeMenuItem2);
        sizeMenu.add(sizeMenuItem3);
        sizeMenu.add(sizeMenuItem4);
        sizeMenu.add(sizeMenuItem5);
		
		Menu colorMenu = widgetfactory.createMenu();
		colorMenu.setMenuText("字體顏色");
		
		MenuItem colorMenuItem1 = widgetfactory.createMenuItem();
		colorMenuItem1.setMenuItemText("紅色");
		colorMenuItem1.addActionListener(new StyledEditorKit.ForegroundAction("紅色", Color.RED));
		
		MenuItem colorMenuItem2 = widgetfactory.createMenuItem();
		colorMenuItem2.setMenuItemText("綠色");
		colorMenuItem2.addActionListener(new StyledEditorKit.ForegroundAction("綠色", Color.GREEN));
		
		MenuItem colorMenuItem3 = widgetfactory.createMenuItem();
		colorMenuItem3.setMenuItemText("藍色");
		colorMenuItem3.addActionListener(new StyledEditorKit.ForegroundAction("藍色", Color.BLUE));
		
		MenuItem colorMenuItem4 = widgetfactory.createMenuItem();
		colorMenuItem4.setMenuItemText("黑色");
		colorMenuItem4.addActionListener(new StyledEditorKit.ForegroundAction("黑色", Color.BLACK));
		
		MenuItem colorMenuItem5 = widgetfactory.createMenuItem();
		colorMenuItem5.setMenuItemText("白色");
		colorMenuItem5.addActionListener(new StyledEditorKit.ForegroundAction("白色", Color.WHITE));
		
		colorMenu.add(colorMenuItem1);
		colorMenu.add(colorMenuItem2);
		colorMenu.add(colorMenuItem3);
		colorMenu.add(colorMenuItem4);
		colorMenu.add(colorMenuItem5);
		
		Menu backgroundColorMenu = widgetfactory.createMenu();
		backgroundColorMenu.setMenuText("背景顏色");
		
		MenuItem backgroundColorItem1 = widgetfactory.createMenuItem();
		backgroundColorItem1.setMenuItemText("紅色");
		backgroundColorItem1.addActionListener(new BackgroundColorAction(Color.RED));
		
		MenuItem backgroundColorItem2 = widgetfactory.createMenuItem();
		backgroundColorItem2.setMenuItemText("綠色");
		backgroundColorItem2.addActionListener(new BackgroundColorAction(Color.GREEN));
		
		MenuItem backgroundColorItem3 = widgetfactory.createMenuItem();
		backgroundColorItem3.setMenuItemText("藍色");
		backgroundColorItem3.addActionListener(new BackgroundColorAction(Color.BLUE));
		
		MenuItem backgroundColorItem4 = widgetfactory.createMenuItem();
		backgroundColorItem4.setMenuItemText("黑色");
		backgroundColorItem4.addActionListener(new BackgroundColorAction(Color.BLACK));
		
		MenuItem backgroundColorItem5 = widgetfactory.createMenuItem();
		backgroundColorItem5.setMenuItemText("白色");
		backgroundColorItem5.addActionListener(new BackgroundColorAction(Color.WHITE));
		
		backgroundColorMenu.add(backgroundColorItem1);
		backgroundColorMenu.add(backgroundColorItem2);
		backgroundColorMenu.add(backgroundColorItem3);
		backgroundColorMenu.add(backgroundColorItem4);
		backgroundColorMenu.add(backgroundColorItem5);
		
		Menu switchMenu = widgetfactory.createMenu();
		switchMenu.setMenuText("切換");
		
		MenuItem switchMenuItem1 = widgetfactory.createMenuItem();
		switchMenuItem1.setMenuItemText("HTML");
		switchMenuItem1.addActionListener(menuActionHandler);
		
		MenuItem switchMenuItem2 = widgetfactory.createMenuItem();
		switchMenuItem2.setMenuItemText("文字");
		switchMenuItem2.addActionListener(menuActionHandler);
        
		switchMenu.add(switchMenuItem1);
		switchMenu.add(switchMenuItem2);
		
		Menu helpMenu = widgetfactory.createMenu();
		helpMenu.setMenuText("幫助");
		
		MenuItem helpMenuItem = widgetfactory.createMenuItem();
		helpMenuItem.setMenuItemText("關於");
		helpMenuItem.addActionListener(menuActionHandler);
		
		helpMenu.add(helpMenuItem);
		
		menuBar.add(fileMenu);
		menuBar.add(fontMenu);
		menuBar.add(textMenu);
		menuBar.add(sizeMenu);
		menuBar.add(colorMenu);
		menuBar.add(backgroundColorMenu);
		menuBar.add(switchMenu);
		menuBar.add(helpMenu);
		
		return menuBar;
	}
	
	
	
	public JPopupMenu createPopupMenu(CustomJTabbedPaneUI tabPane) {
		//設置 增加頁籤 與 關閉全部頁籤 之 Menu
        JPopupMenu popMenu = new JPopupMenu();
        popMenu.add(new TabActionHandler("增加頁籤", this));
        popMenu.addSeparator();
        popMenu.add(new TabActionHandler("關閉全部頁籤", this));
        return popMenu;
	}
	
	public CustomJTabbedPaneUI createTabPane(JTextPane textPane) {
		//textPane actionEvent
		add(textPane);
		JScrollPane scrollBar1 = new JScrollPane(textPane);
		
		
		//設置 頁籤內容
        tabPane.addTab("EditPage", scrollBar1);

		//設置 增加頁籤 與 關閉全部頁籤 之 Menu
        tabPane.setComponentPopupMenu(this.createPopupMenu(tabPane));
        
        tabPane.setSelectedIndex(0);                 // 預設顯示
        tabPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);      // 頁籤滿時，多出往前往後之按鈕，以防頁籤變成上下擠之樣貌
        
        return tabPane;
	}
	
	public JToolBar createToolBar() {
		//建立ToolBar
		JToolBar toolBar = new JToolBar();
        
		//建立boldButton並設定Icon
		ImageIcon bold = new ImageIcon(MainWindow.class.getResource("/resources/bold.png"));
		bold.setImage(bold.getImage().getScaledInstance(15, 15,Image.SCALE_DEFAULT));
		JButton boldButton = new JButton(bold);
		boldButton.addActionListener(new StyledEditorKit.BoldAction());
		
		//建立italicButton並設定Icon
		ImageIcon italic = new ImageIcon(MainWindow.class.getResource("/resources/italic.png"));
		italic.setImage(italic.getImage().getScaledInstance(15, 15,Image.SCALE_DEFAULT));
		JButton ItalicButton = new JButton(italic);
		ItalicButton.addActionListener(new StyledEditorKit.ItalicAction());
		
		//建立underlinedButton並設定Icon
		ImageIcon underlined = new ImageIcon(MainWindow.class.getResource("/resources/underlined.png"));
		underlined.setImage(underlined.getImage().getScaledInstance(15, 15,Image.SCALE_DEFAULT));
		JButton underlinedButton = new JButton(underlined);
		underlinedButton.addActionListener(new StyledEditorKit.UnderlineAction());
		
		//建立openfileButton並設定Icon
		ImageIcon openfile = new ImageIcon(MainWindow.class.getResource("/resources/open-file.png"));
		openfile.setImage(openfile.getImage().getScaledInstance(15, 15,Image.SCALE_DEFAULT));
		JButton openButton = new JButton(openfile);
		openButton.setText("開檔");
		openButton.addActionListener(fileActionHandler);
		
		//建立saveButton並設定Icon
		ImageIcon save = new ImageIcon(MainWindow.class.getResource("/resources/save.png"));
		save.setImage(save.getImage().getScaledInstance(15, 15,Image.SCALE_DEFAULT));
		JButton saveButton = new JButton(save);
		saveButton.setText("存檔");
		saveButton.addActionListener(fileActionHandler);
		
		//建立imageButton並設定Icon
		ImageIcon image = new ImageIcon(MainWindow.class.getResource("/resources/picture.png"));
		image.setImage(image.getImage().getScaledInstance(15, 15,Image.SCALE_DEFAULT));
		JButton imageButton = new JButton(image);
		imageButton.setText("圖片");
		imageButton.addActionListener(fileActionHandler);
		
		//將Button加進toolBar
		toolBar.add(openButton);
        toolBar.add(saveButton);
        toolBar.add(imageButton);
        toolBar.add(boldButton);
        toolBar.add(ItalicButton);
        toolBar.add(underlinedButton);

        
        return toolBar;
	}
	
	//set Strategy
	public void setStrategy(ShowStrategy st) {
		this.showStrategy = st;
	}
	
	//get Strategy
	public ShowStrategy getStrategy() {
		return this.showStrategy;
	}
	
	public CustomJTabbedPaneUI getTabPane() {
		return this.tabPane;
	}
	
	public void add(JTextPane t) {
    	t.getDocument().addDocumentListener(new DocumentListener() {
    		
	        @Override
	        public void removeUpdate(DocumentEvent e) {
	        	if(Flag) {
	        		setTextPane(t);
	        	}
	        }

	        @Override
	        public void insertUpdate(DocumentEvent e) {
	        	if(Flag) {
	        		setTextPane(t);
	        	}
	        }

	        @Override
	        public void changedUpdate(DocumentEvent e) {
	        	if(Flag) {
	        		setTextPane(t);
	        	}
	        }
	    });
    }
	
	public void show() {
		showStrategy.show();
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public JTextPane getTextPane() {
		return this.textPane;
	}
	
	//判斷是否按下enter鍵
	public void addKeyListener(JTextPane jtp) {
		jtp.addKeyListener(new KeyAdapter() { 
			@Override 
		     public void keyPressed(KeyEvent e) { 
				if(jtp.getContentType().equals("text/html")) {
					if (e.getKeyCode() == 10) {
				    	  HTMLDocument doc = (HTMLDocument) jtp.getDocument();
				    	  try {
							doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()), "<p> </p>");
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				      }
				}
		     } 
		    }); 
	}
	
	//當每次觸發action時將Glyph parse成HTML設定到TextPane上
	public void setTextPane(JTextPane jtp){
		HTMLparser parser = new HTMLparser(jtp.getText());
		parser.parseToGlyph();
        if(Flag) {
            Runnable doAssist = () -> {
                // 暫停監聽
            	Flag = false;
                // 執行Visit
                CountVisitor countvisitor = new CountVisitor();
	        	PrintVisitor printvisitor = new PrintVisitor();
	        	
	        	parser.getGlyphs().accept(countvisitor);
				parser.getGlyphs().accept(printvisitor);
				status.setText("字數: " + Integer.toString(countvisitor.getcharCount()));
                // 將Glyph parse to HTML並set到TextPane
				
				jtp.setText("<html><head></head>" + printvisitor.getHTML() + "</html>");
                // 重新監聽
                Flag = true;
            };
            SwingUtilities.invokeLater(doAssist);
        }
    }
}
