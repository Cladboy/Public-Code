

/*
 *  Implementation of the interface Graph with adjacency matrix.
*/


import java.util.LinkedList;

public class GraphAdjMatrix implements Graph{

	// ATTRIBUTES: 
    //TO-DO
    int[][] matrix;
    boolean isDirected;

 
    
    // CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no edges
    public GraphAdjMatrix(int V, boolean directed) {
    //TO-DO
        this.isDirected = directed;
        matrix = new int[V][V];
        for(int x = 0; x < V; x++){
            for(int y = 0; y < V; y++){
                matrix[x][y] = -1;
            }
        }
    }


    // 1. IMPLEMENTATION METHOD numVerts: 
    public int numVerts() {
        return matrix.length;
    //TO-DO

    }
    
   
    // 2. IMPLEMENTATION METHOD numEdges:
    public int numEdges() { 
        //TO-DO
        int res = 0;
        int cycles = 0;
        int v = matrix.length;

        for(int x = 0; x < v; x++){
            for(int y = 0; y < v; y++){
                if(matrix[y][x] >= 0){
                    if(y==x){
                        cycles++;
                    }
                    else {
                        res++;
                    }
                }
            }
        }
        if(!isDirected){
            res = res/2;
        }
        return res + cycles;
    }


   //  3. IMPLEMENTATION METHOD addEdge:
    public void addEdge(int v1, int v2, int w) {
        //TO-DO
        try {
            matrix[v1][v2] = w;
            if (!isDirected) {
                matrix[v2][v1] = w;
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Vertext can't be added");
        }
    }
    
   // 4. IMPLEMENTATION METHOD removeEdge:
   public void removeEdge (int v1, int v2) {
    //TO-DO
       try {
           matrix[v1][v2] = -1;
           if (!isDirected) {
               matrix[v2][v1] = -1;
           }
       }catch (ArrayIndexOutOfBoundsException e){
           System.out.println("Vertex can't be removed");
       }
   }

    // 5. IMPLEMENTATION METHOD hasEdge:
    public boolean hasEdge(int v1, int v2) {
        //TO-DO
        if(matrix[v1][v2] >= 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    // 6. IMPLEMENTATION METHOD getWeightEdge:
	public int getWeightEdge(int v1, int v2) {
		//TO-DO
        return matrix[v1][v2];
	}

    
	// 7. IMPLEMENTATION METHOD getNeighbors:
	public LinkedList getNeighbors(int v)
	{
    	//TO-DO
        LinkedList<Integer> res = new LinkedList<Integer>();
        for(int i = 0; i < matrix.length; i++){
            if(matrix[v][i] >=0){
                res.add(i);
            }
        }
        return res;
	}
   	
	// 8. IMPLEMENTATION METHOD getDegree:
	public int getDegree(int v) 
	{
	   //TO-DO
        int res = 0;
        for(int i = 0; i < matrix.length; i++){
            if(matrix[v][i] >= 0){
                res++;
            }
        }
        if(isDirected){
            for(int i = 0; i < matrix.length; i++){
                if(matrix[i][v] >= 0){
                    res++;
                }
            }
        }
        return res;
	}
	

	// 9. IMPLEMENTATION METHOD toString:
   	public String toString() {
    //TO-DO
        String res = String.format("%7d|", 0);

        ///Print X axis key
        for(int x = 1; x < matrix.length; x++){
            res += String.format("|%3d|", x);
        }
        res+= "\n";

        //Creates seperator between x axis and data
        for(int i = 0; i < matrix.length; i++){
            res += "------";
        }
        res += "\n";

        //Prints out data
        for(int y = 0; y < matrix.length; y++){
            //Prints out Y co-ordinate
            res += String.format("%-3d", y);
            for(int x = 0; x < matrix.length; x++){
                res += (String.format("|%3d|", matrix[y][x]));
            }
            res += ("\n");
        }
        return res;
    }    
}