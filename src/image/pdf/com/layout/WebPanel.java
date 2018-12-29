package image.pdf.com.layout;


import image.pdf.com.util.HttpUtil;
import image.pdf.com.util.StringUtil;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WebPanel {
	
	public AnchorPane createWebRequest(){
	    
	    GridPane grid = new GridPane();
	    grid.setVgap(4);
	    grid.setHgap(10);
	    grid.setPadding(new Insets(15, 5, 5, 20));
	    
	    
	    Text text = new Text(10, 50, "");     
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
		
		
		int left=255;
		detailPane.setBackground(new Background(new BackgroundFill(Color.rgb(left, left, left, .99),null,null)));
		return detailPane;
	}

	public void doQuest(GridPane grid,Text output){
		TextField inputText = new TextField();
			
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
						output.setText(HttpUtil.sendPost(text,""));    //post
					}
				}
			}
			
		});
		
		int row=0;
		grid.add(new Label("请求地址："), 0, row);
	    grid.add(inputText, 1, row);
	    grid.add(choiceBox, 2, row);
	    grid.add(chang, 3, row);
	}

}
