

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import org.testng.annotations.Test;

public class HypoThyroidism extends BaseTest {


  public static ArrayList recipeNameList = new ArrayList();
  public static ArrayList recipeid = new ArrayList();
  public static ArrayList ingredientList = new ArrayList();
  public static ArrayList prepTimeList = new ArrayList();
  public static ArrayList cookTimeList = new ArrayList();
  public static ArrayList prepMethodList = new ArrayList();
  public static ArrayList NutrientList = new ArrayList();
  public static ArrayList RecipeUrlList = new ArrayList();
  public static ArrayList PaginationList = new ArrayList();
  static ArrayList < String > EliminatedList;

  public static ArrayList IngredientsAdditionList= new ArrayList();
  public static ArrayList AllergyIngredientsList= new ArrayList();
  static CheckListHypoThyroidism cht = new CheckListHypoThyroidism();
  
  static XSSFWorkbook workbook = new XSSFWorkbook();
  static XSSFSheet eliminationSheet = workbook.createSheet("HypoThyroidism_Elimination");
  static XSSFSheet addonSheet = workbook.createSheet("HypoThyroidism_AddOn");
  static XSSFSheet allergySheet = workbook.createSheet("HypoThyroidism_Allergy");
  
  static int pgSize = 0;
  static int item_size = 0;
  static int recipeNameListSize = 0;
  
  public static void init() throws InterruptedException, IOException {
	// TODO Auto-generated constructor stub
	  
	  WebElement searchTxt = driver.findElement(By.id("ctl00_txtsearch"));
	    searchTxt.sendKeys("hypothyroidism");

	    WebElement searchBtn = driver.findElement(By.id("ctl00_imgsearch"));
	    searchBtn.click();

	    driver.findElement(By.xpath("//a[@href='recipes-for-hypothyroidism-veg-diet-indian-recipes-849']")).click();
	  
	    pgSize = driver.findElements(By.xpath("//div[@id='pagination']/a")).size();
	    System.out.println("Pagination size:" + pgSize);
	    
	    Thread.sleep(1000);
	    recipeNameList.clear();
	    recipeid.clear();
	    ingredientList.clear();
	    prepTimeList.clear();
	    cookTimeList.clear();
	    prepMethodList.clear();
	    NutrientList.clear();
	    RecipeUrlList.clear();
	    IngredientsAdditionList.clear();
	    AllergyIngredientsList.clear();

}
  

  @Test
  public static void eliminationFilter() throws InterruptedException, IOException {
	 System.out.println("Inside eliminationFilter");  
	 init();

	    for (int x = 1; x <= pgSize; x++) {
	    	System.out.println("Inside eliminationFilter for"+pgSize);  
	      try {
	        WebElement pagei = driver.findElement(By.xpath("(//div[@id='pagination']/a)[" + x + "]"));
	        pagei.click();

	        item_size = driver.findElements(By.xpath("//article[@class='rcc_recipecard']")).size();
	        System.out.println("Item size: " + item_size);

	      } catch (Exception e) {
	        e.printStackTrace();
	      };

	      for (int j = 1; j <= item_size; j++) {
	        Thread.sleep(1000);
	        String item_name = driver.findElement(
	        By.xpath("(//article[@class='rcc_recipecard'])[" + j + "]//span[@class='rcc_recipename']//a")).getText();
	        System.out.println("Receipe Name**********:" + item_name);

	        WebElement r_id = driver.findElement(By.xpath("(//div[@class='rcc_rcpno'])[" + j + "]//span"));
	        String s = r_id.getText();
	        String s1 = r_id.getAttribute("innerHTML");
	        String formattedrecipeid = StringUtils.substringBetween(s1, ";", "<");

	        driver.findElement(By.xpath("//article[@class='rcc_recipecard'][" + j + "]//span[@class='rcc_recipename']/a")).click();
	        WebElement Ingredients = driver.findElement(By.xpath("//div[@id='rcpinglist']"));
	        String ingredientsList = Ingredients.getText().toLowerCase().trim();
	        boolean isMatchedInElimination = cht.checkMatchedingredients(ingredientsList);
	        System.out.println("isMatchedInElimination :" + isMatchedInElimination);
	        if (!isMatchedInElimination) {
	            recipeid.add(formattedrecipeid.trim());
	            recipeNameList.add(item_name);
	            ingredientList.add(ingredientsList);

	            WebElement PrepTime = driver.findElement(By.xpath("//p//time[1]"));
	            prepTimeList.add(PrepTime.getText());
	            try {
	              WebElement CookTime = driver.findElement(By.xpath("//p//time[2]"));
	              cookTimeList.add(CookTime.getText());
	            } catch (Exception e) {
	              cookTimeList.add("NA");
	            };

	            WebElement PrepMethod = driver.findElement(By.xpath("//div[@id='recipe_small_steps']"));
	            prepMethodList.add(PrepMethod.getText());
	            Thread.sleep(4000);
	            try {
	              WebElement Nutrients = driver.findElement(By.xpath("//div[@id='rcpnuts']"));
	              NutrientList.add(Nutrients.getText());
	            } catch (Exception e) {
	              System.out.println("Nutrient Values are : ");
	              NutrientList.add("NA");
	            };

	            String strUrl = driver.getCurrentUrl();
	            RecipeUrlList.add(strUrl);
	            System.out.println("Recipe URL : " + strUrl);

	          }
	        String url = "https://www.tarladalal.com/recipes-for-hypothyroidism-veg-diet-indian-recipes-849?pageindex=" + x;
	        Thread.sleep(3000);
	        driver.get(url);

	      }
	    }

	    System.out.println("XSS write : recipeNameList.size" + recipeNameList.size());
	    recipeNameListSize = recipeNameList.size();
	    writeContentToExcel( eliminationSheet, "Serial No", "RecipeId","Recipe Name", "Ingredients",
	    		"Preparation Time","Cooking Time", "Preparation method", "Nutrient values", "Recipe URL","To Add Ingredient","Allergy Ingredient", recipeNameListSize );
	    
	    driver.quit();
 }

  @Test
  public static void addonFilter() throws InterruptedException, IOException {
	  System.out.println("Inside addonFilter");
	  init();

	    for (int x = 1; x <= pgSize; x++) {
	     
	      try {
	        WebElement pagei = driver.findElement(By.xpath("(//div[@id='pagination']/a)[" + x + "]"));
	        pagei.click();

	        item_size = driver.findElements(By.xpath("//article[@class='rcc_recipecard']")).size();
	        System.out.println("Item size: " + item_size);

	      } catch (Exception e) {
	        e.printStackTrace();
	      };

	      for (int j = 1; j <= item_size; j++) {
	        Thread.sleep(1000);
	        String item_name = driver.findElement(
	        By.xpath("(//article[@class='rcc_recipecard'])[" + j + "]//span[@class='rcc_recipename']//a")).getText();
	        System.out.println("Receipe Name**********:" + item_name);

	        WebElement r_id = driver.findElement(By.xpath("(//div[@class='rcc_rcpno'])[" + j + "]//span"));
	        String s = r_id.getText();
	        String s1 = r_id.getAttribute("innerHTML");
	        String formattedrecipeid = StringUtils.substringBetween(s1, ";", "<");

	        driver.findElement(By.xpath("//article[@class='rcc_recipecard'][" + j + "]//span[@class='rcc_recipename']/a")).click();
	        WebElement Ingredients = driver.findElement(By.xpath("//div[@id='rcpinglist']"));
	        String ingredientsList = Ingredients.getText().toLowerCase().trim();
	        boolean isMatchedInElimination = cht.checkMatchedingredients(ingredientsList);
	      // System.out.println("ingredientsList  >>>>>>>>>>"+ ingredientsList.toString());
	        if (!isMatchedInElimination) {
	        	//System.out.println("isMatchedInElimination - true:");
	        	  String addedIngredient = cht.checkToAddIngredients(ingredientsList);
	        	
	            if (addedIngredient.length() > 0) {
	            	System.out.println("addedIngredient > 0:"+addedIngredient );
	        	
	            recipeid.add(formattedrecipeid.trim());
	            recipeNameList.add(item_name);
	            ingredientList.add(ingredientsList);
	            IngredientsAdditionList.add(addedIngredient);
	            
	            WebElement PrepTime = driver.findElement(By.xpath("//p//time[1]"));
	            prepTimeList.add(PrepTime.getText());
	            try {
	              WebElement CookTime = driver.findElement(By.xpath("//p//time[2]"));
	              cookTimeList.add(CookTime.getText());
	            } catch (Exception e) {
	              cookTimeList.add("NA");
	            };

	            WebElement PrepMethod = driver.findElement(By.xpath("//div[@id='recipe_small_steps']"));
	            prepMethodList.add(PrepMethod.getText());
	            Thread.sleep(4000);
	            try {
	              WebElement Nutrients = driver.findElement(By.xpath("//div[@id='rcpnuts']"));
	              NutrientList.add(Nutrients.getText());
	            } catch (Exception e) {
	              System.out.println("Nutrient Values are : ");
	              NutrientList.add("NA");
	            };

	            String strUrl = driver.getCurrentUrl();
	            RecipeUrlList.add(strUrl);
	            System.out.println("Recipe URL : " + strUrl);
	            }
	          }
	        String url = "https://www.tarladalal.com/recipes-for-hypothyroidism-veg-diet-indian-recipes-849?pageindex=" + x;
	        Thread.sleep(3000);
	        driver.get(url);

	      }
	    
	    }
	    System.out.println("XSS write : recipeNameList.size" + recipeNameList.size());
	   
	    recipeNameListSize = recipeNameList.size();
	    writeContentToExcel( addonSheet, "Serial No", "RecipeId","Recipe Name", "Ingredients",
	    		"Preparation Time","Cooking Time", "Preparation method", "Nutrient values", "Recipe URL","To Add Ingredient","Allergy Ingredient", recipeNameListSize );
	    
	    driver.quit();
 }
  @Test
  public static void allergyFilter() throws InterruptedException, IOException {
	  System.out.println("Inside allergyFilter");
	  init();


	    for (int x = 1; x <= pgSize; x++) {
	     
	      try {
	        WebElement pagei = driver.findElement(By.xpath("(//div[@id='pagination']/a)[" + x + "]"));
	        pagei.click();

	        item_size = driver.findElements(By.xpath("//article[@class='rcc_recipecard']")).size();
	        System.out.println("Item size: " + item_size);

	      } catch (Exception e) {
	        e.printStackTrace();
	      };

	      for (int j = 1; j <= item_size; j++) {
	        Thread.sleep(1000);
	        String item_name = driver.findElement(
	        By.xpath("(//article[@class='rcc_recipecard'])[" + j + "]//span[@class='rcc_recipename']//a")).getText();
	        System.out.println("Receipe Name**********:" + item_name);

	        WebElement r_id = driver.findElement(By.xpath("(//div[@class='rcc_rcpno'])[" + j + "]//span"));
	        String s = r_id.getText();
	        String s1 = r_id.getAttribute("innerHTML");
	        String formattedrecipeid = StringUtils.substringBetween(s1, ";", "<");

	        driver.findElement(By.xpath("//article[@class='rcc_recipecard'][" + j + "]//span[@class='rcc_recipename']/a")).click();
	        WebElement Ingredients = driver.findElement(By.xpath("//div[@id='rcpinglist']"));
	        String ingredientsList = Ingredients.getText().toLowerCase().trim();
	        boolean isMatchedInElimination = cht.checkMatchedingredients(ingredientsList);
	      
	        if (!isMatchedInElimination) {
	        	//System.out.println("isMatchedInElimination - true:");
	        	  String allergyIngredient = cht.checkAllergyIngredients(ingredientsList);
	        	  
	            if (allergyIngredient.length() <= 0) {
	            		        	
	            recipeid.add(formattedrecipeid.trim());
	            recipeNameList.add(item_name);
	            ingredientList.add(ingredientsList);
	          //  AllergyIngredientsList.add(allergyIngredient);
	            
	            
	            WebElement PrepTime = driver.findElement(By.xpath("//p//time[1]"));
	            prepTimeList.add(PrepTime.getText());
	            try {
	              WebElement CookTime = driver.findElement(By.xpath("//p//time[2]"));
	              cookTimeList.add(CookTime.getText());
	            } catch (Exception e) {
	              cookTimeList.add("NA");
	            };

	            WebElement PrepMethod = driver.findElement(By.xpath("//div[@id='recipe_small_steps']"));
	            prepMethodList.add(PrepMethod.getText());
	            Thread.sleep(4000);
	            try {
	              WebElement Nutrients = driver.findElement(By.xpath("//div[@id='rcpnuts']"));
	              NutrientList.add(Nutrients.getText());
	            } catch (Exception e) {
	              System.out.println("Nutrient Values are : ");
	              NutrientList.add("NA");
	            };

	            String strUrl = driver.getCurrentUrl();
	            RecipeUrlList.add(strUrl);
	            System.out.println("Recipe URL : " + strUrl);
	            }
	          }
	        String url = "https://www.tarladalal.com/recipes-for-hypothyroidism-veg-diet-indian-recipes-849?pageindex=" + x;
	        Thread.sleep(3000);
	        driver.get(url);

	      }
	    
	    }
	    System.out.println("XSS write : recipeNameList.size" + recipeNameList.size());
	   
	    recipeNameListSize = recipeNameList.size();
	    writeContentToExcel( allergySheet, "Serial No", "RecipeId","Recipe Name", "Ingredients",
	    		"Preparation Time","Cooking Time", "Preparation method", "Nutrient values", "Recipe URL","To Add Ingredient","Allergy Ingredient", recipeNameListSize );
	    
	    
	    driver.quit();
	  
  }
  
  public static void writeContentToExcel (XSSFSheet sht, String Sno, String RecipeId,String RecipeName, String Ingredients,
  		String PreparationTime, String CookingTime, String PreparationMethod, String NutrientValues, String RecipeURL,String ToAddIngredient,String AllergyIngredient,  int recipeListSize ) throws InterruptedException, IOException{

	  sht.createRow(0);
	    
	  sht.getRow(0).createCell(0).setCellValue(Sno);
	  sht.getRow(0).createCell(1).setCellValue(RecipeId);
	  sht.getRow(0).createCell(2).setCellValue(RecipeName);
	    //sheet.getRow(0).createCell(3).setCellValue("Recipe Category(Breakfast/lunch/snack/dinner)");
	    //sheet.getRow(0).createCell(4).setCellValue("Food Category(Veg/non-veg/vegan/Jain)");
	  sht.getRow(0).createCell(3).setCellValue(Ingredients);
	  sht.getRow(0).createCell(4).setCellValue(PreparationTime);
	  sht.getRow(0).createCell(5).setCellValue(CookingTime);
	  sht.getRow(0).createCell(6).setCellValue(PreparationMethod);
	  sht.getRow(0).createCell(7).setCellValue(NutrientValues);
	    //sheet.getRow(0).createCell(10).setCellValue("Targetted morbid conditions (Diabeties/Hypertension/Hypothyroidism)");
	  sht.getRow(0).createCell(8).setCellValue(RecipeURL);
	  
	  if(sht == addonSheet) {
		  sht.getRow(0).createCell(9).setCellValue(ToAddIngredient);
	  }
	/*	if(sht == allergySheet) {
			sht.getRow(0).createCell(10).setCellValue(AllergyIngredient);  
		}*/
	  
	    int rowno = 1;

	    for (int i = 0; i < recipeListSize; i++) {
	      {
	        XSSFRow row = sht.createRow(rowno++);
	        System.out.println("rowno : " + rowno);
	        row.createCell(0).setCellValue(rowno-1);
	        row.createCell(1).setCellValue(recipeid.get(i).toString());
	        row.createCell(2).setCellValue(recipeNameList.get(i).toString());
	        row.createCell(3).setCellValue(ingredientList.get(i).toString());
	        row.createCell(4).setCellValue(prepTimeList.get(i).toString());
	        row.createCell(5).setCellValue(cookTimeList.get(i).toString());
	        row.createCell(6).setCellValue(prepMethodList.get(i).toString());
	        row.createCell(7).setCellValue(NutrientList.get(i).toString());
	        row.createCell(8).setCellValue(RecipeUrlList.get(i).toString());
	        if(sht == addonSheet) {
	        	row.createCell(9).setCellValue(IngredientsAdditionList.get(i).toString());
	  	  }
//	  		if(sht == allergySheet) {
//	  			row.createCell(10).setCellValue(AllergyIngredientsList.get(i).toString()); 
//	  		}
	  		
	        System.out.println("XSS write : " + i);
	      }

	      FileOutputStream FOS = new FileOutputStream(".\\datafiles\\SmartScrapers_Hypothyroidism.xlsx");
	      
	      workbook.write(FOS);

	      FOS.close();
	    }
  }
 
   
}