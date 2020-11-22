package support;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import data_structure.GameObject;

public class ObjectLoader {
	
	
	public static GameObject loadObjectFromFile(Long ID, String filename) throws FileNotFoundException, IOException, ParseException, NullPointerException{
		java.lang.Object obj = new JSONParser().parse(new FileReader(filename));
		JSONObject jo = (JSONObject) obj;
		JSONObject go = (JSONObject) jo.get(Long.toString(ID));
		String name = ((String) go.get("objectName")).toLowerCase();
		String description = (String) go.get("objectDescription");
		String dialogue = (String) go.get("objectDialogue");
		String type = ((String) go.get("objectType")).toLowerCase();
		ObjectType o = ObjectType.NOTHING;
		for(ObjectType ot : ObjectType.values()){
			if(type.equals(ot.toString().toLowerCase())){
				o = ot;
				break;
			}
		}
		boolean consumable = (boolean) go.get("consumable");
		
		GameObject gameObj = new GameObject(name, o, ID);
		gameObj.setConsumable(consumable);
		gameObj.setDescription(description);
		gameObj.setDialogue(dialogue);
		return gameObj;
	}
	
	public static GameObject loadObjectFromFile(Long ID) throws FileNotFoundException, IOException, ParseException, NullPointerException{
		java.lang.Object obj = new JSONParser().parse(new FileReader(Constants.objectFile));
		JSONObject jo = (JSONObject) obj;
		JSONObject go = (JSONObject) jo.get(Long.toString(ID));
		String name = ((String) go.get("objectName")).toLowerCase();
		String description = (String) go.get("objectDescription");
		String dialogue = (String) go.get("objectDialogue");
		String type = ((String) go.get("objectType")).toLowerCase();
		ObjectType o = ObjectType.NOTHING;
		for(ObjectType ot : ObjectType.values()){
			if(type.equals(ot.toString().toLowerCase())){
				o = ot;
				break;
			}
		}
		boolean consumable = (boolean) go.get("consumable");
		
		GameObject gameObj = new GameObject(name, o, ID);
		gameObj.setConsumable(consumable);
		gameObj.setDescription(description);
		gameObj.setDialogue(dialogue);
		return gameObj;
	}
	
	public static GameObject[] loadObjectsFromFile(Long[] IDs, String filename) throws FileNotFoundException, IOException, ParseException{
		java.lang.Object obj = new JSONParser().parse(new FileReader(filename));
		JSONObject jo = (JSONObject) obj;
		ArrayList<GameObject> list = new ArrayList<GameObject>();
		for(long ID : IDs){
			System.out.println(ID);
			JSONObject go = (JSONObject) jo.get(Long.toString(ID));
			String name = ((String) go.get("objectName")).toLowerCase();
			String description = (String) go.get("objectDescription");
			String dialogue = (String) go.get("objectDialogue");
			String type = ((String) go.get("objectType")).toLowerCase();
			ObjectType o = ObjectType.NOTHING;
			for(ObjectType ot : ObjectType.values()){
				if(type.equals(ot.toString().toLowerCase())){
					o = ot;
					break;
				}
			}
			boolean consumable = (boolean) go.get("consumable");
			
			GameObject gameObj = new GameObject(name, o, ID);
			gameObj.setConsumable(consumable);
			gameObj.setDescription(description);
			gameObj.setDialogue(dialogue);
			list.add(gameObj);
		}
		return (GameObject[]) list.toArray(new GameObject[0]);
	}
}
