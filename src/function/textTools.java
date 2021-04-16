package function;

import java.util.HashMap;
import java.util.Map;

public abstract class textTools {

	   private static final Map<String, String> myMap = new HashMap<>();
	   
	   static {
	        myMap.put("bedroom", "Chambre");
	        myMap.put("kitchen", "Cuisine");
	        myMap.put("bathroom", "Salle de bain");
	        
	        myMap.put("singleBed", "Lit simple");
	        myMap.put("doubleBed", "Lit double");
	        myMap.put("closet", "Penderie");
	        myMap.put("tv", "T�l�vision");
	        myMap.put("wifi", "Wi-Fi");
	        
	        myMap.put("shower", "Douche");
	        myMap.put("bathtub", "Baignoire");
	        myMap.put("washingMachine", "Machine � laver");
	        
	        myMap.put("oven", "Four");
	        myMap.put("freezer", "Cong�lateur");
	        myMap.put("coffeeMaker", "Machine � caf�");
	   }
	   
	   public static String getTextFromDBEntry(String dbEntry) {
		   return myMap.get(dbEntry);
	   }
	   
		public static String getTextFromBool(boolean bool) {
			return bool ? "Oui" : "Non";
		}
}
