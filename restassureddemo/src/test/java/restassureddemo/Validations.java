package restassureddemo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;


import org.testng.annotations.Test;

public class Validations {
	
	
	@Test
	public void statusCode() {
		given().
		when().
			get("http://restapi.demoqa.com/utilities/weather/city/Hyderabad").
		then().
			statusCode(200)
			.log().all();
	}
	@Test
	public void unitresult() {
		given().
		when().
			get("http://dummy.restapiexample.com/api/v1/employee/22").
		then().
			statusCode(200)
			.body(".data.employee_name",equalTo("Yuri Berry"))
			;
	}
	@Test
	public void test2() {
		given().
		when().
			get("http://dummy.restapiexample.com/api/v1/employees").
		then().
			statusCode(200)
			.body("data.employee_name",hasItems("Airi Satou","Tiger Nixon"))
			.log().all();
	}

}
//Airi Satou Herrod Chandler
//http://dummy.restapiexample.com/api/v1/employee/22