package pattern;

import model.Menu;
import model.MenuBar;
import model.MenuItem;

public interface WidgetFactory {
	MenuBar createMenuBar();
	Menu createMenu();
	MenuItem createMenuItem();
}
