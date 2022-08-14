package restItems;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jsonFiles.FileOperations;

import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.Assert;

public class SecondRest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
				//given -- All the input details
				//when -- submit the API, resource -- http methods
				//then -- validate the response
				
				
				//.baseURI = "https://rahulshettyacademy.com/";
		
		//with body mentioned
			/*	RestAssured.baseURI = "https://rahulshettyacademy.com";
				given().log().all().queryParam("key", "qaclick123").header("ContentType", "application/json")
				.body("{\r\n" + 
						"  \"location\": {\r\n" + 
						"    \"lat\": -38.383494,\r\n" + 
						"    \"lng\": 33.427362\r\n" + 
						"  },\r\n" + 
						"  \"accuracy\": 50,\r\n" + 
						"  \"name\": \"Frontline house\",\r\n" + 
						"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
						"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
						"  \"types\": [\r\n" + 
						"    \"shoe park\",\r\n" + 
						"    \"shop\" \r\n" + 
						"  ],\r\n" + 
						"  \"website\": \"http://google.com\",\r\n" + 
						"  \"language\": \"French-IN\"\r\n" + 
						"}").when().post("maps/api/place/add/json")
				.then().assertThat().statusCode(200).log().all().body("scope", equalTo("APP")).header("Server", equalTo("Apache/2.4.41 (Ubuntu)"));
				*/
				//using static method for the body
		
				try {
					FileInputStream fis = new FileInputStream("src/jsonFiles/Add_Place.json");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
				RestAssured.baseURI = "https://rahulshettyacademy.com";
				String response = given().log().all().queryParam("key", "qaclick123").header("ContentType", "application/json")
				.body(FileOperations.addPlace()).when().post("maps/api/place/add/json")
				.then().assertThat().statusCode(200).log().all().body("scope", equalTo("APP")).header("Server", equalTo("Apache/2.4.41 (Ubuntu)")).extract().response().asString();
				
				System.out.println(response);
			
				//Used Response Class
			/*	Response response1 = given().log().all().queryParam("key", "qaclick123").header("ContentType", "application/json")
						.body(IOUtils.to).when().post("maps/api/place/add/json")
						.then().assertThat().statusCode(200).log().all().body("scope", equalTo("APP")).header("Server", equalTo("Apache/2.4.41 (Ubuntu)")).extract().response();
				
				System.out.println(response1);
			*/
				
				JsonPath jsObject = new JsonPath(response);
				
		/*		FileInputStream fis = new FileInputStream("src/jsonFiles/Add_Place.json");
				
				JsonPath jsReuest = new JsonPath(IOUtils.toString(fis, "UTF-8"));
				
				String location = jsReuest.getString("location.lat"); // Finding Child node
				
							
				
				System.out.println("Name is "+location);
			*/	
				String placeId = jsObject.getString("place_id"); // For Parsing Json
				
				System.out.println("Place ID is "+placeId);
				
				
				//Update API
				
				String newAddress = "70 winter walk, USA";
				
				FileInputStream fis2 = new FileInputStream("src/jsonFiles/Update_Place.json");
				
				given().log().all().queryParams("place_id", "248a761e0ce9c55039ca6c79ed0149a4", "key", "qaclick123")
				.body(FileOperations.update_Place(placeId, newAddress))
				.when()
				.put("maps/api/place/update/json")
				.then().assertThat().statusCode(200).assertThat().body("msg",equalTo( "Address successfully updated"))
				.log().all().extract().response();
				
				
				//Get API verification
				
			String res =	given().log().all().queryParams("place_id", placeId, "key", "qaclick123")
				.when()
				.get("maps/api/place/get/json")
				.then().assertThat().statusCode(200).assertThat().body("address",equalTo( "70 winter walk, USA"))
				.log().all().extract().response().asString();
			
			
			
			
			JsonPath js1 = ReusableMethods.responseToJson(res);
			
			String actualAddress = js1.getString("address");
			
			//
			
			System.out.println(actualAddress);
			
			//Cucumber Junit & TestNG
			
			Assert.assertEquals(actualAddress, newAddress);
			
			//
			
			
			
			

	}

}
