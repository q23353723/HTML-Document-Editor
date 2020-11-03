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

import handler.ButtonActionHandler;
import handler.MenuActionHandler;
import handler.TabActionHandler;
import parser.HTMLparser;
import pattern.CountVisitor;
import pattern.GrayStyleWidgetFactory;
import pattern.HtmlStrategy;
import pattern.PrintVisitor;
import pattern.ShowStrategy;
import pattern.WidgetFactory;
import pattern.WidgetFactoryProducer;
import pattern.WinWindowImp;
import pattern.WindowImp;

public class MainWindow extends Window{
	private JFrame frame;
	//Create Abstract Factory
	WidgetFactory widgetfactory = WidgetFactoryProducer.getFactory(this.getSystemName());
	
	//��l�Ƥ�������
	CustomJTabbedPaneUI tabPane = new CustomJTabbedPaneUI(null);
	JTextPane textPane = new JTextPane();
	JLabel status = new JLabel();
	
	//�إ߾ާ@��ť
	MenuActionHandler menuActionHandler = new MenuActionHandler(this);
	ButtonActionHandler buttonActionHandler = new ButtonActionHandler(this);
	
	//Strategy
	private ShowStrategy strategy = new HtmlStrategy();
	
	HTMLparser parser = new HTMLparser();
	
	public MainWindow(WindowImp imp) {
		super(imp);
		//��imp��@drawFrame�óo��u�t�d�]�wFrame
		frame = this.drawFrame();
		frame.setTitle("HTML Editor");
		frame.setBounds(100, 100, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//�]�wMenuBar
		frame.setJMenuBar(this.createMenuBar());
		textPane.setContentType("text/html");
		
		//�[�WtabPane									
        frame.getContentPane().add(this.createTabPane(textPane), BorderLayout.CENTER); //�NtabPane�[�iFrame��
		
        //�[�WLabel
		status.setText("�r��: 0");
		frame.getContentPane().add(status, BorderLayout.AFTER_LAST_LINE);
	}
	
	
	//Create MenuBar
	public MenuBar createMenuBar() {
		//Create Menu
		MenuBar menuBar = widgetfactory.createMenuBar();

		Menu menu = widgetfactory.createMenu();
		menu.setMenuText("�r��");

		MenuItem menuItem1 = widgetfactory.createMenuItem();
		menuItem1.setMenuItemText("����");
		menuItem1.addActionListener(new StyledEditorKit.BoldAction());

		MenuItem menuItem2 = widgetfactory.createMenuItem();
		menuItem2.setMenuItemText("����");
		menuItem2.addActionListener(new StyledEditorKit.ItalicAction());
		
		MenuItem menuItem3 = widgetfactory.createMenuItem();
		menuItem3.setMenuItemText("���u");
		menuItem3.addActionListener(new StyledEditorKit.UnderlineAction());
		MenuItem menuItem4 = widgetfactory.createMenuItem();
        menuItem4.setMenuItemText("HTML");
		
        menuItem4.addActionListener(menuActionHandler);
        
        MenuItem menuItem6 = widgetfactory.createMenuItem();
        menuItem6.setMenuItemText("�s�ө���");
        
        menuItem6.addActionListener(new StyledEditorKit.FontFamilyAction("�s�ө���", "�s�ө���"));
        
        
		menu.add(menuItem1);
		menu.add(menuItem2);
		menu.add(menuItem3);
		menu.add(menuItem6);
						
		Menu menu2 = widgetfactory.createMenu();
		menu2.setMenuText("����");
		
		menu2.add(menuItem4);
		
		Menu menu3 = widgetfactory.createMenu();
		menu3.setMenuText("���U");
		
		MenuItem menuItem5 = widgetfactory.createMenuItem();
		menuItem5.setMenuItemText("����");
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
		//�]�m �W�[���� �P ������������ �� Menu
        JPopupMenu popMenu = new JPopupMenu();
        popMenu.add(new TabActionHandler("�W�[����", tabPane, this));
        popMenu.addSeparator();
        popMenu.add(new TabActionHandler("������������", tabPane, this));
        return popMenu;
	}
	
	public CustomJTabbedPaneUI createTabPane(JTextPane textPane) {
		//textPane actionEvent
		add(textPane);
		JScrollPane scrollBar1 = new JScrollPane(textPane);	
		
		//�]�m ���Ҥ��e
        tabPane.addTab("EditPage", scrollBar1);

		//�]�m �W�[���� �P ������������ �� Menu
        tabPane.setComponentPopupMenu(this.createPopupMenu(tabPane));
        
        tabPane.setSelectedIndex(0);                 // �w�]���
        tabPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);      // ���Һ��ɡA�h�X���e���ᤧ���s�A�H�������ܦ��W�U�����˻�
        
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
		
		
		ImageIcon openfile = new ImageIcon("C:\\Users\\a2335\\git\\HTML-Document-Editor\\WindowBuilder\\src\\main\\open-file.png");
		openfile.setImage(openfile.getImage().getScaledInstance(15, 15,Image.SCALE_DEFAULT));
		JButton openButton = new JButton(openfile);
		openButton.setText("�}��");
		openButton.addActionListener(buttonActionHandler);
		
		ImageIcon save = new ImageIcon("C:\\Users\\a2335\\git\\HTML-Document-Editor\\WindowBuilder\\src\\main\\open-file.png");
		save.setImage(openfile.getImage().getScaledInstance(15, 15,Image.SCALE_DEFAULT));
		JButton saveButton = new JButton(save);
		saveButton.setText("�s��");
		saveButton.addActionListener(buttonActionHandler);
		
		
		toolBar.add(openButton);
        toolBar.add(saveButton);
        
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
	
	public void add(JTextPane t) {
    	t.getDocument().addDocumentListener(new DocumentListener() {
    		
	        @Override
	        public void removeUpdate(DocumentEvent e) {
	        	CountVisitor countvisitor = new CountVisitor();
	        	PrintVisitor printvisitor = new PrintVisitor();
				parser.getString(t.getText());
				parser.print();
				parser.getGlyphs().accept(countvisitor);
				parser.getGlyphs().accept(printvisitor);
				status.setText("�r��: " + Integer.toString(countvisitor.getcharCount()));
	        }

	        @Override
	        public void insertUpdate(DocumentEvent e) {
	        	CountVisitor countvisitor = new CountVisitor();
	        	PrintVisitor printvisitor = new PrintVisitor();
				parser.getString(t.getText());
				parser.print();
				parser.getGlyphs().accept(countvisitor);
				parser.getGlyphs().accept(printvisitor);
				status.setText("�r��: " + Integer.toString(countvisitor.getcharCount()));
	        }

	        @Override
	        public void changedUpdate(DocumentEvent e) {
	        }
	    });
    }
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public JTextPane getTextPane() {
		return this.textPane;
	}
}
