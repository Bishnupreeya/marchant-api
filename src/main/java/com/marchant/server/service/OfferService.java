package com.marchant.server.service;

import java.util.List;

import com.marchant.server.model.Offer;
import com.marchant.server.model.OfferCancelOnly;


public interface OfferService {
	List<Offer> getAllOffers();
	Offer getOfferById(long id);
	Offer getOfferByDescription(String name);
	Offer getOfferByProductName(String name);
	String updateOffer(long id, Offer offer);
	String updatePartialOffer(long id, OfferCancelOnly offer);
	void addOffer(Offer offer);
	boolean removeOffer(long id);
	

}
