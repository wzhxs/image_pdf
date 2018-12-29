package image.pdf.com.layout;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import image.pdf.com.StartRun;
import image.pdf.com.util.DateUtil;
import image.pdf.com.util.ImgUtil;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
	private List<File> getFile=new ArrayList<>();
	
	private String fileDoc=System.getProperty("user.home")+File.separator+"Desktop";
	
	/**
	 * 默认导航栏
	 */
    public MenuBar  createTopMenu(){
    	
	    Menu fileMenu = new Menu("文本");
	    MenuItem txtMenuItem = new MenuItem("文本过滤");
	    txtMenuItem.setOnAction((ActionEvent e)->{
	    	StartRun.setTop(createTopMenu());
			StartRun.setConter(new ConterPanel().createTxtPanel());
		});
	    fileMenu.getItems().addAll(txtMenuItem);

	    Menu pdfMenu = new Menu("pdf");
	    MenuItem pdfMenuItem = new MenuItem("图片转pdf");
	    pdfMenuItem.setOnAction((ActionEvent e)->{
	    	VBox topBox=new VBox();
			topBox.getChildren().addAll(createTopMenu(),createActivityPane());
			StartRun.setTop(topBox);
			StartRun.setConter();
		}); 
	    pdfMenu.getItems().add(pdfMenuItem);

	    Menu imgMenu = new Menu("图片");
	    MenuItem imgChange=new MenuItem("图片转换");
	    imgChange.setOnAction((ActionEvent e)->{
	    	VBox topBox=new VBox();
			topBox.getChildren().addAll(createTopMenu(),changeImagePan());
			StartRun.setTop(topBox);
			StartRun.setConter();
	    });
	    MenuItem imgMv = new MenuItem("图片移动（未）");
	    imgMenu.getItems().addAll(imgChange,imgMv);
	    
	    Menu webMenu = new Menu("web");
	    MenuItem requestTest=new MenuItem("请求测试");
	    requestTest.setOnAction((ActionEvent e)->{
	    	VBox topBox=new VBox();
			topBox.getChildren().addAll(createTopMenu());
			StartRun.setTop(topBox);
			StartRun.setConter(new WebPanel().createWebRequest());
	    });
	    webMenu.getItems().addAll(requestTest);
	    
	    Menu aboutMenu = new Menu("关于");
	    MenuItem authorMenuItem = new MenuItem("作者");
	    authorMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
	            Alert alert = new Alert(AlertType.INFORMATION);
	            alert.setTitle("作者");
	            alert.setHeaderText(null);
	            alert.setContentText("王宗会个人工具");
	            alert.showAndWait();
        }});
	    
	    MenuItem exitMenuItem = new MenuItem("退出");
	    exitMenuItem.setOnAction(actionEvent -> Platform.exit());
	    aboutMenu.getItems().addAll(authorMenuItem,exitMenuItem);
	    
	    MenuBar menuBar=new MenuBar();
	    menuBar.getMenus().addAll(fileMenu, pdfMenu, imgMenu,webMenu,aboutMenu);
	    return menuBar;
    }
    
    /**
     * 图片转换
     */
    public HBox changeImagePan(){
    	
		HBox  title=new HBox();
		Button moreFileBtn = new Button("选择");
		setborderColor(moreFileBtn);
		moreFileBtn.setOnAction((ActionEvent e)->{
			StartRun.fileChooser.setTitle("MoreFile");
			getFile =StartRun.fileChooser.showOpenMultipleDialog(StartRun.getStage());
			if(getFile!=null&&getFile.size()>0){
				rightPanel.addImage(getFile);	
			}
		});
		
		Button createBtn = new Button("tif转jpg");
		setborderColor(createBtn);
		createBtn.setOnAction((ActionEvent e) ->{
			 if(getFile!=null&&getFile.size()>0){
				 for(File file:getFile){
					 ImgUtil.tifToJpg(file.toString());
				 }
				getFile=new ArrayList<>();
				StartRun.setConter();
			}
		});
	    
	    title.getChildren().setAll(moreFileBtn,createBtn);
	    return title;
    }
	
	/**
	 * 处理pdf
	 */
	public  HBox createActivityPane(){

		HBox  title=new HBox();
		Button oneFileBtn = new Button("单文件");
		setborderColor(oneFileBtn);
		oneFileBtn.setOnAction((ActionEvent e)->{
			StartRun.fileChooser.setTitle("OneFile");
			oneFile=StartRun.fileChooser.showOpenMultipleDialog(StartRun.getStage());
			if(oneFile!=null&&oneFile.size()>0){
				rightPanel.addImage(oneFile);	
			}
		});
		
		Button moreFileBtn = new Button("多文件");
		setborderColor(moreFileBtn);
		moreFileBtn.setOnAction((ActionEvent e)->{
			StartRun.fileChooser.setTitle("MoreFile");
			moreFile =StartRun.fileChooser.showOpenMultipleDialog(StartRun.getStage());
			if(moreFile!=null&&moreFile.size()>0){
				rightPanel.addImage(moreFile);	
			}
		});
		
		Button createBtn = new Button("生成");
		setborderColor(createBtn);
		createBtn.setOnAction((ActionEvent e) ->{
			if(oneFile!=null&&oneFile.size()>0){
				String pdfUrl=fileDoc+File.separator+"more"+File.separator;
				ImgUtil.oneFile(oneFile, pdfUrl);
				oneFile=new ArrayList<>();
				StartRun.setConter();
			}else if(moreFile!=null&&moreFile.size()>0){
				String pdfFile=fileDoc+File.separator+DateUtil.getTime()+".pdf";
				ImgUtil.moreFile(moreFile, pdfFile);
				moreFile=new ArrayList<>();
				StartRun.setConter();
			}
		});
	    
	    title.getChildren().setAll(oneFileBtn,moreFileBtn,createBtn);
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
