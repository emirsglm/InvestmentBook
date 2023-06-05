package mysql;

import backEnd.Invest;
import backEnd.MarketPrice;

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

public class mysqlconnect {
	
	public static Connection Connect2Db() {
		
		String url="jdbc:mysql://localhost:3306/invests";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = (Connection) DriverManager.getConnection(url,"root","12345678");
			System.out.println(conn);


			//JOptionPane.showMessageDialog(null, "Connection Established");

			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
	}
	
	public static ObservableList<Invest> getInvestData(String tableName){

		Connection conn = Connect2Db();
		ObservableList<Invest> list = FXCollections.observableArrayList();

		
		try {
			PreparedStatement ps0 = conn.prepareStatement("SELECT * FROM "+tableName);
			System.out.println("SELECT * FROM "+tableName);
			ResultSet rs0 = ps0.executeQuery();
			
			while (rs0.next()) {
				
				String comment = "";
				boolean paid = false;
				boolean nonPaid = false;
				boolean bonus = false;
				boolean buy = true;
				

				
				if (rs0.getString("INV_COMM") != null) {
					comment = rs0.getString("INV_COMM");
				}
				if (rs0.getString("INV_INFO") == "bedelli") {
					nonPaid = true;
				}
				if (rs0.getString("INV_INFO") == "bedelsiz") {
					paid = true;
				}
				if (rs0.getString("INV_BONUS") == "evet") {
					bonus = true;
				}
				if(rs0.getString("INV_BUY").equals("satis")) {
					buy = false;
				} 
				System.out.println(rs0.getString("INV_ID"));
				System.out.println(buy);
				System.out.println();


			
				list.add(new Invest(Integer.parseInt(rs0.getString("INV_ID")),
						rs0.getString("INV_TYPE"), 
						rs0.getString("INV_KIND"),
						LocalDate.parse(rs0.getString("INV_DATE")), 
						Double.parseDouble(rs0.getString("INV_COST")), 
						Double.parseDouble(rs0.getString("INV_AMOUNT")), 
						buy, paid, nonPaid, bonus, comment));
				
				System.out.println(list.get(0));
				
				
			}
			rs0.close();
	        ps0.close();
		}catch(Exception e){
			System.out.println("getInvestData1 hatası");
		}
		
		
		return list;
	}
	
	
	public static ObservableList<Invest> getInvestData(String tableName, String type, String kind){

		Connection conn = Connect2Db();
		ObservableList<Invest> list = FXCollections.observableArrayList();

		
		try {
			PreparedStatement ps1 = conn.prepareStatement("SELECT DISTINCT INV_TYPE, INV_KIND FROM INVESTMENTS WHERE "
					+ "INV_TYPE = \"" + type + "\""
					+ "INV_KIND = \"" + kind + "\"");
			
			System.out.println("SELECT DISTINCT INV_TYPE, INV_KIND FROM INVESTMENTS WHERE "
					+ "INV_TYPE = \"" + type + "\""
					+ "INV_KIND = \"" + kind + "\"");

			
			ResultSet rs1 = ps1.executeQuery();
			
			while (rs1.next()) {
				
				String comment = "";
				boolean paid = false;
				boolean nonPaid = false;
				boolean bonus = false;
				boolean buy = true;
				

				
				if (rs1.getString("INV_COMM") != null) {
					comment = rs1.getString("INV_COMM");
				}
				if (rs1.getString("INV_INFO") == "bedelli") {
					nonPaid = true;
				}
				if (rs1.getString("INV_INFO") == "bedelsiz") {
					paid = true;
				}
				if (rs1.getString("INV_BONUS") == "evet") {
					bonus = true;
				}
				if(rs1.getString("INV_BUY").equals("satis")) {
					buy = false;
				} 
				System.out.println(rs1.getString("INV_BUY"));
				System.out.println(buy);
				System.out.println();


			
				list.add(new Invest(Integer.parseInt(rs1.getString("INV_ID")),
						rs1.getString("INV_TYPE"), 
						rs1.getString("INV_KIND"),
						LocalDate.parse(rs1.getString("INV_DATE")), 
						Double.parseDouble(rs1.getString("INV_COST")), 
						Double.parseDouble(rs1.getString("INV_AMOUNT")), 
						buy, paid, nonPaid, bonus, comment));
				
				
			}
			rs1.close();
	        ps1.close();
			
		}catch(Exception e){
			System.out.println("getInvestData2 hatası");
		}
		return list;
	}
	
	
	public static ObservableList<MarketPrice> getPriceData(){

		Connection conn = Connect2Db();
		ObservableList<MarketPrice> list = FXCollections.observableArrayList();

		
		try {
			PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM PRICES");
			ResultSet rs2 = ps2.executeQuery();
			System.out.println("SELECT * FROM PRICES");

			
			while (rs2.next()) {
				


				list.add(new MarketPrice(
						Integer.parseInt(rs2.getString("PRICE_ID")),
						rs2.getString("PRICE_TYPE"), 
						rs2.getString("PRICE_KIND"),
						Double.parseDouble(rs2.getString("PRICE")),
						LocalDate.parse(rs2.getString("PRICE_DATE"))));
				
				
			}
			rs2.close();
	        ps2.close();
			
		}catch(Exception e){
			System.out.println("getPriceData1 hatası");
		}
		return list;
	}
	
	public static ObservableList<MarketPrice> getPriceData(String type, String kind){

		Connection conn = Connect2Db();
		ObservableList<MarketPrice> list = FXCollections.observableArrayList();

		
		try {
			PreparedStatement ps3 = conn.prepareStatement("SELECT * FROM PRICES WHERE "+ "PRICE_TYPE = \"" + type + "\""+ " AND PRICE_KIND = \"" + kind + "\"" + " ORDER BY PRICE_DATE DESC LIMIT 1" );
			//                                           SELECT * FROM PRICES WHERE PRICE_TYPE = "hisse" AND PRICE_KIND = "ereg" ORDER BY PRICE_DATE DESC

			System.out.println("SELECT * FROM PRICES WHERE "+ "PRICE_TYPE = \"" + type + "\""+ " AND PRICE_KIND = \"" + kind + "\"" + " ORDER BY PRICE_DATE DESC LIMIT 1" );
			ResultSet rs3 = ps3.executeQuery();
			System.out.println(rs3);

		


			while (rs3.next()){
				System.out.println(rs3.getString("PRICE_ID"));
				System.out.println(rs3.getString("PRICE_TYPE")); 
				System.out.println(rs3.getString("PRICE_KIND")); 
				System.out.println(rs3.getString("PRICE")); 
				System.out.println(rs3.getString("PRICE_DATE")); 
				
				list.add(new MarketPrice(
						Integer.parseInt(rs3.getString("PRICE_ID")),
						rs3.getString("PRICE_TYPE"), 
						rs3.getString("PRICE_KIND"),
						Double.parseDouble(rs3.getString("PRICE")),
						LocalDate.parse(rs3.getString("PRICE_DATE"))));
				}
			System.out.println(list.size());
			rs3.close();
	        ps3.close();
		
			
		}catch(Exception e){
			System.out.println("getPriceData2 hatası");
		}
		return list;
	}
	
	
	public static List<String> getDistinctValues(String column) {
		Connection conn = Connect2Db();

		List<String> box = new ArrayList<>();

		try {
			
			String sqlFunc = "SELECT DISTINCT " + column  + " FROM INVESTMENTS";
            System.out.println(sqlFunc);

			PreparedStatement pstFunc = conn.prepareStatement(sqlFunc);
			ResultSet rsFunc = pstFunc.executeQuery();
			
            
			while (rsFunc.next()) {
                String kind = rsFunc.getString(column);
                box.add(kind);
             
                System.out.println(kind);
            }
			
			pstFunc.close();
			rsFunc.close();
		}catch(Exception e) { 
			System.out.println(e);
		}
		return box;
	}
	
	public static List<String> getDistinctValues(String type, String column ) {
		List<String> box = new ArrayList<>();
		Connection conn = Connect2Db();



		try {
			
			String sqlFunc = "SELECT DISTINCT INV_TYPE, "+ column +" FROM INVESTMENTS WHERE INV_TYPE = \"" + type + "\"";
            System.out.println(sqlFunc);

			PreparedStatement pstFunc = conn.prepareStatement(sqlFunc);
			ResultSet rsFunc = pstFunc.executeQuery();
			
            
			while (rsFunc.next()) {
                String kind = rsFunc.getString(column);
                box.add(kind);
             
                System.out.println(kind);
            }
			
			pstFunc.close();
			rsFunc.close();
			
		}catch(Exception e) { 
			System.out.println(e);
		}
		return box;
	}
}
