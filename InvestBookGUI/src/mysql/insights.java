package mysql;

import backEnd.Invest;
import backEnd.MarketPrice;
import mysql.mysqlconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class insights {
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	static ObservableList<Invest> investData0;
	static ObservableList<MarketPrice> marketData0;

	public static double getAmount(String table,String type, String kind) {
		double amount = 0.0;
		investData0 = mysqlconnect.getInvestData(table,type,kind);
		
		for( Invest i: investData0) {
			amount += i.getAmount();
		}
		
		return amount;
	}
	
	public static double getCost(String table,String type, String kind) {
		double cost = 0.0;
		investData0 = mysqlconnect.getInvestData(table,type,kind);
		
		for( Invest i: investData0) {
			cost += i.getCost();
		}
		
		return cost;
	}
	
	
	public static double getProfit(String table,String type, String kind) {
		double profit = 0.0;
		double investAmount = 0.0;
		double investCost = 0.0;

		investCost = getCost(table,type,kind);
		investAmount = getAmount(table,type,kind);
		marketData0 = mysqlconnect.getPriceData(type,kind);
		
		profit = investCost - investAmount*(marketData0.get(0).getPrice());
		
		
		return profit;
	}
	
	
	public static double[] getTotalInfo(String table) {
		double[] info = new double[3];
		double market = 0.0;
		double profit = 0.0;
		double investAmount = 0.0;
		double investCost = 0.0;
		String investType;
		String investKind;

		investData0 = mysqlconnect.getInvestData(table);
		
		for( Invest i: investData0) {
			investType = i.getType();
			investKind = i.getKind();
			investCost = i.getCost();
			investAmount  = i.getAmount();
			marketData0 = mysqlconnect.getPriceData(investType,investKind);
			System.out.println(marketData0.size());
			market = marketData0.get(0).getPrice();

			info[0] += investCost;
			info[1] += market*investAmount;
			info[2] += investCost-market*investAmount;
			
		}
				
		
		return info;
	}
	
	
}
