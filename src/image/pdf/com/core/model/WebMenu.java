package image.pdf.com.core.model;

import image.pdf.com.MainPanel;
import image.pdf.com.core.common.Common;
import image.pdf.com.core.inter.MenuInter;
import image.pdf.com.util.HttpUtil;
import image.pdf.com.util.StringUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WebMenu implements MenuInter<AnchorPane>{

	@Override
	public MenuItem createMenu() {
		MenuItem requestTest=new MenuItem("请求测试");
	    requestTest.setOnAction((ActionEvent e)->{
	    	MainPanel.mainPanel.setCenter(createAction());
	    });
		 
		return requestTest;
	}

	@Override
	public AnchorPane createAction() {
		GridPane grid = new GridPane();
	    grid.setVgap(4);
	    grid.setHgap(10);
	    grid.setPadding(new Insets(15, 5, 5, 20));
	    
	    
	    Text text = new Text(20, 100, "");     
		text.prefWidth(400);         
		text.setFont(new Font(12));
		
		
		doQuest(grid,text);
		
	    AnchorPane detailPane=createMainPane();
		detailPane.getChildren().addAll(grid,text);
		return detailPane;
	}
	
	private AnchorPane createMainPane(){
		AnchorPane detailPane=new AnchorPane();
		detailPane.setPrefWidth(400.0);
		Common.setBackColor(detailPane);
		return detailPane;
	}

	public void doQuest(GridPane grid,Text output){
		TextField inputText = new TextField();
		TextField paramText = new TextField();
			
		//下拉框
		ChoiceBox<Object> choiceBox=new ChoiceBox<>();
		choiceBox.setItems(FXCollections.observableArrayList("get", "post"));
		choiceBox.getSelectionModel().select(0);
		choiceBox.setTooltip(new Tooltip("选择请求方式"));  //解释 
		
		Button chang=new Button("请求");
		chang.setOnAction( event -> {
			String text=inputText.getText();
			if(StringUtil.isEmpty(text)){
				output.setText("网址不能为空");
			}else{
				if(text.startsWith("http")){
					if(choiceBox.getSelectionModel().getSelectedIndex()==0){  //get
						output.setText(HttpUtil.getContext(text));
					}else{
						
						output.setText(HttpUtil.sendPost(text,paramText.getText()));    //post
					}
				}
			}
			
		});
		
		int row=0;
		grid.add(new Label("请求地址："), 0, row);
	    grid.add(inputText, 1, row);
	    grid.add(choiceBox, 2, row);
	    grid.add(chang, 3, row);
	    
	    grid.add(new Label("请求参数："), 0, row+2);
	    grid.add(paramText, 1, row+2);
	}

}