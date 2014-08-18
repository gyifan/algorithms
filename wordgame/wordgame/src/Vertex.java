import java.util.ArrayList;


/**
 * Vertex class represents a vertex (and extends HeapElt).
 *
 */
public class Vertex extends HeapElt {

	private ArrayList<Edge> edgeArray;
	
	/**
	 * Constructor takes in the String to be stored in the vertex and 
	 * creates an ArrayList of Edges this Vertex connects to. 
	 */
	public Vertex(String record) {
		this.record = record;
		edgeArray = new ArrayList<Edge>();
		handle = 0;
	}
	
	/**
	 * This method insert an Edge to current Vertex. It connects the vertex
	 * to another.
	 */
	public void insertEdge(Vertex v, int cost){
		edgeArray.add(new Edge(v, cost));
	}
	
	/**
	 * This method prints current edges with their costs.
	 */
	public void printEdge(){
		int count = 0;
		System.out.print("\t");
		for(Edge e: edgeArray){
			if(count > 5){
				System.out.println();
				count = 0;
				System.out.print("\t");
			}
			count ++;
			System.out.printf("%s (%d)  ", e.getVertex().getRecord(), e.getCost());
		}
		System.out.println();
	}

}
