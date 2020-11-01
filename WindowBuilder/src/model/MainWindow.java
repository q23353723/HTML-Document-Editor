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
	//Factory�سyJTextPane
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
				
		//debug��
		JTextArea textArea = new JTextArea();
				
		JScrollPane scrollBar2 = new JScrollPane(textArea);
		
        frame.getContentPane().add(this.createTabPane(scrollBar1), BorderLayout.CENTER); //�NtabPane�[�iFrame��
		
		//textPane actionEvent
		add(textPane,textArea);
		
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
		
        menuItem4.addActionListener(new MenuActionHandler(tabPane, this));
        
		menu.add(menuItem1);
		menu.add(menuItem2);
		menu.add(menuItem3);
						
		Menu menu2 = widgetfactory.createMenu();
		menu2.setMenuText("����");
		
		menu2.add(menuItem4);
		
		menuBar.add(menu);
		menuBar.add(menu2);
		
		return menuBar;
	}
	
	
	
	public JPopupMenu createPopupMenu(CustomJTabbedPaneUI tabPane) {
		//�]�m �W�[���� �P ������������ �� Menu
        JPopupMenu popMenu = new JPopupMenu();
        popMenu.add(new TabActionHandler("�W�[����", tabPane));
        popMenu.addSeparator();
        popMenu.add(new TabActionHandler("������������", tabPane));
        return popMenu;
	}
	
	public CustomJTabbedPaneUI createTabPane(JScrollPane scrollBar1) {
		//�]�m ���Ҥ��e
        tabPane.addTab("EditPage", scrollBar1);
		
		//�]�m �W�[���� �P ������������ �� Menu
        tabPane.setComponentPopupMenu(this.createPopupMenu(tabPane));
        
        tabPane.setSelectedIndex(0);                 // �w�]���
        tabPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);      // ���Һ��ɡA�h�X���e���ᤧ���s�A�H�������ܦ��W�U�����˻�
        
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
				status.setText("�r��: " + Integer.toString(countvisitor.getcharCount()));
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
