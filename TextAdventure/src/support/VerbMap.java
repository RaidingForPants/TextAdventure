package support;

import java.util.ArrayList;

import data_structure.GameVerb;
/*
 * Stores a mapping of String to GameVerb
 * 
 */
public class VerbMap{

	MapList<String, GameVerb> map;
	ArrayList<String> keys;
	int length;
	
	public VerbMap(){
		map = new MapList<String, GameVerb>();
		keys = new ArrayList<String>();
		length = 0;
	}
	
	public void clear() {
		// TODO Auto-generated method stub
		map = new MapList<String, GameVerb>();
	}

	public boolean containsKey(String key) {
		// TODO Auto-generated method stub
		return map.get(key) == null;
	}

	public GameVerb get(String key) {
		// TODO Auto-generated method stub
		return map.get(key);
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return length <= 0;
	}

	public void put(String key, GameVerb value) {
		// TODO Auto-generated method stub
		map.add(key, value);
		length++;
		keys.add(key);
	}

	public GameVerb remove(String key) {
		// TODO Auto-generated method stub
		GameVerb v = map.remove(key);
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
			String s2 = map.get(key).getType().toString();
			if(!s.contains(s2)){
				s += s2 + "\n";
			}
		}
		if(s.length() > 0)
			s = s.substring(0, s.length()-1);
		return s;
	}

}
