package tests;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.PojoData;
import utils.ConfigDataprovider;

public class TC002_POSTPojo {
	Logger logger;
	ConfigDataprovider config;
	PojoData user;

	@BeforeClass
	public void setUp() {
		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\resources\\log4j.properties");
		logger = Logger.getLogger(ConfigDataprovider.class);
		config = new ConfigDataprovider();

		user = createDummyUser();

	}

	@Test
	public void PostData() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(user);
		RestAssured.baseURI = config.getDataFromConfig("postpath");
		RequestSpecification httpRequest = RestAssured.given();

		httpRequest.header("Content-Type", "application/json");

		System.out.println(jsonInString);
		httpRequest.body(jsonInString);
		Response responce = httpRequest.request(Method.POST);

		String responseBody = responce.getBody().asString();
		logger.info(responseBody);
		logger.info(responce.getStatusCode());
		logger.info(responce.jsonPath().get("SuccessCode"));

	}

	public PojoData createDummyUser() {
		user = new PojoData();
		user.setName("afda");
		user.setSalary("78987");
		user.setAge("45");
		return user;
	}
}
