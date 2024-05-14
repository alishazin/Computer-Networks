import java.util.*;

public class Main {

    public static Map<Character, Integer> dijkstra(Map<Character, Map<Character, Integer>> graph, char start) {
        Map<Character, Integer> distances = new HashMap<>();
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for (char node : graph.keySet()) {
            distances.put(node, node == start ? 0 : Integer.MAX_VALUE);
            pq.offer(new AbstractMap.SimpleEntry<>(node, distances.get(node)));
        }

        while (!pq.isEmpty()) {
            char current = pq.poll().getKey();
            for (Map.Entry<Character, Integer> neighbor : graph.get(current).entrySet()) {
                int distance = distances.get(current) + neighbor.getValue();
                if (distance < distances.get(neighbor.getKey())) {
                    distances.put(neighbor.getKey(), distance);
                    pq.offer(new AbstractMap.SimpleEntry<>(neighbor.getKey(), distance));
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        Map<Character, Map<Character, Integer>> graph = new HashMap<>();
        graph.put('A', Map.of('B', 1, 'C', 4));
        graph.put('B', Map.of('A', 1, 'C', 2, 'D', 5));
        graph.put('C', Map.of('A', 4, 'B', 2, 'D', 1));
        graph.put('D', Map.of('B', 5, 'C', 1));

        char startNode = 'A';
        Map<Character, Integer> shortestDistances = dijkstra(graph, startNode);
        System.out.println("Shortest distances from node " + startNode + ": " + shortestDistances);
    }
}
