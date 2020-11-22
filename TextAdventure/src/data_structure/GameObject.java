package data_structure;
import support.ObjectType;
/*
 * Data structure for holding all the information about the things you interact with in the game
 * 
 */
public class GameObject {
	
	ObjectType type;
	String name;
	String description;
	String dialogue;
	boolean consumable;
	long id;
	
	public GameObject(String n, ObjectType t, long i){
		name = n.toLowerCase();
		type = t;
		id = i;
		description = "";
		dialogue = "";
		consumable = false;
	}
	
	public void setDescription(String desc){
		description = desc;
	}
	
	public void setConsumable(boolean b){
		consumable = b;
	}
	
	public void setDialogue(String dial){
		dialogue = dial;
	}
	
	
	public String getName(){
		return name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String getDialogue(){
		return dialogue;
	}
	
	public ObjectType getType(){
		return type;
	}
	
	public long getID(){
		return id;
	}
	
	public boolean isConsumable(){
		return consumable;
	}
	
	/*
	 * Shorthand for creating an instance of GameObject that represents nothing. The indirect object is usually nothing.
	 * 
	 */
	public static GameObject nothing(){
		return new GameObject("", ObjectType.NOTHING, 0);
	}
}
