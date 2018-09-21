package image.pdf.com.layout;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import image.pdf.com.StartRun;
import image.pdf.com.util.FileUtil;
import image.pdf.com.util.StringUtil;
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
	    
	    TextField filterText = new TextField();
	    TextField spaceText = new TextField();
	    
		Button choseBtn=new Button("选择");
		choseBtn.setOnAction((ActionEvent e)->{
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("MoreFile");
			fileChooser.setInitialDirectory(new File(FileUtil.getDesktop()));                 
	        fileChooser.getExtensionFilters().addAll(
	            new FileChooser.ExtensionFilter("All File", "*.*")
	        );
	        
			List<File> txtFile =fileChooser.showOpenMultipleDialog(StartRun.getStage());
			if(txtFile!=null&&txtFile.size()>0){
				inputText.setText(txtFile.get(0).toString());	
			}
		});
		Button createBtn=new Button("生成");
		createBtn.setOnAction((ActionEvent e)->{
			String fileUrl=inputText.getText();
			if(StringUtil.isNotEmpty(fileUrl)){
				String fileter=filterText.getText();
				String[] filterList = new String[]{};
				if(StringUtil.isNotEmpty(fileter)){
					filterList=fileter.split(",");
				}
				Map<String,String> spaceMap=new HashMap<>();
				String space=spaceText.getText();
				if(StringUtil.isNotEmpty(space)){
					String[] hashMap=space.split("\\,");
					String[] key;
					for(String hash:hashMap){
						key=hash.split("\\=");
						spaceMap.put(key[0], key[1]);
					}
				}
				
				FileUtil.filterFile(fileUrl, filterList, spaceMap);
			}
		});
		
		int row=3;  //第几行
	    grid.add(new Label("文件："), 0, row);
	    grid.add(inputText, 1, row);
	    grid.add(choseBtn, 2, row);
	    grid.add(createBtn, 3, row);
	   
	    
	    
	    grid.add(new Label("过滤："), 0, row+2);
	    grid.add(filterText, 1, row+2);
	    
	    
	    grid.add(new Label("替换："), 0, row+4);
	    grid.add(spaceText, 1, row+4);
	    
		detailPane.getChildren().add(grid);
		
		
		return detailPane;
	}
	

}
