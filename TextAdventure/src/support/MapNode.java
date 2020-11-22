package support;

/*
 * Linked list node for the key-value map
 * 
 */
public class MapNode<K, V>{
	private V v;
	private K name;
	MapNode<K, V> next;
	public MapNode(K key, V value){
		v = value;
		name = key;
		next = null;
	}
	
	public V get(K key){
		return (name.equals(key))? v : null;
	}
	
	public void setNext(MapNode<K, V> newNode){
		next = newNode;
	}
	
	public MapNode<K, V> getNext(){
		return next;
	}
}
