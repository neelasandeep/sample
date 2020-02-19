package tests;

import static io.restassured.RestAssured.given;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ConfigDataprovider;

public class TC005_Auth {
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
	RestAssured.baseURI=config.getDataFromConfig("auth");
	PreemptiveBasicAuthScheme authsceme=new PreemptiveBasicAuthScheme();
	authsceme.setUserName("ToolsQA");
	authsceme.setPassword("TestPassword");
	RestAssured.authentication=authsceme;
	RequestSpecification httpRequest=RestAssured.given();
	Response responce= httpRequest.request(Method.GET);
	String responseBody=responce.asString();
	logger.info(responseBody);
	logger.info(responce.getStatusCode());
  }
  
  //wether server ask or dont but we will send credentials
  @Test
  public void usepreemptiveBasicAuthentication() {

      given().
          auth().
          preemptive().
          basic("ToolsQA", "TestPassword").
      when().
          get(config.getDataFromConfig("auth")).
      then().
          assertThat().
          statusCode(200);
  }
  
  //directly hit server and if serveer ask credentials then it will send credentials
  @Test
  public void useBasicAuthentication() {

      given().
          auth().
         
          basic("ToolsQA", "TestPassword").
      when().
          get(config.getDataFromConfig("auth")).
      then().
          assertThat().
          statusCode(200);
  }
  
  //more secure because here Digestive key is involved
  @Test
  public void useDigestAuthentication() {

      given().
          auth().
         
          digest("ToolsQA", "TestPassword").
      when().
          get(config.getDataFromConfig("auth")).
      then().
          assertThat().
          statusCode(200);
  }
  @Test
  public void useOAuthAuthentication() {

      given().
          auth().
          oauth2("myAuthenticationToken").
      when().
          get("https://my.very.secure/api").
      then().
          assertThat().
          statusCode(200);
  }
}
