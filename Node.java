import java.util.*;

public class Node {
    private int x;
    private int y;
    private Map<Node, Integer> neighbors; // Neighboring nodes and cost to move to each neighbor
    
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.neighbors = new HashMap<>();
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void addNeighbor(Node neighbor, int cost) {
        neighbors.put(neighbor, cost);
    }
    
    public Collection<Node> getNeighbors() {
        return neighbors.keySet();
    }
    
    public int getCost(Node neighbor) {
        return neighbors.get(neighbor);
    }
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}



