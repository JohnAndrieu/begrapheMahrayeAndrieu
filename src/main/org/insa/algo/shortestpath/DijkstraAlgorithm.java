package org.insa.algo.shortestpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;

import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.utils.BinaryHeap;
import org.insa.graph.Arc;
import org.insa.algo.utils.Label;
import org.insa.graph.Graph;
import org.insa.graph.Node;
import org.insa.graph.Path;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        ShortestPathData data = getInputData();
       
        ShortestPathSolution solution = null;
        
        Graph graph = data.getGraph();

        final int nbNodes = graph.size();
        
        boolean test_mark = true ;
        
        boolean fin = false ;
        
        //on récupère les nodes du graph
        List<Node> nodes = new ArrayList <Node> ();
        nodes = graph.getNodes() ;
        
        //on crée une hashmap
        HashMap<Integer, Label> hmap = new HashMap<Integer, Label>(); 

        //mettre un label à tous les noeuds
        for(int i = 0 ; i < nbNodes-1 ; i++) {
        	hmap.put(nodes.get(i).getId(),new Label (nodes.get(i),null,false,Double.POSITIVE_INFINITY)) ;
        }
        
        //on met l'origine à 0
        Node origin = data.getOrigin() ;
        Label OriginLabel = new Label (origin,null,false,0);
        
      
        
        //on crée un tas de label
        BinaryHeap<Label> LabelHeap = new BinaryHeap <Label> () ;

        //on insere l'origine dans le tas
        LabelHeap.insert(OriginLabel);
        OriginLabel.setInTas();
        
        notifyOriginProcessed(data.getOrigin());
        
        List<Arc> successeurs = new ArrayList <Arc>() ;
        
        // Initialize array of predecessors.
        Arc[] predecessorArcs = new Arc[nbNodes];
        
        while(test_mark == true && fin == false) {	
        	
        	Label minLabel = LabelHeap.deleteMin() ;	//on a le min des label x ici on est dans le tas
        	minLabel.setMark(true); 	//on le mark true
        	notifyNodeMarked(minLabel.getNode());
        	
        	if (minLabel.getNode() == data.getDestination()) {
				fin = true;
			}
        	
        	successeurs = minLabel.getNode().getSuccessors() ; //les successeurs y 

        	for(int i = 0 ; i < successeurs.size()-1 ; i++ ) {
        		
        		int indice = successeurs.get(i).getDestination().getId();
        		Label lbSuccess = hmap.get(indice) ;
        		notifyNodeReached(successeurs.get(i).getDestination());
        			
	        		if(lbSuccess.getMark() == false) { 	//on verifie si le noeud selectionné est mark
	        			
	        			double w  = minLabel.getCost() + data.getCost(successeurs.get(i)) ;
	        			
	        			if(lbSuccess.getCost() > w) {
	        				
	        				lbSuccess.setCost(w);
	        				
	        				
	        				if(lbSuccess.getInTas() == true) {
	        					LabelHeap.remove(lbSuccess);
	        				}
	        				else {
	        					lbSuccess.setInTas();
	        				}
	        				LabelHeap.insert(lbSuccess) ; 
	        				lbSuccess.setFather(successeurs.get(i)); 
	        				predecessorArcs[indice] = successeurs.get(i);
	        			}
	        		}
	        		
        	}
        	
        	//on vérifie s'il y a des sommets non marqués
        	for(int i = 0; i < nbNodes-1 ; i++) {
        		if(hmap.get(nodes.get(i).getId()).getMark() == false) {
        			test_mark = true ;
        		}
        		else {
        			test_mark = false ;
        		}
        	}
        	
        }
        
        
     // Destination has no predecessor, the solution is infeasible...
     		if (predecessorArcs[data.getDestination().getId()] == null) {
     			solution = new ShortestPathSolution(data, Status.INFEASIBLE);
     		} else {

     			// The destination has been found, notify the observers.
     			notifyDestinationReached(data.getDestination());

     			// Create the path from the array of predecessors...
     			ArrayList<Arc> arcs = new ArrayList<>();
     			Arc arc = predecessorArcs[data.getDestination().getId()];

     			while (arc != null) {
     				arcs.add(arc);
     				arc = predecessorArcs[arc.getOrigin().getId()];
     			}

     			// Reverse the path...
     			Collections.reverse(arcs);

     			// Create the final solution.
     			solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));

     		}

        return solution;
    }

}