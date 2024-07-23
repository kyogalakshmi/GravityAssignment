package Restassured;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;

public class RestAPI {
	/*
	 * https://fakestoreapi.com/products
	 * method:"POST",
            body:JSON.stringify(
                {
                    title: 'test product',
                    price: 10.5,
                    description: 'lorem ipsum set',
                    image: 'https://i.pravatar.cc',
                    category: 'electronic'
                }
            )
        })
	 * 
	 */
	@Test
	public void postRequestBooksAPI() 
	{ 
		
String generatedString = RandomStringUtils.random(6, true, false);

		    
		
	  PojoRequest data = new PojoRequest();
	  data.setTitle("test product"+generatedString);
	  data.setPrice(10.5);
	  data.setDescription("lorem ipsum set"+generatedString);
	  data.setImage("https://i.pravatar.img"+generatedString);
	  data.setCategory("electronics");
		
	given()
	.contentType("application/json")
	.body(data).log().all()
	  .when()
	   .post("https://fakestoreapi.com/products")
	   .then()
	    .log().all()
	    .statusCode(200)//statuscode validation
	    //Assertions
	    .body("title",equalTo("test product"+generatedString))
	    .body("price",equalTo(10.5F))
	    .body("description",equalTo("lorem ipsum set"+generatedString))
	    .body("category",equalTo("electronics"))
	    .body("image",equalTo("https://i.pravatar.img"+generatedString)).log().all();
	    
	  
	}
	@Test(dependsOnMethods ="postRequestBooksAPI")
	public void getRequestBooksAPI() 
	{ 
		given()
		.contentType("application/json").log().all()
		
		  .when()
		   .get("https://fakestoreapi.com/products/category/electronics?limit=1")
		   .then()
		    .log().all()
		    .statusCode(200);
	}
	
}