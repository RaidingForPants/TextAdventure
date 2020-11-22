package data_structure;

import support.Change;

public class SceneEdit {
	Change type;
	long sceneID;
	long objectID;
	public SceneEdit(Change c, long sceneID, long objectID){
		this.sceneID = sceneID;
		this.objectID = objectID;
		type = c;
	}
	
	public Change getType(){
		return type;
	}
	
	public long getSceneID(){
		return sceneID;
	}
	
	public long getObjectID(){
		return objectID;
	}
}
