
/**
* ADT MyStack: Private Part<br>. 
* The class implements all the operations available in MyStack<br>
*/
public class MyDynamicStack implements MyStack {

	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------
		private MyNode head;
		
	//-------------------------------------------------------------------
	// Basic Operation --> Check if myStack is empty: myCreateEmpty
	//-------------------------------------------------------------------	

	public MyDynamicStack( ){
	//TO-COMPLETE
		this.head = null;
		
	}

	//-------------------------------------------------------------------
	// Basic Operation --> Check if MyStack is empty: isEmpty
	//-------------------------------------------------------------------	

	public boolean isEmpty(){
		if (this.head == null){
			return true;
		}
		else{
			return false;
		}
	}
	
	//-------------------------------------------------------------------
	// Basic Operation (Partial) --> Get first element from the top of MyStack and removes it: pop
	//-------------------------------------------------------------------
	
	public int pop(){
		
		int info = -1;
		
		if (this.isEmpty() == false) {

			info = this.head.getInfo();
			this.head = this.head.getNext();			
		}
		else{
			System.out.println("This Stack is empty");
		}
		return info;
	}

	//-------------------------------------------------------------------
	// Basic Operation (Partial) --> Add element to the top of MyStack: push
	//-------------------------------------------------------------------

	public void push(int element){
		
		MyNode newNode = new MyNode(element, this.head);
		this.head = newNode;

	}
	
	//-------------------------------------------------------------------
	// Basic Operation (Partial) --> prints all the elements from MyStack: print
	//-------------------------------------------------------------------
		
	public void print(){
		String toPrint = " m : [ ";
		MyNode currentNode = this.head;
		
		//Checks to see if list is empty
		if (this.head == null) {
			System.out.println("m = []");
		}
		
		//Runs if list has items
		else {
			while(currentNode != null) {
				toPrint = toPrint + Integer.toString(currentNode.getInfo());
				if (currentNode.getNext() != null) {
					toPrint = toPrint + ",";
				}
				currentNode = currentNode.getNext();
			}
			System.out.println(toPrint + "]");
		}
	
	}
	
}
