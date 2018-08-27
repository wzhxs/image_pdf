package image.pdf.com.layout;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import image.pdf.com.StartRun;
import image.pdf.com.util.DateUtil;
import image.pdf.com.util.ImgToPdf;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * 左侧面板
 * 
 * @author wangzh
 * @date 2018年8月23日 上午9:46:38
 */
public class TopPanel {
	private RightPanel rightPanel=new RightPanel();
	private List<File> oneFile=new ArrayList<>();
	private List<File> moreFile=new ArrayList<>();
	private String fileDoc=System.getProperty("user.home")+File.separator+"Desktop";
	
	public  HBox createActivityPane(Stage stage){
		HBox  title=new HBox();
		
		Button oneFileBtn = new Button("单文件");
		setborderColor(oneFileBtn);
		oneFileBtn.setOnAction((ActionEvent e)->{
			StartRun.fileChooser.setTitle("OneFile");
			oneFile=StartRun.fileChooser.showOpenMultipleDialog(stage);
			if(oneFile!=null&&oneFile.size()>0){
				rightPanel.addImage(oneFile);	
			}
		});
		
		Button moreFileBtn = new Button("多文件");
		setborderColor(moreFileBtn);
		moreFileBtn.setOnAction((ActionEvent e)->{
			StartRun.fileChooser.setTitle("MoreFile");
			moreFile =StartRun.fileChooser.showOpenMultipleDialog(stage);
			if(moreFile!=null&&moreFile.size()>0){
				rightPanel.addImage(moreFile);	
			}
		});
		
		Button createBtn = new Button("生成");
		setborderColor(createBtn);
		createBtn.setOnAction((ActionEvent e) ->{
			if(oneFile!=null&&oneFile.size()>0){
				String pdfUrl=fileDoc+File.separator+"more"+File.separator;
				ImgToPdf.oneFile(oneFile, pdfUrl);
				oneFile=new ArrayList<>();
				StartRun.setConter();
			}else if(moreFile!=null&&moreFile.size()>0){
				String pdfFile=fileDoc+File.separator+DateUtil.getTime()+".pdf";
				ImgToPdf.moreFile(moreFile, pdfFile);
				moreFile=new ArrayList<>();
				StartRun.setConter();
			}
		});
		
		Button aboutBtn = new Button("关于");
		setborderColor(aboutBtn);
		
	    aboutBtn.setOnAction((ActionEvent e) -> {
	    	 Alert alert = new Alert(AlertType.INFORMATION);
	            alert.setTitle("说明");
	            alert.setHeaderText(null);
	            alert.setContentText("个人工具");

	            alert.showAndWait();
	     });
	    
	    
	    title.getChildren().setAll(oneFileBtn,moreFileBtn,createBtn,aboutBtn);
	    return title;
	}
	
	/**
	 * 配置按钮
	 * @param btn
	 */
	public void setborderColor(Button btn){
		btn.setStyle("-fx-border-color: #E1E1E1;");
	}
}