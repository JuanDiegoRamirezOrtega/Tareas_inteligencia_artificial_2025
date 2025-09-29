import java.util.*;

public class Arbol {

    // ================= BFS =================
    public static List<Nodo> bfsNodos(Nodo inicio) {
        Queue<Nodo> frontera = new LinkedList<>();
        Set<Nodo> visitados = new HashSet<>();
        frontera.add(inicio);
        visitados.add(inicio);

        while (!frontera.isEmpty()) {
            Nodo actual = frontera.poll();
            if (actual.esMeta()) return actual.obtenerNodosCamino(); 

            for (Nodo hijo : actual.generarHijos()) {
                if (!visitados.contains(hijo)) {
                    frontera.add(hijo);
                    visitados.add(hijo);
                }
            }
        }
        return null;
    }

    public static List<String> bfs(Nodo inicio) {
        List<Nodo> nodos = bfsNodos(inicio);
        if (nodos == null) return null;
        List<String> camino = new ArrayList<>();
        for (int i = 1; i < nodos.size(); i++) camino.add(nodos.get(i).accion);
        return camino;
    }

    // ================= DFS =================
    public static List<Nodo> dfsNodos(Nodo inicio, int limite) {
        Stack<Nodo> pila = new Stack<>();
        Set<Nodo> visitados = new HashSet<>();
        pila.push(inicio);

        while (!pila.isEmpty()) {
            Nodo actual = pila.pop();
            if (actual.esMeta()) return actual.obtenerNodosCamino();
            if (visitados.contains(actual)) continue;
            if (actual.profundidad >= limite) continue;
            
            visitados.add(actual);
            for (Nodo hijo : actual.generarHijos()) {
                if (!visitados.contains(hijo) && hijo.profundidad <= limite) {
                    pila.push(hijo);
                }
            }
        }
        return null;
    }

    public static List<String> dfs(Nodo inicio, int limite) {
        List<Nodo> nodos = dfsNodos(inicio, limite);
        if (nodos == null) return null;
        List<String> camino = new ArrayList<>();
        for (int i = 1; i < nodos.size(); i++) camino.add(nodos.get(i).accion);
        return camino;
    }

    // ================= UCS =================
    public static List<Nodo> ucsNodos(Nodo inicio) {
        PriorityQueue<Nodo> frontera = new PriorityQueue<>(Comparator.comparingInt(n -> n.costo));
        Map<Nodo, Integer> costos = new HashMap<>();
        frontera.add(inicio);
        costos.put(inicio, 0);

        while (!frontera.isEmpty()) {
            Nodo actual = frontera.poll();
            if (actual.esMeta()) return actual.obtenerNodosCamino();

            for (Nodo hijo : actual.generarHijos()) {
                int nuevoCosto = actual.costo + 1;
                if (!costos.containsKey(hijo) || nuevoCosto < costos.get(hijo)) {
                    costos.put(hijo, nuevoCosto);
                    frontera.add(hijo);
                }
            }
        }
        return null;
    }

    public static List<String> ucs(Nodo inicio) {
        List<Nodo> nodos = ucsNodos(inicio);
        if (nodos == null) return null;
        List<String> camino = new ArrayList<>();
        for (int i = 1; i < nodos.size(); i++) camino.add(nodos.get(i).accion);
        return camino;
    }

    // ================= IDS =================
    public static List<Nodo> idsNodos(Nodo inicio) {
        for (int limite = 0; limite <= 50; limite++) { // Límite máximo de 50 para evitar bucles infinitos
            List<Nodo> resultado = dfsNodosConLimite(inicio, limite);
            if (resultado != null) return resultado;
        }
        return null;
    }

    private static List<Nodo> dfsNodosConLimite(Nodo inicio, int limite) {
        return dfsRecNodosConLimite(inicio, limite, new HashSet<>());
    }

    private static List<Nodo> dfsRecNodosConLimite(Nodo nodo, int limite, Set<Nodo> visitados) {
        if (nodo.esMeta()) return nodo.obtenerNodosCamino();
        if (limite == 0) return null;
        if (visitados.contains(nodo)) return null;
        
        visitados.add(nodo);
        for (Nodo hijo : nodo.generarHijos()) {
            if (!visitados.contains(hijo)) {
                List<Nodo> camino = dfsRecNodosConLimite(hijo, limite - 1, visitados);
                if (camino != null) return camino;
            }
        }
        return null;
    }

    public static List<String> ids(Nodo inicio) {
        List<Nodo> nodos = idsNodos(inicio);
        if (nodos == null) return null;
        List<String> camino = new ArrayList<>();
        for (int i = 1; i < nodos.size(); i++) camino.add(nodos.get(i).accion);
        return camino;
    }

    // ================= A* =================
    public static List<Nodo> aStarNodos(Nodo inicio) {
        PriorityQueue<Nodo> frontera = new PriorityQueue<>(Comparator.comparingInt(n -> n.costo + n.heuristica()));
        Set<Nodo> visitados = new HashSet<>();
        frontera.add(inicio);

        while (!frontera.isEmpty()) {
            Nodo actual = frontera.poll();
            if (actual.esMeta()) return actual.obtenerNodosCamino();
            if (visitados.contains(actual)) continue;

            visitados.add(actual);

            for (Nodo hijo : actual.generarHijos()) {
                if (!visitados.contains(hijo)) frontera.add(hijo);
            }
        }
        return null;
    }

    public static List<String> aStar(Nodo inicio) {
        List<Nodo> nodos = aStarNodos(inicio);
        if (nodos == null) return null;
        List<String> camino = new ArrayList<>();
        for (int i = 1; i < nodos.size(); i++) camino.add(nodos.get(i).accion);
        return camino;
    }
}
