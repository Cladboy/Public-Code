
public class MyDoubleDynamicStack<T> implements MyStack<T> {

	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------

	// TO-COMPLETE
	private MyDoubleLinkedNode<T> head;
	private MyDoubleLinkedNode<T> tail;

	//-------------------------------------------------------------------
	// Basic Operation --> Check if myStack is empty: myCreateEmpty
	//-------------------------------------------------------------------	
	public MyDoubleDynamicStack(){
	this.head = null;
	this.tail = null;
	}

	//-------------------------------------------------------------------
	// Basic Operation --> Check if myStack is empty: isEmpty
	//-------------------------------------------------------------------	
	public boolean isEmpty(){
		
		if(this.head == null) {
			return true;
		}
		else {
			return false;
		}

	}
	
	//-------------------------------------------------------------------
	// Basic Operation (Partial) --> Get first element from front of MyStack: first
	//-------------------------------------------------------------------
	public T first(){
		if (isEmpty() == false) {
			return (this.head).getInfo();
		}
		else {
			return (T)"The list is empty";
		}
	}

	//-------------------------------------------------------------------
	// Basic Operation --> Add element to back of MyStack: addByFirst 
	//-------------------------------------------------------------------
	public void addByFirst(T element){
    // TO-COMPLETE
		MyDoubleLinkedNode<T> newNode = new MyDoubleLinkedNode<T>(null, element,this.head);
		if (this.head == null) {
			this.head = newNode;
			this.tail = newNode;
		}
		else {
			(this.head).setLeft(newNode);
			//newNode.setRight(this.head);
			this.head = newNode;
		}
		
	}
	

	//-------------------------------------------------------------------
	// Basic Operation (Partial) --> Remove element from front of MyStack: removeByFirst 
	//-------------------------------------------------------------------	
	public void removeByFirst(){
    // TO-COMPLETE
		if (this.head != null) {
			if (this.head != this.tail) {
				MyDoubleLinkedNode<T> temp = (this.head).getRight();
				temp.setLeft(null);
				this.head = (this.head).getRight();
			}
			else {
				this.head = null;
				this.tail = null;
			}
		}
		else {
			System.out.println("Can't remove, this stack is empty.");
		}
	}

	//-------------------------------------------------------------------
	// Basic Operation (Partial) --> Get first element from front of MyStack: last
	//-------------------------------------------------------------------
	public T last(){
    // TO-COMPLETE
		if (isEmpty() == false) {
			return (this.tail).getInfo();
		}
		else {
			return (T)"The list is empty";
		}
	}

	//-------------------------------------------------------------------
	// Basic Operation --> Add element to back of MyStack: addByLast 
	//-------------------------------------------------------------------
	public void addByLast(T element){
		MyDoubleLinkedNode<T> newNode = new MyDoubleLinkedNode<T>(this.tail, element,null);
		if (this.tail == null) {
			this.head = newNode;
			this.tail = newNode;
		}
		else {
			(this.tail).setRight(newNode);
			this.tail = newNode;
		}
	}
	
	//-------------------------------------------------------------------
	// Basic Operation (Partial) --> Remove element from front of MyStack: removeByFirst 
	//-------------------------------------------------------------------	
	public void removeByLast(){
    // TO-COMPLETE
		if (this.tail != null) {
			if(this.tail != this.head) {
				MyDoubleLinkedNode<T> temp = (this.tail).getLeft();
				temp.setRight(null);
				this.tail = (this.tail).getLeft();
			}
			else {
				this.tail = null;
				this.head = null;
			}
		}
		else {
			System.out.println("Can't remove, this stack is empty.");
		}
	}
	
}
