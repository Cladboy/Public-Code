
/**
 * The class contains the Divide and Conquer-based Algorithms we are using. 
 */
public class DivideAndConquerAlgorithms {

	//----------------------------------------------
	// Class constructor
	//----------------------------------------------	
	/**
	 * Constructor of the class. Do not edit it.
	 */
	public DivideAndConquerAlgorithms(){}
		
	//-------------------------------------------------------------------
	// 0. iterativeDisplayElements --> Displays all elements of a MyList 
	//-------------------------------------------------------------------	
	/**
	 * Given a concrete MyList, this iterative algorithm displays its elements by screen (if any).
	 * @param m: The MyList we want to display its elements.	  
	 */	
	public void iterativeDisplayElements(MyList<Integer> m){
		//-----------------------------
		//SET OF OPS
		//-----------------------------

		//-----------------------------
		// I. SCENARIO IDENTIFICATION
		//-----------------------------
		int scenario = 0; 
		
		//Rule 1. MyList is empty
		if (m.length() == 0) 
			scenario = 1;
		//Rule 2. MyList is non-empty
		else
			scenario = 2;

		//-----------------------------
		// II. SCENARIO IMPLEMENTATION 
		//-----------------------------
		switch(scenario){	
				
		//Rule 1. MyList is empty
		case 1: 
			//1. We print the empty message
			System.out.println("Empty MyList");
			
			break;
			
		//Rule 2. MyList is non-empty
		case 2: 
			//1. We print the initial message
			int size = m.length();
			System.out.println("MyList Contains the following " + size + " items: ");
			
			//2. We traverse the items
			for (int i = 0; i < size; i++)
				System.out.println("Item " + i + ": " + m.getElement(i));
			
			break;
	
		}
		
	}

	//-------------------------------------------------------------------
	// 1. maxInt --> Computes the maximum item of MyList 
	//-------------------------------------------------------------------	
	/**
	 * The function computes the maximum item of m (-1 if m is empty). 
	 * @param m: The MyList we want to compute its maximum item.
	 * @return: The maximum item of MyList	  
	 */	
	public int maxInt(MyList<Integer> m){

			 int toReturn;
			 int removed;
			 int removedPos;

			 if(m.length() == 1){
				 toReturn = m.getElement(0);
			 }
			 
			 else if(m.length() == 0){
				toReturn = -1;
			 }

			 else {
				 if(m.getElement(0) > m.getElement(1)){
					 removed = m.getElement(1);
					 removedPos = 1;
					 m.removeElement(1);
				 }
				 else{
					 removed = m.getElement(0);
					 removedPos = 0;
					 m.removeElement(0);
				 }
				 toReturn = maxInt(m);
				 m.addElement(removedPos, removed);
			 }

			 return toReturn;
	}

	//-------------------------------------------------------------------
	// 2. isReverse --> Computes if MyList is sorted in decreasing order 
	//-------------------------------------------------------------------	
	/**
	 * The function computes whether m is sorted in decreasing order or not.  
	 * @param m: The MyList we want to check.
	 * @return: Whether m is sorted in decreasing order or not.  
	 */	
	public boolean isReverse(MyList<Integer> m){
			 boolean toReturn = false;
			 int removed;

			 if(m.length() == 1 || m.length() == 0){
				 toReturn = true;
			 }
			 else{
				 if(m.getElement(0) > m.getElement(1)){
					 removed = m.getElement(0);
					 m.removeElement(0);
					 toReturn = isReverse(m);
					 m.addElement(0, removed);
				 }
			 }

			 return toReturn;

			 /*
				SOLVED THE WRONG PROBLEM ;_;

			 boolean palindrome = false;
			 int size = m.length();
			 if(size == 0 || size == 1){
				palindrome = true;
			 }
			 else{
				 if(m.getElement(0) == m.getElement(size-1)){
					m.removeElement(size - 1);
					 m.removeElement(0);
					 palindrome = isReverse(m);
				 }
			 }
			 return palindrome;
			 */
	}

	//-------------------------------------------------------------------
	// 3. getNumAppearances --> Computes the amount of times that integer appears in MyList  
	//-------------------------------------------------------------------	
	/**
	 * The function computes the amount of times that the integer n appears in m.   
	 * @param m: The MyList we want to use.
	 * @param n: The number we want to compute its appearances for.
	 * @return: The amount of appearances of n into m  
	 */	
	public int getNumAppearances(MyList<Integer> m, int n){
		int appears = 0;
		int removed;
		
		if(m.length() >= 1){
			if(m.getElement(0) == n){
				removed = m.getElement(0);
				m.removeElement(0);
				appears = 1 + getNumAppearances(m, n);
				m.addElement(0, removed);
			}
			else{
				removed = m.getElement(0);
				m.removeElement(0);
				appears += getNumAppearances(m, n);
				m.addElement(0, removed);
			}
		}
		
		return appears;
	}
	
	//-------------------------------------------------------------------
	// 4. power --> Computes the m-est power of n
	//-------------------------------------------------------------------	
	/**
	 * The function computes n to the power of m.
	 * @param n: The base number.
	 * @param m: The power of n we want to compute
	 * @return: n to the power of m.  
	 */	

	public int power(int n, int m){
		 int toReturn = 0;
		 if(m > 1){
			 toReturn = n * power(n, m - 1);
		 }
		 else if(m == 1){
			 toReturn = n;
		 }
		 return toReturn;
	}
	
	//-------------------------------------------------------------------
	// 5. lucas --> Computes the n-est term of the Lucas series
	//-------------------------------------------------------------------	
	/**
	 * The function computes the n-est term of the Lucas series
	 * @param n: The n-est term of the series we want to compute
	 * @return: The term being computed 
	 */	
	public int lucas(int n){
		int toReturn = 2;
		if(n > 1){
			toReturn = lucas(n-1) + lucas(n-2);
		}
		else if(n == 1){
			toReturn = 1;
		}

		return toReturn;
	}

	//-------------------------------------------------------------------
	// 6. drawImage --> Prints a pattern of a given length
	//-------------------------------------------------------------------	
	/**
	 * The function prints prints a pattern of a given length.
	 * *
	 * **
	 * ***
	 * ... 
	 * @param n: The length of the desired pattern
	 */	
	public void drawImage(int n){
		String toPrint = "";
		if(n > 1){
			drawImage(n-1);
		}
		for(int i = n; i >= 1; i--){
			toPrint = toPrint + "*";
		}
		System.out.println(toPrint);
	}
		
}
