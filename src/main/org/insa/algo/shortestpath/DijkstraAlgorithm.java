package org.insa.algo.shortestpath;

import java.util.*; 

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
        
        int sizeGraph = graph.size();
        
        List<Node> nodeGraph = new ArrayList <Node> ();
        nodeGraph = graph.getNodes() ;

        boolean fin = false;

        HashMap<Integer, Label> hmap = new HashMap<Integer, Label>(); 

        Iterator<Node> nodeIt = nodeGraph.iterator();
        while(nodeIt.hasNext()) {
        	Node nodeCurrent = nodeIt.next();
        	if(nodeCurrent.equals(data.getOrigin())) {} else {
        	hmap.put(nodeCurrent.getId(),new Label (nodeCurrent)) ;
        	}
        }
        
        // Initialize array of predecessors.
        Arc[] predecessorArcs = new Arc[sizeGraph];
        
        // Tas de labels
        BinaryHeap<Label> labelHeap = new BinaryHeap <Label> () ;
        
        // Ajout de l'origine
        Node origin = data.getOrigin() ;
        Label originLabel = new Label (origin);
        hmap.put(origin.getId(), originLabel);
        
        labelHeap.insert(originLabel);
        originLabel.setInTas();
        originLabel.setCost(0);
        
        notifyOriginProcessed(data.getOrigin());
        
        while(!labelHeap.isEmpty() && !fin) {	
        	
        	Label minLabel = labelHeap.deleteMin() ;
        	minLabel.setMark(true); 	
        	notifyNodeMarked(minLabel.getNode());
        	
        	if (minLabel.getNode() == data.getDestination()) {
				fin = true;
			}
        	
        	List<Arc> successeurs = new ArrayList <Arc>() ;
        	successeurs = minLabel.getNode().getSuccessors() ;
        	Iterator <Arc> arc = successeurs.iterator();

        	while(arc.hasNext()) {
        		Arc arcIter = arc.next();
        		
        		if (!data.isAllowed(arcIter)) {
					continue;
				}
        		
        		int id = arcIter.getDestination().getId();
        		Label lbSuccess = hmap.get(id) ;
        		
        		notifyNodeReached(arcIter.getDestination());
        			
	        		if(!lbSuccess.getMark()) { 
	        			
	        			if(lbSuccess.getCost() > minLabel.getCost()
	        					+ data.getCost(arcIter)) {
	        				
	        				lbSuccess.setCost(minLabel.getCost()
		        					+ (double)data.getCost(arcIter));
	        				

	        				lbSuccess.setCost(w); 
	        				lbSuccess.setFather(arcIter); 

	        				
	        				if(lbSuccess.getInTas()) {
	        					labelHeap.remove(lbSuccess);
	        				}
	        				else {
	        					lbSuccess.setInTas();
	        				}
	        				LabelHeap.insert(lbSuccess) ; 
	        				lbSuccess.setFather(successeurs.get(i));
	        				labelHeap.insert(lbSuccess) ; 
	        				predecessorArcs[id] = arcIter;
	        			}
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