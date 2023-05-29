    import java.util.*;

public class DijkstraMST {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        // Create the graph
        int[][] graph = {
                {0, 2, INF, 6, INF},
                {2, 0, 3, 8, 5},
                {INF, 3, 0, INF, 7},
                {6, 8, INF, 0, 9},
                {INF, 5, 7, 9, 0}
        };

        // Find the minimum spanning tree
        List<Edge> mst = dijkstraMST(graph);

        // Print the minimum spanning tree edges
        System.out.println("Minimum Spanning Tree Edges:");
        for (Edge edge : mst) {
            System.out.println(edge.src + " - " + edge.dest + ", Weight: " + edge.weight);
        }
    }

    private static List<Edge> dijkstraMST(int[][] graph) {
        int n = graph.length;

        List<Edge> mst = new ArrayList<>(); // Store the edges of the minimum spanning tree
        int[] parent = new int[n]; // Store the parent of each vertex in the MST
        int[] dist = new int[n]; // Store the cumulative weight to reach each vertex

        Arrays.fill(dist, INF); // Initialize all distances with infinity
        Arrays.fill(parent, -1); // Mark all vertices as unvisited

        dist[0] = 0; // Set the distance of the source vertex to 0

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.dist));
        pq.offer(new Node(0, 0)); // Add the source vertex to the priority queue

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.vertex;

            if (parent[u] != -1) {
                // Add the edge to the minimum spanning tree
                mst.add(new Edge(parent[u], u, graph[parent[u]][u]));
            }

            for (int v = 0; v < n; v++) {
                int weight = graph[u][v];

                if (weight != INF && weight < dist[v]) {
                    // Update the distance and parent of the neighboring vertex
                    dist[v] = weight;
                    parent[v] = u;
                    pq.offer(new Node(v, dist[v]));
                }
            }
        }

        return mst;
    }

    static class Node {
        int vertex;
        int dist;

        Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }

    static class Edge {
        int src;
        int dest;
        int weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
}



