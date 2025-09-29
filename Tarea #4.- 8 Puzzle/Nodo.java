import java.util.*;

public class Nodo {
    public int[] estado;
    public Nodo padre;
    public String accion;
    public int costo;
    public int profundidad;

    public static final int[] META = {1, 2, 3, 4, 5, 6, 7, 8, 0};

    public Nodo(int[] estado) {
        this.estado = estado;
        this.padre = null;
        this.accion = null;
        this.costo = 0;
        this.profundidad = 0;
    }

    public Nodo(int[] estado, Nodo padre, String accion, int costo, int profundidad) {
        this.estado = estado;
        this.padre = padre;
        this.accion = accion;
        this.costo = costo;
        this.profundidad = profundidad;
    }

    // Validación de estado
    public static void validarEstado(int[] estado) {
        if (estado.length != 9) {
            throw new IllegalArgumentException("❌ El estado debe tener exactamente 9 valores.");
        }
        Set<Integer> conjunto = new HashSet<>();
        for (int v : estado) conjunto.add(v);
        for (int i = 0; i <= 8; i++) {
            if (!conjunto.contains(i)) {
                throw new IllegalArgumentException("❌ El estado debe contener todos los dígitos del 0 al 8.");
            }
        }
    }

    public boolean esMeta() {
        return Arrays.equals(estado, META);
    }

    // Genera los vecinos/hijos posibles
    public List<Nodo> generarHijos() {
        List<Nodo> hijos = new ArrayList<>();
        int pos0 = -1;
        for (int i = 0; i < 9; i++) {
            if (estado[i] == 0) {
                pos0 = i;
                break;
            }
        }

        int fila = pos0 / 3, col = pos0 % 3;
        int[][] movimientos = {
            {-1, 0, 'U'}, // arriba
            {1, 0, 'D'},  // abajo
            {0, -1, 'L'}, // izquierda
            {0, 1, 'R'}   // derecha
        };

        for (int[] m : movimientos) {
            int nuevaFila = fila + m[0], nuevaCol = col + m[1];
            if (nuevaFila >= 0 && nuevaFila < 3 && nuevaCol >= 0 && nuevaCol < 3) {
                int[] nuevoEstado = estado.clone();
                int posSwap = nuevaFila * 3 + nuevaCol;
                nuevoEstado[pos0] = nuevoEstado[posSwap];
                nuevoEstado[posSwap] = 0;
                hijos.add(new Nodo(nuevoEstado, this, Character.toString((char)m[2]), this.costo + 1, this.profundidad + 1));
            }
        }
        return hijos;
    }

    // Devuelve lista de nodos desde inicial hasta este
    public List<Nodo> obtenerNodosCamino() {
        List<Nodo> camino = new ArrayList<>();
        Nodo actual = this;
        while (actual != null) {
            camino.add(actual);
            actual = actual.padre;
        }
        Collections.reverse(camino);
        return camino;
    }

    // Heurística Manhattan
    public int heuristica() {
        int dist = 0;
        for (int i = 0; i < estado.length; i++) {
            int val = estado[i];
            if (val == 0) continue;
            int filaMeta = (val - 1) / 3;
            int colMeta = (val - 1) % 3;
            int fila = i / 3;
            int col = i % 3;
            dist += Math.abs(fila - filaMeta) + Math.abs(col - colMeta);
        }
        return dist;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Nodo)) return false;
        return Arrays.equals(estado, ((Nodo)o).estado);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(estado);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("| ").append(estado[0] == 0 ? " " : estado[0]).append(" ")
          .append(estado[1] == 0 ? " " : estado[1]).append(" ")
          .append(estado[2] == 0 ? " " : estado[2]).append(" |\n");
        sb.append("| ").append(estado[3] == 0 ? " " : estado[3]).append(" ")
          .append(estado[4] == 0 ? " " : estado[4]).append(" ")
          .append(estado[5] == 0 ? " " : estado[5]).append(" |\n");
        sb.append("| ").append(estado[6] == 0 ? " " : estado[6]).append(" ")
          .append(estado[7] == 0 ? " " : estado[7]).append(" ")
          .append(estado[8] == 0 ? " " : estado[8]).append(" |\n");
        return sb.toString();
    }
}
