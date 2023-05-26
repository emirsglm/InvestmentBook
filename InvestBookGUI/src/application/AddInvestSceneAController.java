package application;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mysql.mysqlconnect;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.DatePicker;

import javafx.scene.control.ChoiceBox;

public class AddInvestSceneAController implements Initializable{
	
	private Stage stage; 
	private Scene scene; 
	private Parent root;
	
	@FXML
	private ChoiceBox<String> isBuy;
	private String[] isBuyArr = {"alis", "satis"};
	
	@FXML
	private ChoiceBox<String> precMetalType;
	private String[] precMetals = {"altin","gümüs","platin"};
	
	@FXML
	private DatePicker precMetalDate;
	
	@FXML
	private TextField precMetalAmount;
	@FXML
	private TextField precMetalCost;
	
	@FXML
	private TextField precComment;
	
	@FXML
	private Button addPrecMetalButton;
	@FXML
	private Label precMetalSaveLabel;
	
	@FXML
	private Button toMainButton;
	@FXML
	private Button prec2InvestsButton;
	
	
	int index = -1;
	Connection conn =null;
	ResultSet rs = null;
	PreparedStatement pst = null;


	// Event Listener on Button[#addPrecMetalButton].onAction
	@FXML
	public void addPrecMetal(ActionEvent event) {
		
		conn = mysqlconnect.Connect2Db();
		String sql = "INSERT INTO INVESTMENTS (INV_BUY, INV_TYPE, INV_KIND, INV_DATE,INV_COST,INV_AMOUNT,INV_INFO,INV_BONUS,INV_COMM) VALUES (?, ?, ?,?, ?, ?, ?, ?,?)";

		try {
			precMetalSaveLabel.setText("");
		
			pst = conn.prepareStatement(sql);
		    pst.setString(1, isBuy.getValue());
		    pst.setString(2, "değerli maden");
		    pst.setString(3, precMetalType.getValue());
		    pst.setString(4, precMetalDate.getValue().toString());
		    pst.setString(5, precMetalCost.getText());
		    pst.setString(6, precMetalAmount.getText());
		    pst.setString(7, "");
		    pst.setString(8, "");
		    String comment = "";
			if (precComment.getText().isEmpty() == false) {
				comment = precComment.getText();}
		    pst.setString(9, comment);
		 
		    pst.execute();
			precMetalSaveLabel.setText(precMetalDate.getValue().toString() + " tarihli " + precMetalType.getValue()+ " yatırımı kaydedildi");

			
			
		}catch(NumberFormatException e) {
			precMetalSaveLabel.setText("adet ve toplam fiyat olarak sayi girdiginize emin olunuz");
			
		}catch(Exception e) { 
			System.out.println(e);
			}	
		}
	
	// Event Listener on Button[#toMainButton].onAction
	@FXML
	public void toMain(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("AddInvestSceneMain.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	}

	// Event Listener on Button[#prec2InvestsButton].onAction
	@FXML
	public void prec2Invests(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("InvestsScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		precMetalType.getItems().addAll(precMetals);	
		isBuy.getItems().addAll(isBuyArr);		

	}
}
