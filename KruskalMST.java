import java.util.*;

public class KruskalMST {
    public static void main(String[] args) {
        // Create the graph
        int[][] graph = {
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}
        };

        // Find the minimum spanning tree
        List<Edge> mst = kruskalMST(graph);

        // Print the minimum spanning tree edges
        System.out.println("Minimum Spanning Tree Edges:");
        for (Edge edge : mst) {
            System.out.println(edge.src + " - " + edge.dest + ", Weight: " + edge.weight);
        }
    }

    private static List<Edge> kruskalMST(int[][] graph) {
        int n = graph.length;

        // Create a list to store the edges of the minimum spanning tree
        List<Edge> mst = new ArrayList<>();

        // Create a priority queue to store all the edges sorted by weight
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));

        // Add all edges to the priority queue
        for (int src = 0; src < n; src++) {
            for (int dest = src + 1; dest < n; dest++) {
                int weight = graph[src][dest];
                if (weight != 0) {
                    pq.offer(new Edge(src, dest, weight));
                }
            }
        }

        // Create a disjoint set to keep track of the connected components
        DisjointSet ds = new DisjointSet(n);

        while (!pq.isEmpty() && mst.size() < n - 1) {
            // Get the edge with the minimum weight from the priority queue
            Edge edge = pq.poll();
            int src = edge.src;
            int dest = edge.dest;

            // Check if adding this edge creates a cycle
            if (ds.find(src) != ds.find(dest)) {
                // Include the edge in the minimum spanning tree
                mst.add(edge);

                // Merge the two sets into one
                ds.union(src, dest);
            }
        }

        return mst;
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

    static class DisjointSet {
        int[] parent;
        int[] rank;

        DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];

            // Initialize parent and rank arrays
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                // Path compression: Make the parent of x the root of its subtree
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);

            if (xRoot == yRoot) {
                return;
            }

            // Union by rank: Attach the smaller rank tree under the root of the higher rank tree
            if (rank[xRoot] < rank[yRoot]) {
                parent[xRoot] = yRoot;
            } else if (rank[xRoot] > rank[yRoot]) {
                parent[yRoot] = xRoot;
            } else {
                parent[yRoot] = xRoot;
                rank[xRoot]++;
            }
        }
    }
}






