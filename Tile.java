package pathfinding.element;

/**
 *
 * @author Dell
 */
import java.awt.Point;
import java.util.ArrayList;
import starpathfinding.element.Network;
import starpathfinding.element.Node;

public class Tile extends Node {
//intialization
    private int x, y;
    public static int TILE_SIZE = 30;     //tile size

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        setValid(true);
    }
//width
    public int getX() {
        return x;
    }
//height
    public int getY() {
        return y;
    }

    @Override
    public void calculateNeighbours(Network network) {
        
        Grid grid = (Grid) network;

        ArrayList<Node> nodes = new ArrayList<>();

        int minX = 0;
        int minY = 0;
        int maxX = grid.getWidth() - 1;
        int maxY = grid.getHeight() - 1;

        if (x > minX) {
            nodes.add(grid.find(x - 1, y)); //west
        }

        if (x < maxX) {
            nodes.add(grid.find(x + 1, y)); //east
        }

        if (y > minY) {
            nodes.add(grid.find(x, y - 1)); //north
        }

        if (y < maxY) {
            nodes.add(grid.find(x, y + 1)); //south
        }

        if (x > minX && y > minY) {
            nodes.add(grid.find(x - 1, y - 1)); //northwest   
        }
        
        if (x < maxX && y < maxY) {
            nodes.add(grid.find(x + 1, y + 1)); //southeast  
        }
        
        if(x < maxX && y > minY){
            nodes.add(grid.find(x + 1, y - 1)); //northeast  
        }
        
        if(x > minY && y < maxY){
            nodes.add(grid.find(x - 1, y + 1)); //southwest
        }
        
        setNeighbours(nodes);

    }

    @Override
    public double heuristic(Node dest) {
        return distanceTo(dest);
    }

    @Override
    public double distanceTo(Node dest) {
        Tile d = (Tile) dest;
        return new Point(x,y).distance(new Point(d.x, d.y));
    }

}
