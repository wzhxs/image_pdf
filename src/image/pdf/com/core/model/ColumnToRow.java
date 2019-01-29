package image.pdf.com.core.model;

import image.pdf.com.MainPanel;
import image.pdf.com.core.common.Common;
import image.pdf.com.core.inter.MenuInter;
import image.pdf.com.util.StringUtil;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class ColumnToRow implements MenuInter<AnchorPane>{

	@Override
	public MenuItem createMenu() {
		MenuItem txtMenuItem = new MenuItem("列转行");
	    txtMenuItem.setOnAction((ActionEvent e)->{
	    	MainPanel.mainPanel.setCenter(createAction());
		});
		 return txtMenuItem;
	}

	@Override
	public AnchorPane createAction() {
		AnchorPane detailPane=new  AnchorPane();
		Common.setBackColor(detailPane);

		GridPane grid = new GridPane();
	    grid.setVgap(4);
	    grid.setHgap(10);
	    grid.setPadding(new Insets(15, 5, 5, 20));
	    
		TextArea inputText=new TextArea();
		TextArea outputText=new TextArea();
		Button toRow=new Button("转换");
		toRow.setOnAction((ActionEvent e)->{
			String context=inputText.getText();
			if(StringUtil.isNotEmpty(context)){
				context=context.replaceAll("\r|\n"," ").replaceAll("\t","");
				outputText.setText(context);
				inputText.setText("");
			}
		});
		
		grid.add(inputText, 1, 3);  //输入
		grid.add(toRow, 2, 3);		//参数
		grid.add(outputText, 1, 5);  //输出
		
		detailPane.getChildren().add(grid);
		return detailPane;
	}

}
