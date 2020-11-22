package support;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import data_structure.GameObject;
import data_structure.GameScene;

/*
 * Loads the scene data from a JSON file
 * 
 */

public class GameSceneData {
	
	MapList<Long, GameScene> data;
	
	public GameSceneData(String inputFile) throws FileNotFoundException, IOException, ParseException, NullPointerException{
		data = new MapList<Long, GameScene>();
		loadFromJSON(inputFile);
	}
	
	public void loadFromJSON(String filename) throws FileNotFoundException, IOException, ParseException, NullPointerException{
		java.lang.Object obj = new JSONParser().parse(new FileReader(filename));
		JSONObject jo = (JSONObject) obj;
		JSONArray scenes = (JSONArray) jo.get("scenes");
		// for each scene
		for(java.lang.Object scene : scenes){
			
			//cast to a JSONObject
			JSONObject JSONscene = (JSONObject) scene;
			
			//read the scene properties
			String sceneDescription = (String) JSONscene.get("sceneDescription");
			long sceneID = (long) JSONscene.get("sceneID");
			
			//create the GameScene
			GameScene sc = new GameScene();
			
			//set the scene description
			sc.addDescription(sceneDescription);
			
			//adding each object to the scene
			
			//get the sceneObjects array
			Long[] sceneObjects = (Long[]) ((JSONArray) JSONscene.get("sceneObjects")).toArray(new Long[0]);
			//load each object from the object file
			GameObject[] objects = ObjectLoader.loadObjectsFromFile(sceneObjects, Constants.objectFile);
			
			//add the objects to the scene
			for(GameObject go : objects){
				sc.addObject(go);
			}
				
			//adding each verb to the scene
				
			//get the sceneActions array
			JSONArray sceneActions = (JSONArray) JSONscene.get("sceneActions");
				
			for(java.lang.Object action : sceneActions){
				String actionName = (String) action;
				//loop through each listed action
				for(Verb v : Verb.values()){
					if (v.toString().toLowerCase().equals(actionName)){
						//add to the scene
						sc.addAction(actionName, v);
						break;
					}
				}
				
			}
			//add the scene to the scene data
			data.add(sceneID, sc);
		}
	}
	
	
	
	public GameScene getScene(long idx){
		return data.get(idx);
	}
}
