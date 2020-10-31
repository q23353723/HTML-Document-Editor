package pattern;


import model.GrayMenu;
import model.GrayMenuBar;
import model.GrayMenuItem;
import model.Menu;
import model.MenuBar;
import model.MenuItem;

public class GrayStyleWidgetFactory implements WidgetFactory{
	public MenuBar createMenuBar() {
		return new GrayMenuBar();
	}
	public Menu createMenu() {
		return new GrayMenu();
	}
	public MenuItem createMenuItem() {
		return new GrayMenuItem();
	}
}
