package restassureddemo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PutExample {
	public static HashMap<String, String> map=new HashMap<>();
	@BeforeClass
	public void postdata() {
		map.put("name", "xdsyz1");
		map.put("salary", "250000");
		map.put("age", "23");
		
	
	}
	@Test
	public void testPut() {
	given()
		.contentType("application/json")
		
		.body(map)
		
	.when()
		.put("http://dummy.restapiexample.com/api/v1/update/22")
		
	.then()
		.statusCode(200)
		.body("status",equalTo("success"))
		.log().all();
				
		
	}
	

}
//http://dummy.restapiexample.com/api/v1/employees :getall
//http://dummy.restapiexample.com/api/v1/employee/22 :get 22
//http://dummy.restapiexample.com/api/v1/update/22