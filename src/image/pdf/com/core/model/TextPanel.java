package image.pdf.com.core.model;

import image.pdf.com.core.Annotation.PanelAnnotation;
import image.pdf.com.core.inter.PanelInter;
import javafx.scene.control.Menu;

@PanelAnnotation
public class TextPanel implements PanelInter{

	@Override
	public Menu init() {
		Menu menu = new Menu("文本");
		menu.getItems().addAll(new TextMenu().createMenu(),new ColumnToRow().createMenu());
		return menu;
	}

}
