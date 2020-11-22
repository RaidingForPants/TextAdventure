package data_structure;

import support.Verb;

/*
 * Data structure for holding a user input in terms of Verb and object IDs rather than Verb and GameObject objects
 * 
 */

public class SimpleUserInput {
	long ID1;
	long ID2;
	Verb v;
	public SimpleUserInput(Verb action, long objectID, long indirectObjectID){
		ID1 = objectID;
		ID2 = indirectObjectID;
		v = action;
	}
	
	public Verb getVerb(){
		return v;
	}
	
	public long getObjectID(){
		return ID1;
	}
	
	public long getIndirectObjectID(){
		return ID2;
	}
	
	@Override
	public boolean equals(Object o){
		if (o == null) return false;
	    if (o == this) return true;
	    if (!(o instanceof SimpleUserInput)) return false;
	    SimpleUserInput i = (SimpleUserInput) o;
	    if(getObjectID() == i.getObjectID() && getIndirectObjectID() == i.getIndirectObjectID() && getVerb() == i.getVerb()){
	    	return true;
	    }
	    return false;
	}
}
