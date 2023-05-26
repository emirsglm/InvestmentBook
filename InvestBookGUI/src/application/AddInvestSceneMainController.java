package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class AddInvestSceneMainController implements Initializable{
	
	private Stage stage; 
	private Scene scene; 
	private Parent root;
	

	@FXML
	private ChoiceBox<String> investType;
	private String[] investTypeArr = {"kiymetli maden", "hisse", "doviz"};
	

	// Event Listener on Button.onAction
	@FXML
	public void next(ActionEvent event) throws IOException{
				
		String type = investType.getValue();
		
		
		if (type == "kiymetli maden") {
			root = FXMLLoader.load(getClass().getResource("AddInvestSceneA.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();	
		}
		 
		
		if (type == "hisse") {
			root = FXMLLoader.load(getClass().getResource("AddInvestSceneB.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
		if (type == "doviz") {
			root = FXMLLoader.load(getClass().getResource("AddInvestSceneC.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		investType.getItems().addAll(investTypeArr);				
	}
}
