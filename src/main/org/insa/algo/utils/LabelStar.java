package org.insa.algo.utils;
 
import org.insa.graph.Node;
import org.insa.graph.Point;
import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.algo.AbstractInputData;

public class LabelStar extends Label implements Comparable<Label> {
	
	private double borneInf ;
	
	public LabelStar (Node courant, ShortestPathData data) {
		super(courant);
		System.out.println("creation labelstar");
		
		if (data.getMode() == AbstractInputData.Mode.LENGTH) {
			this.borneInf = Point.distance(courant.getPoint(),data.getDestination().getPoint());
		}
		else {
			int vitesse = Math.max(data.getMaximumSpeed(), data.getGraph().getGraphInformation().getMaximumSpeed());
			double lg = Point.distance(courant.getPoint(),data.getDestination().getPoint()) ;
			this.borneInf = lg /(vitesse*1000.0/3600.0);
		}
	}

	@Override
	public double getTotalCost() {
		return this.borneInf+this.cout;
	}

}

//on a le cout grace à notre calcul "maison"


