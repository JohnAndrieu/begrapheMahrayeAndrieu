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

	protected int nbSommetsVisites;
    protected int nbIter ;
	
    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
        this.nbSommetsVisites = 0 ;
        this.nbIter = 0 ;
    }

    @Override
    protected ShortestPathSolution doRun() {
        ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        Graph graph = data.getGraph();
        
        int sizeGraph = graph.size();
        
        // creation d'une liste de noeuds
        //List<Node> nodeGraph = new ArrayList <Node> ();
        
        // on récupère les noeuds du graphe
        //nodeGraph = graph.getNodes() ;

        boolean fin = false;
        
        // creation d'une Hmap de Label
        HashMap<Integer, Label> hmap = new HashMap<Integer, Label>(); 
        
        // mise en pace de l'iterator des noeuds
        //Iterator<Node> nodeIt = nodeGraph.iterator();
        
        /* Tant qu'il existe  un noeud: on crée un label correspondant 
         * sauf pour l'origine (crée par la suite: eviter doublant */
        /*while(nodeIt.hasNext()) {
        	Node nodeCurrent = nodeIt.next();
        	if(nodeCurrent.equals(data.getOrigin())) {} else {
        	hmap.put(nodeCurrent.getId(),Newlb (nodeCurrent, data)) ;
        	}
        }*/
        
        // tableau de predecesseurs
        Arc[] predecessorArcs = new Arc[sizeGraph];
        
        // Tas de labels
        BinaryHeap<Label> labelHeap = new BinaryHeap <Label> () ;
        
        // Création et  Ajout de l'origine dans le tas de label
        Node origin = data.getOrigin() ;
        Label originLabel = Newlb(origin, data);
        hmap.put(origin.getId(), originLabel);
        
        labelHeap.insert(originLabel);
        originLabel.setInTas();
        originLabel.setCost(0);
        
        notifyOriginProcessed(data.getOrigin());
        
        
        
        // tant qu'il existe des sommets non marqués 
        while(!labelHeap.isEmpty() && !fin) {	
        	
        	nbIter++;
        	
        	Label minLabel = labelHeap.deleteMin() ;
        	
        	minLabel.setMark(true); 	
        	notifyNodeMarked(minLabel.getNode());
        	
        	
        	// lorsque la destination est atteinte, arret de la boucle while
        	if (minLabel.getNode() == data.getDestination()) {
				fin = true;
			}
        	// création liste d'arc successeur et son ieterator  
        	List<Arc> successeurs = new ArrayList <Arc>() ;
        	successeurs = minLabel.getNode().getSuccessors() ;
        	Iterator <Arc> arc = successeurs.iterator();
        	// parcours du sommet courant
        	while(arc.hasNext()) {
        		
        		Arc arcIter = arc.next();
        		// On verifie si on peut prendre cet arc
        		if (!data.isAllowed(arcIter)) {
					continue;
				}
        		
        		int id = arcIter.getDestination().getId(); // on récupère l'id du successeur
        		Node success = arcIter.getDestination() ;
        		
        		// On recupèe le label correspondant au noeud dans la Hmap
        		Label lbSuccess = hmap.get(id) ;
        		
        		if(lbSuccess == null) {
        			notifyNodeReached(arcIter.getDestination());
        			lbSuccess = Newlb(success,data);
        			hmap.put(id, lbSuccess) ;
        			
        			this.nbSommetsVisites++;
        		}
        		

        			// si le successeur n'est pas encore marqué on procède à la mise à jour des cout
	        		if(!lbSuccess.getMark()) { 
	        			/* Si on obtient un meilleur coût 
	        			 * alors on le met à jour
	        			 */
	        			if(lbSuccess.getTotalCost() > minLabel.getCost()
	        					+ data.getCost(arcIter) + (lbSuccess.getTotalCost() - lbSuccess.getCost()) 
	        					|| (lbSuccess.getCost()==Double.POSITIVE_INFINITY) ) {
	        				
	        				lbSuccess.setCost(minLabel.getCost()
		        					+ (double)data.getCost(arcIter));
	        				
	        				lbSuccess.setFather(arcIter); 
	        				/*
	        				 * Si le label est déjà dans le tas
	        				 * on le met à jour sa position dans le tas
	        				 */
	        				if(lbSuccess.getInTas()) {
	        					labelHeap.remove(lbSuccess);
	        				}
	        				/*
	        				 * sinon on l'ajoute dans le tas
	        				 */
	        				else {
	        					lbSuccess.setInTas();
	        				}
	        				labelHeap.insert(lbSuccess) ; 
	        				
	        				predecessorArcs[id] = arcIter;
	        			}
	        		}
	        		
        	}
        	
        	/* Test de la croissance des coûts des labels marqués pour Dijkstra */
        	/*Label new_min = labelHeap.findMin();
        	int res = new_min.compareTo(minLabel) ;
        	if(res == 1) {
        		System.out.println("[OK] Cout lbCurrent = "+minLabel.getCost()+" < Cout lbSucess = "+new_min.getCost());
        	}
        	else if(res == -1){
        		System.out.println("[Warning] Cout lbCurrent = "+minLabel.getCost()+" > Cout lbSucess = "+new_min.getCost());
        	}*/
        	
        	

        }
        
        //System.out.println("Nombre d'itérations : "+ nbIter);
        //System.out.println("Nombre de sommets visités : "+ nbSommetsVisites);
        
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
     			//System.out.println("Nombre d'arcs : " + arcs.size() );

     		}
     		
     		

        return solution;
    }
    
    /* Crée et retourne le Label correspondant au Node */
	protected Label Newlb(Node node, ShortestPathData data) {
		return new Label(node);
	}
	
	public int getNbSommetsVisites() {
		return this.nbSommetsVisites;
	}
	
	public int getNbIter() {
		return this.nbIter;
	}

}