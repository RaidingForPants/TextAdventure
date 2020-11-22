package data_structure;

import support.Verb;
import support.ObjectType;

/*
 * UserInput:
 *   Data structure for storing the input from the user in verb - object format
 * 
 */
public class UserInput {
	private GameVerb v;
	private GameObject object;
	private GameObject indirectObject;
	
	public UserInput(GameVerb verb, GameObject o, GameObject io){
		v = verb;
		object = o;
		indirectObject = io;
	}
	
	public GameVerb getVerb(){
		return v;
	}
	
	public Verb verbType(){
		return v.getType();
	}
	
	public GameObject getObject(){
		return object;
	}
	public ObjectType objectType(){
		return object.getType();
	}
	
	public Object indirectObjectType(){
		return indirectObject.getType();
	}
	public GameObject getIndirectObject(){
		return indirectObject;
	}
}
