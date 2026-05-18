//Como la clase de Node para arboles binarios

/**
 * Clase que representa una conexion entre dos vertices.
 * @param <T> tipo de dato de los vertices
 */
public class Edge<T> {

    private T origen;
    private T destino;
    private int peso;

    /**
     * Constructor de la arista.
     * @param origen vertice origen
     * @param destino vertice destino
     * @param peso peso de la conexion
     */
    public Edge(T origen, T destino, int peso) {

        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    /**
     * Obtiene el origen.
     * @return vertice origen
     */
    public T getOrigen() {
        return origen;
    }

    /**
     * Obtiene el destino.
     * @return vertice destino
     */
    public T getDestino() {
        return destino;
    }

    /**
     * Obtiene el peso.
     * @return peso de la conexion
     */
    public int getPeso() {
        return peso;
    }

    /**
     * Cambia el origen.
     * @param origen nuevo origen
     */
    public void setOrigen(T origen) {
        this.origen = origen;
    }

    /**
     * Cambia el destino.
     * @param destino nuevo destino
     */
    public void setDestino(T destino) {
        this.destino = destino;
    }

    /**
     * Cambia el peso.
     * @param peso nuevo peso
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

    /**
     * Devuelve una representacion en String.
     * @return arista en formato texto
     */
    @Override
    public String toString() {

        return origen + " -> " + destino + " (" + peso + ")";
    }
}