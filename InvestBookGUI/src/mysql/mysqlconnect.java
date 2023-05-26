package mysql;

import backEnd.Invest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class mysqlconnect {
	Connection conn = null ;
	
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
	
	public static ObservableList<Invest> getData(String tableName){

		Connection conn = Connect2Db();
		ObservableList<Invest> list = FXCollections.observableArrayList();

		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM "+tableName);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				String comment = "";
				boolean paid = false;
				boolean nonPaid = false;
				boolean bonus = false;
				boolean buy = true;
				

				
				if (rs.getString("INV_COMM") != null) {
					comment = rs.getString("INV_COMM");
				}
				if (rs.getString("INV_INFO") == "bedelli") {
					nonPaid = true;
				}
				if (rs.getString("INV_INFO") == "bedelsiz") {
					paid = true;
				}
				if (rs.getString("INV_BONUS") == "evet") {
					bonus = true;
				}
				if(rs.getString("INV_BUY").equals("satis")) {
					buy = false;
				} 
				System.out.println(rs.getString("INV_BUY"));
				System.out.println(buy);
				System.out.println();


			
				list.add(new Invest(Integer.parseInt(rs.getString("INV_ID")),
						rs.getString("INV_TYPE"), 
						rs.getString("INV_KIND"),
						LocalDate.parse(rs.getString("INV_DATE")), 
						Double.parseDouble(rs.getString("INV_COST")), 
						Double.parseDouble(rs.getString("INV_AMOUNT")), 
						buy, paid, nonPaid, bonus, comment));
			}
			
		}catch(Exception e){
			System.out.println("null input from data");
		}
		return list;
	}
}
