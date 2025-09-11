import java.util.*;

public class DijkstraSimple {
    static final int INF = Integer.MAX_VALUE;
    static final int V = 5; // Número de nodos

    // Encuentra el nodo con la distancia más corta
    int minDistance(int[] dist, boolean[] visited) {
        int min = INF, minIndex = -1;
        for (int v = 0; v < V; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // Algoritmo de Dijkstra
    void dijkstra(int[][] graph, int src) {
        int[] dist = new int[V]; // dist[i] es la distancia más corta de src a i
        boolean[] visited = new boolean[V]; // Marca los nodos ya visitados

        // Inicializa las distancias
        Arrays.fill(dist, INF);
        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, visited); // Elige el nodo más cercano
            visited[u] = true;

            // Actualiza la distancia de los nodos vecinos
            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 &&
                    dist[u] != INF &&
                    dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        // Imprimir resultados
        System.out.println("Nodo \t Distancia desde el nodo fuente");
        for (int i = 0; i < V; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }
    }

    public static void main(String[] args) {
        // Grafo representado como una matriz
        int[][] graph = {
            {0, 10, 0, 30, 100},
            {10, 0, 50, 0, 0},
            {0, 50, 0, 20, 10},
            {30, 0, 20, 0, 60},
            {100, 0, 10, 60, 0}
        };

        DijkstraSimple d = new DijkstraSimple();
        d.dijkstra(graph, 0); // Iniciar desde el nodo 0
    }
}
