package backEnd;

import java.time.LocalDate;

public class MarketPrice {
	private final int id;
	private String type;
	private String kind;
	private double price;
	private LocalDate date;
	
	public MarketPrice(int id,String type,String kind,double price,LocalDate date) {
		this.id = id;
		this.type = type;
		this.kind = kind;
		this.price = price;
		this.date = date;
	}
	public int getID() {return id;}

	public String getType() {return type;}
	public String getKind() {return kind;}
	public double getPrice() {return price;}
	public LocalDate getDate() {return date;}
	
	
}
