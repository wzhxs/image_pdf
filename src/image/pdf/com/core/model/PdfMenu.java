package image.pdf.com.core.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import image.pdf.com.MainPanel;
import image.pdf.com.core.common.Common;
import image.pdf.com.core.inter.MenuInter;
import image.pdf.com.util.DateUtil;
import image.pdf.com.util.ImgUtil;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class PdfMenu implements MenuInter<HBox>{
	
	private List<File> oneFile=new ArrayList<>();
	private List<File> moreFile=new ArrayList<>();

	@Override
	public MenuItem createMenu() {
		MenuItem pdfMenuItem = new MenuItem("img转pdf");
		pdfMenuItem.setOnAction((ActionEvent e)->{
			MainPanel.mainPanel.setCenter(createAction());
		});
		return pdfMenuItem;
	}

	@Override
	public HBox createAction() {
		FileChooser fileChooser=Common.getSelectFile();  
		HBox  title=new HBox();
		Button oneFileBtn = new Button("单文件");
		Common.setborderColor(oneFileBtn);
		oneFileBtn.setOnAction((ActionEvent e)->{
			fileChooser.setTitle("OneFile");
			oneFile=fileChooser.showOpenMultipleDialog(MainPanel.getStage());
			if(oneFile!=null&&oneFile.size()>0){
				addImage(oneFile);	
			}
		});
		
		Button moreFileBtn = new Button("多文件");
		Common.setborderColor(moreFileBtn);
		moreFileBtn.setOnAction((ActionEvent e)->{
			fileChooser.setTitle("MoreFile");
			moreFile =fileChooser.showOpenMultipleDialog(MainPanel.getStage());
			if(moreFile!=null&&moreFile.size()>0){
				addImage(moreFile);	
			}
		});
		
		Button createBtn = new Button("生成");
		Common.setborderColor(createBtn);
		createBtn.setOnAction((ActionEvent e) ->{
			if(oneFile!=null&&oneFile.size()>0){
				String pdfUrl=Common.fileDoc+File.separator+"more"+File.separator;
				ImgUtil.oneFile(oneFile, pdfUrl);
				oneFile=new ArrayList<>();
			}else if(moreFile!=null&&moreFile.size()>0){
				String pdfFile=Common.fileDoc+File.separator+DateUtil.getTime()+".pdf";
				ImgUtil.moreFile(moreFile, pdfFile);
				moreFile=new ArrayList<>();
			}
			MainPanel.mainPanel.setCenter(createAction());
		});
	    
	    title.getChildren().setAll(oneFileBtn,moreFileBtn,createBtn);
	    return title;
		
	}
	
	private void addImage(List<File> fileList){
		ScrollPane  imagePane=new ScrollPane();
		FlowPane flowPane = new FlowPane();
		flowPane.setPrefWidth(900);
		flowPane.setPrefHeight(600);
		
		for(File file:fileList){
			if(!file.toString().endsWith("jpg")){
				Alert alert = new Alert(AlertType.INFORMATION);
	            alert.setTitle("异常");
	            alert.setHeaderText(null);
	            alert.setContentText(file+" Is Not Jpg");
	            alert.showAndWait();
	            MainPanel.mainPanel.setCenter(createAction());
			}
			
			Image image=new Image("file:"+file.toString());
			ImageView imageView = new ImageView(image);
			imageView.setFitWidth(300);    //高度
			imageView.setPreserveRatio(true);  //宽度
			flowPane.getChildren().add(imageView);
		}
		
		imagePane.setContent(flowPane);
		
		VBox vbox=new VBox();
		vbox.getChildren().setAll(createAction(),imagePane);
		MainPanel.mainPanel.setCenter(vbox);
	}

}
