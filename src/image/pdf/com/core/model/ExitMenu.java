package image.pdf.com.core.model;

import image.pdf.com.core.inter.MenuInter;
import javafx.application.Platform;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class ExitMenu implements MenuInter<AnchorPane>{

	@Override
	public MenuItem createMenu() {
		MenuItem exitMenuItem = new MenuItem("退出");
	    exitMenuItem.setOnAction(actionEvent -> Platform.exit());
	    return exitMenuItem;
	}

	@Override
	public AnchorPane createAction() {
		return null;
	}

}
