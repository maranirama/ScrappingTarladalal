package morbidityModules;

import java.io.IOException;
import java.util.ArrayList;



public class checkListDiabetic 
{

public static ArrayList<String> eliminatedList = new ArrayList<String>();

public static ArrayList<String> addedElementList= new ArrayList<String>();	

public static ArrayList<String> allergyList= new ArrayList<String>();


	public static boolean checkIngrediant1(String i_Ingredient) 
	{
		eliminatedList.add("cream of rice");
		eliminatedList.add("rice flour");
		eliminatedList.add("rice rava");
		eliminatedList.add("corn");
		eliminatedList.add("sugar");
		eliminatedList.add("white rice");
		eliminatedList.add("white bread");
		eliminatedList.add("pasta");
		eliminatedList.add("soda");
		eliminatedList.add("flavoured water");
		eliminatedList.add("gatorade");
		eliminatedList.add("apple juice");
		eliminatedList.add("orange juice");
		eliminatedList.add("pomegranate juice");
		eliminatedList.add("flavoured curd");
		eliminatedList.add("yogurt");
		eliminatedList.add("corn flakes");
		eliminatedList.add("puffed rice");
		eliminatedList.add("bran flakes");
		eliminatedList.add("instant oatmeal");
		eliminatedList.add("honey");
		eliminatedList.add("maple syrup");
		eliminatedList.add("jaggery");
		eliminatedList.add("sweets");
		eliminatedList.add("candies");
		eliminatedList.add("chocolates");
		eliminatedList.add("refined");
		eliminatedList.add("all purpose flour");
		eliminatedList.add("alcoholic beverages");
		eliminatedList.add("bacon");
		eliminatedList.add("sausages");
		eliminatedList.add("hot dos");
		eliminatedList.add("deli meats");
		eliminatedList.add("chicken nuggets");
		eliminatedList.add("chciken patties");
		eliminatedList.add("bacon");
		eliminatedList.add("jams");
		eliminatedList.add("jelly");
		eliminatedList.add("mango");
		eliminatedList.add("cucumber");
		eliminatedList.add("tomatoes");
		eliminatedList.add("pineapple");
		eliminatedList.add("peaches");
		eliminatedList.add("mangos ");
		eliminatedList.add("pear");
		eliminatedList.add("mixed");
		eliminatedList.add("fruit");
		eliminatedList.add("mandarine");
		eliminatedList.add("oranges");
		eliminatedList.add("cherries");
		eliminatedList.add("chips");
		eliminatedList.add("mayonnaise");
		eliminatedList.add("palmolein oil");
		eliminatedList.add("powdered milk");
		eliminatedList.add("beans");
		eliminatedList.add("peas");
		eliminatedList.add("corn");
		eliminatedList.add("doughnuts");
		eliminatedList.add("cakes");
		eliminatedList.add("pastries");
		eliminatedList.add("cookies");
		eliminatedList.add("croissants");
		eliminatedList.add("sweetened tea");
		eliminatedList.add("coffee");
		eliminatedList.add("packaged snacks");
		eliminatedList.add("soft drinks");
		eliminatedList.add("banana");
		eliminatedList.add("melon");
		eliminatedList.add("dairy milk");
		eliminatedList.add("butter");
		eliminatedList.add("cheese");

		String p_Ingredient = i_Ingredient.toLowerCase();
		System.out.println("igredeintes coming" + p_Ingredient);

		boolean isIngrediant = true;
		for (String v : eliminatedList) {
			if (p_Ingredient.contains(v)) // p_Ingredient
			{
				System.out.println("Eliminated::" + p_Ingredient + "because of " + v + "recepid" + "");
				isIngrediant = false;
				break;
			}
		}

		return isIngrediant;

	}
	
	
	public  static  String checkAddedIngredients(String i_Ingredient)
	{
		
		

		String p_Ingredient = i_Ingredient.toLowerCase();
		System.out.println("igredeintes coming"+p_Ingredient);
		
		addedElementList.add("broccoli");
		addedElementList.add("pumpkin");
		addedElementList.add("pumpkin seeds");
		addedElementList.add("chia seeds ");
		addedElementList.add("flaxseeds");
		addedElementList.add("apples");
		addedElementList.add("nuts");
		addedElementList.add("lady finger");
		addedElementList.add("okra");
		addedElementList.add("beans");
		addedElementList.add("raspberries");
		addedElementList.add("strawberries");
		addedElementList.add("blueberries");
		addedElementList.add("blackberries");
		addedElementList.add("eggs");
		addedElementList.add("yogurt");
		addedElementList.add("bitter gaurd");
		addedElementList.add("rolled oats");
		addedElementList.add("steel cut oats");
		addedElementList.add("chicken");
		addedElementList.add("fish");
		addedElementList.add("quinoa");
		addedElementList.add("mushroom");

		
		//ing compare with arraylist
		String isIngrediant="";
		
		for(String toaddingredient:addedElementList)
		
		{
			
			
			
			if (p_Ingredient.contains(toaddingredient)) //p_Ingredient
			{
				System.out.println("addedingredeint::"+p_Ingredient+"because of "+toaddingredient+"recepid"+"");
				
				isIngrediant=toaddingredient;
				
				
				break;
			}
		}


		return isIngrediant;
			
			
	
	}
	
	
	public static boolean checkAllergyIngredients(String allg_Ingredient) 
	
	{
		 allergyList.add("milk");
		 allergyList.add("soy");
		 allergyList.add("egg");
		 allergyList.add("sesame");
		 allergyList.add("peanuts");
		 allergyList.add("walnuts");
		 allergyList.add("almonds");
		allergyList.add("hazelnut");
		allergyList.add("pecan");
		allergyList.add("pistachio");
		
		
		
		
		boolean isIngredientAllergy=true;
		//String isIngrediant="";
		String allergy_Ingredient = allg_Ingredient.toLowerCase();
		System.out.println("igredeintes coming" + allergy_Ingredient);

		
		for (String al: allergyList) 
		{
			if (allergy_Ingredient.contains(al)) // p_Ingredient
			{
				System.out.println("Allergy" + allg_Ingredient + "because of " + al + "recepid" + "");
				
				isIngredientAllergy=false;
				
				break;
			}
		}
		return isIngredientAllergy;
	
	
		}


	
}


