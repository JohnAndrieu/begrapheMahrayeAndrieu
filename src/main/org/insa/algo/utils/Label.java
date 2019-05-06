package org.insa.algo.utils;

import org.insa.graph.Arc;
import org.insa.graph.Node;

public class Label {

	private Node sommet_courant;
	private boolean marque;
	private int cout;
	private Arc pere;
	
	int getcost() {
		return this.cout;
	}
}
