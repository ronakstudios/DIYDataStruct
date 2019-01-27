
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedList<T>{
	
	public static void main(String[]args) {
		Node<Integer> front = new Node<Integer>(3,4,5,34,5);
		front.addAfter(2, 1);
		front.addAfter(24, 1);
		front.append(new Node(21));
		System.out.println(front);
		System.out.println(front.get(3));
		System.out.println(front.size());
		front.truncate();
		front.remove(1);
		front.addBefore(4, 1);
		System.out.println(front);
		front.reverse();
		System.out.println(front);
		
	}
	
	private static class Node<T>{
		private T data;
		private Node<T> next;
		
		public Node(T data) {
			this.data = data;
		}
		
		public Node(T data, Node<T> next) {
			this.data = data;
			this.next = next;
		}
		
		public Node(T...data) {
			this.data = data[0];
			if(data.length!=1) {
			this.next = new Node<T>(data[1]);
			if(data.length>2) {
			Node<T> nextNode = next;
			for(int i = 2; i<data.length;i++) {
				nextNode.next=new Node<T>(data[i]);
				nextNode=nextNode.next;
			}
			}
			}
		}
		
		public Node(Node<T>...nodes){
			this.data = nodes[0].data;
			if(nodes.length!=1) {
			this.next = nodes[1];
			if(nodes.length>2) {
			Node<T> nextNode = next;
			for(int i = 2; i<nodes.length;i++) {
				//can't do nextNode = nodes[i] and then
				//nextNode = nextNode.next because that 
				//overwrites with null!
				nextNode.next=nodes[i];
				nextNode=nextNode.next;
			}
			}
			}
		}
		
		public int size() {
			int i = 0;
			Node<T> n = this;
			while(n!=null) {
				n=n.next;
				i++;
			}
			return i;
		}
		/**
		 * @param index from 0 to len-1
		 */
		public Node<T> get(int index){
			if(index>=size()||index<0)
				throw new IndexOutOfBoundsException();
			Node<T> n = this;
			for(int i = 0; i!= index; i++) 
				if(index==i+1)
					return n.next;
				else
					n=n.next;
			return this;
			}
		public void append(Node<T> a) {
			get(this.size()-1).next=a;
		}
		/**
		 * @param index from 0 to len-1
		 */
		public Node<T> remove(int index){
			if(index>=size()||index<0)
				throw new IndexOutOfBoundsException();
			
			//size 1, index 0, index is last node
			if(index==0) {
				try {
					Node<T> removedNode = new Node(this.data, null);
					this.data = this.next.data;
					this.next = this.next.next;
					return removedNode;
				}catch (NullPointerException a) {
					this.data = null;
					return null;
				}
			}else if(index==size()-1){
				Node<T> removedNode  = get(index);
				removedNode.next=null;
				get(index-1).next=null;
				return removedNode;
			}else {
				
				
				Node<T> n = this;
		
				for(int i = 0; i<size();i++) {
					if(i==index-1) {
						Node<T> removedNode= n.next;
						n.next=n.next.next;
						removedNode.next=null;
						return removedNode;
					}
					n = n.next;
				}
				
				
				
			}
			return null;
			
		}
		public Node<T> truncate(){
			return remove(size()-1);
		}
		
		/**
		 * @param index from 0 to len-1
		 */
		public void addAfter(T data, int index) {
			get(index).next=new Node(data,get(index).next);
		}
		
		/**
		 * @param index from 0 to len-1
		 */
		public void addBefore(T data,int index) {
			if(index==0) {
				this.next=new Node(this.data, this.next);
				this.data = data;
			}else {
				addAfter(data,--index);
			}
		}
		
		@Override
		public String toString() {
			String str="";
			for(int i = 0; i<size();i++) {
				str+=(get(i).data)+((i==size()-1)?"":",");
			}
			return str;
		}
		
		public void reverse() {
			Node<T> h = new Node<T>(get(0));
			for (int i = 1; i <size(); i++) {
				h.addBefore(get(i).data, 0);
			}
			this.data=h.get(0).data;
			this.next=h.get(0).next;
		}
		}
	
	

}
