package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.text.StyledEditorKit;


import handler.BackgroundColorAction;
import handler.FileActionHandler;
import handler.KeyActionHandler;
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
	
	//��l�Ƥ�������
	private CustomJTabbedPaneUI tabPane = new CustomJTabbedPaneUI(null);
	private JTextPane textPane = new JTextPane();
	private JLabel status = new JLabel();
	
	//�إ߾ާ@��ť
	private MenuActionHandler menuActionHandler = new MenuActionHandler(this);
	private FileActionHandler fileActionHandler = new FileActionHandler(this);
	
	//Strategy
	private ShowStrategy showStrategy;
	
	public MainWindow(WindowImp imp) {
		super(imp);
		//��imp��@drawFrame�óo��u�t�d�]�wFrame
		frame = this.drawFrame();
		frame.setTitle("HTML Document Editor");
		frame.setBounds(100, 100, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//�[�Wtoolbar
		frame.getContentPane().add(this.createToolBar(), BorderLayout.BEFORE_FIRST_LINE);
		
		//�]�wMenuBar
		frame.setJMenuBar(this.createMenuBar());
		textPane.setContentType("text/html");
		
		//�[�WtabPane									
        frame.getContentPane().add(this.createTabPane(textPane), BorderLayout.CENTER); //�NtabPane�[�iFrame��
        
        //�[�WLabel
		status.setText("�r��: 0");
		frame.getContentPane().add(status, BorderLayout.AFTER_LAST_LINE);
		
		textPane.addKeyListener(new KeyActionHandler(textPane));
	}
	
	//Create MenuBar
	public MenuBar createMenuBar() {
		//Create Menu
		MenuBar menuBar = widgetfactory.createMenuBar();
		
		Menu fileMenu = widgetfactory.createMenu();
		fileMenu.setMenuText("�ɮ�");
		
		MenuItem openFile = widgetfactory.createMenuItem();
		openFile.setText("�}��");
		openFile.addActionListener(fileActionHandler);
		
		MenuItem saveFile = widgetfactory.createMenuItem();
		saveFile.setText("�s��");
		saveFile.addActionListener(fileActionHandler);
		
		fileMenu.add(openFile);
		fileMenu.add(saveFile);
		
		Menu fontMenu = widgetfactory.createMenu();
		fontMenu.setMenuText("�r��");
		
		MenuItem fontMenuItem1 = widgetfactory.createMenuItem();
		fontMenuItem1.setMenuItemText("�s�ө���");
		fontMenuItem1.addActionListener(new StyledEditorKit.FontFamilyAction("�s�ө���", "�s�ө���"));
		
		MenuItem fontMenuItem2 = widgetfactory.createMenuItem();
		fontMenuItem2.setMenuItemText("�L�n������");
		fontMenuItem2.addActionListener(new StyledEditorKit.FontFamilyAction("�L�n������", "�L�n������"));
		
		fontMenu.add(fontMenuItem1);
        fontMenu.add(fontMenuItem2);
		
        Menu textMenu = widgetfactory.createMenu();
		textMenu.setMenuText("�r��");
        
        MenuItem textMenuItem1 = widgetfactory.createMenuItem();
        textMenuItem1.setMenuItemText("����");
        textMenuItem1.addActionListener(new StyledEditorKit.BoldAction());

		MenuItem textMenuItem2 = widgetfactory.createMenuItem();
		textMenuItem2.setMenuItemText("����");
		textMenuItem2.addActionListener(new StyledEditorKit.ItalicAction());
		
		MenuItem textMenuItem3 = widgetfactory.createMenuItem();
		textMenuItem3.setMenuItemText("���u");
		textMenuItem3.addActionListener(new StyledEditorKit.UnderlineAction());
        
		textMenu.add(textMenuItem1);
		textMenu.add(textMenuItem2);
		textMenu.add(textMenuItem3);
		
		Menu sizeMenu = widgetfactory.createMenu();
		sizeMenu.setMenuText("�r��j�p");
        
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
		colorMenu.setMenuText("�r���C��");
		
		MenuItem colorMenuItem1 = widgetfactory.createMenuItem();
		colorMenuItem1.setMenuItemText("����");
		colorMenuItem1.addActionListener(new StyledEditorKit.ForegroundAction("����", Color.RED));
		
		MenuItem colorMenuItem2 = widgetfactory.createMenuItem();
		colorMenuItem2.setMenuItemText("���");
		colorMenuItem2.addActionListener(new StyledEditorKit.ForegroundAction("���", Color.GREEN));
		
		MenuItem colorMenuItem3 = widgetfactory.createMenuItem();
		colorMenuItem3.setMenuItemText("�Ŧ�");
		colorMenuItem3.addActionListener(new StyledEditorKit.ForegroundAction("�Ŧ�", Color.BLUE));
		
		MenuItem colorMenuItem4 = widgetfactory.createMenuItem();
		colorMenuItem4.setMenuItemText("�¦�");
		colorMenuItem4.addActionListener(new StyledEditorKit.ForegroundAction("�¦�", Color.BLACK));
		
		MenuItem colorMenuItem5 = widgetfactory.createMenuItem();
		colorMenuItem5.setMenuItemText("�զ�");
		colorMenuItem5.addActionListener(new StyledEditorKit.ForegroundAction("�զ�", Color.WHITE));
		
		colorMenu.add(colorMenuItem1);
		colorMenu.add(colorMenuItem2);
		colorMenu.add(colorMenuItem3);
		colorMenu.add(colorMenuItem4);
		colorMenu.add(colorMenuItem5);
		
		Menu backgroundColorMenu = widgetfactory.createMenu();
		backgroundColorMenu.setMenuText("�I���C��");
		
		MenuItem backgroundColorItem1 = widgetfactory.createMenuItem();
		backgroundColorItem1.setMenuItemText("����");
		backgroundColorItem1.addActionListener(new BackgroundColorAction(Color.RED));
		
		MenuItem backgroundColorItem2 = widgetfactory.createMenuItem();
		backgroundColorItem2.setMenuItemText("���");
		backgroundColorItem2.addActionListener(new BackgroundColorAction(Color.GREEN));
		
		MenuItem backgroundColorItem3 = widgetfactory.createMenuItem();
		backgroundColorItem3.setMenuItemText("�Ŧ�");
		backgroundColorItem3.addActionListener(new BackgroundColorAction(Color.BLUE));
		
		MenuItem backgroundColorItem4 = widgetfactory.createMenuItem();
		backgroundColorItem4.setMenuItemText("�¦�");
		backgroundColorItem4.addActionListener(new BackgroundColorAction(Color.BLACK));
		
		MenuItem backgroundColorItem5 = widgetfactory.createMenuItem();
		backgroundColorItem5.setMenuItemText("�զ�");
		backgroundColorItem5.addActionListener(new BackgroundColorAction(Color.WHITE));
		
		backgroundColorMenu.add(backgroundColorItem1);
		backgroundColorMenu.add(backgroundColorItem2);
		backgroundColorMenu.add(backgroundColorItem3);
		backgroundColorMenu.add(backgroundColorItem4);
		backgroundColorMenu.add(backgroundColorItem5);
		
		Menu switchMenu = widgetfactory.createMenu();
		switchMenu.setMenuText("����");
		
		MenuItem switchMenuItem1 = widgetfactory.createMenuItem();
		switchMenuItem1.setMenuItemText("HTML");
		switchMenuItem1.addActionListener(menuActionHandler);
		
		MenuItem switchMenuItem2 = widgetfactory.createMenuItem();
		switchMenuItem2.setMenuItemText("��r");
		switchMenuItem2.addActionListener(menuActionHandler);
        
		switchMenu.add(switchMenuItem1);
		switchMenu.add(switchMenuItem2);
		
		Menu helpMenu = widgetfactory.createMenu();
		helpMenu.setMenuText("���U");
		
		MenuItem helpMenuItem = widgetfactory.createMenuItem();
		helpMenuItem.setMenuItemText("����");
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
		//�]�m �W�[���� �P ������������ �� Menu
        JPopupMenu popMenu = new JPopupMenu();
        popMenu.add(new TabActionHandler("�W�[����", this));
        popMenu.addSeparator();
        popMenu.add(new TabActionHandler("������������", this));
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
		//�إ�ToolBar
		JToolBar toolBar = new JToolBar();
        
		//�إ�boldButton�ó]�wIcon
		ImageIcon bold = new ImageIcon(MainWindow.class.getResource("/resources/bold.png"));
		bold.setImage(bold.getImage().getScaledInstance(15, 15,Image.SCALE_DEFAULT));
		JButton boldButton = new JButton(bold);
		boldButton.addActionListener(new StyledEditorKit.BoldAction());
		
		//�إ�italicButton�ó]�wIcon
		ImageIcon italic = new ImageIcon(MainWindow.class.getResource("/resources/italic.png"));
		italic.setImage(italic.getImage().getScaledInstance(15, 15,Image.SCALE_DEFAULT));
		JButton ItalicButton = new JButton(italic);
		ItalicButton.addActionListener(new StyledEditorKit.ItalicAction());
		
		//�إ�underlinedButton�ó]�wIcon
		ImageIcon underlined = new ImageIcon(MainWindow.class.getResource("/resources/underlined.png"));
		underlined.setImage(underlined.getImage().getScaledInstance(15, 15,Image.SCALE_DEFAULT));
		JButton underlinedButton = new JButton(underlined);
		underlinedButton.addActionListener(new StyledEditorKit.UnderlineAction());
		
		//�إ�openfileButton�ó]�wIcon
		ImageIcon openfile = new ImageIcon(MainWindow.class.getResource("/resources/open-file.png"));
		openfile.setImage(openfile.getImage().getScaledInstance(15, 15,Image.SCALE_DEFAULT));
		JButton openButton = new JButton(openfile);
		openButton.setText("�}��");
		openButton.addActionListener(fileActionHandler);
		
		//�إ�saveButton�ó]�wIcon
		ImageIcon save = new ImageIcon(MainWindow.class.getResource("/resources/save.png"));
		save.setImage(save.getImage().getScaledInstance(15, 15,Image.SCALE_DEFAULT));
		JButton saveButton = new JButton(save);
		saveButton.setText("�s��");
		saveButton.addActionListener(fileActionHandler);
		
		//�إ�imageButton�ó]�wIcon
		ImageIcon image = new ImageIcon(MainWindow.class.getResource("/resources/picture.png"));
		image.setImage(image.getImage().getScaledInstance(15, 15,Image.SCALE_DEFAULT));
		JButton imageButton = new JButton(image);
		imageButton.setText("�Ϥ�");
		imageButton.addActionListener(fileActionHandler);
		
		//�NButton�[�itoolBar
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
	
	//��C��Ĳ�oaction�ɱNGlyph parse��HTML�]�w��TextPane�W
	public void setTextPane(JTextPane jtp){
        if(Flag) {
            Runnable doAssist = () -> {
                // �Ȱ���ť
            	Flag = false;
            	
            	int caretPosition = jtp.getCaretPosition();
            	
                // ����Visit
                CountVisitor countvisitor = new CountVisitor();
	        	PrintVisitor printvisitor = new PrintVisitor();
	        	
	        	HTMLparser parser = new HTMLparser(jtp.getText());
	    		parser.parseToGlyph();
	        	parser.getGlyphs().accept(countvisitor);
				parser.getGlyphs().accept(printvisitor);
				status.setText("�r��: " + Integer.toString(countvisitor.getcharCount()));
                // �NGlyph parse to HTML��set��TextPane
				
				jtp.setText("<html><head></head>" + printvisitor.getHTML() + "</html>");
				
				jtp.setCaretPosition(Math.min(caretPosition, jtp.getDocument().getLength()-1));
                // ���s��ť
                Flag = true;
            };
            SwingUtilities.invokeLater(doAssist);
        }
    }
}
