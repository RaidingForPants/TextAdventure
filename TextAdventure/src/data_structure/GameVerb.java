package data_structure;

import support.Verb;

/*
 * Data structure for storing the information about the action being taken in the game
 * 
 */
public class GameVerb {
	Verb v;
	String name;
	
	public GameVerb(String name, Verb type){
		v = type;
		this.name = name;
	}
	
	public Verb getType(){
		return v;
	}
	
	public String getActionName(){
		return name;
	}
	
	/*
	 * Shorthand forms for creating game verbs of each type
	 */
	public static GameVerb attack(String s){
		return new GameVerb(s, Verb.ATTACK);
	}
	
	public static GameVerb cast(String s){
		return new GameVerb(s, Verb.CAST);
	}
	
	public static GameVerb command(String s){
		return new GameVerb(s, Verb.COMMAND);
	}
	
	public static GameVerb equip(String s){
		return new GameVerb(s, Verb.EQUIP);
	}
	
	public static GameVerb look(String s){
		return new GameVerb(s, Verb.LOOK);
	}
	
	public static GameVerb move(String s){
		return new GameVerb(s, Verb.MOVE);
	}
	
	public static GameVerb take(String s){
		return new GameVerb(s, Verb.TAKE);
	}
	
	public static GameVerb talk(String s){
		return new GameVerb(s, Verb.TALK);
	}
	
	public static GameVerb use(String s){
		return new GameVerb(s, Verb.USE);
	}
	
	public static GameVerb invalid(){
		return new GameVerb("", Verb.INVALID);
	}
}
