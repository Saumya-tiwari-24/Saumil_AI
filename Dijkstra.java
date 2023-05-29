import java.util.*;

public class Dijkstra {

    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        // Example graph represented as an adjacency matrix
        int[][] graph = {
            {0, 4, 2, 0, 0, 0},
            {4, 0, 1, 5, 0, 0},
            {2, 1, 0, 8, 10, 0},
            {0, 5, 8, 0, 2, 6},
            {0, 0, 10, 2, 0, 3},
            {0, 0, 0, 6, 3, 0}
        };

        int source = 0; // Source vertex

        int[] shortestDistances = dijkstra(graph, source);

        // Print shortest distances from the source vertex to all other vertices
        System.out.println("Shortest Distances from source vertex " + source + ":");
        for (int i = 0; i < shortestDistances.length; i++) {
            System.out.println("Vertex " + i + ": " + shortestDistances[i]);
        }
    }

    private static int[] dijkstra(int[][] graph, int source) {
        int n = graph.length;
        int[] distances = new int[n]; // Shortest distances from the source vertex
        boolean[] visited = new boolean[n]; // Mark vertices as visited

        Arrays.fill(distances, INF); // Initialize distances with infinity
        distances[source] = 0; // Set distance of source vertex to 0

        for (int i = 0; i < n - 1; i++) {
            int minVertex = findMinVertex(distances, visited); // Find the vertex with the minimum distance

            visited[minVertex] = true; // Mark the vertex as visited

            for (int j = 0; j < n; j++) {
                // Update distances of adjacent vertices if a shorter path is found
                if (graph[minVertex][j] != 0 && !visited[j]
                        && distances[minVertex] + graph[minVertex][j] < distances[j]) {
                    distances[j] = distances[minVertex] + graph[minVertex][j];
                }
            }
        }

        return distances;
    }

    private static int findMinVertex(int[] distances, boolean[] visited) {
        int minVertex = -1;
        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && (minVertex == -1 || distances[i] < distances[minVertex])) {
                minVertex = i;
            }
        }
        return minVertex;
    }
}







