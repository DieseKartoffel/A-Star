package aStar;

import java.util.LinkedList;

import graph.Node;

//Priority List
//First Object in List has highest priority

public class OpenList {

	private LinkedList<Node> nodes = new LinkedList<Node>();

	
	// The Magic Moment of the A Star Algorithm:
	// Using a linked list allows to order the Nodes easily to define the order in which they are checked later (like Enqueue and Dequeue)
	// With Dijkstra, the shortest current cost node will be checked first.
	// With AStar, in addition to the cost, the direct distance to the goal node will be added to the current cost value of the node.
	// Since the Algorithm is used on a map-like envoirment, "distance to goal" is an admissable, monotonous heuristic function.
	// Since the weights on edges are real distances instead of random values, a direct-distance heuristic is well suited.
	// The heuristic H will have following property: DirectDistance(start, goal) <= H(Node) + CostToReach(Node) <= Shortest Path to Goal
	// Where H(Node) describes the node.getDistanceToGoalNode function
	// Based on this heuristic, added to the actual cost of a node, the order in which they are checked is defined
	// Remove Heuristic to get Dijkstra
	public void addNode(Node node) {
		for (int i = 0; i < nodes.size(); i++) {
			
			Node compareNode = nodes.get(i);
			
			double myCostAndHeuristic = node.getCurrentCost() + node.getDistanceToGoalNode(); //Cost + Heuristic
			double otherCostAndHeuristic = compareNode.getCurrentCost() + compareNode.getDistanceToGoalNode();
			if (myCostAndHeuristic < otherCostAndHeuristic) {
				//Add "myNode" in front of compareNode
				nodes.add(i, node);
				return;
			}
		}
		// Add "myNode" to end of List
		nodes.add(node); 
	}

	public boolean containsNodes() {
		if (nodes.isEmpty())
			return false;
		else
			return true;
	}

	public Node dequeue() {
		return nodes.removeFirst();
	}

	public boolean containsNode(Node node) {
		return nodes.contains(node);
	}

	public void removeNode(Node node) {
		nodes.remove(node);
	}

}
