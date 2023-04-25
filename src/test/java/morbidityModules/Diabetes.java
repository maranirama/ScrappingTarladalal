package morbidityModules;


	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.time.Duration;
	import java.util.ArrayList;
	import java.util.List;
	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebElement;
	import org.testng.annotations.Test;

	
public class Diabetes extends BaseTest{

	//static WebDriver driver;
	public static ArrayList recipeNameList = new ArrayList();
	public static ArrayList recipeid = new ArrayList();
	public static ArrayList ingredientList = new ArrayList();
	public static ArrayList prepTimeList = new ArrayList();
	public static ArrayList cookTimeList = new ArrayList();
	public static ArrayList prepMethodList = new ArrayList();
	public static ArrayList NutrientList = new ArrayList();
	public static ArrayList RecipeUrlList = new ArrayList();
	public static ArrayList<String> eliminatedList= new ArrayList<String>();
	public static ArrayList<String> addedElementList= new ArrayList<String>();	
	
	
	public static ArrayList toaddingredientList = new ArrayList();
	
	
	//ToAdd start comment
	
	
	@Test
	
	public static void EliminateAddList() throws InterruptedException, IOException {
		
		 driver.findElement(By.name("ctl00$txtsearch")).sendKeys("diabetic");
      driver.findElement(By.id("ctl00_imgsearch")).click();
      driver.findElement(By.xpath("//a[@href='recipes-for-indian-diabetic-recipes-370']")).click();          
		driver.manage().deleteAllCookies();
		
      List<WebElement> NoOfPages = driver.findElements(By.xpath("//div[@id='pagination']//a"));
		int TotalPages = NoOfPages.size()+9;
		System.out.println("Total Pages: "+ TotalPages);
			
			
	 for ( int p =1 ; p <TotalPages ; p++)
	        {
			 
			 System.out.println("onpage"+p);
				String url = "https://tarladalal.com/recipes-for-indian-diabetic-recipes-370?pageindex="+p;//;
	        		driver.get(url);
		  List<WebElement> RecipeList = driver.findElements(By.xpath("//div[@class='recipelist']//article"));
	    System.out.println("Recipe list count is: "+RecipeList.size());
		
	    
	    for ( int k=1; k<=RecipeList.size(); k++)  {
	    	String s_recipeName ;
	    	String s_recipe_id ; 
	    	String s_Ingredients ;
	    	String formattedrecipeid;
	    	WebElement Ingrediants;
	    	WebElement PrepTime;
	    	WebElement CookTime;
	    	WebElement PrepMethod;
	    	WebElement Nutrients;
		System.out.println("valueok"+k);
			
		try {
	    	WebElement recipe_id =
		  			  driver.findElement(By.xpath("//article["+k+"]//div[@class='rcc_rcpno']/span")); 
	  
	       		s_recipe_id = recipe_id.getText();
		          	
		        	 formattedrecipeid =  s_recipe_id.substring(8, s_recipe_id.length()-9);
		          	System.out.println(formattedrecipeid); 
		        	WebElement recipeName = driver.findElement
		    				(By.xpath("//article["+k+"]//div[@class='rcc_rcpcore']/span[1]/a"));
		       	s_recipeName = recipeName.getText();
		    		recipeName.click();
		    		Thread.sleep(2000);
			}
			catch(Exception e)
			{
				continue;
			}
		
		
		
	        		
	        		try {
	        	
	        	 Ingrediants = driver.findElement(By.xpath("//div[@id='rcpinglist']"));
	        	s_Ingredients = Ingrediants.getText();
	        	PrepTime = driver.findElement(By.xpath("//p//time[1]"));
	        	 CookTime = driver.findElement(By.xpath("//p//time[2]"));
	        	 PrepMethod = driver.findElement(By.xpath("//div[@id='recipe_small_steps']"));
	        	 Nutrients = driver.findElement(By.xpath("//div[@id='rcpnuts']"));
	        	}
	        	catch(Exception e)
				{
					continue;
				}
	        	boolean eliminateList = checkListDiabetic.checkIngrediant1(s_Ingredients);
	        	if(eliminateList)
	        	{
		        String addedIngrdient = checkListDiabetic.checkAddedIngredients(s_Ingredients) ;
		        	if ( addedIngrdient.length()>0)
		        	{
		        		recipeNameList.add(s_recipeName);
		        		recipeid.add(formattedrecipeid.trim());
			        	ingredientList.add(s_Ingredients);
			        	toaddingredientList.add(addedIngrdient);
			        	
			        	
			        	
			        	
			        	System.out.println("Ingrediants are : "+Ingrediants.getText());
			        	
			        
			        	prepTimeList.add(PrepTime.getText());      	
			        	System.out.println("Preperation Time is : "+ PrepTime.getText());
			        
			        	
			        	cookTimeList.add(CookTime.getText());
			        	System.out.println("Cooking Time is : "+CookTime.getText());
			        	
			        	prepMethodList.add(PrepMethod.getText());
			        	System.out.println("Preperation Method is : "+PrepMethod.getText());
			        	
			        	Thread.sleep(4000);
			        
			        	
			        	NutrientList.add(Nutrients.getText());
			        	
			        	String strUrl = driver.getCurrentUrl();
			        	//String strUrl = driver.getCurrentUrl();
			        	RecipeUrlList.add(strUrl);
			        	System.out.println("Recipe URL : "+strUrl);
		        	}
	        	}
	        	
       	
	        	
	        	String url1 = "https://tarladalal.com/recipes-for-indian-diabetic-recipes-370?pageindex="+p;//;
	        	
	        	driver.get(url1);
	        	Thread.sleep(5000);
	         	
		}
	    

		 
	       }
		 

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("SmartScrapers_Diabeties_ToAdd_Recipes");
			
			sheet.createRow(0);
			sheet.getRow(0).createCell(0).setCellValue("RecipeId");
			sheet.getRow(0).createCell(1).setCellValue("Recipe Name");
			sheet.getRow(0).createCell(2).setCellValue("Recipe Category(Breakfast/lunch/snack/dinner)");
			sheet.getRow(0).createCell(3).setCellValue("Food Category(Veg/non-veg/vegan/Jain)");
			sheet.getRow(0).createCell(4).setCellValue("Ingredients");
			sheet.getRow(0).createCell(5).setCellValue("Preparation Time");
			sheet.getRow(0).createCell(6).setCellValue("Cooking Time");
			sheet.getRow(0).createCell(7).setCellValue("Preparation method");
			sheet.getRow(0).createCell(8).setCellValue("Nutrient values");
			sheet.getRow(0).createCell(9).setCellValue("Targetted morbid conditions (Diabeties/Hypertension/Hypothyroidism)");
			sheet.getRow(0).createCell(10).setCellValue("Recipe URL");
			sheet.getRow(0).createCell(11).setCellValue("toaddingredient");
			
			int rowno=1;
	//
			for(int i = 0; i <recipeNameList.size() ; i++)
			{
				XSSFRow row=sheet.createRow(rowno++);
				row.createCell(0).setCellValue(recipeid.get(i).toString())	;
				row.createCell(1).setCellValue(recipeNameList.get(i).toString());
				row.createCell(4).setCellValue(ingredientList.get(i).toString());
				row.createCell(5).setCellValue(prepTimeList.get(i).toString());
				row.createCell(6).setCellValue(cookTimeList.get(i).toString());
				row.createCell(7).setCellValue(prepMethodList.get(i).toString());
				row.createCell(8).setCellValue(NutrientList.get(i).toString());
				//row.createCell(9).setCellValue(NutrientList.get(i).toString());
				row.createCell(10).setCellValue(RecipeUrlList.get(i).toString());
				row.createCell(11).setCellValue(toaddingredientList.get(i).toString());
			}
			System.out.println("Total Recipe Count"+recipeNameList.size());
			FileOutputStream FOS = new FileOutputStream(".\\datafiles\\SmartScrapers_DiabetiesToAddList.xlsx");
			//if(FOS.e)
			workbook.write(FOS);
			FOS.close();
			workbook.close();
	}
	
	
	//end comment
	
	//start comment
	
	

	@Test
	
	public static void EliminateDiabeteseList() throws InterruptedException, IOException {
		

		 driver.findElement(By.name("ctl00$txtsearch")).sendKeys("diabetic");
	      driver.findElement(By.id("ctl00_imgsearch")).click();
	      driver.findElement(By.xpath("//a[@href='recipes-for-indian-diabetic-recipes-370']")).click();          
			driver.manage().deleteAllCookies();
			
	      List<WebElement> NoOfPages = driver.findElements(By.xpath("//div[@id='pagination']//a"));
			int TotalPages = NoOfPages.size()+9;
			System.out.println("Total Pages: "+ TotalPages);
				
				
		 for ( int p =1 ; p <TotalPages ; p++)
		        {

			 
			 
			 System.out.println("onpage"+p);
				String url = "https://tarladalal.com/recipes-for-indian-diabetic-recipes-370?pageindex="+p;//;
	        	
	        	driver.get(url);
		   List<WebElement> RecipeList = driver.findElements(By.xpath("//div[@class='recipelist']//article"));
	    System.out.println("Recipe list count is: "+RecipeList.size());
		
	    
	    for ( int k=1; k<=RecipeList.size(); k++)  {
	    	String s_recipeName ;
	    	String s_recipe_id ; 
	    	String s_Ingredients ;
	    	String formattedrecipeid;
	    	WebElement Ingrediants;
	    	WebElement PrepTime;
	    	WebElement CookTime;
	    	WebElement PrepMethod;
	    	WebElement Nutrients;
		System.out.println("valueok"+k);
			
		try {
	    	WebElement recipe_id =
		  			  driver.findElement(By.xpath("//article["+k+"]//div[@class='rcc_rcpno']/span")); 
	  
			
	    	
	    	
	    	 		s_recipe_id = recipe_id.getText();
		            	 formattedrecipeid =  s_recipe_id.substring(8, s_recipe_id.length()-9);
		        	System.out.println(formattedrecipeid); 
		        	WebElement recipeName = driver.findElement
		    				(By.xpath("//article["+k+"]//div[@class='rcc_rcpcore']/span[1]/a"));
		        	s_recipeName = recipeName.getText();
		    		recipeName.click();
		    		Thread.sleep(2000);
			}
			catch(Exception e)
			{
				continue;
			}
		
		
		
	              	try {
	        	
	        	 Ingrediants = driver.findElement(By.xpath("//div[@id='rcpinglist']"));
	        	s_Ingredients = Ingrediants.getText();
	        	PrepTime = driver.findElement(By.xpath("//p//time[1]"));
	        	 CookTime = driver.findElement(By.xpath("//p//time[2]"));
	        	 PrepMethod = driver.findElement(By.xpath("//div[@id='recipe_small_steps']"));
	        	 Nutrients = driver.findElement(By.xpath("//div[@id='rcpnuts']"));
	        	}
	        	catch(Exception e)
				{
					continue;
				}
	              	boolean eliminateList = checkListDiabetic.checkIngrediant1(s_Ingredients);
		        	if(eliminateList)
	        	{
		       
		        		recipeNameList.add(s_recipeName);
		        		recipeid.add(formattedrecipeid.trim());
			        	ingredientList.add(s_Ingredients);
			           	System.out.println("Ingrediants are : "+Ingrediants.getText());
			        	
			        
			        	prepTimeList.add(PrepTime.getText());      	
			        	System.out.println("Preperation Time is : "+ PrepTime.getText());
			        
			        	
			        	cookTimeList.add(CookTime.getText());
			        	System.out.println("Cooking Time is : "+CookTime.getText());
			        	
			        	prepMethodList.add(PrepMethod.getText());
			        	System.out.println("Preperation Method is : "+PrepMethod.getText());
			        	
			        	Thread.sleep(4000);
			        
			        	
			        	NutrientList.add(Nutrients.getText());
			        	
			        	String strUrl = driver.getCurrentUrl();
			         	RecipeUrlList.add(strUrl);
			        	System.out.println("Recipe URL : "+strUrl);
		        	
	        	}
	        	
	        	String url1 = "https://tarladalal.com/recipes-for-indian-diabetic-recipes-370?pageindex="+p;//;
	        	
	        	driver.get(url1);
	        	Thread.sleep(5000);
	        	
		}
	    

		 
	       }
		 

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("SmartScrapers_Diabeties_Eliminated_Recipes");
			
			sheet.createRow(0);
			sheet.getRow(0).createCell(0).setCellValue("RecipeId");
			sheet.getRow(0).createCell(1).setCellValue("Recipe Name");
			sheet.getRow(0).createCell(2).setCellValue("Recipe Category(Breakfast/lunch/snack/dinner)");
			sheet.getRow(0).createCell(3).setCellValue("Food Category(Veg/non-veg/vegan/Jain)");
			sheet.getRow(0).createCell(4).setCellValue("Ingredients");
			sheet.getRow(0).createCell(5).setCellValue("Preparation Time");
			sheet.getRow(0).createCell(6).setCellValue("Cooking Time");
			sheet.getRow(0).createCell(7).setCellValue("Preparation method");
			sheet.getRow(0).createCell(8).setCellValue("Nutrient values");
			sheet.getRow(0).createCell(9).setCellValue("Targetted morbid conditions (Diabeties/Hypertension/Hypothyroidism)");
			sheet.getRow(0).createCell(10).setCellValue("Recipe URL");
			
			int rowno=1;
	//
			for(int i = 0; i <recipeNameList.size() ; i++)
			{
				XSSFRow row=sheet.createRow(rowno++);
				row.createCell(0).setCellValue(recipeid.get(i).toString())	;
				row.createCell(1).setCellValue(recipeNameList.get(i).toString());
				row.createCell(4).setCellValue(ingredientList.get(i).toString());
				row.createCell(5).setCellValue(prepTimeList.get(i).toString());
				row.createCell(6).setCellValue(cookTimeList.get(i).toString());
				row.createCell(7).setCellValue(prepMethodList.get(i).toString());
				row.createCell(8).setCellValue(NutrientList.get(i).toString());
				row.createCell(10).setCellValue(RecipeUrlList.get(i).toString());
			}
			System.out.println("Total Recipe Count"+recipeNameList.size());
			FileOutputStream FOS = new FileOutputStream(".\\datafiles\\SmartScrapers_DiabetiesEliminateList.xlsx");
			//if(FOS.e)
			workbook.write(FOS);
			FOS.close();
			workbook.close();
	}
	
	//end comment
	
	
	// Eliminate start
	
@Test
	
	public static void EliminateAllergenList() throws InterruptedException, IOException {
		
		 driver.findElement(By.name("ctl00$txtsearch")).sendKeys("diabetic");
      driver.findElement(By.id("ctl00_imgsearch")).click();
      driver.findElement(By.xpath("//a[@href='recipes-for-indian-diabetic-recipes-370']")).click();          
		driver.manage().deleteAllCookies();
		
      List<WebElement> NoOfPages = driver.findElements(By.xpath("//div[@id='pagination']//a"));
		int TotalPages = NoOfPages.size()+9;
		System.out.println("Total Pages: "+ TotalPages);
			
			
	 for ( int p =1 ; p <TotalPages ; p++)
	        {
			 
			 System.out.println("onpage"+p);
				String url = "https://tarladalal.com/recipes-for-indian-diabetic-recipes-370?pageindex="+p;//;
	        		driver.get(url);
		  List<WebElement> RecipeList = driver.findElements(By.xpath("//div[@class='recipelist']//article"));
	    System.out.println("Recipe list count is: "+RecipeList.size());
		
	    
	    for ( int k=1; k<=RecipeList.size(); k++)  {
	    	String s_recipeName ;
	    	String s_recipe_id ; 
	    	String s_Ingredients ;
	    	String formattedrecipeid;
	    	WebElement Ingrediants;
	    	WebElement PrepTime;
	    	WebElement CookTime;
	    	WebElement PrepMethod;
	    	WebElement Nutrients;
		System.out.println("valueok"+k);
			
		try {
	    	WebElement recipe_id =
		  			  driver.findElement(By.xpath("//article["+k+"]//div[@class='rcc_rcpno']/span")); 
	  
	       		s_recipe_id = recipe_id.getText();
		          	
		        	 formattedrecipeid =  s_recipe_id.substring(8, s_recipe_id.length()-9);
		          	System.out.println(formattedrecipeid); 
		        	WebElement recipeName = driver.findElement
		    				(By.xpath("//article["+k+"]//div[@class='rcc_rcpcore']/span[1]/a"));
		       	s_recipeName = recipeName.getText();
		    		recipeName.click();
		    		Thread.sleep(2000);
			}
			catch(Exception e)
			{
				continue;
			}
		
		
		
	        		
	        		try {
	        	
	        	 Ingrediants = driver.findElement(By.xpath("//div[@id='rcpinglist']"));
	        	s_Ingredients = Ingrediants.getText();
	        	PrepTime = driver.findElement(By.xpath("//p//time[1]"));
	        	 CookTime = driver.findElement(By.xpath("//p//time[2]"));
	        	 PrepMethod = driver.findElement(By.xpath("//div[@id='recipe_small_steps']"));
	        	 Nutrients = driver.findElement(By.xpath("//div[@id='rcpnuts']"));
	        	}
	        	catch(Exception e)
				{
					continue;
				}
	        	boolean eliminateList = checkListDiabetic.checkIngrediant1(s_Ingredients);
	        	if(eliminateList)
	        	{
		        boolean noallergyRecipe = checkListDiabetic.checkAllergyIngredients(s_Ingredients) ;
		        	if ( noallergyRecipe)  // if value true then recipe will be added
		        	{
		        		recipeNameList.add(s_recipeName);
		        		recipeid.add(formattedrecipeid.trim());
			        	ingredientList.add(s_Ingredients);
			        	//toaddingredientList.add(addedIngrdient);
			        	
			        	
			        	
			        	
			        	System.out.println("Ingrediants are : "+Ingrediants.getText());
			        	
			        
			        	prepTimeList.add(PrepTime.getText());      	
			        	System.out.println("Preperation Time is : "+ PrepTime.getText());
			        
			        	
			        	cookTimeList.add(CookTime.getText());
			        	System.out.println("Cooking Time is : "+CookTime.getText());
			        	
			        	prepMethodList.add(PrepMethod.getText());
			        	System.out.println("Preperation Method is : "+PrepMethod.getText());
			        	
			        	Thread.sleep(4000);
			        
			        	
			        	NutrientList.add(Nutrients.getText());
			        	
			        	String strUrl = driver.getCurrentUrl();
			        	//String strUrl = driver.getCurrentUrl();
			        	RecipeUrlList.add(strUrl);
			        	System.out.println("Recipe URL : "+strUrl);
		        	}
	        	}
	        	
       	
	        	
	        	String url1 = "https://tarladalal.com/recipes-for-indian-diabetic-recipes-370?pageindex="+p;//;
	        	
	        	driver.get(url1);
	        	Thread.sleep(5000);
	         	
		}
	    

		 
	       }
		 

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("SmartScrapers_Diabeties_NonAllergen_Recipes");
			
			sheet.createRow(0);
			sheet.getRow(0).createCell(0).setCellValue("RecipeId");
			sheet.getRow(0).createCell(1).setCellValue("Recipe Name");
			sheet.getRow(0).createCell(2).setCellValue("Recipe Category(Breakfast/lunch/snack/dinner)");
			sheet.getRow(0).createCell(3).setCellValue("Food Category(Veg/non-veg/vegan/Jain)");
			sheet.getRow(0).createCell(4).setCellValue("Ingredients");
			sheet.getRow(0).createCell(5).setCellValue("Preparation Time");
			sheet.getRow(0).createCell(6).setCellValue("Cooking Time");
			sheet.getRow(0).createCell(7).setCellValue("Preparation method");
			sheet.getRow(0).createCell(8).setCellValue("Nutrient values");
			sheet.getRow(0).createCell(9).setCellValue("Targetted morbid conditions (Diabeties/Hypertension/Hypothyroidism)");
			sheet.getRow(0).createCell(10).setCellValue("Recipe URL");
		//	sheet.getRow(0).createCell(11).setCellValue("toaddingredient");
			
			int rowno=1;
	//
			for(int i = 0; i <recipeNameList.size() ; i++)
			{
				XSSFRow row=sheet.createRow(rowno++);
				row.createCell(0).setCellValue(recipeid.get(i).toString())	;
				row.createCell(1).setCellValue(recipeNameList.get(i).toString());
				row.createCell(4).setCellValue(ingredientList.get(i).toString());
				row.createCell(5).setCellValue(prepTimeList.get(i).toString());
				row.createCell(6).setCellValue(cookTimeList.get(i).toString());
				row.createCell(7).setCellValue(prepMethodList.get(i).toString());
				row.createCell(8).setCellValue(NutrientList.get(i).toString());
				//row.createCell(9).setCellValue(NutrientList.get(i).toString());
				row.createCell(10).setCellValue(RecipeUrlList.get(i).toString());
				//row.createCell(11).setCellValue(toaddingredientList.get(i).toString());
			}
			System.out.println("Total Recipe Count"+recipeNameList.size());
			FileOutputStream FOS = new FileOutputStream(".\\datafiles\\SmartScrapers_Diabeties_NonAllergenList.xlsx");
			//if(FOS.e)
			workbook.write(FOS);
			FOS.close();
			workbook.close();
	}
	
	

	// eliminate end

}

	
	

