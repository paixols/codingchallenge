import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
* BFS
* Breadth First Search is a simple strategy in which the root node is expanded first, then all the successors of the
* root node are expanded next, then their successors and so on until the best possible path has been found. Due to the
* fact that this strategy for graph traversal has no additional information about states beyond of what is provided in
* the problem definition, BFS is classed as an uninformed or blind search.
*
* - BFS uses the queue data structure as opposed to the stack used in DFS (Depth First Search)
* - The Queue data structure is a First in - First out (FIFO) data structure that stores all the nodes that we have
*   to explore and each time a node is explored it is added to our set of visited nodes.
*
* - This algorithm will search recursively every level of the binary tree going deeper and deeper.
* */
public class BreadthFirstSearchTutorial {

    /*
    * How it Works
    *
    * Say we had a map of the London Underground, each station would represent a node which would itself have a smaller
    * list of stations that are directly connected to it. The entire map of the London Underground represents our graph
    * and each of the stations on that graph represent a node.
    * */

    public static class Node {

        //Unique identifier of the node
        public String stationName;
        //List of nodes connected to this node (child nodes)
        Node leftChild;
        Node rightChild;

        public Node(String stationName, Node firstChild, Node secondChild) {
            this.stationName = stationName;
            this.leftChild = firstChild;
            this.rightChild = secondChild;
        }

        public ArrayList<Node> getChildren() {
            ArrayList<Node> childNodes = new ArrayList<>();
            if (this.leftChild != null) {
                childNodes.add(leftChild);
            }
            if (this.rightChild != null) {
                childNodes.add(rightChild);
            }
            return childNodes;
        }

    }

    /*
    * Basic BFS
    * */
    public static class BreadthFirstSearch {

        Node startNode;
        Node goalNode;

        public BreadthFirstSearch(Node start, Node goal) {
            this.startNode = start;
            this.goalNode = goal;
        }

        public Boolean compute() {

            if (this.startNode.equals(goalNode)) {
                System.out.print("Goal Node Found!");
                System.out.print(startNode);
            }

            Queue<Node> queue = new LinkedList<>();
            ArrayList<Node> explored = new ArrayList<>();
            queue.add(startNode);
            explored.add(startNode);

            while (!queue.isEmpty()) {
                Node current = queue.remove();
                if (current.equals(this.goalNode)) {
                    System.out.print(explored + "\n");
                    System.out.print("Number of nodes explored: " + explored.size() + "\n");
                    return true;
                } else {
                    if (current.getChildren().isEmpty()) {
                        return false;
                    } else {
                        queue.addAll(current.getChildren());
                    }
                }
                explored.add(current);
            }
            return false;
        }

    }

    /*
    * Driver class which instantiates some example nodes an then performs the breadth search first upon the nodes
    *
    * - Whilst Breadth First Search can be useful in graph traversal algorithms, one of its flaws is that it finds the
    *   shallowest goal node which doesn't necessarily mean it's the most optimal solution. BFS is only ever optimal if
    *   for instance you happen to be in a scenario where all actions have the same cost.
    *
    * - Stations:
    *
    *                        s6
    *                 /             \
    *                s5             s4
    *             /     \          /   \
    *           s4       s3       s2   s3
    *          /  \      / \      /    / \
    *         s2   s3   S1  s2   s1   s1  s2
    *        /              /             /
    *       s1             s1            s1
    *
    * - Path:
    *   Station6 -> Station5 -> Station3 -> Station1
    *   10 Nodes explored to the closest path (S1)
    * */
    public static class Driver {

        public void runDriver() {
            Node station1 = new Node("Westminister", null, null);
            Node station2 = new Node("Waterloo", station1, null);
            Node station3 = new Node("Trafalgar Square", station1, station2);
            Node station4 = new Node("Canary Wharf", station2, station3);
            Node station5 = new Node("London Bridge", station4, station3);
            Node station6 = new Node("Tottenham Court Road", station5, station4);

            BreadthFirstSearch bfs = new BreadthFirstSearch(station6, station1);

            if (bfs.compute()) {
                System.out.print("Path Found");
            }
        }
    }

}
