package org.insa.algo.utils;

import org.insa.graph.Arc;
import org.insa.graph.Node;

public class Label implements Comparable<Label>{

	private Node sommet_courant;
	private boolean marque;
	private double cout;
	private Arc pere;
	
	public Label (Node courant, Arc papa, boolean mark, double cost) {
		this.sommet_courant = courant ;
		this.marque = mark ;
		this.cout = cost ;
		this.pere = papa ;
	}
	
	public double getCost() {
		return this.cout;
	}
	
	public boolean getMark() {
		return this.marque;
	}
	
	public Node getNode() {
		return this.sommet_courant;
	}
	
	public Arc getFather() {
		return this.pere;
	}

	@Override
	public int compareTo(Label o) {
		// TODO Auto-generated method stub
		
		return 0;
	}
}
