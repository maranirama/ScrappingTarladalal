package morbidityModules;




	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.List;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebElement;
	import org.testng.annotations.Test;

import com.util.ExcelReader;

	
	public class PCOSAllergy extends BaseTest{
	
	
		public static ArrayList<String> recipeid = new ArrayList<String>();
		public static ArrayList<String> recipeName = new ArrayList<String>();
		public static ArrayList<String> ingredientList = new ArrayList<String>();
		public static ArrayList<String> prepTime = new ArrayList<String>();
		public static ArrayList<String> cookTime = new ArrayList<String>();
		public static ArrayList<String> prepMethod = new ArrayList<String>();
		public static ArrayList<String> NutrientList = new ArrayList<String>();
		public static ArrayList<String> RecipeUrlList = new ArrayList<String>();
		public static String foodCategory = "Vegetarian";
	    public static String morbidities = "PCOS";
		public static  String nutrients ;
		public static String ct;
		public static String r ;
		public static String s_recipe_id ; 
		public static String s_Ingredients ;
		public static String formattedrecipeid;
		public static String pm;
		public static String pt;
		public static String strUrl; 
		
		@Test
		
		public static void recipedetails() throws InterruptedException, IOException 
		{
			  
			 for ( int p =1 ; p <=6 ; p++)
		        
			 {
				
				String url = "https://www.tarladalal.com/recipes-for-pcos-1040?pageindex="+p;//;
		        driver.get(url);
			
	        List<WebElement> RecipeList = driver.findElements(By.xpath("//article[@class='rcc_recipecard']"));
		    System.out.println("Recipe list count is: "+RecipeList.size());
			
		    
		    for ( int k=1; k<=RecipeList.size(); k++)  
		    {
		    	
			System.out.println("valueok"+k);
				//Rec. id
				try {

					WebElement recipe_id = driver
							.findElement(By.xpath("//article[" + k + "]//div[@class='rcc_rcpno']/span"));
					s_recipe_id = recipe_id.getText();
					formattedrecipeid = s_recipe_id.substring(8, s_recipe_id.length() - 9);
					System.out.println(formattedrecipeid);

				}
				catch(Exception e)
				{
					continue;
				}
			
			// Rec. name
			
				try 
				{
					WebElement recipeName = driver.findElement(By.xpath("//article[" + k + "]//div[@class='rcc_rcpcore']/span[1]/a"));
					r = recipeName.getText();
					// click recipeName
					recipeName.click();
				}
				catch (Exception e) 
				{
					continue;
				}
				
				//Ingrediant
					Thread.sleep(1000);
		        	String Ingrediants = driver.findElement(By.xpath("//div[@id='rcpinglist']")).getText();
		        	boolean isIngrediant =PCOSCheckList.checkIngrediant(Ingrediants);
		        	boolean isIngrediantAllergy = PCOSCheckList.allergy(Ingrediants);
		        	
		        	// pre time
		        	WebElement PrepTime = driver.findElement(By.xpath("//p//time[1]"));
		        	pt=PrepTime.getText();
		        	
		        	
		        	
		        	// cook time
		        	try {
			        	WebElement CookTime = driver.findElement(By.xpath("//p//time[2]"));
			        	String ct=CookTime.getText();
			        	
			        	System.out.println("Cooking Time is : "+CookTime.getText());
			        	}
		        	
		        	catch (Exception e) 
		        	{
		        		continue;
		        	}
		        	
		        	//prep. method
		        	
		        	WebElement PrepMethod = driver.findElement(By.xpath("//div[@id='recipe_small_steps']"));
		        	String pm=PrepMethod.getText();
		        	
		        	System.out.println("Preperation Method is : "+PrepMethod.getText());
		        	
		        	Thread.sleep(4000);
		        	
		        	//nutrient
		        	try
		        	{
		        	WebElement Nutrients = driver.findElement(By.xpath("//div/span//table[@id='rcpnutrients']"));
		        	nutrients=Nutrients.getText();
		        	
		        	System.out.println("Nutrient Values are : "+Nutrients.getText());
		        	} 
		        	catch (Exception e) 
		        	{
		        		continue;
		        	}
		        	 strUrl = driver.getCurrentUrl();
		        	
			     
			 	
		        	
		    if (isIngrediant && isIngrediantAllergy)
			{
				recipeid.add(formattedrecipeid.trim());
				recipeName.add(r);

				ingredientList.add(Ingrediants);
				System.out.println("Ingrediants are : " + Ingrediants);

				
				  prepTime.add(pt); System.out.println("Preperation Time is : " + pt);
				 
				
				  try 
				  { 
					  cookTime.add(ct); 
				  }
				  catch (Exception e)
				  
				  { 
					  cookTime.add("NA");
				  }
				 
				Thread.sleep(4000);

				// preparation method
				
				  prepMethod.add(pm); 
				 
				 
				Thread.sleep(4000);
				
				
				  try
				  {
				  
				  NutrientList.add(nutrients); 
				  System.out.println("Nutrient Values are : " + nutrients); 
				  } 
				  catch(Exception e) 
				  {
					  NutrientList.add("NA"); 
				  }
				  
				  RecipeUrlList.add(strUrl);
		}
		    String url1 = "https://www.tarladalal.com/recipes-for-pcos-1040?pageindex="+p;//;
	    	
	    	driver.get(url1);
	    	Thread.sleep(5000);
		    }
		    System.out.println(recipeName.size());
			
			ExcelReader xlreader = new ExcelReader(".\\datafiles\\SmartScrapers.xlsx");
			xlreader.setcelldata("PCOSALLERGY", 0, 0, "recipeid");
			xlreader.setcelldata("PCOSALLERGY", 0, 1, "recipename");

			xlreader.setcelldata("PCOSALLERGY", 0, 2, "ingrediant");

			xlreader.setcelldata("PCOSALLERGY", 0, 3, "preparation Time");
			xlreader.setcelldata("PCOSALLERGY", 0, 4, "cook Time");

			xlreader.setcelldata("PCOSALLERGY", 0, 5, "preparation method");
			xlreader.setcelldata("PCOSALLERGY", 0, 6, "Nutrients");
			xlreader.setcelldata("PCOSALLERGY", 0, 7, "url");
			xlreader.setcelldata("PCOSALLERGY", 0, 8, "morbidity");
			xlreader.setcelldata("PCOSALLERGY", 0, 9, "foodcatagory");
			
					
					for (int a = 0; a <=recipeName.size(); a++) 
					{
						try
						{
						xlreader.setcelldata("PCOSALLERGY", a+1, 0, recipeid.get(a));
						xlreader.setcelldata("PCOSALLERGY", a+1, 1, recipeName.get(a));
						xlreader.setcelldata("PCOSALLERGY", a+1, 2, ingredientList.get(a));

						xlreader.setcelldata("PCOSALLERGY", a+1, 3, prepTime.get(a));
						xlreader.setcelldata("PCOSALLERGY", a+1, 4, cookTime.get(a));
						xlreader.setcelldata("PCOSALLERGY", a+1, 5, prepMethod.get(a)); 
						xlreader.setcelldata("PCOSALLERGY", a+1, 6, NutrientList.get(a));
						xlreader.setcelldata("PCOSALLERGY", a+1, 7, RecipeUrlList.get(a));
						xlreader.setcelldata("PCOSALLERGY", a+1, 8, foodCategory);
						xlreader.setcelldata("PCOSALLERGY", a+1, 9, morbidities) ;
						}
						catch(Exception e)
						{
							System.out.println(e.getMessage());
						}

					}

		       }
		        	
		        	
		        	
			}
		    
			 }
