package Tree;

public class BSTMap implements Map{
	protected static class Entry implements Comparable{
		Object key; Object value;
		public Entry(Object k,Object v){
			this.key = k;
			this.value = v;
		}
		
		public int compareTo(Object obj){
			return ((Comparable)key).compareTo(((Entry)obj).key);
		}
	}
	
	private BSTree tree = new BSTree();
	public int size(){
		return tree.size();
	}
	
	public boolean isEmpty(){
		return tree.isEmpty();
	}
	
	public boolean containsKey(Object key){
		return tree.get(new Entry(key,null)) != null;
	}
	
	public Object get(Object key){
		Entry e = (Entry) tree.get(new Entry(key,null));
		return e == null ? null : e.value;
	}
	
	public void remove(Object key){
		tree.remove(new Entry(key,null));
	}
	
	public Object put(Object key,Object value){
		Entry newEntry = new Entry(key,value);
		Entry e = (Entry) tree.get(newEntry);
		Object oldValue;
		if(e == null){
			tree.add(newEntry);
			oldValue = null;
		}else{
			oldValue = e.value;
			e.value = value;
		}
		return oldValue;
	}
	
	
}















