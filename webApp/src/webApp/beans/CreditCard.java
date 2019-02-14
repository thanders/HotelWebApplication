package webApp.beans;

import java.io.Serializable;

public class CreditCard implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cardNumber;
	public CreditCard(int cardNumber, String cardOwner) {
		super();
		this.cardNumber = cardNumber;
		this.cardOwner = cardOwner;
	}
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardOwner() {
		return cardOwner;
	}
	public void setCardOwner(String cardOwner) {
		this.cardOwner = cardOwner;
	}
	private String cardOwner;
}
