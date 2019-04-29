package aStar;

import java.util.LinkedList;

import graph.Node;

public class ClosedList {

	private LinkedList<Node> nodes = new LinkedList<Node>();

	public void add(Node node) {
//		System.out.println("Closing the " + (nodes.size() + 1) + "th Node Nr. " + node.getNodeNumber());
		nodes.add(node);
	}

	public boolean contains(Node node) {
		return nodes.contains(node);
	}
	
	public void remove(Node node){
		nodes.remove(node);
	}

	public int size() {
		return nodes.size();
		
	}

}
