
import java.util.LinkedList;
import java.util.Iterator;

	/**
	* Graph implementation that uses Adjacency Lists to store edges. It
	* contains one linked-list for every vertex i of the graph. The list for i
	* contains one instance of VertexAdjList for every vertex that is adjacent to i.
	* For directed graphs, if there is an edge from vertex i to vertex j then there is
	* a corresponding element in the adjacency list of node i (only). For
	* undirected graphs, if there is an edge between vertex i and vertex j, then there is a
	* corresponding element in the adjacency lists of *both* vertex i and vertex j. The
	* edges are not sorted; they contain the adjacent nodes in *order* of
	* edge insertion. In other words, for a graph, the node at the head of
	* the list of some vertex i corresponds to the edge involving i that was
	* added to the graph least recently (and has not been removed, yet). 
	*/

public class GraphAdjList  implements Graph {

	// ATTRIBUTES: 
	    
	 //TO-DO

		boolean isDirected;
		LinkedList[] vList;


	 /*
	  * CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no edges.
	  * It initializes the array of adjacency edges so that each list is empty.
	  */

	 public GraphAdjList(int v, boolean directed) {
	     
	    //TO-DO
		 this.isDirected = directed;
		 vList = new LinkedList[v];
		 for(int i = 0; i < v; i++){
		 	vList[i] = new LinkedList<Edge>();
		 }
	 }

	 
	  // 1. IMPLEMENTATION METHOD numVerts: 
	  public int numVerts() { 

	    //TO-DO
		  return vList.length;
     
     }

	  // 2. IMPLEMENTATION METHOD numEdges:
	  public int numEdges() { 
	    //TO-DO
		  int res = 0;
		  int cycle = 0;
		  for(int i = 0; i< vList.length; i++){
			  LinkedList<Edge> tempList = vList[i];
			  for (int j = 0; j < tempList.size(); j++) {
				  if(tempList.get(j).getVertex() == i){
				  	cycle++;
				  }
				  else{
				  	res++;
				  }
			  }
		  }
		  if(!isDirected){
		  	res /= 2;
		  }
		  return res + cycle;
	 }

	    
	  //  3. IMPLEMENTATION METHOD addEdge:
	  public void addEdge(int v1, int v2, int w) {
		
	    //TO-DO
		  try{
		  	vList[v1].add(new Edge(v2, w));
		  	if(!isDirected){
		  		vList[v2].add(new Edge(v1,w));
			}
		  }catch(ArrayIndexOutOfBoundsException e){
		  	System.out.println("Vertex does not exist");
		  }

    }
	  
	 // 4. IMPLEMENTATION METHOD removeEdge: 
	 public void removeEdge(int v1, int v2) {
		 try{
			 LinkedList<Edge> tempList = vList[v1];
			 for (int i = 0; i < tempList.size(); i++) {
			 	Edge tempEdge = tempList.get(i);
			 	if(tempEdge.getVertex() == v2){
			 		vList[v1].remove(i);
				}
			 }
			 if(!isDirected){
				 tempList = vList[v2];
				 for (int i = 0; i < tempList.size(); i++) {
					 Edge tempEdge = tempList.get(i);
					 if(tempEdge.getVertex() == v1){
						 vList[v2].remove(i);
					 }
				 }
			 }
		 }catch(ArrayIndexOutOfBoundsException e) {
				 System.out.println("Vertex does not exist");
			 }
	 }
	 
	 // 5. IMPLEMENTATION METHOD hasEdge:
	 public boolean hasEdge(int v1, int v2) {
		 //TO-DO
		 try{
			 LinkedList<Edge> tempList = vList[v1];
			 for (int i = 0; i < tempList.size(); i++) {
				 Edge tempEdge = tempList.get(i);
				 if(tempEdge.getVertex() == v2){
					 return true;
				 }
			 }

		 }catch(ArrayIndexOutOfBoundsException e) {
			 System.out.println("Vertex does not exist");
		 }
		 return false;
	 }

	// 6. IMPLEMENTATION METHOD getWeightEdge:
	 public int getWeightEdge(int v1, int v2) {
	    //TO-DO
		 try{
			 LinkedList<Edge> tempList = vList[v1];
			 for (int i = 0; i < tempList.size(); i++) {
				 Edge tempEdge = tempList.get(i);
				 if(tempEdge.getVertex() == v2){
					 return tempEdge.getWeight();
				 }
			 }

		 }catch(ArrayIndexOutOfBoundsException e) {
			 System.out.println("Vertex does not exist");
		 }
		 return -1;
	 }

	// 7. IMPLEMENTATION METHOD getNeighbors:
	 public LinkedList getNeighbors(int v) {
	     //TO-DO
		 LinkedList<Edge> res = new LinkedList();
		 try{
			 LinkedList<Edge> tempList = vList[v];
			 for (int i = 0; i < tempList.size(); i++) {
				 res.add(tempList.get(i));
			 }

		 }catch(ArrayIndexOutOfBoundsException e) {
			 System.out.println("Vertex does not exist");
		 }
		 return res;
	 }

    // 8. IMPLEMENTATION METHOD getDegree:
	public int getDegree(int v) {
		//TO-DO
		int res = 0;
		res += vList[v].size();

		if(isDirected){
			LinkedList<Edge> temp;
			for (int i = 0; i < vList.length; i++) {
				temp = vList[i];
				if(i!=v) {
					for (int j = 0; j < temp.size(); j++) {
						if (temp.get(j).getVertex() == v) {
							res++;
						}
					}
				}
			}
		}
		return res;
	}

	// 9. IMPLEMENTATION METHOD toString:
	 public String toString() {
	     //TO-DO
		 String res = "________________________\n";
		 for(int i = 0; i<vList.length; i++){
		 	res+= i + ":\n";
			 for (int j = 0; j < vList[i].size(); j++) {
			 	Edge temp = (Edge) vList[i].get(j);
				 res += "\t- Vertex: " + temp.getVertex();
				 res += "\tWeight: " + temp.getWeight() + "\n";
			 }
			 res += "\n";
		 }
		 res += "________________________\n";
		 return res;
	 }

	}


