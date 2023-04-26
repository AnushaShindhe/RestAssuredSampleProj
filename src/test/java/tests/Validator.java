package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.testng.annotations.Test;

public class Validator {
	
@Test (enabled=true)
	
	public void test3()
	{
		baseURI="https://reqres.in/api/";
//		given().get("users?page=2").then().body("data[4].first_name", equalTo("George"));
		given().
			get("users?page=2").
		then().
			assertThat().body(matchesJsonSchemaInClasspath("schema.json")).
			statusCode(200);

	}

}
