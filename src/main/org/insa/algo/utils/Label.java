package org.insa.algo.utils;

import org.insa.graph.Arc; 
import org.insa.graph.Node;

public class Label implements Comparable<Label>{

	private Node sommet_courant;
	private boolean marque;
	protected double cout;
	private Arc pere;
	private boolean inTas;
	
	public Label (Node courant) {
		this.sommet_courant = courant ;
		this.marque = false ;
		this.cout = Double.POSITIVE_INFINITY;
		this.pere = null ;
		this.inTas = false ;
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
	
	public void setFather(Arc x) {
		this.pere = x;
	}
	
	public void setMark(boolean x) {
		this.marque = x;
	}
	
	public void setCost(double x) {
		this.cout = x;
	}
	
	
	
	public boolean getInTas() {
		return this.inTas;
	}
	
	public void setInTas() {
		this.inTas = true;
	}

	
	public double getTotalCost() {
		return this.cout;
	}
	
	public void setCostTotal(double x) {
		this.cout = x;
	}
	
	@Override
	public int compareTo(Label o) {
		int resultat;
		if (this.getTotalCost() < o.getTotalCost()) {
			resultat = -1;
		}
		else if (this.getTotalCost() == o.getTotalCost()) {
			resultat = 0;
		}
		else {
			resultat = 1;
		}
		
		return resultat;
	}
	
}


//à dire à la soutenance : on a le cout grace à la fonction getcost()