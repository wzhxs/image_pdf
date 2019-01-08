package image.pdf.com.core.model;

import image.pdf.com.core.inter.MenuInter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class AuthorMenu implements MenuInter<AnchorPane>{

	@Override
	public MenuItem createMenu() {
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
	   
	    return authorMenuItem;
	}

	@Override
	public AnchorPane createAction() {
		return null;
	}

}
