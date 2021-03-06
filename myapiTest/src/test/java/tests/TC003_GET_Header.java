package tests;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ConfigDataprovider;

public class TC003_GET_Header {
	Logger logger;
	ConfigDataprovider config;
	@BeforeClass
	public void setUp() {
		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\resources\\log4j.properties");
		logger = Logger.getLogger(ConfigDataprovider.class);
		config=new ConfigDataprovider();
	}
	@Test
	  public void get() {
		RestAssured.baseURI=config.getDataFromConfig("googleuri");
		RequestSpecification httpRequest=RestAssured.given();
		Response responce= httpRequest.request(Method.GET,config.getDataFromConfig("googlepath"));
		String responseBody=responce.getBody().asString();
		logger.info(responseBody);
		
		Headers allheaders=responce.headers();
		for(Header head:allheaders) {
			logger.info(head.getName()+" --> "+head.getValue());
		}
		logger.info(responce.getStatusCode());
	  }
	
	  

}
