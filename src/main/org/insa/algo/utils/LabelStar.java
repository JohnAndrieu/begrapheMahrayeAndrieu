package org.insa.algo.utils;
 
import org.insa.graph.Node;
import org.insa.graph.Arc;

public class LabelStar extends Label {
	
	private Label lb ;
	
	public LabelStar (Node courant) {
	super(courant);
	}
	
	public double getTotalCost() {
		return lb.getCost()+lb.getTotalCost();
	}
}


