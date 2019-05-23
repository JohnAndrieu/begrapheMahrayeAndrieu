package org.insa.algo.utils;
 
import org.insa.graph.Node;
import org.insa.graph.Point;
import org.insa.algo.AbstractInputData.Mode;
import org.insa.algo.shortestpath.ShortestPathData;

public class LabelStar extends Label {
	
	private double totCost ;
	
	public LabelStar (Node courant, ShortestPathData data) {
		super(courant);
		
		if(data.getMode() == Mode.LENGTH) {
			double lg = Point.distance(courant.getPoint(), data.getDestination().getPoint()) ;
			this.setTotCost(lg);
			}
		else {
			double v = data.getMaximumSpeed() ;
			double lg = Point.distance(courant.getPoint(), data.getDestination().getPoint()) ;
			double tm = lg / v;
			this.setTotCost(tm) ;
		}
	}

	public double getTotCost() {
		return totCost;
	}

	public void setTotCost(double totCost) {
		this.totCost = totCost;
	}
	
	
	
	
}


