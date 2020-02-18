package restassureddemo;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.*;




public class SynntaxExplanation {
	
	@Test
	public void statusCode() {
		given().
		when().
			get("http://restapi.demoqa.com/utilities/weather/city/Hyderabad").
		then().
			statusCode(200);
	}
	
	@Test
	public void logging() {
		given().
		when().
			get("http://restapi.demoqa.com/utilities/weather").
		then().
			statusCode(200).
			log().all();
	}
	@Test
	public void equalmethod() {
		given().
		when().
			get("http://restapi.demoqa.com/utilities/weather/city/Hyderabad").
		then().
		
			body("City",equalTo("Hyderabad"));
	}
	
	

}
