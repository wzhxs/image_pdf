package image.pdf.com.core.model;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import image.pdf.com.MainPanel;
import image.pdf.com.core.common.Common;
import image.pdf.com.core.inter.MenuInter;
import image.pdf.com.util.FileUtil;
import image.pdf.com.util.StringUtil;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class TextMenu implements MenuInter<AnchorPane>{

	@Override
	public MenuItem createMenu() {
		MenuItem txtMenuItem = new MenuItem("文本过滤");
	    txtMenuItem.setOnAction((ActionEvent e)->{
	    	MainPanel.mainPanel.setCenter(createAction());
		});
		 return txtMenuItem;
	}

	@Override
	public AnchorPane createAction() {
		AnchorPane detailPane=new  AnchorPane();
		Common.setBackColor(detailPane);
		
		RadioButton radio = new RadioButton("仅首个");
		
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
	        
			List<File> txtFile =fileChooser.showOpenMultipleDialog(MainPanel.getStage());
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
						if(hash.contains("=")){
							key=hash.split("\\=");
							if(StringUtil.isEmpty(key[1])){
								spaceMap.put(key[0], "");
							}else{
								spaceMap.put(key[0], key[1]);
							}
							
						}
						
					}
				}
				FileUtil.filterFile(fileUrl, filterList, spaceMap,radio.isSelected());
				
				Alert alert = new Alert(AlertType.INFORMATION);
	            alert.setTitle("提示");
	            alert.setHeaderText(null);
	            alert.setContentText("处理成功！");
	            alert.showAndWait();
//	            alert.initStyle(StageStyle.UTILITY); //不显示图标
	            try {
					Thread.sleep(1000);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
	            alert.hide();
	            
			}
		});
		
		GridPane grid = new GridPane();
	    grid.setVgap(4);
	    grid.setHgap(10);
	    grid.setPadding(new Insets(15, 5, 5, 20));
		int row=3;  //第几行
	    grid.add(new Label("文件："), 2, row);
	    grid.add(inputText, 3, row);
	    grid.add(choseBtn, 4, row);
	    
	    grid.add(new Label("过滤："), 2, row+2);
	    grid.add(filterText, 3, row+2);
	    
	    grid.add(new Label("替换："), 2, row+4);
	    grid.add(spaceText, 3, row+4);
	    
	    grid.add(radio, 2, row+6);
	    
	    
	    grid.add(createBtn, 3, row+8);
	    
		detailPane.getChildren().add(grid);
		
		
		return detailPane;
	}

}
