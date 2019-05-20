package org.beans;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

public class CreditCard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cardNumber;
	private BigInteger id;
	private int CVV;
	private LocalDate expiryDate;

	public CreditCard(String cardNumber, BigInteger id, int cVV, LocalDate expiryDate) {
		super();
		this.cardNumber = cardNumber;
		this.id = id;
		CVV = cVV;
		this.expiryDate = expiryDate;
	}

	
	public int getCVV() {
		return CVV;
	}

	public void setCVV(int cVV) {
		CVV = cVV;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}
	
	public CreditCard(String cardNumber, BigInteger id) {
		super();
		this.cardNumber = cardNumber;
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
}
