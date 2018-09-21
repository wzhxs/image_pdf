package image.pdf.com.layout;

import java.io.File;
import java.util.List;

import image.pdf.com.StartRun;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

/**
 * 中间区域处理
 * @author wangzh
 * @date 2018年9月21日 下午12:48:32
 */
public class ConterPanel {
	
	/**
	 * 文本文件
	 */
	public AnchorPane createTxtPanel(){
		AnchorPane detailPane=new  AnchorPane();
		GridPane grid = new GridPane();
	    grid.setVgap(4);
	    grid.setHgap(10);
	    grid.setPadding(new Insets(15, 5, 5, 20));
	    TextField inputText = new TextField();
	    
		Button choseBtn=new Button("选择");
		choseBtn.setOnAction((ActionEvent e)->{
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("MoreFile");
			fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));                 
	        fileChooser.getExtensionFilters().addAll(
	            new FileChooser.ExtensionFilter("All File", "*.*")
	        );
	        
			List<File> txtFile =fileChooser.showOpenMultipleDialog(StartRun.getStage());
			if(txtFile!=null&&txtFile.size()>0){
				inputText.setText(txtFile.get(0).toString());	
			}
		});
		
		int row=3;  //第几行
	    grid.add(new Label("文件："), 0, row);
	    grid.add(inputText, 1, row);
	    grid.add(choseBtn, 2, row);
	   
	    
	    TextField filterText = new TextField();
	    grid.add(new Label("过滤："), 0, row+2);
	    grid.add(filterText, 1, row+2);
	    
	    TextField spaceText = new TextField();
	    grid.add(new Label("替换："), 0, row+4);
	    grid.add(spaceText, 1, row+4);
	    
		detailPane.getChildren().add(grid);
		
		
		return detailPane;
	}
	

}
