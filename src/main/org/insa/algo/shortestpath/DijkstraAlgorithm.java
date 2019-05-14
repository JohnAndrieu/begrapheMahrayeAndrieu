package org.insa.algo.shortestpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.insa.algo.utils.BinaryHeap;
import org.insa.graph.Arc;
import org.insa.algo.utils.Label;
import org.insa.graph.Graph;
import org.insa.graph.Node;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        // TODO:
        
        Graph graph = data.getGraph();

        final int nbNodes = graph.size();
        
        boolean test_mark = true ;
        
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
        OriginLabel.setInTas();
        
        //on crée un tas de label
        BinaryHeap<Label> LabelHeap = new BinaryHeap <Label> () ;

        //on insere l'origine dans le tas
        LabelHeap.insert(OriginLabel);
        
        List<Arc> successeurs = new ArrayList <Arc>() ;
        
        while(test_mark == true) {	
        	
        	Label minLabel = LabelHeap.findMin() ;	//on a le min des label x ici on est dans le tas
        	minLabel.setMark(true); 	//on le mark true
        	
        	successeurs = minLabel.getNode().getSuccessors() ; //les successeurs y 

        	for(int i = 0 ; i < successeurs.size()-1 ; i++ ) {
        		
        		int indice = successeurs.get(i).getDestination().getId();
        		Label lbSuccess = hmap.get(indice) ;
        			
	        		if(lbSuccess != null && lbSuccess.getMark() == false) { 	//on verifie si le noeud selectionné est mark
	        			
	        			double w  = minLabel.getCost() + data.getCost(successeurs.get(indice)) ;
	        			
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
	        				
	        			}
	        		}
        		
        	}
        	
        	//on vérifie s'il y a des sommets non marqués
        	for(int i = 0; i < nbNodes ; i++) {
        		if(hmap.get(i).getMark() == false) {
        			test_mark = true ;
        		}
        		else {
        			test_mark = false ;
        		}
        	}
        }
        
        return solution;
    }

}
