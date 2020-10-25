package com.marchant.server.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.marchant.server.model.Offer;
import com.marchant.server.model.OfferCancelOnly;

@Service
public class OfferServiceImpl implements OfferService {

	
	List<Offer> offerList = new ArrayList<>();
	@Override
	public List<Offer> getAllOffers() {
		
		if(offerList.size() != 0) {
			return searchOffers();
		}
		else
			return Collections.emptyList();
	}

	private List<Offer> searchOffers() {
		for (int i =0; i<offerList.size();i++) {
				offerList.get(i).setOfferExpired(checkofferExpire(offerList.get(i).getOfferEndDate()));				
			}
			return offerList;			
			
	}

	private boolean checkofferExpire(Date expireDate) {
		if(expireDate.before(new Date())) {
			return true;
		}
		return false;
	}

	@Override
	public Offer getOfferById(long id) {
		if(offerList.size() > 0) {
		Offer offer = offerList.stream().filter(p -> p.getOfferId() == id).findFirst().get();
		if(offer != null) {
		    offer.setOfferExpired(checkofferExpire(offer.getOfferEndDate()));
		}
	    return offer;		
		}
		return null;
	}

	@Override
	public String updateOffer(long id, Offer offer) {
		if (offerList.size() > 0) {
			
		for (int i =0; i<offerList.size();i++) {
			Offer offerDetails = offerList.get(i);
			if(offerDetails.getOfferId() == id) {             
				offerList.set(i, offer);
			    return "success";
			}
		}
		}
		return "";

	}

	@Override
	public void addOffer(Offer offer) {
		offerList.add(offer);
		
	}

	@Override
	public boolean removeOffer(long id) {
		return (offerList.removeIf(p -> p.getOfferId()==id));

	}

	@Override
	public Offer getOfferByDescription(String name) {
		if(offerList.size() >0 ) {
			Offer offer = offerList.stream().filter(p -> p.getOfferDescription().equalsIgnoreCase(name)).findFirst().get();
			if(offer != null) {
				offer.setOfferExpired(checkofferExpire(offer.getOfferEndDate()));
			}
			return offer;
		}
		return null;
	}

	@Override
	public Offer getOfferByProductName(String name) {
		if (offerList.size() >0) {
		Offer offer = offerList.stream().filter(p -> p.getProductName().equalsIgnoreCase(name)).findFirst().get();
		if(offer != null) {
			offer.setOfferExpired(checkofferExpire(offer.getOfferEndDate()));
		}
		return offer;
		}
		return null;
	}

	@Override
	public String updatePartialOffer(long id, OfferCancelOnly offer) {
		if (offerList.size() > 0) {
			Offer offerDetails = offerList.stream().filter(p -> p.getOfferId() == id).findFirst().get();
			if(offerDetails != null) {
				offerDetails.setOfferCancel(offer.isOfferCancel());
				return "Success";
			}
		}
		return "";
	}

}
