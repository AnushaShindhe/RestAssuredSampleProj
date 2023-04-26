package tests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.*;

public class Test1 {
	
	@Test (enabled=false)
	public void test1()
	{
		Response response=get("https://reqres.in/api/users?page=2");
			
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeader("content-type"));
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test (enabled=false)
	public void test2()
	{
		baseURI="https://reqres.in/api/";
		given().
			get("users?page=2").
		then().
			statusCode(200).body("data.id[1]",equalTo(8)).log().all();
	}
	@Test (enabled=false)
	
	public void test3()
	{
		baseURI="https://reqres.in/api/";
		given().get("users?page=2").then().body("data[4].first_name", equalTo("George"));
		given().get("users?page=2").then().body("data.first_name", hasItems("Rachel","George"));
		
	}
	
@Test (enabled=false)
	
	public void test4()
	{
	Map<String,String> map=new HashMap<String,String>();
	map.put("name", "morpheus");
	map.put("job", "leader");
	
	JSONObject req=new JSONObject(map);
	System.out.println(req.toJSONString());
	
	JSONObject req1=new JSONObject();
	req1.put("name", "Anusha");
	req1.put("job", "leader");
	System.out.println(req1.toJSONString());
	
	
	baseURI="https://reqres.in/api/";
	
	given().
		header("Content-Type","application/json").accept(ContentType.JSON).
		body(req1.toJSONString()).
	when().
		post("users").
	then().
		statusCode(201).log().all();

		
	}
@Test (priority= 1)
public void test5()
{
	baseURI="https://reqres.in/api/";
	
	JSONObject reqBody=new JSONObject();
	reqBody.put("name", "Jackson");
	reqBody.put("job", "Teacher");
	
	given().
		header("Content-Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(reqBody.toJSONString()).
	when().
		put("users/2").
	then().
		statusCode(200).log().all();
}
@Test 
public void Patch()
{
	baseURI="https://reqres.in/api/";
	
	JSONObject reqBody=new JSONObject();
	reqBody.put("name", "Jackson");
	reqBody.put("job", "Teacher");
	
	given().
		header("Content-Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(reqBody.toJSONString()).
	when().
		patch("users/2").
	then().
		statusCode(200).log().all();
}


@Test (priority= 3,invocationCount=3)
public void Delete()
{
	baseURI="https://reqres.in/api/";
		
	when().
		delete("users/2").
	then().
		statusCode(204).log().all();
}
}
