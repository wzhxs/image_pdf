package image.pdf.com.core.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import image.pdf.com.MainPanel;
import image.pdf.com.core.common.Common;
import image.pdf.com.core.inter.MenuInter;
import image.pdf.com.util.ImgUtil;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class ImgChangeMenu implements MenuInter<HBox>{
	
	private List<File> getFile=new ArrayList<>();
	
	@Override
	public MenuItem createMenu() {
		MenuItem imgChange=new MenuItem("图片转换");
	    imgChange.setOnAction((ActionEvent e)->{
	    	MainPanel.mainPanel.setCenter(createAction());
	    });
	    return imgChange;
	}

	@Override
	public HBox createAction() {
		FileChooser fileChooser=Common.getSelectFile(); 
		HBox  title=new HBox();
		Button moreFileBtn = new Button("选择");
		moreFileBtn.setOnAction((ActionEvent e)->{
			fileChooser.setTitle("MoreFile");
			getFile =fileChooser.showOpenMultipleDialog(MainPanel.getStage());
			if(getFile!=null&&getFile.size()>0){
				addImage(getFile);	
			}
		});
		
		Button createBtn = new Button("tif转jpg");
		createBtn.setOnAction((ActionEvent e) ->{
			 if(getFile!=null&&getFile.size()>0){
				 for(File file:getFile){
					 ImgUtil.tifToJpg(file.toString());
				 }
				getFile=new ArrayList<>();
			}
		});
		title.getChildren().setAll(moreFileBtn,createBtn);
		return title;
	}
	
	private void addImage(List<File> fileList){
		ScrollPane  imagePane=new ScrollPane();
		FlowPane flowPane = new FlowPane();
		flowPane.setPrefWidth(900);
		flowPane.setPrefHeight(600);
		
		for(File file:fileList){
			
			Image image=new Image("file:"+file.toString());
			ImageView imageView = new ImageView(image);
			imageView.setFitWidth(300);    //高度
			imageView.setPreserveRatio(true);  //宽度
			flowPane.getChildren().add(imageView);
		}
		
		imagePane.setContent(flowPane);
		VBox topBox=new VBox();
		topBox.getChildren().addAll(createAction(),imagePane);
		
		MainPanel.mainPanel.setCenter(topBox);
	}

}
