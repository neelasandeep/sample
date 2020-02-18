package tests;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ConfigDataprovider;

public class TC001_GET_Request {
	
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
	RestAssured.baseURI=config.getApiURI();
	RequestSpecification httpRequest=RestAssured.given();
	Response responce= httpRequest.request(Method.GET,config.getCity());
	String responseBody=responce.asString();
	logger.info(responseBody);
	logger.info(responce.getStatusCode());
  }
  
  
}
