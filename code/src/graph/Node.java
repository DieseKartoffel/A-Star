package graph;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private int number;

	// Coordintes
	private int x;
	private int y;

	private List<Edge> edges = new ArrayList<Edge>();

	// variables for A* Implementation:
	private Node parentNode; // in route that is currently examined
	private double currentCost; // cost to reach this node from start node
	private double distanceToGoalNode; // heuristic value

	public Node(int number, int x, int y) {
		this.number = number;
		this.x = x;
		this.y = y;
		//System.out.println("Node " + number + " created at (" + x + "," + y + ")");
	}

	public void connectTo(Node target) {
		edges.add(new Edge(this, target));
	}

	public int getNodeNumber() {
		return number;
	}

	public List<Edge> getEdges() {
		return this.edges;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// Methods for A* Implementation:

	public void setDistanceToGoalNode(double distance) {
		this.distanceToGoalNode = distance;
	}

	public double getDistanceToGoalNode() {
		return this.distanceToGoalNode;
	}

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	public double getCurrentCost() {
		return currentCost;
	}

	public void setCurrentCost(double currentCost) {
		this.currentCost = currentCost;
	}
}
