package org.insa.algo.utils;

import org.insa.graph.Arc;
import org.insa.graph.Node;

public class Label implements Comparable<Label>{

	private Node sommet_courant;
	private boolean marque;
	private double cout;
	private Arc pere;
	private boolean inTas;
	
	public Label (Node courant, Arc papa, boolean mark, double cost) {
		this.sommet_courant = courant ;
		this.marque = mark ;
		this.cout = cost ;
		this.pere = papa ;
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

	@Override
	public int compareTo(Label o) {
		int resultat;
		if (this.getCost() < o.getCost()) {
			resultat = -1;
		}
		else if (this.getCost() == o.getCost()) {
			resultat = 0;
		}
		else {
			resultat = 1;
		}
		return resultat;
	}
}
