package morbidityModules;

import java.util.ArrayList;

	public class PCOSCheckList {
		public static ArrayList<String> eliminatedList= new ArrayList<String>();
		 public static ArrayList<String> allergyList=new  ArrayList<String>();
		 public static ArrayList<String> addedElementList=new  ArrayList<String>();
		static boolean checkIngrediant(String i_Ingredient) 
		{
			
			
		
		
		eliminatedList.add("cake");
		eliminatedList.add("pastries");
		eliminatedList.add("fried food");
		eliminatedList.add("white bread");
		eliminatedList.add("pizza");
		eliminatedList.add("burger");
		eliminatedList.add("carbonated beverages");
		eliminatedList.add("sweets");
		eliminatedList.add("soda");
		eliminatedList.add("icecream");
		eliminatedList.add("gatorade");
		eliminatedList.add("apple juice");
		eliminatedList.add("orange juice");
		eliminatedList.add("pomegranate juice");
		eliminatedList.add("red meat");
		eliminatedList.add("processed meatt");
		eliminatedList.add("milk");
		eliminatedList.add("curd");
		eliminatedList.add("dahi");
		eliminatedList.add("paneer");
		eliminatedList.add("cream");
		eliminatedList.add("soy products");
		eliminatedList.add("gluten");
		eliminatedList.add("pasta");
		eliminatedList.add("white rice");
		eliminatedList.add("donuts");
		eliminatedList.add("fries");
		eliminatedList.add("coffee");
		eliminatedList.add("vegetable oil");
		eliminatedList.add("soybean oil");
		eliminatedList.add("canola oil");
		eliminatedList.add("rapeseed oil");
		eliminatedList.add("sunflower oil");
		eliminatedList.add("safflower oil");
		eliminatedList.add("doughnuts");
		eliminatedList.add("ghee");
		//ing compare with arraylist
		boolean isIngrediant=true;
		String p_Ingredient = i_Ingredient.toLowerCase();
		
		for(String v:eliminatedList)
		
		{
			

			
			System.out.println("igredeintes coming" + p_Ingredient);
			
			if (p_Ingredient.contains(v)) //p_Ingredient
			{
				System.out.println("Eliminated::"+p_Ingredient+"because of "+v+"recepid"+"");
				
				isIngrediant=false;
				
				
				break;
			}
		}


		return isIngrediant;
			
		}

		
		

	
	  static boolean checkAddedIngredients(String add_Ingredient) {
	  
	  
	  
	  String p_Ingredient = add_Ingredient.toLowerCase();
	  System.out.println("igredeintes coming"+p_Ingredient);
	  
	  addedElementList.add("broccoli"); addedElementList.add("peas");
	  addedElementList.add("palak"); addedElementList.add("methi ");
	  addedElementList.add("cauliflower"); addedElementList.add("flaxseed");
	  addedElementList.add("apples"); addedElementList.add("beetroot");
	  addedElementList.add("capsicum"); addedElementList.add("almond");
	  addedElementList.add("kokum"); addedElementList.add("chiaseed");
	  addedElementList.add("almonds"); addedElementList.add("walnuts");
	  addedElementList.add("pistachios"); addedElementList.add("mushroom");
	  addedElementList.add("carrot"); addedElementList.add("tomato");
	  addedElementList.add("cucumber"); addedElementList.add("kale");
	  addedElementList.add("black grape");
	  
	  boolean addIngrediant=true;
		String addon_Ingredient = add_Ingredient.toLowerCase();
		
		for(String m:eliminatedList)
		
		{
			

			
			System.out.println("igredeintes coming" + p_Ingredient);
			
			if (addon_Ingredient.contains(m)) //p_Ingredient
			{
				System.out.println("Eliminated::"+p_Ingredient+"because of "+m+"recepid"+"");
				
				
				return addIngrediant;
				
				
			}
		}

		return false;
		
	  }
	 
	
	

	 static boolean allergy(String allg_Ingredient)

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
	 }
	 }
	 return isIngredientAllergy;




	 }
}