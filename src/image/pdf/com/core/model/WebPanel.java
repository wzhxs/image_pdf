package image.pdf.com.core.model;

import image.pdf.com.core.Annotation.PanelAnnotation;
import image.pdf.com.core.inter.PanelInter;
import javafx.scene.control.Menu;

@PanelAnnotation
public class WebPanel implements PanelInter{

	@Override
	public Menu init() {
		Menu menu = new Menu("web");
	    menu.getItems().add(new WebMenu().createMenu());
	    return menu;
	}
	
}
