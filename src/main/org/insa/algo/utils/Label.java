package org.insa.algo.utils;

import org.insa.graph.Arc;
import org.insa.graph.Node;

public class Label {

	private Node sommet_courant;
	private boolean marque;
	private int cout;
	private Arc pere;
	
	public Label (Node courant, Arc papa, boolean mark, int cost) {
		this.sommet_courant = courant ;
		this.marque = mark ;
		this.cout = cost ;
		this.pere = papa ;
	}
	
	int getcost() {
		return this.cout;
	}
}
