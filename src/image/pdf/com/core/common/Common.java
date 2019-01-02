package image.pdf.com.core.common;

import java.io.File;

import javafx.scene.control.Button;

public final class Common {
	
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

}
