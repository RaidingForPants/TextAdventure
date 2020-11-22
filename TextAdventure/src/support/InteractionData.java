package support;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import data_structure.Interaction;
import data_structure.SimpleUserInput;
import data_structure.UserInput;
/*
 * Loads the interaction data from the JSON file
 * 
 */
public class InteractionData {
	MapList<SimpleUserInput, Interaction> interactions;
	
	public InteractionData(String filePath) throws FileNotFoundException, IOException, ParseException, NullPointerException{
		interactions = new MapList<SimpleUserInput, Interaction>();
		loadFromJSON(filePath);
	}
	
	public void loadFromJSON(String filename) throws FileNotFoundException, IOException, ParseException, NullPointerException{
		java.lang.Object obj = new JSONParser().parse(new FileReader(filename));
		JSONObject jo = (JSONObject) obj;
		JSONArray interactionArr = (JSONArray) jo.get("interactions");
		// for each interaction
		for(java.lang.Object interaction : interactionArr){
			
			//cast to JSONObject
			JSONObject JSONinter = (JSONObject) interaction;
			
			//get all the interaction properties
			long ID1 = (long) JSONinter.get("objectID1");
			long ID2 = (long) JSONinter.get("objectID2");
			String description = (String) JSONinter.get("description");
			String vType = (String) JSONinter.get("verbType");
			Verb v = Verb.INVALID;
			for(Verb vt : Verb.values()){
				if(vt.toString().toLowerCase().equals(vType)){
					v = vt;
					break;
				}
			}
			
			//create the new interaction
			Interaction inter = new Interaction(description);
			inter.setAction(v);
			
			//get the array of new objects that will be added
			JSONArray changes = (JSONArray) JSONinter.get("sceneChanges");
			for(Object change : changes){
				//convert to JSONObject
				JSONObject sceneChange = (JSONObject) change;
				long sceneID = (long) sceneChange.get("sceneID");
				long objectID = (long) sceneChange.get("objectID");
				Change type = (((String) sceneChange.get("type")).equals("add"))? Change.ADD : Change.REMOVE;
				
				inter.addEdit(type, sceneID, objectID);
			}
			
			//add the interaction to the list
			interactions.add(new SimpleUserInput(v, ID1, ID2), inter);
		}
	}
	
	public String getDescription(SimpleUserInput i){
		return getInteraction(i).getDescription();
	}
	
	public Interaction getInteraction(SimpleUserInput i){
		return interactions.get(i);
	}
	
	public Interaction getInteraction(UserInput i){
		return getInteraction(new SimpleUserInput(i.getVerb().getType(), i.getObject().getID(), i.getIndirectObject().getID()));
	}
	

}
