package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;


import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mysql.mysqlconnect;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.DatePicker;

import javafx.scene.control.ChoiceBox;

public class AddInvestSceneBController implements Initializable{

	private Stage stage; 
	private Scene scene; 
	private Parent root;
	
	@FXML
	private ChoiceBox<String> isBuy;
	private String[] isBuyArr = {"alis", "satis"};
	
	@FXML
	private TextField stockFirm;
	
	@FXML
	private DatePicker stockDate;
	
	@FXML
	private TextField stockAmount;
	
	@FXML
	private TextField stockCost;
	
	@FXML
	private ChoiceBox<String> paidOrNonPaid;
	private String[] ponpArr = {"normal", "bedelli", "bedelsiz"};
	
	@FXML
	private TextField paidCost;
	
	@FXML
	private ChoiceBox<String> isBonus;
	private String[] isBonusArr = {"evet", "hayir"};
	
	@FXML
	private TextField bonusMoney;

	@FXML
	private TextField stockComment;

	@FXML
	private Button addStockButton;
	@FXML
	private Label stockSaveLabel;
	@FXML
	private Button toMainButton;
	@FXML
	private Button stock2InvestsButton;


	int index = -1;
	Connection conn =null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	// Event Listener on Button[#addPrecMetalButton].onAction
	@FXML
	public void addStock(ActionEvent event) {
		conn = mysqlconnect.Connect2Db();
		String sql = "INSERT INTO INVESTMENTS (INV_BUY, INV_TYPE, INV_KIND, INV_DATE,INV_COST,INV_AMOUNT,INV_INFO,INV_BONUS,INV_COMM) VALUES (?, ?, ?,?, ?, ?, ?, ?,?)";

		try {
			stockSaveLabel.setText("");
		
			pst = conn.prepareStatement(sql);
		    pst.setString(1, isBuy.getValue());
		    pst.setString(2, "hisse");
		    pst.setString(3, stockFirm.getText());
		    pst.setString(4, stockDate.getValue().toString());
		    pst.setString(5, stockCost.getText());
		    pst.setString(6, stockAmount.getText());
		    pst.setString(7, paidOrNonPaid.getValue());
		    pst.setString(8, isBonus.getValue());
		    String comment = "";
			if (stockComment.getText().isEmpty() == false) {
				comment = stockComment.getText();}
		    pst.setString(9, comment);
		 
		    pst.execute();
			
			stockSaveLabel.setText(stockDate.getValue().toString() + " tarihli " + stockFirm.getText()+ " yatırımı kaydedildi");
			
		}catch(NumberFormatException e) {
			stockSaveLabel.setText(" hata ");
		
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
	
	
	// Event Listener on Button[#stock2InvestsButton].onAction
	@FXML
	public void stock2Invests(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("InvestsScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		isBuy.getItems().addAll(isBuyArr);
		paidOrNonPaid.getItems().addAll(ponpArr);
		isBonus.getItems().addAll(isBonusArr);
	}
}
