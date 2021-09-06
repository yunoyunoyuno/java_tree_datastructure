package Tree;
import java.util.Arrays;
import javax.swing.JFrame;

public class BSTree extends BinaryTree{
	
	protected int size;
	
	public BSTree(){};
	
	public int size(){
		return this.size;
	}
	
	public boolean isEmpty(){
		return this.size == 0;
	}
	
	protected int compare(Object a, Object b){
		return ((Comparable) a).compareTo(b);
	}
	
	public Object get(Object e){
		Node n = this.getNode(this.root,e);
		return n == null ?  null : n.element;
	}
	
	protected Node getNode(Node r,Object e){
		if(r == null) return null;
		if(this.compare(r.element,e) < 0) return this.getNode(r.right,e);
		else if(this.compare(r.element,e) > 0) return this.getNode(r.left,e);
		return r;
	}
	
	public Object getMax(){
		Node r = this.root;
		if(r == null) return null;
		while(r.right != null){
			r = r.right;
		}
		return r.element;
	}
	
	public Object getMin(){
		Node r = this.root;
		if(r == null) return null;
		while(r.left != null){
			r = r.left;
		}
		
		return r.element;
	}
	
	/*public void add(Object e){
		if(e == null) throw new IllegalArgumentException();
		Node newNode = new Node(e,null,null);
		if(this.root == null) this.root = newNode; 
		else{
			Node p = null, r = root;
			while(r != null){
				if(this.compare(e,r.element) < 0) {p = r; r = r.left;}
				else if(this.compare(e,r.element) > 0){ p = r; r = r.right;}
				else return;
			}
			if(this.compare(e,p.element) > 0){
				p.right = newNode;
			}else{
				p.left = newNode;
			}
		}
		this.size++;
	}*/
	
	public void add(Object e){
		this.root = this.add(e,this.root);
	}
	
	public void remove(Object e){
		this.root = this.remove(this.root,e);
	}
	
	public static void treeSort(final double[] arr){
		BSTree b = new BSTree();
		for(int i = arr.length -1 ; i >= 0 ; i--){
			int j = (int)(i * Math.random());
			Object t = arr[j]; arr[j] = arr[i]; arr[i] = (double)(t);
			b.add(arr[i]);
		}

		
		b.inOrder(new Visitor(){
			int k = 0;
			public void visit(Object element){
				arr[k++] = (double) element;
			}
		});
	}
	
	protected Node remove(Node r,Object e){
		if(r == null) return null;
		else{
			if(this.compare(e,r.element) < 0) r.left = this.remove(r.left,e);
			else if(this.compare(e,r.element) > 0) r.right = this.remove(r.right,e);
			else{
				if(r.left == null || r.right == null){
					r = (r.left == null ? r.right : r.left);
					this.size--;
				}else{
					Node m = r.right;
					while(m.left != null) m = m.left;
					r.element = m.element;
					r.right = this.remove(r.right,m.element);	
				}
			}
		}
		return r;
	}
	
	protected Node add(Object e,Node r){
		if(e == null) throw new IllegalArgumentException("Null is not allowed !");
		if(r == null) {r = new Node(e,null,null);this.size++;}
		else{
			if(this.compare(e,r.element) < 0) r.left = add(e,r.left);
			else if(this.compare(e,r.element) > 0) r.right = add(e,r.right);
		}
		return r;
	}
	
	protected Node rotateLeftChild(Node r){
		Node newNode = r.left;
		r.left = newNode.right;
		newNode.right = r;
		return newNode;
	}
	
	protected Node rotateRightChild(Node r){
		Node newNode = r.right;
		r.right = newNode.left;
		newNode.left = r;
		return newNode;
	}
	
	
	
	
	
	
}