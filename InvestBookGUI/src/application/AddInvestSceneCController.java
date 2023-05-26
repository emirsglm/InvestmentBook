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
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.DatePicker;

import javafx.scene.control.ChoiceBox;

public class AddInvestSceneCController implements Initializable{
	
	private Stage stage; 
	private Scene scene; 
	private Parent root;
	
	@FXML
	private DatePicker currDate;
	@FXML
	private ChoiceBox<String> isBuy;
	private String[] isBuyArr = {"alis", "satis"};
	@FXML
	private TextField currAmount;
	@FXML
	private TextField currCost;
	@FXML
	private Button addCurrButton;
	@FXML
	private Label currSaveLabel;
	@FXML
	private Button toMainButton;
	@FXML
	private Button curr2InvestsButton;

	@FXML
	private ChoiceBox<String> currKind;
	private String[] currKinds = {"altin","gümüs","platin"};
	@FXML
	private TextField currComment;
	

	int index = -1;
	Connection conn =null;
	ResultSet rs = null;
	PreparedStatement pst = null;


	// Event Listener on Button[#addCurrButton].onAction
	@FXML
	public void addCurr(ActionEvent event) {
		conn = mysqlconnect.Connect2Db();
		String sql = "INSERT INTO INVESTMENTS (INV_BUY, INV_TYPE, INV_KIND, INV_DATE,INV_COST,INV_AMOUNT,INV_INFO,INV_BONUS,INV_COMM) VALUES (?, ?, ?,?, ?, ?, ?, ?,?)";

		try {
			currSaveLabel.setText("");
		
			pst = conn.prepareStatement(sql);
		    pst.setString(1, isBuy.getValue());
		    pst.setString(2, "döviz");
		    pst.setString(3, currKind.getValue());
		    pst.setString(4, currDate.getValue().toString());
		    pst.setString(5, currCost.getText());
		    pst.setString(6, currAmount.getText());
		    pst.setString(7, "");
		    pst.setString(8, "");
		    String comment = "";
			if (currComment.getText().isEmpty() == false) {
				comment = currComment.getText();}
		    pst.setString(9, comment);
		 
		    pst.execute();
		    currSaveLabel.setText(currDate.getValue().toString() + " tarihli " + currKind.getValue()+ " yatırımı kaydedildi");

			
			
		}catch(NumberFormatException e) {
			currSaveLabel.setText("adet ve toplam fiyat olarak sayi girdiginize emin olunuz");
			
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

	
	// Event Listener on Button[#curr2InvestsButton].onAction
	@FXML
	public void curr2Invests(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("InvestsScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		currKind.getItems().addAll(currKinds);	
		isBuy.getItems().addAll(isBuyArr);		

	}
}
