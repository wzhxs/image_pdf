package image.pdf.com.core.model;

import image.pdf.com.core.Annotation.PanelAnnotation;
import image.pdf.com.core.inter.PanelInter;
import javafx.scene.control.Menu;

@PanelAnnotation
public class ImgPanel implements PanelInter{

	@Override
	public Menu init() {
		Menu menu = new Menu("图片");
	    menu.getItems().add(new ImgChangeMenu().createMenu());
	    return menu;
	}

}
