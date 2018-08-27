package image.pdf.com.layout;

import java.io.File;
import java.util.List;

import image.pdf.com.StartRun;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

/**
 * 右侧面板
 * https://blog.csdn.net/wingfourever/article/details/8895886
 * 
 * @author wangzh
 * @date 2018年8月23日 上午9:46:49
 */
public class RightPanel {
	
	private ScrollPane  imagePane=new ScrollPane ();
	
	public ScrollPane  createRightPanel(){		
//		Image img=new Image("file:E:\\img12.jpg");
//		ImageView iv1 = new ImageView(img);
//		iv1.setFitHeight(200);    //高度
//		iv1.setPreserveRatio(true);  //宽度
////		iv1.setSmooth(true);
////		iv1.setCache(true);
//		
//		Image img2=new Image("file:E:\\python-space\\eci\\objectDecision\\tianxian\\image\\test\\IMG_5459.JPG");
//		ImageView image2=new ImageView(img2);
//		
//		imagePane.getChildren().add(iv1);
    	
		return imagePane;
	}
	
	
	public void addImage(List<File> fileList){
		this.imagePane.setContent(null);
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
		
		this.imagePane.setContent(flowPane);
		StartRun.setConter(this.imagePane);
	}

}
