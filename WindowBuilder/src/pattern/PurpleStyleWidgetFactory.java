package pattern;

import model.Menu;
import model.MenuBar;
import model.MenuItem;
import model.PurpleMenu;
import model.PurpleMenuBar;
import model.PurpleMenuItem;

public class PurpleStyleWidgetFactory implements WidgetFactory{
	public MenuBar createMenuBar() {
		return new PurpleMenuBar();
	}
	public Menu createMenu() {
		return new PurpleMenu();
	}
	public MenuItem createMenuItem() {
		return new PurpleMenuItem();
	}
}
