////MAIN FILE

package starpathfinding;


import java.util.ArrayList;
import java.util.Observable;
import starpathfinding.element.Network;
import starpathfinding.element.Node;

//Main code backend workend
public class AStarPathFinding extends Observable {
 //path   
    private Network network;
    private ArrayList<Node> path;
//starting and ending point
    private Node start;
    private Node end;
//list of open point  and closed point
    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;
//constructor
    public AStarPathFinding(Network network) {
        this.network = network;
    }
//conditions to check
    
    public void solve() {
//if start and end points are not given,then return
        if (start == null && end == null) {
            System.out.println("no data found");
            return;
        }
//if start is equal to end
        if (start.equals(end)) {
            
            this.path = new ArrayList<>();
            System.out.println("no suitable data found");
            return;
        }
//else
        this.path = new ArrayList<>();

        this.openList = new ArrayList<>();
        this.closedList = new ArrayList<>();
//list add the start
        this.openList.add(start);
//
        while (!openList.isEmpty()) {
            Node current = getLowestF();

            if (current.equals(end)) {
                retracePath(current);
                break;
            }

            openList.remove(current);
            closedList.add(current);

            for (Node n : current.getNeighbours()) {

                if (closedList.contains(n) || !n.isValid()) {
                    continue;
                }

                double tempScore = current.getCost() + current.distanceTo(n);

                if (openList.contains(n)) {
                    if (tempScore < n.getCost()) {
                        n.setCost(tempScore);
                        n.setParent(current);
                    }
                } else {
                    n.setCost(tempScore);
                    openList.add(n);
                    n.setParent(current);
                }

                n.setHeuristic(n.heuristic(end));
                n.setFunction(n.getCost() + n.getHeuristic());

            }

        }

        updateGUI();
    }
//to reset the data
    public void reset() {
        this.start = null;
        this.end = null;
        this.path = null;
        this.openList = null;
        this.closedList = null;
        for (Node n : network.getNodes()) {
            n.setValid(true);
        }
    }
//to retrace on a given path
    private void retracePath(Node current) {
        Node temp = current;
        this.path.add(current);
        
        while (temp.getParent() != null) {
            this.path.add(temp.getParent());
            temp = temp.getParent();
        }
        
        this.path.add(start);
    }
//function to get lowest value node or cell
    private Node getLowestF() {
        Node lowest = openList.get(0);
        for (Node n : openList) {
            if (n.getFunction()< lowest.getFunction()) {
                lowest = n;
            }
        }
        return lowest;
    }
//update the gui 
    public void updateGUI() {
        //by calling different method
        setChanged();
        notifyObservers();
        clearChanged();
    }

    public Network getNetwork() {
        return network;
    }

    public ArrayList<Node> getPath() {
        return path;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

}
