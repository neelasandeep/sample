package restassureddemo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Postexample2 {
	public static HashMap<String, String> map=new HashMap<>();
	@BeforeClass
	public void postdata() {
		map.put("name", "xdsyz1");
		map.put("salary", "250000");
		map.put("age", "23");
		
	
	}
	@Test
	public void testPost() {
	given()
		.contentType("application/json")
		
		.body(map)
		
	.when()
		.post("http://dummy.restapiexample.com/api/v1/create")
		
	.then()
		.statusCode(200)
		.body("status",equalTo("success"))
		.log().all();
				
		
	}
	



}
