package apiChaining;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEndTest {

	Response response;
	String BaseURI="http://localhost:3000/employees";
	
	@Test
	public void TestMethod() {
		
		response=GetMethodAll();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		response=PostMethod("Ria", "4000");
		Assert.assertEquals(response.getStatusCode(), 201);
		
		JsonPath jpath =response.jsonPath();
		int empId=jpath.get("id");
		System.out.println(empId);
		
		response=PutMethod(empId, "Sam", "10000");
		Assert.assertEquals(response.getStatusCode(), 200);
		jpath=response.jsonPath();
		Assert.assertEquals(jpath.get("name"), "Sam");
		
		response=DeleteMethod(empId);
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getBody().asString(),"{}");
		
		response=DeleteMethod(empId);
		Assert.assertEquals(response.getStatusCode(), 404);
		Assert.assertEquals(response.getBody().asString(),"{}");
	}
	
	public Response GetMethodAll() {
	
		RestAssured.baseURI=BaseURI;
		RequestSpecification request=RestAssured.given();
		Response response=request.get();
		return response;
	}
	
	public Response PostMethod(String Name, String Salary) {
		RestAssured.baseURI=BaseURI;
		RequestSpecification request=RestAssured.given();
		JSONObject obj=new JSONObject();
		obj.put("name", Name);
		obj.put("salary", Salary);
		Response response=request.contentType(ContentType.JSON)
								 .accept(ContentType.JSON)
								 .body(obj.toString())
								 .post("/create");
		return response;
	}
	
	public Response PutMethod(int empId, String Name, String Salary) {
		RestAssured.baseURI=BaseURI;
		RequestSpecification request=RestAssured.given();
		JSONObject obj=new JSONObject();
		obj.put("name", Name);
		obj.put("salary", Salary);
		Response response=request.contentType(ContentType.JSON)
								 .accept(ContentType.JSON)
								 .body(obj.toString())
								 .put("/"+empId);
		return response;
	}
	
	public Response DeleteMethod(int empId) {
		
		RestAssured.baseURI=BaseURI;
		RequestSpecification request=RestAssured.given();
		Response response=request.delete("/"+empId);
		return response;
	}
	
	public Response GetMethod(int empId) {
		
		RestAssured.baseURI=BaseURI;
		RequestSpecification request=RestAssured.given();
		Response response=request.get("/"+empId);
		return response;
	}
}
