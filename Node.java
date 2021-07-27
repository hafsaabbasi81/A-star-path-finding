package starpathfinding.element;

import java.util.ArrayList;


public abstract class Node {
    
    private Node parent;          //previous node
    private ArrayList<Node> neighbours;             //linked node or surrounding nodes
    private double cost, heuristic, function;       //varibles for nodes movement value
    private boolean valid;                          //check the validity

    public abstract void calculateNeighbours(Network network);  //calculation the value ofthe neighbour nodes
    public abstract double distanceTo(Node dest);         //calculate the ditance
    public abstract double heuristic(Node dest);           //calculate heuristics  

    //get cost
    public double getCost() {
        return cost;
    }
//set cost
    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(double heuristic) {
        this.heuristic = heuristic;
    }

    public double getFunction() {
        return function;
    }

    public void setFunction(double function) {
        this.function = function;
    }

   
    
    public ArrayList<Node> getNeighbours() {
        return neighbours;
    }
//adding of neighbour nodes
    public void setNeighbours(ArrayList<Node> neighbours) {
        this.neighbours = neighbours;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
    //reverse the valid
    public void reverseValidation(){
        valid = !valid;
    }


    
}
