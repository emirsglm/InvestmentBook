package backEnd;

import java.time.LocalDate;

public class MarketPrice {
	private String type;
	private String kind;
	private double price;
	private LocalDate date;
	
	public MarketPrice(String type,String kind,double price,LocalDate date) {
		this.type = type;
		this.kind = kind;
		this.price = price;
		this.date = date;
	}
	
	public String getType() {return type;}
	public String getKind() {return kind;}
	public double getPrice() {return price;}
	public LocalDate getDate() {return date;}
	
	
}
