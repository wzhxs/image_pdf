package image.pdf.com.core.common;

import java.io.File;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public final class Common {
	
	private static Integer white=255;
	
	/**
	 * 用户桌面路径
	 */
	public static String fileDoc=System.getProperty("user.home")+File.separator+"Desktop";
	
	/**
	 * 配置按钮
	 * @param btn
	 */
	public static void setborderColor(Button btn){
		btn.setStyle("-fx-border-color: #E1E1E1;");
	}
	
	/**
	 * 选择文件对象
	 */
	public static FileChooser fileChooser = new FileChooser();
	
	public static FileChooser getSelectFile(){
		fileChooser.setInitialDirectory(new File(Common.fileDoc));
		return fileChooser;
	}
	
	/**
	 * 设置背景色
	 * @param panel
	 */
	public static void setBackColor(Region panel){
		panel.setBackground(new Background(new BackgroundFill(Color.rgb(white, white, white, .99),null,null)));
	}

}
