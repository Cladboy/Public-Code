
/**
* ADT MyStack: Private Part<br>. 
* The class implements all the operations available in MyStack<br>
*/
public class MyStaticStack implements MyStack {

	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------
	
	private int items[];
	private int numItems;
	private int maxItems;

	//-------------------------------------------------------------------
	// Basic Operation --> Check if myStack is empty: myCreateEmpty
	//-------------------------------------------------------------------	

	public MyStaticStack(int m){
		this.maxItems = m;
		this.items = new int[m];
		this.numItems = 0;
	}

	//-------------------------------------------------------------------
	// Basic Operation --> Check if MyStack is empty: isEmpty
	//-------------------------------------------------------------------	

	public boolean isEmpty(){
		if (this.numItems>0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	//-------------------------------------------------------------------
	// Basic Operation (Partial) --> Get first element from the top of MyStack and removes it: pop
	//-------------------------------------------------------------------
	
	public int pop(){
		int toPop = -1;
		if (this.isEmpty()==false) {
			//System.out.print("popNum: " + this.numItems);
			toPop = this.items[this.numItems-1];
			this.numItems = this.numItems - 1;
		}
		else {
			System.out.println("No items in stack to pop.");
		}
		return toPop;
	}

	//-------------------------------------------------------------------
	// Basic Operation (Partial) --> Add element to the top of MyStack: push
	//-------------------------------------------------------------------

	public void push(int element){
		if(this.numItems<=this.maxItems-1) {
			this.items[numItems] = element;
			this.numItems = this.numItems + 1;
		}
		else {
			System.out.println("Item can not be added to stack as it is full");
		}
	}
	
	//-------------------------------------------------------------------
	// Basic Operation (Partial) --> prints all the elements from MyStack: print
	//-------------------------------------------------------------------
		
	public void print(){
		String stackString = "m : [ ";
		for (int i = this.numItems-1; i>=0; i--) {
			System.out.println("i: " + i);
			stackString = stackString + String.valueOf(this.items[i]) + ", ";
		}
		stackString = stackString + " ]";
		System.out.println(stackString);
	}
	
}
