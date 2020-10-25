package com.marchant.server.model;

import java.util.Currency;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Offer {
	private long offerId;
	private String offerDescription;
	private String productName;
	private double price;
	private Currency currency;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
	private Date offerStartDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
	private Date offerEndDate;
	private boolean offerExpired;
	private boolean offerCancel;
	
	public Offer(long offerId, String offerDescription, String productName, double price, Currency currency,
			Date offerStartDate, Date offerEndDate, boolean offerExpired, boolean offerCancel) {
		super();
		this.offerId = offerId;
		this.offerDescription = offerDescription;
		this.productName = productName;
		this.price = price;
		this.currency = currency;
		this.offerStartDate = offerStartDate;
		this.offerEndDate = offerEndDate;
		this.offerExpired = offerExpired;
		this.offerCancel = offerCancel;
	}

	public long getOfferId() {
		return offerId;
	}

	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}

	public String getOfferDescription() {
		return offerDescription;
	}

	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Date getOfferStartDate() {
		return offerStartDate;
	}

	public void setOfferStartDate(Date offerStartDate) {
		this.offerStartDate = offerStartDate;
	}

	public Date getOfferEndDate() {
		return offerEndDate;
	}

	public void setOfferEndDate(Date offerEndDate) {
		this.offerEndDate = offerEndDate;
	}

	public boolean isOfferExpired() {
		return offerExpired;
	}

	public void setOfferExpired(boolean offerExpired) {
		this.offerExpired = offerExpired;
	}

	public boolean isOfferCancel() {
		return offerCancel;
	}

	public void setOfferCancel(boolean offerCancel) {
		this.offerCancel = offerCancel;
	}
	
	
	
	
	/*public Offer(long productId, String productName, double price, String discount, Currency currency) {
		super();
		this.offer = productId;
		this.productName = productName;
		this.price = price;
		this.discount = discount;
		this.currency = currency;
		
	}
	public long getOffer() {
		return offer;
	}
	public void setOffer(long productId) {
		this.offer = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}*/

}
