package restBDD;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostRequestBDD {

	@Test
	public void Test1() {
		
		Map<String, Object> mapObj=new HashMap<String, Object>();
		mapObj.put("name", "Anuja");
		mapObj.put("salary", "60000");
		
		RestAssured.given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.baseUri("http://localhost:3000/employees")
					.body(mapObj)
					.when()
					.post("/create")
					.then()
					.log()
					.body()
					.statusCode(201)
					.body("name", Matchers.equalTo("Anuja"));
					
	}
	
	
}
