import java.io.*;
import java.util.*;

/**
 * Lee archivos, ayuda a obtener las conexiones del grafo.
 */
public class TextManager {

    private List<Edge<String>> edges;

    private Set<String> ciudades;

    /**
     * Constructor de la clase.
     */
    public TextManager() {

        edges = new ArrayList<>();
        ciudades = new HashSet<>();
    }

    /**
     * Lee un archivo y obtiene las conexiones del grafo.
     * @param nombreArchivo nombre del archivo
     */
    public void leerArchivo(String nombreArchivo) {

        try (BufferedReader br =
                new BufferedReader(new FileReader(nombreArchivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                linea = linea.trim();

                if (linea.isEmpty()) {
                    continue;
                }

                String[] partes = linea.split("\\s+");

                if (partes.length != 3) {

                    System.out.println(
                        "Linea invalida: " + linea);

                    continue;
                }

                String origen = partes[0];
                String destino = partes[1];

                int peso;

                try {

                    peso = Integer.parseInt(partes[2]);

                } catch (NumberFormatException e) {

                    System.out.println(
                        "Peso invalido en linea: " + linea);

                    continue;
                }

                Edge<String> edge = new Edge<>(origen, destino, peso);

                edges.add(edge);

                ciudades.add(origen);
                ciudades.add(destino);
            }

        } catch (IOException e) {

            System.out.println(
                "Error leyendo archivo: " + e.getMessage());
        }
    }

    /**
     * Regresa la lista de aristas.
     * @return lista de aristas
     */
    public List<Edge<String>> getEdges() {
        return edges;
    }

    /**
     * Regresa el conjunto de ciudades.
     * @return conjunto de ciudades
     */
    public Set<String> getCiudades() {
        return ciudades;
    }

    /**
     * Regresa la cantidad de ciudades.
     * @return numero de ciudades
     */
    public int getNumeroCiudades() {
        return ciudades.size();
    }

    /**
     * Imprime las aristas.
     */
    public void imprimirEdges() {

        System.out.println("Aristas:");

        for (Edge<String> edge : edges) {
            System.out.println(edge);
        }
    }

    /**
     * Imprime las ciudades.
     */
    public void imprimirCiudades() {

        System.out.println("Ciudades:");

        for (String ciudad : ciudades) {
            System.out.println(ciudad);
        }
    }
}