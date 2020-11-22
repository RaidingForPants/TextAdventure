package support;

/*
 * Stores which object types are allowed to be used with which verb.
 * 
 */
public class VerbObjectMapping {
	
	//returns whether a type of object can be used with a type of verb
	public static boolean validObject(Verb v, ObjectType o){
		switch(v){
		case ATTACK:
			return (o == ObjectType.MONSTER || o == ObjectType.PERSON);
		case CAST:
			return (o == ObjectType.MAGIC);
		case EQUIP:
			return (o == ObjectType.ARMOR || o == ObjectType.WEAPON);
		case LOOK:
			return (o == ObjectType.ITEM || o == ObjectType.ARMOR || o == ObjectType.DECORATION || o == ObjectType.LOCATION || o == ObjectType.WEAPON || o == ObjectType.NOTHING || o == ObjectType.MONSTER || o == ObjectType.OBJECT || o == ObjectType.PERSON);
		case MOVE:
			return (o == ObjectType.LOCATION);
		case TALK:
			return (o == ObjectType.MONSTER || o == ObjectType.PERSON);
		case TAKE:
			return (o == ObjectType.ITEM || o == ObjectType.ARMOR || o == ObjectType.WEAPON);
		case USE:
			return (o == ObjectType.ITEM || o == ObjectType.ARMOR || o == ObjectType.WEAPON || o == ObjectType.MAGIC || o == ObjectType.OBJECT);
		case INVALID:
			return false;
		default:
			return false;
		}
	}
}
