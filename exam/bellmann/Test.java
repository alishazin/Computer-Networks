
import java.util.UUID;

class Node {
    String id;
    char value;
    int s_d = Integer.MAX_VALUE;

    Node(char value) {
        this.id = UUID.randomUUID().toString();
        this.value = value;
    }
}

class Graph {

    class Edge {
        
        Node src;
        Node des;
        int weight;

        Edge(Node src, Node des, int weight) {
            this.src = src;
            this.des = des;
            this.weight = weight;
        }
    }

    Node vertices[];
    int verc = 0;

    Edge edges[];
    int edgc = 0;

    Graph(int v, int e) {
        vertices = new Node[v];
        edges = new Edge[e];
    }

    Node addVertex(char value) {
        Node v = new Node(value);
        this.vertices[this.verc] = v;
        this.verc++;
        return v;
    }

    void addEdge(Node src, Node des, int weight, Boolean directed) {
        Edge e = new Edge(src, des, weight);
        this.edges[this.edgc] = e;
        this.edgc++;
        if (!directed) {
            this.addEdge(des, src, weight, directed);
        }
    }

    void bellman(Node source) {

        source.s_d = 0;
        Boolean negc = false;

        for (int i=0; i<this.verc; i++) {

            Boolean updated=true;
            for (int j=0; j<this.edgc; j++) {
                Edge now = this.edges[j];

                if (i == this.verc-1) {
                    if (now.des.id != source.id && now.des.s_d > now.src.s_d + now.weight) {
                        negc = true;
                    }
                } else {
                    if (now.des.id != source.id && now.des.s_d > now.src.s_d + now.weight) {
                        now.des.s_d = now.src.s_d + now.weight;
                        updated=true;
                    }
                }
            }

            if (!updated) break;

        }

        if (negc) {
            System.out.println("Graph has negative cycle");
        } else {
            System.out.println("Graph does not have negative cycle");

            for (int i=0; i<this.verc; i++) {
                Node now=this.vertices[i];
                if (now.id == source.id) continue;
                System.out.println(now.value + " : " + now.s_d);
            }
        }

    }


}

public class Test {
    public static void main(String[] args) {
        
        Graph g = new Graph(4, 5);
        
        Node n1 = g.addVertex('1');
        Node n2 = g.addVertex('3');
        Node n3 = g.addVertex('2');
        Node n4 = g.addVertex('4');
        
        g.addEdge(n1, n2, 4, true);
        g.addEdge(n1, n3, 5, true);
        g.addEdge(n3, n2, 7, true);
        g.addEdge(n4, n3, -15, true);
        g.addEdge(n2, n4, 7, true);
        
        g.bellman(n1);

        // Graph g = new Graph(5, 9);
        
        // Node A = g.addVertex('A');
        // Node B = g.addVertex('B');
        // Node C = g.addVertex('C');
        // Node D = g.addVertex('D');
        // Node E = g.addVertex('E');
        
        // g.addEdge(A, B, 4, true);
        // g.addEdge(A, C, 2, true);
        
        // g.addEdge(B, C, 3, true);
        // g.addEdge(B, D, 2, true);
        // g.addEdge(B, E, 3, true);

        // g.addEdge(C, B, 1, true);
        // g.addEdge(C, D, 4, true);
        // g.addEdge(C, E, 5, true);

        // g.addEdge(E, D, -5, true);
        
        // g.bellman(A);

    }
}