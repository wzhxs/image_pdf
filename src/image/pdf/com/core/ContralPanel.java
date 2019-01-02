package image.pdf.com.core;

import image.pdf.com.core.model.PdfPanel;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;

public class ContralPanel {
	
	public static BorderPane mainPanel=new BorderPane();
	
	/**
	 * 初始化面板
	 */
	static{
		MenuBar menuBar=new MenuBar();
	    menuBar.getMenus().addAll(new PdfPanel().init());
		mainPanel.setTop(menuBar);
		
	}
}
