import java.util.*;

public class PrimMST {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] graph = {
                {INF, 2, INF, 6, INF},
                {2, INF, 3, 8, 5},
                {INF, 3, INF, INF, 7},
                {6, 8, INF, INF, 9},
                {INF, 5, 7, 9, INF}
        };

        int[] parent = primMST(graph);

        System.out.println("Minimum Spanning Tree Edges:");
        for (int i = 1; i < graph.length; i++) {
            System.out.println(parent[i] + " - " + i);
        }
    }

    private static int[] primMST(int[][] graph) {
        int n = graph.length;

        int[] parent = new int[n]; // Store the parent of each vertex in the MST
        int[] key = new int[n]; // Key values used to pick minimum weight edges
        boolean[] visited = new boolean[n]; // Track visited vertices

        Arrays.fill(key, INF); // Initialize key values with infinity
        Arrays.fill(visited, false); // Mark all vertices as not visited

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.key));
        pq.offer(new Node(0, 0)); // Start with the first vertex as the source
        key[0] = 0; // Set key value of source vertex to 0

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.vertex;

            visited[u] = true; // Mark the vertex as visited

            for (int v = 0; v < n; v++) {
                int weight = graph[u][v];

                if (!visited[v] && weight != INF && weight < key[v]) {
                    // Update key value and enqueue the neighboring vertex
                    pq.offer(new Node(v, weight));
                    parent[v] = u;
                    key[v] = weight;
                }
            }
        }

        return parent;
    }

    static class Node {
        int vertex;
        int key;

        Node(int vertex, int key) {
            this.vertex = vertex;
            this.key = key;
        }
    }
}





