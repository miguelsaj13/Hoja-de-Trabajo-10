import java.util.*;

public class Grafo<T> {

    private static final int INF = 9999999;

    private int[][] dist;
    private int[][] next;

    private int numeroVertices;

    private Map<T, Integer> vertices;

    private List<T> nombresVertices;

    public Grafo(int numeroVertices) {

        this.numeroVertices = numeroVertices;

        dist = new int[numeroVertices][numeroVertices];
        next = new int[numeroVertices][numeroVertices];

        vertices = new HashMap<>();
        nombresVertices = new ArrayList<>();

        inicializarMatrices();
    }

    private void inicializarMatrices() {

        for (int i = 0; i < numeroVertices; i++) {

            for (int j = 0; j < numeroVertices; j++) {

                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = INF;
                }

                next[i][j] = -1;
            }
        }
    }

    public void agregarVertice(T vertice) {

        if (!vertices.containsKey(vertice)) {

            vertices.put(vertice, nombresVertices.size());
            nombresVertices.add(vertice);
        }
    }

    public void agregarArista(T origen, T destino, int peso) {

        int i = vertices.get(origen);
        int j = vertices.get(destino);

        dist[i][j] = peso;

        next[i][j] = j;
    }

    public void eliminarArista(T origen, T destino) {

        int i = vertices.get(origen);
        int j = vertices.get(destino);

        dist[i][j] = INF;
        next[i][j] = -1;
    }

    public void floyd() {

        for (int k = 0; k < numeroVertices; k++) {

            for (int i = 0; i < numeroVertices; i++) {

                for (int j = 0; j < numeroVertices; j++) {

                    if (dist[i][k] == INF || dist[k][j] == INF) {
                        continue;
                    }

                    int nuevaDistancia = dist[i][k] + dist[k][j];

                    if (nuevaDistancia < dist[i][j]) {

                        dist[i][j] = nuevaDistancia;

                        next[i][j] = next[i][k];
                    }
                }
            }
        }
    }

    public int getDistancia(T origen, T destino) {

        int i = vertices.get(origen);
        int j = vertices.get(destino);

        return dist[i][j];
    }

    public List<T> getRuta(T origen, T destino) {

        List<T> ruta = new ArrayList<>();

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

    public T getCentroGrafo() {

        int mejorEccentricidad = INF;

        T centro = null;

        for (int i = 0; i < numeroVertices; i++) {

            int eccentricidad = 0;

            for (int j = 0; j < numeroVertices; j++) {

                if (dist[i][j] != INF) {
                    eccentricidad = Math.max(eccentricidad, dist[i][j]);
                }
            }

            if (eccentricidad < mejorEccentricidad) {

                mejorEccentricidad = eccentricidad;

                centro = nombresVertices.get(i);
            }
        }

        return centro;
    }

    public void imprimirMatriz() {

        System.out.println("Matriz de Distancias:");

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

    public void imprimirVertices() {

        System.out.println("Vertices:");

        for (T vertice : nombresVertices) {
            System.out.println(vertice);
        }
    }
}