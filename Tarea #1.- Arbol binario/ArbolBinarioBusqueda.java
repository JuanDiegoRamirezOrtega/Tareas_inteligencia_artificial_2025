public class ArbolBinarioBusqueda {

    // Clase Nodo que representa cada elemento del árbol
    static class Nodo {
        String nombre;
        Nodo izquierdo, derecho;

        public Nodo(String nombre) {
            this.nombre = nombre;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    private Nodo raiz;

    public ArbolBinarioBusqueda() {
        this.raiz = null;
    }

    // Verifica si el árbol está vacío
    public boolean vacio() {
        return raiz == null;
    }

    // Inserta un nodo en el árbol
    public void insertar(String nombre) {
        raiz = insertarRec(raiz, nombre);
    }

    private Nodo insertarRec(Nodo actual, String nombre) {
        if (actual == null) {
            return new Nodo(nombre);
        }
        if (nombre.compareToIgnoreCase(actual.nombre) < 0) {
            actual.izquierdo = insertarRec(actual.izquierdo, nombre);
        } else if (nombre.compareToIgnoreCase(actual.nombre) > 0) {
            actual.derecho = insertarRec(actual.derecho, nombre);
        }
        return actual;
    }

    // Busca un nodo por su nombre
    public Nodo buscarNodo(String nombre) {
        return buscarRec(raiz, nombre);
    }

    private Nodo buscarRec(Nodo actual, String nombre) {
        if (actual == null || actual.nombre.equalsIgnoreCase(nombre)) {
            return actual;
        }
        return nombre.compareToIgnoreCase(actual.nombre) < 0 ? buscarRec(actual.izquierdo, nombre) : buscarRec(actual.derecho, nombre);
    }

    // Imprime el árbol en orden
    public void imprimirArbol() {
        imprimirInOrden(raiz);
    }

    private void imprimirInOrden(Nodo nodo) {
        if (nodo != null) {
            imprimirInOrden(nodo.izquierdo);
            System.out.println(nodo.nombre);
            imprimirInOrden(nodo.derecho);
        }
    }

    // Método principal para probar el árbol
    public static void main(String[] args) {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        
        System.out.println("¿Árbol vacío? " + arbol.vacio());
        
        arbol.insertar("Carlos");
        arbol.insertar("Ana");
        arbol.insertar("Luis");
        arbol.insertar("Beatriz");

        System.out.println("¿Árbol vacío? " + arbol.vacio());
        
        System.out.println("\nImpresión del árbol en orden:");
        arbol.imprimirArbol();

        String nombreBuscado = "Luis";
        Nodo encontrado = arbol.buscarNodo(nombreBuscado);
        System.out.println("\nNodo " + (encontrado != null ? "encontrado: " + encontrado.nombre : "no encontrado: " + nombreBuscado));
    }
}
