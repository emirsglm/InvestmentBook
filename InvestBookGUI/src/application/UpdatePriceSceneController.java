package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mysql.mysqlconnect;

import javafx.scene.control.ChoiceBox;

public class UpdatePriceSceneController implements Initializable{
	
	private Stage stage; 
	private Scene scene; 
	private Parent root;
	
	int index = -1;
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
		
	
	@FXML
	private ChoiceBox<String> marketType;
	private List<String> marketTypeArr = mysqlconnect.getDistinctValues("INV_TYPE");
	@FXML
	private TextField marketPrice;
	
	@FXML
	private DatePicker marketDate;
	@FXML
	private Button updateButton;
	@FXML
	private ComboBox<String> marketKind;
	private List<String> marketKindArr = mysqlconnect.getDistinctValues("INV_KIND");

	@FXML
	private Button toAddInvest;
	@FXML
	private Button toInvests;
	
	@FXML
	private Label updateLabel;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		   marketType.getItems().addAll(marketTypeArr);
	        
	        // Add event listener to marketType ChoiceBox
	        marketType.setOnAction(event -> {
	            String selectedType = marketType.getValue();
	            marketKindArr.clear();
	            marketKindArr.addAll(mysqlconnect.getDistinctValues(selectedType, "INV_KIND"));
	            marketKind.getItems().clear();
	            marketKind.getItems().addAll(marketKindArr);
	        });
	}
	// Event Listener on Button[#updateButton].onAction
	@FXML
	public void updateMarket(ActionEvent event) {
		conn = mysqlconnect.Connect2Db();
		String sql = "INSERT INTO PRICES (PRICE_TYPE, PRICE_KIND, PRICE, PRICE_DATE) VALUES (?, ?, ?, ?)";

		try {
			updateLabel.setText("");
		
			pst = conn.prepareStatement(sql);
		    pst.setString(1, marketType.getValue());
		    pst.setString(2, marketKind.getValue());
		    pst.setString(3, marketPrice.getText());

		    pst.setString(4, marketDate.getValue().toString());
				 
		    pst.execute();
			
		    updateLabel.setText(marketKind.getValue()+ " fiyatı " + marketDate.getValue().toString() + " tarihine güncellendi " );
			
		}catch(NumberFormatException e) {
			updateLabel.setText(" hata ");
		
		}catch(Exception e) { 
			System.out.println(e);
			}	
		}
	
	// Event Listener on Button[#toAddInvest].onAction
	@FXML
	public void toAddInvestScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("AddInvestSceneMain.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
	}
	// Event Listener on Button[#toInvests].onAction
	@FXML
	public void toInvestsScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("InvestsScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
	}
}
