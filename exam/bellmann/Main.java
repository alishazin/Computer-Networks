
import java.util.UUID;

class Node {

    String id;
    Character value;
    int s_d = Integer.MAX_VALUE;

    Node(Character value) {
        this.id = UUID.randomUUID().toString();
        this.value = value;
    }

}

class Graph {

    class Edge {

        Node source;
        Node destination;
        int weight;


        Edge(Node src, Node des, int weight) {
            this.source = src;
            this.destination = des;
            this.weight = weight;
        }

    }

    Node[] vertices;
    int vertices_count = 0;

    Edge[] edges;
    int edges_count = 0;

    Graph(int vertices_num, int edges_count) {
        this.vertices = new Node[vertices_num];
        this.edges = new Edge[edges_count];
    }

    public Node addVertex(Character value) {
        Node vertex = new Node(value);
        this.vertices[this.vertices_count] = vertex;
        this.vertices_count++;
        return vertex;
    }

    public void addEdge(Node src, Node des, int weight, Boolean directed) {
        Edge edge = new Edge(src, des, weight);
        this.edges[this.edges_count] = edge;
        this.edges_count++;
        if (directed == false) {
            this.addEdge(des, src, weight, true);
        }

    }

    public void bellmanFordSP(Node source) {

        source.s_d = 0;

        Boolean has_neg_cycle = false;

        for (int i=0; i<this.vertices_count; i++) {

            Boolean updated = false;
            for (int j=0; j<this.edges_count; j++) {
                Edge edgeNow = this.edges[j];

                if (i != this.vertices_count-1 && edgeNow.destination.id != source.id && edgeNow.destination.s_d > edgeNow.source.s_d + edgeNow.weight) {
                    edgeNow.destination.s_d = edgeNow.source.s_d + edgeNow.weight;
                    updated = true;
                }
                
                if (i == this.vertices_count-1) {
                    if (edgeNow.destination.id != source.id && edgeNow.destination.s_d > edgeNow.source.s_d + edgeNow.weight) {
                        has_neg_cycle = true;
                        edgeNow.destination.s_d = edgeNow.source.s_d + edgeNow.weight;
                        System.out.println(edgeNow.destination.value + " is affected.");
                    }
                }

            }

            if (i == this.vertices_count-1) {
                if (has_neg_cycle) {
                    System.out.println("Graph has negative cycle(s)");
                } else {
                    System.out.println("Graph has no negative cycle(s)");
                }
            } else {
                if (!updated) {
                    System.out.println("Graph has no negative cycle(s)");
                    break;
                } 
            }
        }

        for (int i=0; i<this.vertices_count; i++) {
            Node vertexNow = this.vertices[i];
            if (vertexNow.id == source.id) continue;
            System.out.println(vertexNow.value + " : " + vertexNow.s_d);
        }

    }

}

public class Main {
 
    public static void main(String[] args) {

        Graph g = new Graph(5, 9);
        
        Node A = g.addVertex('A');
        Node B = g.addVertex('B');
        Node C = g.addVertex('C');
        Node D = g.addVertex('D');
        Node E = g.addVertex('E');
        
        g.addEdge(A, B, 4, true);
        g.addEdge(A, C, 2, true);
        
        g.addEdge(B, C, 3, true);
        g.addEdge(B, D, 2, true);
        g.addEdge(B, E, 3, true);

        g.addEdge(C, B, 1, true);
        g.addEdge(C, D, 4, true);
        g.addEdge(C, E, 5, true);

        g.addEdge(E, D, -5, true);
        
        g.bellmanFordSP(A);

        // g.display();

    }

}