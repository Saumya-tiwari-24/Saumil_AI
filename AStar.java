import java.util.*;

public class AStar {
    public static int estimateDistance(Node a, Node b) {
        // Heuristic function to estimate distance between nodes a and b
        // In this example, we use the Euclidean distance between the node coordinates
        int dx = a.getX() - b.getX();
        int dy = a.getY() - b.getY();
        return (int) Math.sqrt(dx*dx + dy*dy);
    }

    public static List<Node> findShortestPath(Node start, Node goal) {
        Map<Node, Integer> gScore = new HashMap<>(); // Cost from start along best known path
        gScore.put(start, 0);
        Map<Node, Integer> fScore = new HashMap<>(); // Estimated total cost from start to goal through y
        fScore.put(start, estimateDistance(start, goal));
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(fScore::get));
        openSet.add(start);
        Map<Node, Node> cameFrom = new HashMap<>(); // Parent node on best known path
        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            if (current.equals(goal)) {
                // Reconstruct the path from start to goal
                List<Node> path = new ArrayList<>();
                path.add(current);
                while (cameFrom.containsKey(current)) {
                    current = cameFrom.get(current);
                    path.add(current);
                }
                Collections.reverse(path);
                return path;
            }
            for (Node neighbor : current.getNeighbors()) {
                int tentativeGScore = gScore.get(current) + current.getCost(neighbor);
                if (!gScore.containsKey(neighbor) || tentativeGScore < gScore.get(neighbor)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    fScore.put(neighbor, tentativeGScore + estimateDistance(neighbor, goal));
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        return null; // No path found
    }
    public static void main(String[] args) {
    // Create example graph
    Node start = new Node(0, 0);
    Node a = new Node(1, 2);
    Node b = new Node(3, 5);
    Node c = new Node(5, 2);
    Node goal = new Node(6, 6);
    start.addNeighbor(a, 5);
    start.addNeighbor(c, 10);
    a.addNeighbor(b, 3);
    b.addNeighbor(goal, 2);
    c.addNeighbor(b, 6);
    c.addNeighbor(goal, 4);
    
    // Find shortest path using A* algorithm
    List<Node> path = AStar.findShortestPath(start, goal);
    if (path != null) {
        System.out.println("Shortest path found: " + path);
    } else {
        System.out.println("No path found from start to goal");
    }
}

}






