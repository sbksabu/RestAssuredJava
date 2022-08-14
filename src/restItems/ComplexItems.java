package restItems;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import jsonFiles.FileOperations;

public class ComplexItems {

	public static void main(String[] args) 
	
	{
		// TODO Auto-generated method stub
		
		JsonPath js2 = new JsonPath(FileOperations.course_Response());
		
		//Problem 1 - Course Size
		System.out.println(js2.getInt("courses.size()"));
		
		//Problem 2 -- Purchase Amount
		
		System.out.println("Purchase Amount is : "+js2.getInt("dashboard.purchaseAmount"));
		
		//Problem  -- get title of the first course
		
		String title1 = js2.get("courses[0].title");
		
		System.out.println("First Course Title is : "+title1);
		
		//Print all the course titles and amounts
		
		int course_cnt = js2.getInt("courses.size()");
		
		for (int i =0; i<course_cnt; i++)
			
		{
			
			System.out.println("Course Title is "+js2.getString("courses["+i+"].title")+ " & Course Fee is "+js2.getInt("courses["+i+"].price"));
			
		}
		
		
		//print number of copies sold by RPA
		
		for (int i = 0; i<course_cnt;i++)
			
		{
			
			
			if (js2.getString("courses["+i+"].title").equals("RPA"))
				
			{
				
				System.out.println("RPA Copies sold are "+ js2.getInt("courses["+i+"].copies"));
				break;// come out of the loop once the condition is met
				
			}
			
		}
		
		
		// Verify the sum of courses match with the total shown
		
		int sum = 0;
		
		for (int i =0;i<course_cnt;i++)
			
		{
			
			sum = sum + (js2.getInt("courses["+i+"].price") * js2.getInt("courses["+i+"].copies") );
			
			System.out.println(sum);
								
		}
		
		Assert.assertEquals(js2.getInt("dashboard.purchaseAmount"), sum);
		
		if (js2.getInt("dashboard.purchaseAmount")== sum)
			
		{
			
			System.out.println("Amount is accurate");
			
		}
		

	}

}
