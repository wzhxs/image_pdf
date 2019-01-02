package image.pdf.com.core.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import image.pdf.com.MainPanel;
import image.pdf.com.core.ContralPanel;
import image.pdf.com.core.common.Common;
import image.pdf.com.core.inter.MenuInter;
import image.pdf.com.layout.RightPanel;
import image.pdf.com.util.DateUtil;
import image.pdf.com.util.ImgUtil;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class PdfMenu implements MenuInter<HBox>{
	
	private RightPanel rightPanel=new RightPanel();
	private List<File> oneFile=new ArrayList<>();
	private List<File> moreFile=new ArrayList<>();
	
	private FileChooser fileChooser = new FileChooser();

	@Override
	public MenuItem createMenu() {
		MenuItem pdfMenuItem = new MenuItem("img转pdf");
		pdfMenuItem.setOnAction((ActionEvent e)->{
			ContralPanel.mainPanel.setCenter(createAction());
		});
		return pdfMenuItem;
	}

	@Override
	public HBox createAction() {
		HBox  title=new HBox();
		Button oneFileBtn = new Button("单文件");
		Common.setborderColor(oneFileBtn);
		oneFileBtn.setOnAction((ActionEvent e)->{
			fileChooser.setTitle("OneFile");
			oneFile=fileChooser.showOpenMultipleDialog(MainPanel.getStage());
			if(oneFile!=null&&oneFile.size()>0){
				rightPanel.addImage(oneFile);	
			}
		});
		
		Button moreFileBtn = new Button("多文件");
		Common.setborderColor(moreFileBtn);
		moreFileBtn.setOnAction((ActionEvent e)->{
			fileChooser.setTitle("MoreFile");
			moreFile =fileChooser.showOpenMultipleDialog(MainPanel.getStage());
			if(moreFile!=null&&moreFile.size()>0){
				rightPanel.addImage(moreFile);	
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
		});
	    
	    title.getChildren().setAll(oneFileBtn,moreFileBtn,createBtn);
	    return title;
		
	}

}
