package org.insa.algo.utils;

import org.insa.graph.Arc;
import org.insa.graph.Node;

public class Label {

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
	
	double getcost() {
		return this.cout;
	}
}
