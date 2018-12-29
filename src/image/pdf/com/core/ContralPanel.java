package image.pdf.com.core;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class ContralPanel {
	
	private BorderPane mainPanel=new BorderPane();
	
	/**
	 * 初始化面板
	 */
	public void init(){
		
	}
	
	/**
	 * 获取面板
	 * @return
	 */
	public BorderPane getPane(){
		return mainPanel;
	}
	
	/**
	 * 初始化显示区域
	 */
	public void setCenter(Node value){
		mainPanel.setCenter(value);
	}
	
	/**
	 * 初始化头
	 */
	public void setTop(Node value){
		mainPanel.setTop(value);
	}
}
