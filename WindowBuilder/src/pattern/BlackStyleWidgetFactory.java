package pattern;

import model.BlackMenu;
import model.BlackMenuBar;
import model.BlackMenuItem;
import model.Menu;
import model.MenuBar;
import model.MenuItem;

public class BlackStyleWidgetFactory implements WidgetFactory{
	public MenuBar createMenuBar() {
		return new BlackMenuBar();
	}
	public Menu createMenu() {
		return new BlackMenu();
	}
	public MenuItem createMenuItem() {
		return new BlackMenuItem();
	}
}
