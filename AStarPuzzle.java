import java.util.*;

public class AStarPuzzle {

    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        int[][] initialState = {{1, 2, 3}, {4, 0, 5}, {6, 7, 8}};
        int[][] goalState = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

        // Create a priority queue to store the open nodes.
        PriorityQueue<Node> open = new PriorityQueue<>((a, b) -> (a.cost + a.h) - (b.cost + b.h));

        // Add the initial state to the open list.
        open.add(new Node(initialState, 0, calculateHScore(initialState, goalState), null));

        while (!open.isEmpty()) {
            // Get the node with the lowest f-score.
            Node current = open.poll();

            // If the current node is the goal state, then we have found a solution.
            if (Arrays.deepEquals(current.state, goalState)) {
                System.out.println("Found a solution!");
                printPath(current);
                return;
            }

            // For each of the neighbors of the current node,
            for (int i = 0; i < 4; i++) {
                int x = current.getBlankX() + dx[i];
                int y = current.getBlankY() + dy[i];

                // If the neighbor is within the bounds of the board,
                if (x >= 0 && x < 3 && y >= 0 && y < 3) {
                    // And the neighbor is not the empty space,
                    int value = current.state[x][y];
                    if (value != 0) {
                        // Then create a new node with the neighbor's state.
                        int[][] newState = deepCopy(current.state);
                        newState[x][y] = 0;
                        newState[current.getBlankX()][current.getBlankY()] = value;

                        // Calculate the h-score for the neighbor.
                        int h = calculateHScore(newState, goalState);

                        // Create the neighbor node and add it to the open list.
                        Node neighbor = new Node(newState, current.cost + 1, h, current);
                        open.add(neighbor);
                    }
                }
            }
        }

        // If we reach this point, then there is no solution.
        System.out.println("No solution found!");
    }

    private static int calculateHScore(int[][] state, int[][] goalState) {
        int h = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] != goalState[i][j]) {
                    h++;
                }
            }
        }
        return h;
    }

    private static void printPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.parent;
        }

        Collections.reverse(path);

        for (Node n : path) {
            System.out.println(Arrays.deepToString(n.state));
        }
    }

    private static int[][] deepCopy(int[][] array) {
        int[][] copy = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(array[i], 0, copy[i], 0, 3);
        }
        return copy;
    }
}

class Node {
    int[][] state;
    int cost;
    int h;
    Node parent;

    public Node(int[][] state, int cost, int h, Node parent) {
        this.state = state;
        this.cost = cost;
        this.h = h;
        this.parent = parent;
    }

    public int getBlankX() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == 0) {
                    return i;
                }
            }
        }
        return -1; // Blank not found
    }

    public int getBlankY() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == 0) {
                    return j;
                }
            }
        }
        return -1; // Blank not found
    }
}





