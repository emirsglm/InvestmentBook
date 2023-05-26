package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;

public class MainSceneController {
	
	private Stage stage; 
	private Scene scene; 
	private Parent root;
	
	@FXML
	public void toAddInvestScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("AddInvestSceneMain.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void toInvestsScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("InvestsScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
      