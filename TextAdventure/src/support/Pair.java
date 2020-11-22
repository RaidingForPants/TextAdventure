package support;

/*
 * Stores a pair of values for key purposes. The order of the pair does not matter. That is, <x, y> = <y, x>.
 * 
 */
public class Pair<T> {
	T x;
	T y;
	public Pair(T a, T b){
		x = a;
		y = b;
	}
	
	public T x(){
		return x;
	}
	
	public T y(){
		return y;
	}
	
	@Override
	public boolean equals(java.lang.Object other){
		if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Pair<?>))return false;
	    Pair<?> otherPair = (Pair<?>) other;
		if(x.equals(otherPair.x())){
			return y.equals(otherPair.y());
		}else if(x.equals(otherPair.y())){
			return y.equals(otherPair.x());
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		int result = 265;
		result *= x.hashCode();
		result *= y.hashCode();
		return result;
	}
}
