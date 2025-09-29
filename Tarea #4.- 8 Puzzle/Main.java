import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            int[] tableroInicial = {8, 6, 7, 2, 5, 4, 3, 0, 1}; // estado inicial editable

            Nodo.validarEstado(tableroInicial);
            Nodo inicio = new Nodo(tableroInicial);

            System.out.println("Estado inicial:");
            System.out.println(inicio);
            System.out.println("Estado meta:");
            System.out.println(new Nodo(Nodo.META));

            Scanner sc = new Scanner(System.in);
            System.out.println("Elige el algoritmo para resolver el 8-puzzle:");
            System.out.println("1. BFS");
            System.out.println("2. DFS (con límite de profundidad)");
            System.out.println("3. UCS");
            System.out.println("4. IDS");
            System.out.println("5. A* (heurística de Coherencia Secuencial)");
            System.out.print("Opción: ");
            int opcion = sc.nextInt();

            List<Nodo> nodosCamino = null;

            switch (opcion) {
                case 1:
                    nodosCamino = Arbol.bfsNodos(inicio);
                    break;
                case 2:
                    System.out.print("Introduce límite de profundidad DFS: ");
                    int limiteDFS = sc.nextInt();
                    nodosCamino = Arbol.dfsNodos(inicio, limiteDFS);
                    break;
                case 3:
                    nodosCamino = Arbol.ucsNodos(inicio);
                    break;
                case 4:
                    nodosCamino = Arbol.idsNodos(inicio);
                    break;
                case 5:
                    System.out.println("🚀 Usando Heurística de Coherencia Secuencial");
                    System.out.println();
                    nodosCamino = Arbol.aStarNodos(inicio);
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
                    sc.close();
                    return;
            }

            if (nodosCamino != null) {
                System.out.println();
                System.out.println("**Solución encontrada en " + (nodosCamino.size() - 1) + " movimientos**");
                System.out.println();
                for (int i = 0; i < nodosCamino.size(); i++) {
                    Nodo n = nodosCamino.get(i);
                    if (i == 0) {
                        System.out.println("Estado inicial:");
                    } else {
                        System.out.println("Movimiento " + i + ": " + n.accion);
                    }
                    System.out.println(n);
                }
            } else {
                System.out.println("❌ No se encontró solución.");
            }

            sc.close();

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.err.println("Corrige el estado inicial y vuelve a ejecutar.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
