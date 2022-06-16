package restBDD;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PutRequestBDD {

	@Test
	public void Test1(){
		
		Map<String, Object> mapObj=new HashMap<String, Object>();
		mapObj.put("name", "Tushar");
		mapObj.put("salary", "50000");
		
		RestAssured.given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.baseUri("http://localhost:3000/employees")
					.body(mapObj)
					.when()
					.put("/6")
					.then()
					.log()
					.body()
					.statusCode(200)
					.body("name", Matchers.not("Anuja"));
	}
}
