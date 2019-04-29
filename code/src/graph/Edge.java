package graph;

public class Edge {
	
	private Node startNode;
	private Node targetNode;
	private double distance;
	
	public Edge(Node start, Node target) {
		this.startNode = start;
		this.targetNode = target;
		this.distance = Graph.computeDistance(start, target);
	}

	public double getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public Node getStartNode() {
		return startNode;
	}
	public void setStartNode(Node startNode) {
		this.startNode = startNode;
	}
	public Node getTargetNode() {
		return targetNode;
	}
	public void setTargetNode(Node targetNode) {
		this.targetNode = targetNode;
	}
	

}
