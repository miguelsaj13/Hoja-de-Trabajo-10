import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        TextManager tm = new TextManager();

        tm.leerArchivo("guategrafo.txt");

        Grafo<String> grafo = new Grafo<>(tm.getNumeroCiudades());

        for (String ciudad : tm.getCiudades()) {

            grafo.agregarVertice(ciudad);
        }

        for (Edge<String> edge : tm.getEdges()) {

            grafo.agregarArista(edge);
        }

        grafo.floyd();

        boolean salir = false;
        
        System.out.println("Hoja de Trabajo 10 - Grafos para algoritmo de Floyd");

        while (!salir) {
            System.out.println("1. Ruta mas corta");
            System.out.println("2. Centro del grafo");
            System.out.println("3. Modificar grafo");
            System.out.println("4. Imprimir matriz");
            System.out.println("5. Salir");

            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:

                    System.out.print("Ciudad origen: ");
                    String origen = scanner.nextLine();

                    System.out.print("Ciudad destino: ");
                    String destino = scanner.nextLine();

                    if (!grafo.existeVertice(origen)) {

                        System.out.println("La ciudad origen no existe.");
                        break;
                    }

                    if (!grafo.existeVertice(destino)) {

                        System.out.println("La ciudad destino no existe.");
                        break;
                    }

                    List<String> ruta = grafo.getRuta(origen, destino);

                    int distancia = grafo.getDistancia(origen, destino);

                    if (ruta.isEmpty()) {

                        System.out.println("No existe ruta.");

                    } else {

                        System.out.println("\nRuta minima:");

                        for (int i = 0; i < ruta.size(); i++) {

                            System.out.print(ruta.get(i));

                            if (i < ruta.size() - 1) {

                                System.out.print(" -> ");
                            }
                        }

                        System.out.println("\nDistancia total: "+ distancia);
                    }

                    break;

                case 2:

                    String centro = grafo.getCentroGrafo();

                    if (centro == null) {

                        System.out.println("El grafo no tiene centro.");

                    } else {

                        System.out.println("\nCentro del grafo: "+ centro);
                    }

                    break;

                case 3:

                    System.out.println("\na) Interrupcion de trafico");

                    System.out.println("b) Nueva conexion");

                    System.out.print("Seleccione opcion: ");

                    String subopcion = scanner.nextLine();

                    if (subopcion.equalsIgnoreCase("a")) {

                        System.out.print("Ciudad origen: ");

                        String o = scanner.nextLine();

                        System.out.print("Ciudad destino: ");

                        String d = scanner.nextLine();

                        if (!grafo.existeVertice(o) || !grafo.existeVertice(d)) {

                            System.out.println("Las ciudades deben existir.");

                            break;
                        }

                        grafo.eliminarArista(o, d);

                        grafo.floyd();

                        System.out.println("Conexion eliminada.");

                        String nuevoCentro = grafo.getCentroGrafo();

                        if (nuevoCentro == null) {

                            System.out.println("El grafo ya no tiene centro.");

                        } else {

                            System.out.println("Nuevo centro: "+ nuevoCentro);
                        }

                    } else if (
                        subopcion.equalsIgnoreCase("b")) {

                        System.out.print("Ciudad origen: ");

                        String o = scanner.nextLine();

                        System.out.print("Ciudad destino: ");

                        String d = scanner.nextLine();

                        if (!grafo.existeVertice(o) || !grafo.existeVertice(d)) {
                            System.out.println("Las ciudades deben existir.");
                            break;
                        }

                        System.out.print(
                            "Distancia: "
                        );

                        int peso = scanner.nextInt();

                        scanner.nextLine();

                        grafo.agregarArista(o, d, peso);

                        grafo.floyd();

                        System.out.println(
                            "Conexion agregada."
                        );

                        String nuevoCentro = grafo.getCentroGrafo();

                        if (nuevoCentro == null) {

                            System.out.println("El grafo no tiene centro.");

                        } else {

                            System.out.println("Nuevo centro: "+ nuevoCentro);
                        }

                    } else {

                        System.out.println("Opcion invalida.");
                    }

                    break;

                case 4:

                    grafo.imprimirMatriz();

                    break;

                case 5:

                    salir = true;

                    System.out.println("Programa finalizado.");

                    break;

                default:

                    System.out.println("Opcion invalida.");
            }
        }

        scanner.close();
    }
}