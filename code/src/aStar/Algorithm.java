package aStar;

import graph.Edge;
import graph.Graph;
import graph.Node;

public class Algorithm {

	// Open List contains any node of which we know that it is reachable.
	// However, it is not yet known if there is a shorter path to that node.
	private OpenList openList = new OpenList();
	// Nodes get added to the closed List, if the shortest path to it has been
	// found.
	private ClosedList closedList = new ClosedList();

	// Will only be set when goalNode is reached
	private Node goalNode;

	// Graph contains information about start and goal node
	public boolean findShortestRoute(Graph graph) {

		// Cost of Start Node doesn't matter for path finding
		// set to 0 keep path cost valid
		graph.getStartNode().setCurrentCost(0);

		openList.addNode(graph.getStartNode());

		while (openList.containsNodes()) {

			Node currentNode = openList.dequeue();

			if (currentNode.equals(graph.getGoalNode())) {
				this.goalNode = currentNode;
				return true;
			}

			// Do not check this node again since shortest route to it has been found
			// Otherwise it wouldn't be on top of the openList
			closedList.add(currentNode);

			// Expanding Current Node - Checking Neighbors:
			for (Edge edge : currentNode.getEdges()) {

				Node neighbour = edge.getTargetNode();

				if (closedList.contains(neighbour))
					continue;

				// Check if new total cost to neighbor node (from start node)
				// is shorter than before
				double newCostFromStartToNeighbour = (currentNode.getCurrentCost() + edge.getDistance());

				// If a route is already been found and that old route is better than this one:
				// keep old route and continue
				if (openList.containsNode(neighbour)) {
					if (newCostFromStartToNeighbour >= neighbour.getCurrentCost())
						continue;
				}

				// if the new route is better than the one before, update the nodes cost
				neighbour.setCurrentCost(newCostFromStartToNeighbour);
				// Remember Route
				neighbour.setParentNode(currentNode);

				// Insert Node into open list or queue it again with new cost value
				openList.removeNode(neighbour);
				openList.addNode(neighbour); // the method will automatically keep list order based on heuristic and
												// current cost
				// Could actually be done with O(n) instead of O(3n) by creating a
				// "deleteAndInstert" method in the open list that only goes thru every node
				// once.
			}

		}

		return false;

	}

	String result;

	public String getResult() {
		if (goalNode == null) {
//			System.out.println(0);
			return "0";
		} else {
			result = "";
			getPredecessorInfo(goalNode); // recursive method
			result += goalNode.getNodeNumber();
//			System.out.println(result);
//			System.out.println("Cost: " + goalNode.getCurrentCost());
			return result;
		}
	}

	private void getPredecessorInfo(Node node) {
		if (node.getParentNode() == null) {
			return;
		}
		node = node.getParentNode();
		getPredecessorInfo(node);
		result += node.getNodeNumber() + " ";
	}

}
