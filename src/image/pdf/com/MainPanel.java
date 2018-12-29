package image.pdf.com;

import image.pdf.com.core.ContralPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * 页面主入口
 * @author wangzh
 * @date 2018年12月29日 下午4:56:50
 */
public class MainPanel  extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		ContralPanel mainPanel=new ContralPanel();
		mainPanel.init();
		//pane放入Scen中
		Scene scene=new Scene(mainPanel.getPane(),910,600);
		stage.setScene(scene);
		stage.setResizable(false);
		
		stage.setTitle("工具");
		stage.show();
		
	}

}
