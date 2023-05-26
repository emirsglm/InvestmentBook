package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import backEnd.Invest;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mysql.mysqlconnect;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;


public class InvestsSceneController implements Initializable{

	private Stage stage; 
	private Scene scene; 
	private Parent root;
	
	@FXML
	private TableView<Invest> investTable;
	@FXML
	private TableColumn<Invest, Integer> inv_id;
	@FXML
	private TableColumn<Invest, String> inv_buy;
	@FXML
	private TableColumn<Invest, String> inv_type;
	@FXML
	private TableColumn<Invest, String> inv_kind;
	@FXML
	private TableColumn<Invest, LocalDate> inv_date;
	@FXML
	private TableColumn<Invest, Double> inv_cost;
	@FXML
	private TableColumn<Invest, Double> inv_amount;
	@FXML
	private TableColumn<Invest, Double> inv_ca;


	@FXML
	private TableColumn<Invest, String> inv_comm;
	
	@FXML
	private Button toPrecTableButton;
	@FXML
	private Button toCurrTableButton;
	@FXML
	private Button toStockTableButton;
	@FXML
	private Button toAddInvestSceneButton;
	@FXML
	private Label totalInvestLabel;
	@FXML
	private Label totalIncomeLabel;
	
	
	ObservableList<Invest> investData;
	

	int index = -1;
	Connection conn =null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	@Override
	public void initialize (URL url, ResourceBundle rb) {
		// TODO

		inv_id.setCellValueFactory(new PropertyValueFactory<Invest,Integer>("id"));
		inv_buy.setCellValueFactory(new PropertyValueFactory<Invest,String>("buy"));
		inv_buy.setCellValueFactory(cellData -> {
		    String buy = cellData.getValue().getBuyString();
		    return new SimpleStringProperty(buy);
		});

		inv_type.setCellValueFactory(new PropertyValueFactory<Invest,String>("type"));
		inv_kind.setCellValueFactory(new PropertyValueFactory<Invest,String>("kind"));
		inv_date.setCellValueFactory(new PropertyValueFactory<Invest,LocalDate>("date"));
		inv_cost.setCellValueFactory(new PropertyValueFactory<Invest,Double>("cost"));
		inv_amount.setCellValueFactory(new PropertyValueFactory<Invest,Double>("amount"));
		inv_ca.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getCostPerUnit()));
		
		inv_comm.setCellValueFactory(cellData -> {
		    String comm = cellData.getValue().getComment();
		    return new SimpleStringProperty(comm);
		});
		
		investData = mysqlconnect.getData("invests.INVESTMENTS");

		investTable.setItems(investData);

	}
	
	
	public void toAddInvestScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("AddInvestSceneMain.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void toPrecTable(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("toPrecTableScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void toCurrTable(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("toCurrTableScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void toStockTable(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("toStockTableScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}