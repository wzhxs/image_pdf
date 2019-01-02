package image.pdf.com.core.inter;

import javafx.scene.control.MenuItem;

public abstract interface MenuInter<T> {
	
	/**
	 * 菜单初始化
	 */
	public abstract MenuItem createMenu();
	
	/**
	 * 菜单排版和功能
	 */
	public abstract T createAction();
}
