package restItems;

import io.restassured.path.json.JsonPath;

public class ReusableMethods 

{
	//String response to JSON format 
	public static JsonPath responseToJson(String response)
	
	{
		
		JsonPath jsPath = new JsonPath(response);
		return jsPath;
		
	}
	
}
