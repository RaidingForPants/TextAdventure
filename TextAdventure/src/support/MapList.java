package support;

/*
 * Key-Value map that is implemented using a linked list
 * 
 */
public class MapList<K, V> {
	MapNode<K, V> head;
	MapNode<K, V> tail;
	
	public MapList(){
		head = null;
		tail = null;
	}
	
	public void add(K key, V value){
		MapNode<K, V> newNode = new MapNode<K, V>(key, value);
		if(head == null){
			head = newNode;
		}
		if(tail != null){
			tail.setNext(newNode);
		}
		tail = newNode;
	}
	
	public V remove(K key){
		MapNode<K, V> tempNode = head;
		MapNode<K, V> prevNode = null;
		while(tempNode != null){
			if(tempNode.get(key) != null){
				//found node for removal
				if(prevNode == null){ //removing head node
					head = tempNode.getNext();
					if(head == null){
						tail = null;
					}
				}else{
					prevNode.setNext(tempNode.getNext());
					if(tail == tempNode){
						tail = prevNode;
					}
				}
				return tempNode.get(key);
			}
			//move to next node
			prevNode = tempNode;
			tempNode = tempNode.getNext();
		}
		return null;
	}
	
	public V get(K key){
		MapNode<K, V> tempNode = head;
		while(tempNode != null){
			if(tempNode.get(key) != null){
				return tempNode.get(key);
			}
			tempNode = tempNode.getNext();
		}
		return null;
	}
}
