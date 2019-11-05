package image.pdf.com.core.model;

import image.pdf.com.core.Annotation.PanelAnnotation;
import image.pdf.com.core.inter.PanelInter;
import javafx.scene.control.Menu;

/**
 * excel面板
 * @author wangzh
 * @date 2019年11月4日 下午2:16:29
 */
@PanelAnnotation
public class ExcelPanel implements PanelInter{

	@Override
	public Menu init() {
		Menu menu = new Menu("excel");
	    menu.getItems().addAll(new ExcelMenu().createMenu());
	    return menu;
	}

}
