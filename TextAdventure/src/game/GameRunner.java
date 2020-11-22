package game;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.json.simple.parser.ParseException;

import data_structure.GameObject;
import data_structure.GameScene;
import data_structure.GameVerb;
import data_structure.Interaction;
import data_structure.Inventory;
import data_structure.SceneEdit;
import data_structure.UserInput;
import support.Verb;
import support.Change;
import support.Constants;
import support.GameOption;
import support.GameSceneData;
import support.InteractionData;
import support.ObjectLoader;
import support.ObjectType;
import support.VerbObjectMapping;

/*
 * Code that handles how the game runs. Takes in input from the user, performs any necessary actions based
 * on that input, and then generates a suitable output text.
 */

public class GameRunner {
	
	String input;
	GameScene scene;
	GameSceneData gsdata;
	InteractionData intData; 
	GameViewInterface inter; //used for passing data between the UI and the game code
	Inventory inventory;
	final long playerInventoryID = 0;
	public GameRunner(GameViewInterface g) throws FileNotFoundException, IOException, ParseException{
		inter = g;
		gsdata = new GameSceneData(Constants.sceneFile);
		scene = gsdata.getScene(Constants.firstSceneIndex); //don't like the fixed number in here
		intData = new InteractionData(Constants.interactionFile);
		inventory = new Inventory();
	}
	
	/*
	 * receiveInputText:
	 *   called when the user presses "enter" in the UI text field. Receives the contents of the text field as input.
	 *   Passes data between the multiple functions that run the game.
	 */
	public void receiveInputText(String text){
		input = text.toLowerCase();
		UserInput processedInput = processInput(input);
		String out = generateOutput(processedInput);
		pushOutput(out);
	}
	
	/*
	 * processInput:
	 *   creates an object/verb representation of the user's input. Searches through the input for the first verb allowed, and then the first object
	 *   allowed that comes after that verb. Verb.INVALID is used if no valid verb is found, and Object.NOTHING is used if no valid object is found
	 *   
	 * returns:
	 *   UserInput object with the verb and objects the user input.
	 */
	
	private UserInput processInput(String in) {
		in = in.toLowerCase();
		String[] splitString = in.split(" ");
		ObjectType o = null;
		ObjectType io = null;
		GameVerb v = null;
		GameObject object = null;
		GameObject indObject = null;
		boolean lookForIndirect = false;
		
		//check if user input a command
		if(isCommand(splitString[0])){
			return new UserInput(GameVerb.command(splitString[0]), GameObject.nothing(), GameObject.nothing());
		}
		
		//if not, try to find a verb in the input line
		int i = 0;
		for(; i < splitString.length; i++){
			GameVerb temp = scene.getActions().get(splitString[i]);
			if(temp != null){ //if this verb is allowed in this scene
				v = temp;
				break;
			}
		}
		
		//if haven't found an allowed verb
		if(v == null){
			return new UserInput(GameVerb.invalid(), GameObject.nothing(), GameObject.nothing());
		}
	
		
		for(; i < splitString.length; i++){ //search after the verb we found for an object
			//look for the object in the player's inventory
			String potentialObject = splitString[i];
			GameObject go = inventory.get(potentialObject);
			if(go != null){
				if(lookForIndirect){
					indObject = go;
					io = indObject.getType();
					break;
				}else{
					object = go;
					o = object.getType();
					lookForIndirect = true;
				}
				
			}
			//look for the object in the scene
			go = scene.getObjects().get(potentialObject);
			if(go != null){ //if this object is in this scene
				if(lookForIndirect){
					io = go.getType();
					indObject = go;
					break;
				}else{
					lookForIndirect = true;
					o = go.getType();
					object = go;
				}
			}else{
			}
		}
		
		//if haven't found an allowed object
		if(o == null){
			return new UserInput(v, GameObject.nothing(), GameObject.nothing());
		}
		//if haven't found an indirect object
		if(io == null){
			return new UserInput(v, object, GameObject.nothing());
		}
		
		//else return full user input
		return new UserInput(v, object, indObject);
	}

	/*
	 * generateOutput:
	 *   generates an output string to tell the user based on their input verb and object.
	 *   Also performs any necessary actions related to the user's input.
	 *   
	 * returns:
	 *   An output string of what happens in the game based on the user's input
	 */
	private String generateOutput(UserInput in){
		String str = "";
		ObjectType o = in.objectType();
		switch(in.verbType()){
		case COMMAND:
			switch(in.getVerb().getActionName()){
			case "actions":
				return scene.getActions().print().toUpperCase();
			case "objects":
				return scene.getObjects().print().toUpperCase();
			case "inventory":
				return "Your inventory contains:\n" + inventory.toString();
			case "quit":
				//quit the game
				System.exit(0);
				return "";
			default:
				break;
			}
		case INVALID:
			return "You can't do that";
		/*case ATTACK:
			//check if it is a valid object to use with attack
			if(VerbObjectMapping.validObject(Verb.ATTACK, o)){
				return "You attack the " + in.getObject().getName();
			}
			break;
		case CAST:
			if(VerbObjectMapping.validObject(Verb.CAST, o)){
				return "You cast " + in.getObject().getName();
				//spell effect happens
			}
			break;
		case EQUIP:
			if(VerbObjectMapping.validObject(Verb.EQUIP, o)){
				return "You equip " + in.getObject().getName();
				//equip happens
			}
			break;
			*/
		case LOOK:
			if(o == ObjectType.NOTHING){
				str += scene.getDescription();
			}
			if(VerbObjectMapping.validObject(Verb.LOOK, o)){
				str += in.getObject().getDescription();
			}
			break;
		case MOVE:
			if(VerbObjectMapping.validObject(Verb.MOVE, o)){
				//change scene
				scene = gsdata.getScene(in.getObject().getID());
				str += "You walk to the " + in.getObject().getName() + ".";
			}else{
				return "You can't do that";
			}
			break;
		case TALK:
			if(o == ObjectType.NOTHING){
				str += "You babble to yourself";
			}else if(VerbObjectMapping.validObject(Verb.TALK, o)){
				str += in.getObject().getDialogue();
			}else{
				return "You can't do that";
			}
			break;
		case TAKE:
			if(VerbObjectMapping.validObject(Verb.TAKE, o)){
				//check if item already exists in inventory so we don't copy it
				if(inventory.contains(in.getObject().getID())) {
					return "You already have that item";
				}else {
					//put into inventory
					inventory.add(in.getObject());
				
					//remove from the scene
					scene.removeObject(in.getObject());
					str += "You gain " + in.getObject().getName().toUpperCase();
				}
			}else{
				return "You can't do that";
			}
			break;
		case USE:
			//check if an interaction exists between the two objects (getInteraction() != null)
			if(VerbObjectMapping.validObject(Verb.USE, o) && intData.getInteraction(in) != null){
				//generate string "you use the __ on the __
				//String s = "You use the " + in.getObject().getName() + ((in.getIndirectObject().getType() != ObjectType.NOTHING)? " on the "+in.getIndirectObject().getName() : "");
				String s = "";
				//if the object is consumable, remove the object from wherever it is
				if(in.getObject().isConsumable()){
					if(inventory.contains(in.getObject())){
						inventory.remove(in.getObject());
					}
					if(scene.getObjects().containsKey(in.getObject().getName())){
						scene.removeObject(in.getObject());
					}
				}
				str += s;
			}else{
				return "You can't do that";
			}
			break;
		default:
			return "You can't do that";
		}
		
		//check if there are any interactions based on this action
		Interaction i = intData.getInteraction(in);
		//if we have the correct action type for this interaction to trigger and the interaction exists...
		if(i != null && i.getAction() == in.getVerb().getType()){
			//get the interaction description
			if(str.equals("")){
				str = i.getDescription();
			}else if(i.getDescription().equals("")){
				//do nothing, there is no interaction description
			}else{
				str += "\n"+i.getDescription();
			}
			
			//loop through every change the interaction causes
			for(SceneEdit e : i.getChanges()){
				if(e == null) break;
				GameObject obj = null;
				
				//load the object we're going to add or remove
				try {
					obj = ObjectLoader.loadObjectFromFile(e.getObjectID(), Constants.objectFile);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					inter.sendError(e1, GameOption.CONTINUE_AFTER_ERROR);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					inter.sendError(e1, GameOption.CONTINUE_AFTER_ERROR);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					inter.sendError(e1, GameOption.CONTINUE_AFTER_ERROR);
				} catch (NullPointerException e1){
					inter.sendError(e1, GameOption.CONTINUE_AFTER_ERROR);
				}
				
				//add or remove the object
				if(obj != null){
					long sceneID = e.getSceneID();
					if(sceneID == playerInventoryID){ //player inventory has ID 0
						//add or remove from inventory
						if(e.getType() == Change.ADD){
							inventory.add(obj);
						}else{
							inventory.remove(obj.getID());
						}
					}else{
						//add or remove from game scene
						if(e.getType() == Change.ADD){
							gsdata.getScene(e.getSceneID()).addObject(obj);
						}else{
							gsdata.getScene(e.getSceneID()).removeObject(obj);
						}
					}
				}
			}
		}
		
		return str;
		
	}
	
	/*
	 * pushOutput:
	 *   displays the output text. This will be through the user interface but will be the console for now
	 */
	
	private void pushOutput(String output){
		inter.pushOutput(output);
	}
	
	public boolean isCommand(String in){
		return (in.equals("actions") || in.equals("objects") || in.equals("quit") || in.equals("inventory"));
	}
}
