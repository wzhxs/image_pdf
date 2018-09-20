package image.pdf.com;

import java.io.File;

import image.pdf.com.layout.RightPanel;
import image.pdf.com.layout.TopPanel;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StartRun extends Application{
	
	public static FileChooser fileChooser = new FileChooser();
	private static BorderPane layout;
	private static Stage stage;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage stage) throws Exception {
		init();
		//创建布局
		layout=new BorderPane();
		VBox topBox=new VBox();
		topBox.getChildren().add(new TopPanel().createTopMenu());
		layout.setTop(topBox);
		this.stage=stage;
		
		//pane放入Scen中
		Scene scene=new Scene(layout,910,600);
		setConter();
		stage.setScene(scene);
		stage.setResizable(false);
		
		stage.setTitle("个人工具");
		stage.show();
	}
	
	/**
	 * 获取stage
	 */
	public static  Stage getStage(){
		return stage;
	}
	
	/**
	 * 初始化图片选择
	 */
	public void init(){
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));                 
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Images", "*.*"),
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
        );
	}
	
	/**
	 * 设置头
	 */
	public static void setTop(Node value){
		layout.setTop(value);
	}
	
	/**
	 * 设置conter面板
	 * @param value
	 */
	public static void setConter(Node value){
		layout.setCenter(value);
	}

	/**
	 * 设置center面板
	 */
	public static void setConter(){
		layout.setCenter(new RightPanel().createRightPanel());
	}
}
