package Tree;

public class AVLTree extends BSTree{
	private static class AVLNode extends Node{
		protected int height;
		
		AVLNode(Object e,Node l,Node r){
			super(e,l,r);
		}
		
		void setHeight(){
			this.height = 1 + Math.max(height(this.left),height(this.right));
		}
		
		int balanceValue(){
			return height(this.right) - height(this.left);
		}
		
		private static int height(Node n){
			return (n == null ? -1 : ((AVLNode) n).height );
		}
	}
	
	protected Node add(Object e,Node r){
		if(r == null){
			r = new AVLNode(e,null,null);
			++size;
		}else{
			r = super.add(e,r);
			r = this.rebalance(r);
		}
		return r;
	}
	
	protected Node remove(Node r,Object e){
		r = super.remove(r,e);
		r = this.rebalance(r);
		return r;
	}
	
	protected Node rotateLeftChild(Node r){
		r = super.rotateLeftChild(r);
		((AVLNode)r.right).setHeight();
		((AVLNode)r).setHeight();
		return r;
	}
	
	protected Node rotateRightChild(Node r){
		r = super.rotateRightChild(r);
		((AVLNode)r.left).setHeight();
		((AVLNode)r).setHeight();
		return r;
	}
	
	private Node rebalance(Node r){
		if(r == null) return r;
		int balance = ((AVLNode)r).balanceValue();
		// too much left incline!
		if(balance == -2){
			if(((AVLNode)r.left).balanceValue() == 1){
				r.left = this.rotateRightChild(r.left);
			}
			r = rotateLeftChild(r);
		}
		// too much right incline!
		else if(balance == 2){
			if(((AVLNode)r.right).balanceValue() == -1){
				r.right = this.rotateLeftChild(r.right);
			}
			r = rotateRightChild(r);
		}
		((AVLNode)r).setHeight();
		return r;
	}
}







