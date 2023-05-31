package backEnd;

import java.time.LocalDate;

public class Invest {
    private int id;
	private boolean buy;
	private String type;
	private String kind ;
	private LocalDate date;
	private double cost;
	private double amount;
	private boolean paid ;
	private boolean nonPaid; 
	private boolean bonus; 
	private String comment;

	public Invest(int id, String type, String kind, LocalDate date,double cost,double amount,boolean buy, boolean paid,boolean nonPaid, boolean bonus, String comment) {
		this.id = id;
		this.type = type;
		this.kind = kind;
		this.date = date;
		
		this.buy = buy;
		
		if (buy == false) {
			
			if (cost < 0) {throw new IllegalArgumentException("Price cannot be less than 0.");
			}else {this.cost = -cost;}
			
			if (amount < 0) {throw new IllegalArgumentException("Count cannot be less than 0.");
			}else {this.amount = -amount;}
			
		}else {
			if (cost < 0) {throw new IllegalArgumentException("Price cannot be less than 0.");
			}else {this.cost = cost;}
			
			if (amount < 0) {throw new IllegalArgumentException("Count cannot be less than 0.");
			}else {this.amount = amount;}
		}
		
		
		if (paid == true && nonPaid == true) {
			throw new IllegalArgumentException("paid and nonPaid options cant be the same.");}
		else {
			this.paid = paid;
			this.nonPaid = nonPaid;
		}
		if(nonPaid == true) {
			cost = 0;}
		
		this.bonus = bonus;

		if (bonus == true) {
			amount = 0;
			cost = -cost;
			paid = false;
			nonPaid = false;
		}
		
		this.comment = comment;
	}
	public int getId() {return id;}
	public String getType() {return type;}
	public String getKind() {return kind;}
	public LocalDate getDate() {return date;}
	public double getCost() {return cost;}
	public double getAmount() {return amount;}
	public boolean getBuy() {return buy;}
	public String getBuyString() {
		String buyTemp;
		if(buy==true) {buyTemp = "aliş";} 
		else {buyTemp = "satiş";}
		return buyTemp;
	}
	public String getComment() {
		if(comment == null) {
			this.comment = "";
		}
		return comment;
		}
	public double getCostPerUnit() {
		return cost/amount;
	}
	
	
	public boolean getPaid() {return paid;}
	public boolean getNonPaid() {return nonPaid;}
	public boolean getBonus() {return bonus;}
	
	public void setPaid(boolean paidi) {this.paid = paidi;}
	public void setNonPaid(boolean nonPaidi) {this.nonPaid = nonPaidi;}
	public void setBonus(boolean bonusi) {this.bonus = bonusi;}
	

	public void setKind(String kindi) {this.kind = kindi;}
	public void setDate(LocalDate datei) {this.date = datei;}
	public void setPrice(double pricei) {
		if (pricei < 0) {
			throw new IllegalArgumentException("Price cannot be less than 0.");
		}else {
			this.cost = pricei;}
	}
	public void setCount(double counti) {
		if (counti < 0) {
			throw new IllegalArgumentException("Price cannot be less than 0.");
		}else {
			this.amount = counti;}
	}
	public void setSale(boolean buyi) {this.buy = buyi;}
	public void setComment(String commenti) {this.comment = commenti;}
	
}
