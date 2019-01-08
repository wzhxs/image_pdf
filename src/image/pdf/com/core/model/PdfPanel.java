package image.pdf.com.core.model;

import image.pdf.com.core.Annotation.PanelAnnotation;
import image.pdf.com.core.inter.PanelInter;
import javafx.scene.control.Menu;

@PanelAnnotation
public class PdfPanel implements PanelInter{

	@Override
	public Menu init() {
		Menu menu = new Menu("pdf");
	    menu.getItems().add(new PdfMenu().createMenu());
	    return menu;
	}
	
}
