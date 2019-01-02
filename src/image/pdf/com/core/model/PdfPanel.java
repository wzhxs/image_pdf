package image.pdf.com.core.model;

import image.pdf.com.core.inter.PanelInter;
import javafx.scene.control.Menu;

public class PdfPanel extends PanelInter<PdfMenu>{

	@Override
	public Menu init() {
		Menu menu = new Menu("pdf");
	    menu.getItems().add(new PdfMenu().createMenu());
	    return menu;
	}
	
}
