package image.pdf.com;

import image.pdf.com.core.model.AboutPanel;
import image.pdf.com.core.model.ExcelPanel;
import image.pdf.com.core.model.ImgPanel;
import image.pdf.com.core.model.PdfMenu;
import image.pdf.com.core.model.PdfPanel;
import image.pdf.com.core.model.TextPanel;
import image.pdf.com.core.model.WebPanel;
import image.pdf.com.util.ClassUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * 页面主入口
 * @author wangzh
 * @date 2018年12月29日 下午4:56:50
 */
public class MainPanel  extends Application{
	private static Stage stage;
	public static BorderPane mainPanel=new BorderPane();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		MenuBar menuBar=new MenuBar();
		Class<?>[] panelClass={PdfPanel.class,ImgPanel.class,TextPanel.class,WebPanel.class,ExcelPanel.class,AboutPanel.class}; //};//
		menuBar=ClassUtil.load(menuBar,panelClass);
		
//		ClassUtil.load(menuBar);
		
		mainPanel.setTop(menuBar);
		mainPanel.setCenter(new PdfMenu().createAction());
		
		MainPanel.stage=stage;
		
		//pane放入Scen中
		Scene scene=new Scene(mainPanel,910,600);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("工具");
		stage.show();
		
	}
	
	public static Stage getStage(){
		return stage;
	}

}
