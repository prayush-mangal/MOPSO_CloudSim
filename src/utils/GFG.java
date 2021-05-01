package utils;
import java.util.*;
import java.lang.*;
public class GFG {
	 public static HashMap<Integer, Double> sortByValue(HashMap<Integer, Double> hm)
	    {
	        // Create a list from elements of HashMap
	        List<Map.Entry<Integer, Double> > list =
	               new LinkedList<Map.Entry<Integer, Double> >(hm.entrySet());
	  
	        // Sort the list
	        Collections.sort(list, new Comparator<Map.Entry<Integer, Double> >() {
	            public int compare(Map.Entry<Integer, Double> o1, 
	                               Map.Entry<Integer, Double> o2)
	            {
	                return (o1.getValue()).compareTo(o2.getValue());
	            }
	        });
	          
	        // put data from sorted list to hashmap 
	        HashMap<Integer, Double> temp = new LinkedHashMap<Integer, Double>();
	        for (Map.Entry<Integer, Double> aa : list) {
	            temp.put(aa.getKey(), aa.getValue());
	        }
	        return temp;
}
}
