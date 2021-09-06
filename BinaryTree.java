package Tree;

import java.awt.*;


public class BinaryTree{
	protected static class Node{
		public Object element;
		public Node left;
		public Node right;
		public Node(Object e,Node l,Node r){
			this.element = e;
			this.left = l;
			this.right = r;
		}
		public boolean isLeaf(){
			return this.left==null && this.right==null;
		}
	}
	
	protected Node root;
	protected BinaryTree(){};
	
	protected int numNodes(Node r){
		if(r == null) return 0;
		return 1 + numNodes(r.left) + numNodes(r.right);
	}
	
	protected int numLeaves(Node r){
		if(r == null) return 0;
		if(r.isLeaf()) return 1;
		return numLeaves(r.left) + numLeaves(r.right);
	}
	
	protected int height(Node r){
		if(r == null) return -1;
		return 1+ Math.max(height(r.left),height(r.right));
	}
	
	protected Node copy(Node r){
		if(r == null) return null;
		return new Node(r.element,copy(r.left),copy(r.right));
	}
	
	/*public Object[] toArray(){
		Object[] a = new Object[this.numNodes(this.root)];
		this.toArray(this.root,a,0);
		return a;
	}*/
	
	/* private int toArray(Node r,Object[] a, int k){
		if(r == null) return k;
		a[k++] = r.element;
		k = this.toArray(r.left,a,k);
		return this.toArray(r.right,a,k);
	}*/
	
	public Object[] toArray(){
		final Object[] a = new Object[this.numNodes(this.root)];
		
		Visitor v = new Visitor(){
			int k = 0;
			public void visit(Object element){
				a[k++] = element;
			}
		};
		
		this.preOrder(this.root,v);
		return a;
		
	}
	
	protected void inOrder(Node r,Visitor v){
		if(r == null || v.isDone()) return;
		this.inOrder(r.left,v);
		v.visit(r.element);
		this.inOrder(r.right,v);
	}
	
	protected void postOrder(Node r,Visitor v){
		if(r == null || v.isDone()) return;
		this.postOrder(r.left,v);
		this.postOrder(r.right,v);
		v.visit(r.element);
	}
	
	protected void preOrder(Node r,Visitor v){
		if(r == null) return;
		v.visit(r.element);
		this.preOrder(r.left,v);
		this.preOrder(r.right,v);
	}
	
	public void preOrder(Visitor v){
		this.preOrder(this.root,v);
	}
	
	public void inOrder(Visitor v){
		this.inOrder(this.root,v);
	}
	
	public void postOrder(Visitor v){
		this.postOrder(this.root,v);
	}
	
	public Canvas toCanvas(){
		return new TreeCanvas();
	}
	
	private class TreeCanvas extends Canvas{
		int ppx, ppy;
		
		// It might be override concept;
		public void paint(Graphics g){
			ppx = this.getWidth()/(2 + numNodes(root));
			ppy = this.getHeight()/(2 + height(root));
			drawTree(g,root,1,1);
		}
		
		int drawTree(Graphics g,Node r,int x0,int y0){
			int xr = x0;
			if(r != null){
				xr += numNodes(r.left);
				int lx = drawTree(g,r.left,x0,y0+1);
				int rx = drawTree(g,r.right,xr+1,y0+1);
				drawNode(g,r,xr,y0);
				if(r.left != null) drawEdge(g,xr,y0,lx,y0+1);
				if(r.right != null) drawEdge(g,xr,y0,rx,y0+1);
			}
			return xr;
		}
		
		void drawNode(Graphics g,Node r,int x,int y){
			int dy = r.isLeaf() ? 15 : 0;
			g.drawString(r.element.toString(),x*ppx,y*ppy + dy);
		}
		
		void drawEdge(Graphics g,int x1,int y1,int x2,int y2){
			g.drawLine(x1*ppx,y1*ppy,x2*ppx,y2*ppy);
		}
		
	}
}
















