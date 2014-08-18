
/**
 * This class instance functions as an Edge object. It contains the vertex which the original
 * vertex connects to and the cost of this edge. 
 *
 */
public class Edge {
	
	private Vertex v;
	private int cost;
	
	/**
	 * Constructor takes in the vertex it connects to and the cost of edge.
	 */
	public Edge(Vertex v, int cost){
		this.v = v;
		this.cost = cost;
	}
	
	/**
	 * Returns the cost of edge.
	 */
	public int getCost(){
		return cost;
	}
	
	/**
	 * Returns the vertex instance this edge connects to.
	 */
	public Vertex getVertex(){
		return v;
	}
	
}
