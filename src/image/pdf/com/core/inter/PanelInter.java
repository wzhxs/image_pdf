package image.pdf.com.core.inter;

import java.util.List;

import javafx.scene.control.Menu;

public abstract class PanelInter<E>{
	
	/**
	 * 模块面板
	 */
	protected  List<E> menuList;
	
	/**
	 * 初始化模块
	 */
	public abstract Menu init();
}
