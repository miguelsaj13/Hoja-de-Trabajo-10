import java.io.*;
import java.util.*;

public class TextManager {

    private List<Edge<String>> edges;

    private Set<String> ciudades;

    public TextManager() {

        edges = new ArrayList<>();
        ciudades = new HashSet<>();
    }

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

    public List<Edge<String>> getEdges() {
        return edges;
    }

    public Set<String> getCiudades() {
        return ciudades;
    }

    public int getNumeroCiudades() {
        return ciudades.size();
    }

    public void imprimirEdges() {

        System.out.println("Aristas:");

        for (Edge<String> edge : edges) {
            System.out.println(edge);
        }
    }

    public void imprimirCiudades() {

        System.out.println("Ciudades:");

        for (String ciudad : ciudades) {
            System.out.println(ciudad);
        }
    }
}