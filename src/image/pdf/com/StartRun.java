package image.pdf.com;

import java.io.File;

import image.pdf.com.layout.RightPanel;
import image.pdf.com.layout.TopPanel;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StartRun extends Application{
	
	public static FileChooser fileChooser = new FileChooser();
	private static BorderPane layout;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage stage) throws Exception {
		init();
		//创建布局
		layout=new BorderPane();
		layout.setTop(new TopPanel().createActivityPane(stage));
		

		//pane放入Scen中
		Scene scene=new Scene(layout,910,600);
		setConter();
		stage.setScene(scene);
		stage.setResizable(false);
		
		stage.setTitle("转换器");
		stage.show();
	}
	
	public void init(){
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));                 
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Images", "*.*"),
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
        );
	}
	
	public static void setConter(Node value){
		layout.setCenter(value);
	}

	public static void setConter(){
		layout.setCenter(new RightPanel().createRightPanel());
	}
}
