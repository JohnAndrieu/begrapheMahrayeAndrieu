package org.insa.algo.utils;

import org.insa.graph.Node;

public class Label {
	
	private int sommet_courant;
	private boolean marque;
	private int cout;
	private Node pere;
	
	int getcost() {
		return this.cout;
	}
}
