package restassureddemo;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Deleterequest {
	Response response;
	@Test
	public void testPut() {

		response= given()	
	.when()
		.delete("http://dummy.restapiexample.com/api/v1/delete/76")
		
	.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.log().all()
		.extract().response();
			
	 String jsonStrinfgResponse=response.asString();
	 
		Assert.assertEquals(jsonStrinfgResponse.contains("successfully! deleted Records"), true);
	}
	



}
