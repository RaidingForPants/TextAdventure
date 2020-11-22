package data_structure;

import java.util.LinkedHashMap;

import support.ObjectMap;
import support.Verb;
import support.VerbMap;
import support.ObjectType;
/*
 * Data structure for storing the data that makes up a scene in the game. A scene is a place where the player is that has objects in it.
 * 
 */
public class GameScene{
	
	VerbMap allowableActions;
	ObjectMap objects;
	String description;
	LinkedHashMap<String, String> objectDescriptions;
	LinkedHashMap<String, String> dialogue;
	
	public GameScene(){
		allowableActions = new VerbMap();
		objects = new ObjectMap();
		objectDescriptions = new LinkedHashMap<String, String>();
		dialogue = new LinkedHashMap<String, String>();
	}
	
	public VerbMap getActions(){
		return allowableActions;
	}
	
	public ObjectMap getObjects(){
		return objects;
	}
	
	public LinkedHashMap<String, String> getObjectDescriptions(){
		return objectDescriptions;
	}
	
	public LinkedHashMap<String, String> getDialogue(){
		return dialogue;
	}
	
	public void addDialogue(String objectName, String text){
		dialogue.put(objectName, text);
	}
	
	public void addAction(String actionName, Verb type){
		allowableActions.put(actionName, new GameVerb(actionName, type));
	}
	
	public void addObject(String objectName, ObjectType type, long ID){
		objects.put(objectName, new GameObject(objectName, type, ID));
	}
	
	public void addObject(GameObject o){
		objects.put(o.getName(), o);
	}
	
	public void addDescription(String text){
		description = text;
	}
	public String getDescription(){
		return description;
	}
	
	public void removeObject(GameObject o){
		objects.remove(o.getName());
	}
	
	public void removeObject(String name){
		objects.remove(name);
	}
}
