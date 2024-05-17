
import java.util.UUID;

class Node {

    String id;
    Character value;
    int s_d = Integer.MAX_VALUE;
    Boolean visited = false;
    Boolean in_priority_q = false;

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

    public void dijkstraSP(Node source) {

        source.s_d = 0;
        Node cur_source = source;

        while (true) {
            
            for (int i=0; i<this.edges_count; i++) {
                Edge edgeNow = this.edges[i];
                if (edgeNow.source.id == cur_source.id && edgeNow.destination.id != source.id) {
                    edgeNow.destination.s_d = Math.min(edgeNow.destination.s_d, edgeNow.source.s_d + edgeNow.weight);
                    edgeNow.destination.in_priority_q = true;
                }
            }
            cur_source.visited = true;

            cur_source = popPriorityQ(source);
            if (cur_source == null) {
                break;
            }

        }

        for (int i=0; i<this.vertices_count; i++) {
            Node vertexNow = this.vertices[i];
            if (vertexNow.id == source.id) continue;
            System.out.println(vertexNow.value + " : " + vertexNow.s_d);
        }

    }

    public Node popPriorityQ(Node source) {
        Node maxPriority = null;
        for (int i=0; i<this.vertices_count; i++) {
            Node vertexNow = this.vertices[i];
            if (vertexNow.id != source.id && vertexNow.visited == false && vertexNow.in_priority_q == true) {
                if (maxPriority == null) {
                    maxPriority = vertexNow;
                } else {
                    if (vertexNow.s_d < maxPriority.s_d) {
                        maxPriority = vertexNow;
                    }
                }
            }
        }
        return maxPriority;
    }

}

public class Main {
    
    public static void main(String[] args) {

        Graph g = new Graph(6, 18);
        
        Node n1 = g.addVertex('1');
        Node n2 = g.addVertex('2');
        Node n3 = g.addVertex('3');
        Node n4 = g.addVertex('4');
        Node n5 = g.addVertex('5');
        Node n6 = g.addVertex('6');
            
        g.addEdge(n1, n6, 14, false);
        g.addEdge(n1, n3, 9, false);
        g.addEdge(n1, n2, 7, false);

        g.addEdge(n6, n3, 2, false);
        g.addEdge(n2, n3, 10, false);

        g.addEdge(n6, n5, 9, false);
        g.addEdge(n3, n4, 11, false);
        g.addEdge(n2, n4, 15, false);

        g.addEdge(n5, n4, 6, false);
        
        g.dijkstraSP(n1);

    }

}