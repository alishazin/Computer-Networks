
import java.util.UUID;

class Node {

    String id;
    char value;
    int s_d = Integer.MAX_VALUE;
    Boolean visited = false;
    Boolean inp = false;

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

    Node[] vertices;
    int v_c = 0;

    Edge[] edges;
    int e_c = 0;

    Graph(int v_c, int e_c) {
        this.vertices = new Node[v_c];
        this.edges = new Edge[e_c];
    }

    public Node addVertex(char value) {
        Node vertex = new Node(value);
        this.vertices[this.v_c] = vertex;
        this.v_c++;
        return vertex;
    }

    public void addEdge(Node src, Node des, int weight, Boolean directed) {
        Edge edge = new Edge(src, des, weight);
        this.edges[this.e_c] = edge;
        this.e_c++;
        if (directed == false) {
            this.addEdge(des, src, weight, true);
        }
    }

    public Node popPriorityQ(Node source) {

        Node max=null;
        for (int i=0; i<this.v_c; i++) {
            Node now = this.vertices[i];
            if (now.id != source.id && now.visited == false && now.inp == true) {
                if (max == null) {
                    max=now;
                } else {
                    if (now.s_d < max.s_d) {
                        max =now;
                    }
                }
            }
        }
        return max;

    }

    public void dijkstraSP(Node source) {

        source.s_d = 0;
        Node cur_s = source;

        while (true) {

            for (int i=0; i<this.e_c; i++) {
                Edge enow = this.edges[i];
                if (enow.src.id == cur_s.id && enow.des.id != source.id) {
                    enow.des.s_d = Math.min(enow.des.s_d, enow.src.s_d + enow.weight);
                    enow.des.inp = true;
                }
            }
            cur_s.visited = true;

            cur_s = popPriorityQ(source);
            if (cur_s == null) break; 

        }

        for (int i=0; i<this.v_c; i++) {
            Node now = this.vertices[i];
            if (now.id == source.id) continue;
            System.out.println(now.value + " : " + now.s_d);
        }


    }

}

public class Test {

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