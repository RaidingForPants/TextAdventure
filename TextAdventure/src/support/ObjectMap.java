package support;

import java.util.ArrayList;

import data_structure.GameObject;

/*
 * Stores a mapping of String to GameObject
 * 
 */
public class ObjectMap{

	MapList<String, GameObject> map;
	int length;
	ArrayList<String> keys;
	
	public ObjectMap(){
		map = new MapList<String, GameObject>();
		keys = new ArrayList<String>();
		length = 0;
	}
	
	public void clear() {
		// TODO Auto-generated method stub
		map = new MapList<String, GameObject>();
	}

	public boolean containsKey(String key) {
		// TODO Auto-generated method stub
		return map.get(key) == null;
	}

	public GameObject get(String key) {
		// TODO Auto-generated method stub
		return map.get(key);
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return length <= 0;
	}

	public void put(String key, GameObject value) {
		// TODO Auto-generated method stub
		map.add(key, value);
		length++;
		keys.add(key);
	}

	public GameObject remove(String key) {
		// TODO Auto-generated method stub
		GameObject v = map.remove(key);
		if(v != null) length--;
		keys.remove(key);
		return v;
	}

	public int size() {
		// TODO Auto-generated method stub
		return length;
	}
	
	public String print(){
		String s = "";
		for(String key : keys){
			s += key + "\n";
		}
		if(s.length() > 0)
			s = s.substring(0, s.length()-1);
		return s;
	}

}
