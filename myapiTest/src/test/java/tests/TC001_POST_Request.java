package tests;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ConfigDataprovider;

public class TC001_POST_Request {
	/* This is Posting data to the URI using JSON params
	 * and JSONObjects
	 * */
	Logger logger;
	ConfigDataprovider config;
	@BeforeClass
	public void setUp() {
		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\resources\\log4j.properties");
		logger = Logger.getLogger(ConfigDataprovider.class);
		config=new ConfigDataprovider();
	}

	/* kk 
	 * 
	 * this is 3 rd video of sdet chanel
	 * */
	@Test
	public void PostData() {
		RestAssured.baseURI=config.getDataFromConfig("postrequest");
		RequestSpecification httpRequest=RestAssured.given();
		JSONObject requestParams=new JSONObject();
		
		requestParams.put("LastName", "Nwe4aeola");
		requestParams.put("UserName", "Nee46eigaeilaSandeep");
		requestParams.put("Password", "Neegl4a123");
		requestParams.put("FirstName","Swan4deeep" );
		requestParams.put("Email", "neeela1s4423s@gmail.com");
		httpRequest.header("Content-Type", "application/json");
		System.out.println(requestParams.toJSONString());
		httpRequest.body(requestParams.toJSONString());
		Response responce=httpRequest.request(Method.POST);
		String responseBody=responce.getBody().asString();
		logger.info(responseBody);
		logger.info(responce.getStatusCode());
		logger.info(responce.jsonPath().get("SuccessCode"));
		
		
	}

}
