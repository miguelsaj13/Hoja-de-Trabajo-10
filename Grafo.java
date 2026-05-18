import java.util.*;

/**
 * Representa un grafo dirigido y con peso.
 * @param <T> tipo de dato de los vertices
 */
public class Grafo<T> {

    private static final int INF = Integer.MAX_VALUE;

    private int[][] adyacencia;
    private int[][] dist;
    private int[][] next;

    private int numeroVertices;

    private Map<T, Integer> vertices;

    private List<T> nombresVertices;

    /**
     * Constructor del grafo.
     * @param numeroVertices cantidad de vertices
     */
    public Grafo(int numeroVertices) {

        this.numeroVertices = numeroVertices;

        adyacencia = new int[numeroVertices][numeroVertices];
        dist = new int[numeroVertices][numeroVertices];
        next = new int[numeroVertices][numeroVertices];

        vertices = new HashMap<>();
        nombresVertices = new ArrayList<>();

        inicializarMatrices();
    }

    /**
     * Inicia las matrices del grafo.
     */
    private void inicializarMatrices() {

        for (int i = 0; i < numeroVertices; i++) {

            for (int j = 0; j < numeroVertices; j++) {

                if (i == j) {
                    adyacencia[i][j] = 0;
                } else {
                    adyacencia[i][j] = INF;
                }

                dist[i][j] = adyacencia[i][j];

                next[i][j] = -1;
            }
        }
    }

    /**
     * Agrega un vertice al grafo.
     * @param vertice vertice a agregar
     */
    public void agregarVertice(T vertice) {

        if (!vertices.containsKey(vertice)) {

            vertices.put(vertice, nombresVertices.size());
            nombresVertices.add(vertice);
        }
    }

    /**
     * Verifica si un vertice existe.
     * @param vertice vertice a buscar
     * @return true si existe
     */
    public boolean existeVertice(T vertice) {

        return vertices.containsKey(vertice);
    }

    /**
     * Agrega una arista al grafo.
     * @param origen vertice origen
     * @param destino vertice destino
     * @param peso peso de la conexion
     */
    public void agregarArista(T origen, T destino, int peso) {

        int i = vertices.get(origen);
        int j = vertices.get(destino);

        adyacencia[i][j] = peso;
    }

    /**
     * Agrega una arista usando Edge.
     * @param edge arista a agregar
     */
    public void agregarArista(Edge<T> edge) {

        agregarArista(
            edge.getOrigen(),
            edge.getDestino(),
            edge.getPeso()
        );
    }

    /**
     * Elimina una arista del grafo.
     * @param origen vertice origen
     * @param destino vertice destino
     */
    public void eliminarArista(T origen, T destino) {

        int i = vertices.get(origen);
        int j = vertices.get(destino);

        adyacencia[i][j] = INF;
    }

    /**
     * Copia la matriz de adyacencia a la matriz de distancias.
     */
    private void copiarAdyacenciaADist() {

        for (int i = 0; i < numeroVertices; i++) {

            for (int j = 0; j < numeroVertices; j++) {

                dist[i][j] = adyacencia[i][j];

                if (adyacencia[i][j] != INF && i != j) {
                    next[i][j] = j;
                } else {
                    next[i][j] = -1;
                }
            }
        }
    }

    /**
     * Algoritmo de Floyd.
     */
    public void floyd() {

        copiarAdyacenciaADist();

        for (int k = 0; k < numeroVertices; k++) {

            for (int i = 0; i < numeroVertices; i++) {

                for (int j = 0; j < numeroVertices; j++) {

                    if (dist[i][k] == INF ||
                        dist[k][j] == INF) {
                        continue;
                    }

                    int nuevaDistancia =
                        dist[i][k] + dist[k][j];

                    if (nuevaDistancia < dist[i][j]) {

                        dist[i][j] = nuevaDistancia;

                        next[i][j] = next[i][k];
                    }
                }
            }
        }
    }

    /**
     * Obtiene la distancia minima entre dos vertices.
     * @param origen vertice origen
     * @param destino vertice destino
     * @return distancia minima
     */
    public int getDistancia(T origen, T destino) {

        if (!existeVertice(origen) ||
            !existeVertice(destino)) {

            return INF;
        }

        int i = vertices.get(origen);
        int j = vertices.get(destino);

        return dist[i][j];
    }

    /**
     * Obtiene la ruta minima entre dos vertices.
     * @param origen vertice origen
     * @param destino vertice destino
     * @return lista con la ruta
     */
    public List<T> getRuta(T origen, T destino) {

        List<T> ruta = new ArrayList<>();

        if (!existeVertice(origen) ||
            !existeVertice(destino)) {

            return ruta;
        }

        int i = vertices.get(origen);
        int j = vertices.get(destino);

        if (next[i][j] == -1) {
            return ruta;
        }

        ruta.add(origen);

        while (i != j) {

            i = next[i][j];

            ruta.add(nombresVertices.get(i));
        }

        return ruta;
    }

    /**
     * Imprime la matriz de distancias.
     */
    public void imprimirMatriz() {

        System.out.println("Matriz:");

        for (int i = 0; i < numeroVertices; i++) {

            for (int j = 0; j < numeroVertices; j++) {

                if (dist[i][j] == INF) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(dist[i][j] + "\t");
                }
            }

            System.out.println();
        }
    }

    /**
     * Imprime los vertices del grafo.
     */
    public void imprimirVertices() {

        for (T vertice : nombresVertices) {

            System.out.println(vertice);
        }
    }

    /**
     * Obtiene el centro del grafo.
     * @return vertice centro
     */
    public T getCentroGrafo() {
        int mejorEccentricidad = INF;
        T centro = null;

        for (int i = 0; i < numeroVertices; i++) {

            int eccentricidad = 0;

            boolean desconectado = false;

            for (int j = 0; j < numeroVertices; j++) {

                if (dist[i][j] == INF) {

                    desconectado = true;
                    break;
                }

                eccentricidad = Math.max(eccentricidad, dist[i][j]);
            }

            if (!desconectado && eccentricidad < mejorEccentricidad) {
                mejorEccentricidad = eccentricidad;
                centro = nombresVertices.get(i);
            }
        }

        return centro;
    }
}