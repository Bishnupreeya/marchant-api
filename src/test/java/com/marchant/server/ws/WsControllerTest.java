package com.marchant.server.ws;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marchant.server.model.Offer;
import com.marchant.server.service.OfferService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = WsController.class)
class WsControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
   
    @MockBean
    private OfferService mockOfferService;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    private ObjectMapper objectMapper		= getObjectMapper();

	@Test
	void testGetAllOffers() throws Exception {
		final String offerStartDateStr = "2020-10-24T18:55:06Z";
		final String offerEndDateStr = "2020-10-24T18:55:06Z";
	    Date offerStartDate = df.parse(offerStartDateStr);
	    Date offerEndDate = df.parse(offerEndDateStr);
	    List<Offer> offerList = new ArrayList<> (Arrays.asList(
	    		new Offer(1,"Bank holiday","product1",5.99,Currency.getInstance("GBP"),offerStartDate,offerEndDate,false,false)
	    	    		));
		Mockito.when(mockOfferService.getAllOffers()).thenReturn(offerList);
		
		String expectedString = objectMapper.writeValueAsString(offerList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/offers")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expectedString, result.getResponse().getContentAsString(), true);
										
     
	}
	
	@Test
	void testGetOfferById() throws Exception {
		final String offerStartDateStr = "2020-10-24T18:55:06Z";
		final String offerEndDateStr = "2020-10-24T18:55:06Z";
	    
	    Date offerStartDate = df.parse(offerStartDateStr);
	    Date offerEndDate = df.parse(offerEndDateStr);
	    Offer offer = new Offer(1,"Bank holiday","service1",5.99,Currency.getInstance("USD"),offerStartDate,offerEndDate,false,false);
		Mockito
		.when(mockOfferService.getOfferById(Mockito.anyLong()))
		.thenReturn(offer);
		
		String expectedString = objectMapper.writeValueAsString(offer);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/offers/1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expectedString, result.getResponse().getContentAsString(), true);								
     
	}
	@Test
	void testGetOfferById_exception() throws Exception {
		final String offerStartDateStr = "2020-10-24T18:55:06Z";
		final String offerEndDateStr = "2020-10-24T18:55:06Z";
	    
	    Date offerStartDate = df.parse(offerStartDateStr);
	    Date offerEndDate = df.parse(offerEndDateStr);
	    Offer offer = new Offer(1,"Bank holiday","service1",5.99,Currency.getInstance("USD"),offerStartDate,offerEndDate,false,false);
		Mockito
		.when(mockOfferService.getOfferById(Mockito.anyLong()))
		.thenReturn(offer);
		
		String expectedString = objectMapper.writeValueAsString(offer);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/offers/3")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expectedString, result.getResponse().getContentAsString(), false);								
     
	}
	
	
	@Test
	void testGetOfferByDescription() throws Exception {
		final String offerStartDateStr = "2020-10-24T18:55:06Z";
		final String offerEndDateStr = "2020-10-24T18:55:06Z";
	    
	    Date offerStartDate = df.parse(offerStartDateStr);
	    Date offerEndDate = df.parse(offerEndDateStr);
	    Offer offer = new Offer(1,"Bank holiday","service1",5.99,Currency.getInstance("USD"),offerStartDate,offerEndDate,false,false);
		Mockito
		.when(mockOfferService.getOfferByDescription(Mockito.anyString()))
		.thenReturn(offer);
		
		String expectedString = objectMapper.writeValueAsString(offer);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/offers/offer/Bank holiday")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expectedString, result.getResponse().getContentAsString(), true);								
     
	}
	
	@Test
	void testGetOfferByProductName() throws Exception {
		final String offerStartDateStr = "2020-10-24T18:55:06Z";
		final String offerEndDateStr = "2020-10-24T18:55:06Z";
	    
	    Date offerStartDate = df.parse(offerStartDateStr);
	    Date offerEndDate = df.parse(offerEndDateStr);
	    Offer offer = new Offer(1,"Bank holiday","service1",5.99,Currency.getInstance("USD"),offerStartDate,offerEndDate,false,false);
		Mockito
		.when(mockOfferService.getOfferByProductName(Mockito.anyString()))
		.thenReturn(offer);
		
		String expectedString = objectMapper.writeValueAsString(offer);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/offers/product/service1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expectedString, result.getResponse().getContentAsString(), true);								
     
	}
	
	@Test
	void testAddOffer() throws Exception {
		
		File file = ResourceUtils.getFile("classpath:offer.json");
		String testCommands = FileUtils.readFileToString(file);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/offers")
				.accept(MediaType.APPLICATION_JSON).content(testCommands)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());     
	}
	
	@Test
	void testDeleteOffer() throws Exception {
			
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/offers/1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		Assert.assertEquals(MockHttpServletResponse.SC_BAD_REQUEST, result.getResponse().getStatus());
	}

	private ObjectMapper getObjectMapper() {
		ObjectMapper obj = new ObjectMapper();
		obj.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX"));
		return obj;
	}



}
