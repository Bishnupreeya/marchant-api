README.txt

----------------------------------------------------------
Environment setup:
----------------------------------------------------------
- Spring Boot 2.3.1.RELEASE
- Java 1.8

----------------------------------------------------------
Project Background:
----------------------------------------------------------
Per Wikipedia, "an offer is a proposal to sell a specific product or 
service under specific conditions". As a merchant I offer goods for 
sale. I want to create an offer so that I can share it with my customers.

All my offers have shopper friendly descriptions. I price all my offers 
up front in a defined currency.

An offer is time-bounded, with the length of time an offer is valid for 
defined as part of the offer, and should expire automatically. Offers may 
also be explicitly cancelled before they expire.

----------------------------------------------------------
Assignment
----------------------------------------------------------
You are required to create a simple RESTful software service that will 
allow a merchant to create a new simple offer. Offers, once created, may be 
queried. After the period of time defined on the offer it should expire and 
further requests to query the offer should reflect that somehow. Before an offer 
has expired users may cancel it.

----------------------------------------------------------
Project Assumptions:
----------------------------------------------------------
- Assumptions on application:
    - Ignore the need for user authentication and security.
    - Ignore the need for error page handling.
	- Assume there is only one application server running the application.
    - Offers are persisted in-memory, and the loading is hard-coded on startup (no database required).

- Assumptions on api:
    - Assume all users are allowed to view offers.
    - Assume only merchant-users are authorised to access/would be accessing the POST and PUT APIs.
	- All data content-type (passed in and out) are JSON only.
	
- Assumptions on offer:
    - Assume the merchant is only offering 'goods', not 'services'. The simple application will only handle one type.
    - It is not explicit on whether the merchant will provide the offer's Duration or Expiry-date. Chosen expiry-date, not duration.
    - Assume all timezones are read and set from one defined time-zone: the system timezone.

----------------------------------------------------------
Running project locally
----------------------------------------------------------
1. Checkout the project from gitHub.
2. Open project from your chosen IDE.
3. Data stores in-memory.
4. Offers will be available as soon as they are created.
5. There is no validation added except date field(yyyy-MM-dd'T'HH:mm:ssXXX).
6. Offer will expire based on offerenddate(field) provided.
6. Testing has been written for controller, found under here: com.marchant.server.ws.WsControllerTest.java


----------------------------------------------------------
API details:
----------------------------------------------------------

1. Get all available  list of offers
	
	GET URI: http://localhost:8080/offers


2. Get all offers by offer Id
	
	GET URI: http://localhost:8080/offers/{offerId}

3. Get all offers by offer description
	
	GET URI: http://localhost:8080/offers/offer/{description}

4. Get all offers by product/service name
	
	GET URI: http://localhost:8080/offers/product/{productName}

5. Create one new offer
	
	POST URI: http://localhost:8080/offers
	
	BODY:
	{
        "offerId": 1,
        "offerDescription": "Black Friday",
        "productName": "Service1",
        "price": 10.99,
        "currency": "GBP",
        "offerStartDate": "2020-10-25T18:55:06Z",
        "offerEndDate": "2020-10-26T18:55:06Z",
        "offerExpired": false,
        "offerCancel": false
         }
	
6. Update offer by offer Id
	
	PUT URI: http://localhost:8080/offers/{offerId}

7. Cancel an existing offer by offer Id
	
	PATCH URI: http://localhost:8080/offerCancel/{offerId}
        
        BODY:
	{
        "offerId": 1,
        "offerCancel": false
        }

8. Delete an existing offer by offer Id
	
	DELETE URI: http://localhost:8080/{offerId}
