package data_structure;

import java.util.ArrayList;

/*
 * Data structure for storing the objects that a player has acquired.
 * 
 */

public class Inventory {
	ArrayList<GameObject> contents;
	
	public Inventory(){
		contents = new ArrayList<GameObject>();
	}
	
	public void add(GameObject o){
		contents.add(o);
	}
	
	public GameObject get(String s){
		for(GameObject o : contents){
			if(o.getName().equals(s)){
				return o;
			}
		}
		return null;
	}
	
	//remove object by object
	public boolean remove(GameObject o){
		return contents.remove(o);
	}
	
	//remove object by name
	public boolean remove(String s){
		for(GameObject o : contents){
			if(o.getName().equals(s)){
				return contents.remove(o);
			}
		}
		return false;
	}
	
	//remove object by ID
	public boolean remove(long l){
		for(GameObject o : contents){
			if(o.getID() == l){
				return contents.remove(o);
			}
		}
		return false;
	}
	
	//check by object
	public boolean contains(GameObject o){
		for(GameObject o2 : contents){
			if(o.equals(o2)){
				return true;
			}
		}
		return false;
	}
	
	//check by ID
	public boolean contains(long l){
		for(GameObject o : contents){
			if(o.getID() == l){
				return true;
			}
		}
		return false;
	}
	
	//check by name
	public boolean contains(String s){
		for(GameObject o : contents){
			if(o.getName().equals(s)){
				return true;
			}
		}
		return false;
	}
	
	//returns a string representation of the inventory suitable for displaying to the user
	public String toString(){
		String s = "";
		for(GameObject o : contents){
			s += o.getName().toUpperCase() + "\n";
		}
		if(s.length() > 0)
			s = s.substring(0, s.length()-1);
		if(s.length() == 0)
			s = "EMPTY";
		return s;
	}
}
