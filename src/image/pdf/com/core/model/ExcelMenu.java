package image.pdf.com.core.model;

import java.io.File;
import java.util.List;

import image.pdf.com.MainPanel;
import image.pdf.com.core.common.Common;
import image.pdf.com.core.inter.MenuInter;
import image.pdf.com.util.ExcelUtil;
import image.pdf.com.util.FileUtil;
import image.pdf.com.util.StringUtil;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class ExcelMenu implements MenuInter<AnchorPane>{

	@Override
	public MenuItem createMenu() {
		MenuItem excelMenuItem = new MenuItem("excel转txt");
		excelMenuItem.setOnAction((ActionEvent e)->{
	    	MainPanel.mainPanel.setCenter(createAction());
		});
		
		return excelMenuItem;
	}

	@Override
	public AnchorPane createAction() {
		//选择分栏，起始行数，选择列（输出顺序和列顺序相同）
		AnchorPane detailPane=new  AnchorPane();
		Common.setBackColor(detailPane);
		
		//选择文件
		TextField inputText = new TextField();
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
		
		//分栏
		TextField sheetNum=new TextField();
		
		//选择列
		TextField fieldNum=new TextField();
		
		//起始行
		TextField startColumn=new TextField();
		
		Button createBtn=new Button("生成");
		createBtn.setOnAction((ActionEvent e)->{
			String fileUrl=inputText.getText();
			if(StringUtil.isNotEmpty(fileUrl)){
				String sheet=sheetNum.getText();  //获取分栏号
				String[] fieldList=fieldNum.getText().split("\\,"); //获取列号
				String start=startColumn.getText();
				
				String context;
				if(fieldList!=null&&fieldList.length>0){
					boolean status=ExcelUtil.readExcel(fileUrl, sheet, fieldList, start);
					context="操作失败，请查看日志确认原因。";
					if(status){
						context="操作成功";
					}
					
				}else{
					context="列号为空";
					
				}
				
				Alert alert = new Alert(AlertType.INFORMATION);
	            alert.setTitle("提示");
	            alert.setHeaderText(null);
	            alert.setContentText(context);
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
		
	    grid.add(new Label("Excel文件："), 2, row);
	    grid.add(inputText, 3, row);
	    grid.add(choseBtn, 4, row);
	    
	    grid.add(new Label("分栏："), 2, row+2);
	    grid.add(sheetNum, 3, row+2);
	    grid.add(new Label("从0开始"), 4, row+2);
	    
	    grid.add(new Label("数据列："), 2, row+4);
	    grid.add(fieldNum, 3, row+4);
	    grid.add(new Label("从0开始，多列逗号分隔"), 4, row+4);
	    
	    grid.add(new Label("起始行："), 2, row+6);
	    grid.add(startColumn, 3, row+6);
	    grid.add(new Label("从0开始，仅支持单行"), 4, row+6);
	    
	    grid.add(createBtn, 3, row+8);
	    
		detailPane.getChildren().add(grid);
		return detailPane;
	}

}
