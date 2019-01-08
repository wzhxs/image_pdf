package image.pdf.com.core.model;

import image.pdf.com.core.Annotation.PanelAnnotation;
import image.pdf.com.core.inter.PanelInter;
import javafx.scene.control.Menu;

@PanelAnnotation
public class AboutPanel implements PanelInter{

	@Override
	public Menu init() {
		Menu menu = new Menu("关于");
	    menu.getItems().addAll(new AuthorMenu().createMenu(),new ExitMenu().createMenu());
	    return menu;
	}
	

}
