package restAPIXML;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.response.Response;

public class XMLTest {

	@Test
	public void Test1() {
		RestAssured.given()
				   .baseUri("https://chercher.tech/sample/api/books.xml")
				   .when()
				   .get()
				   .then()
				   .log()
				   .body()
				   .statusCode(200);
				   
		
	}
	
	@Test
	public void Test2() {
		Response response=RestAssured.given()
									   .baseUri("https://chercher.tech/sample/api/books.xml")
									   .when()
									   .get();
	
		NodeChildrenImpl books=response.then().extract().path("bookstore.book.title");
		System.out.println("All books title are: "+books.toString());
		System.out.println("First book is: "+books.get(0).toString());
		System.out.println("Second book is: "+books.get(1).toString());
		
		System.out.println("Language of the book: "+books.get(0).getAttribute("lang"));
		System.out.println("\n*****Using Foreach ****");
		for (String booksName : books) {
			
			System.out.println(booksName.toString());
		}
		
		System.out.println("\n*****Using For loop ****");
		for(int index=0;index<books.size();index++)
		{
			System.out.println(books.get(index).toString());
		}
		
		NodeChildrenImpl price=response.then().extract().path("bookstore.book.price");
		System.out.println("paperback price is: "+price.get(0).children().get("paperback"));
	
		
	}
	
}
