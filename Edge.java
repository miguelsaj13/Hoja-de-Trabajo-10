//Como la clase de Node para arboles binarios

public class Edge<T> {

    private T origen;
    private T destino;
    private int peso;

    public Edge(T origen, T destino, int peso) {

        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    public T getOrigen() {
        return origen;
    }

    public T getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }

    public void setOrigen(T origen) {
        this.origen = origen;
    }

    public void setDestino(T destino) {
        this.destino = destino;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {

        return origen + " -> " + destino + " (" + peso + ")";
    }
}