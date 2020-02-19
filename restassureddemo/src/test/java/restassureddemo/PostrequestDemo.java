package restassureddemo;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostrequestDemo {
	public static HashMap<String, String> map=new HashMap<>();
	@BeforeClass
	public void postdata() {
		map.put("FirstName", "xdsyz1");
		map.put("LastName", "xydsz11");
		map.put("UserName", "xsddyz111");
		map.put("Password", "1ds23");
		map.put("Email", "123ds@gmail.com");
	
	}
	@Test
	public void testPost() {
	given()
		.contentType("application/json")
		
		.body(map)
		
	.when()
		.post("http://restapi.demoqa.com/customer/register")
		
	.then()
		.statusCode(201)
		.body("SuccessCode", equalTo("OPERATION_SUCCESS"))
		.and()
		.body("Message",equalTo("Operation completed successfully"));
				
		
	}
	

}
