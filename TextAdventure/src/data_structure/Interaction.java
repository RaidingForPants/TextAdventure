package data_structure;

import support.Change;
import support.Verb;

/*
 * Data structure for storing the information needed when one object interacts with another. Stores text to be displayed and new objects that
 * will be added.
 */

public class Interaction {
	String description;
	SceneEdit[] changes;
	Verb v;
	int count;

	public Interaction(String s){
		description = s;
		changes = new SceneEdit[10];
		count = 0;

		v = Verb.INVALID;
	}
	
	public Interaction(){
		description = "";
	}
	
	public void setDescription(String s){
		description = s;
	}
	
	public void setAction(Verb action){
		v = action;
	}
	
	public Verb getAction(){
		return v;
	}
	
	public String getDescription(){
		return description;
	}
	
	public SceneEdit[] getChanges(){
		return changes;
	}
	
	public void addEdit(Change type, long sceneID, long objID){
		if(count < 10){
			SceneEdit se = new SceneEdit(type, sceneID, objID);
			changes[count] = se;
			count++;
		}
	}
	
	public void addObjectIDForAddition(long sceneID, long objID){
		if(count < 10){
			SceneEdit se = new SceneEdit(Change.ADD, sceneID, objID);
			changes[count] = se;
			count++;
		}
	}
	
	public void addObjectIDForRemoval(long sceneID, long objID){
		if(count < 10){
			SceneEdit se = new SceneEdit(Change.REMOVE, sceneID, objID);
			changes[count] = se;
			count++;
		}
	}
}
