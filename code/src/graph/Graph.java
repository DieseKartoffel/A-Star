package graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {

	// Zero bound -> Node i always on position (i+1)
	private List<Node> nodes = new ArrayList<Node>();
	
	private Node startNode;
	private Node goalNode;

	public Graph(int[] data) {
		// Number of Nodes N
		int nodeCount = data[0];
		if(nodeCount < 1)
			throw new IllegalArgumentException("Can't build Graph with zero or less Nodes!");

		// X and Y Coordinates for N nodes
		int i = 1;
		for (; i < nodeCount * 2; i += 2) {
			this.addNode(new Node(1 + (i / 2), data[i], data[i + 1]));
		}

		// next N^2 values describing connection matrix
		int position = i;
		for (i = 0; i < (nodeCount * nodeCount);) {
			int to = 1 + (i / nodeCount);
			for (int j = 0; j < nodeCount; j++) {
				int from = 1 + (i % nodeCount);
				if (data[i + position] == 1) {
					getNode(from).connectTo(getNode(to));
					// System.out.println("Connected " + from + " to " + to + ":" + data[i + position]);
				}
				i++;
			}
		}

		// After Graph is set up it can be prepared for A*:
		this.startNode = getNode(1);
		this.goalNode = getNode(nodeCount);

		for (Node node : nodes) {
			node.setDistanceToGoalNode(computeDistance(node, goalNode));
		}

	}

	public static double computeDistance(Node node1, Node node2) {
		int x1 = node1.getX();
		int y1 = node1.getY();
		int x2 = node2.getX();
		int y2 = node2.getY();
		double cSquared = (Math.pow((x2 - x1), 2)) + Math.pow((y2 - y1), 2);
		return Math.sqrt(cSquared);
	}

	public void addNode(Node node) {
		nodes.add(node);
	}

	// nodeNumber between 1 and N
	public Node getNode(int nodeNumber) {
		return nodes.get(nodeNumber - 1);
	}

	//Returns string with information about every node and its connections
	@Override
	public String toString() {
		String string = "";
		for (Node node : nodes) {
			double toGoal = Math.round(node.getDistanceToGoalNode() * 100);
			toGoal /= 100;
			string += "Node " + node.getNodeNumber() + " (h="+toGoal+")  \t is connected to {  ";
			for (Edge edge : node.getEdges()) {
				double distance = Math.round(edge.getDistance() * 100);
				distance /= 100;
				string += edge.getTargetNode().getNodeNumber() + "(" + distance + ")  ";
			}

			string += "}\n";
		}
		return string;
	}
	
	public Node getStartNode() {
		return startNode;
	}

	public Node getGoalNode() {
		return goalNode;
	}

}
