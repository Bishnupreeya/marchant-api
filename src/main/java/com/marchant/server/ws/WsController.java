package com.marchant.server.ws;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marchant.server.model.Offer;
import com.marchant.server.model.OfferCancelOnly;
import com.marchant.server.service.OfferService;

@RestController
@RequestMapping("/offers")
public class WsController {
	
	private static final Logger s_logger = LoggerFactory.getLogger(WsController.class);
	private OfferService offerService;
	
	@Autowired
    public WsController(OfferService offerService) {
        this.offerService = offerService;
    }
	
	@RequestMapping("/hello")
    public String sayHello() {
        return "Welcome to Marchant Shopping with best offer";
    }
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Offer>> getAllOffers(){
		List<Offer> offerList = offerService.getAllOffers();
		if (offerList.size() > 0 ) {
			return new ResponseEntity<>(offerService.getAllOffers(), HttpStatus.OK);
		}else {
			s_logger.info("Offer(s) not found available");
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
				
	}
	
	@RequestMapping(value = "/{offerId}", method = RequestMethod.GET)
	public ResponseEntity<Offer> getOfferById(@PathVariable long offerId){
		Offer offer = offerService.getOfferById(offerId);
		if(offer == null) {
			s_logger.info("Offer not found for offer id {}",offerId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
		 return new ResponseEntity<>(offer, HttpStatus.OK);
		}
				
	}
	
	@RequestMapping(value = "/offer/{offerDescription}", method = RequestMethod.GET)
	public ResponseEntity<Offer> getOfferByName(@PathVariable String offerDescription){
		Offer offer = offerService.getOfferByDescription(offerDescription);
		if (offer != null) {
			return new ResponseEntity<>(offer, HttpStatus.OK);
		}
		else {
			s_logger.info("Offer not found for offer offerDesciption {}",offerDescription);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(value = "/product/{productName}", method = RequestMethod.GET)
	public ResponseEntity<Offer> getOfferByProductName(@PathVariable String productName){
		Offer offer = offerService.getOfferByProductName(productName);
		if (offer != null) {
			s_logger.info("Offer not found for product name {}",productName);
			return new ResponseEntity<>(offer, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(value = "/{offerId}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateOfferById(@PathVariable long offerId, @RequestBody Offer offer){
		String updateOffer = offerService.updateOffer(offerId, offer);
		if (updateOffer != null) {
			return new ResponseEntity<>(HttpStatus.OK);			
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
		
	
	@RequestMapping(method = RequestMethod.POST)
	public void addOffer(@RequestBody Offer offer) {
		offerService.addOffer(offer);
	}
	
	@RequestMapping(value ="/{offerId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeOffer(@PathVariable long offerId) {
		if (!offerService.removeOffer(offerId)){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/offerCancel/{offerId}", method = RequestMethod.PATCH)
	public ResponseEntity<String> partialUpdateOffer(@PathVariable long offerId, @RequestBody OfferCancelOnly offer){
		String updateOffer = offerService.updatePartialOffer(offerId, offer);
		if (!updateOffer.equalsIgnoreCase("")) {
			return new ResponseEntity<>(HttpStatus.OK);			
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

}
