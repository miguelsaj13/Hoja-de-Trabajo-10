import networkx as nx

INF = float("inf")


# Lee el archivo y crea el grafo
def leer_archivo(nombre_archivo):

    G = nx.DiGraph()

    with open(nombre_archivo, "r") as file:

        for line in file:

            line = line.strip()

            if not line:
                continue

            origen, destino, peso = line.split()

            G.add_edge(
                origen,
                destino,
                weight=int(peso)
            )

    return G


# Ejecuta el algoritmo de Floyd
def ejecutar_floyd(G):

    pred, dist = nx.floyd_warshall_predecessor_and_distance(G, weight="weight")

    return pred, dist


# Obtiene la ruta minima
def obtener_ruta(pred, origen, destino):

    try:

        ruta = nx.reconstruct_path(origen, destino, pred)

        return ruta

    except:

        return []


# Calcula el centro del grafo
def obtener_centro(G, dist):

    mejor = INF

    centro = None

    for nodo in G.nodes():

        if len(dist[nodo]) != len(G.nodes()):

            continue

        eccentricidad = max(dist[nodo].values())

        if eccentricidad < mejor:

            mejor = eccentricidad

            centro = nodo

    return centro


# Imprime la ruta
def imprimir_ruta(ruta):

    for i in range(len(ruta)):

        print(ruta[i], end="")

        if i < len(ruta) - 1:
            print(" -> ", end="")

    print()


def main():

    G = leer_archivo("guategrafo.txt")

    pred, dist = ejecutar_floyd(G)

    salir = False
    print("Hoja de trabajo 10 - Algoritmo de floyd con Python y NetworkX")

    while not salir:

        print("1. Ruta mas corta")
        print("2. Centro del grafo")
        print("3. Modificar grafo")
        print("4. Mostrar nodos")
        print("5. Salir")

        opcion = input("Escoja una opcion: ")

        if opcion == "1":

            origen = input("Ciudad origen: ")
            destino = input("Ciudad destino: ")

            if origen not in G.nodes():

                print("La ciudad origen no existe.")
                continue

            if destino not in G.nodes():

                print("La ciudad destino no existe.")
                continue

            ruta = obtener_ruta(pred, origen, destino)

            if not ruta:

                print("No existe ruta.")

            else:

                print("\nRuta minima:")

                imprimir_ruta(ruta)

                print("Distancia total:", dist[origen][destino])

        elif opcion == "2":

            centro = obtener_centro(G, dist)

            if centro is None:

                print("El grafo no tiene centro.")

            else:

                print("\nCentro del grafo:", centro)

        elif opcion == "3":

            print("\na) Interrupcion de trafico")
            print("b) Nueva conexion")

            sub = input("Seleccione opcion: ")

            if sub.lower() == "a":

                origen = input("Ciudad origen: ")
                destino = input("Ciudad destino: ")

                if not G.has_edge(origen, destino):

                    print("La conexion no existe.")
                    continue

                G.remove_edge(origen, destino)

                pred, dist = ejecutar_floyd(G)

                print("Conexion eliminada.")

                centro = obtener_centro(G, dist)

                if centro is None:

                    print("El grafo no tiene centro.")

                else:

                    print("Nuevo centro:", centro)

            elif sub.lower() == "b":

                origen = input("Ciudad origen: ")
                destino = input("Ciudad destino: ")

                if origen not in G.nodes() or \
                   destino not in G.nodes():

                    print("Las ciudades deben existir.")

                    continue

                peso = int(input("Distancia: "))

                G.add_edge(origen, destino, weight=peso)

                pred, dist = ejecutar_floyd(G)

                print("Conexion agregada.")

                centro = obtener_centro(G, dist)

                if centro is None:

                    print("El grafo no tiene centro.")

                else:

                    print("Nuevo centro:", centro)

            else:

                print("Opción invalida.")

        elif opcion == "4":

            print("\nCiudades:")

            for nodo in G.nodes():

                print(nodo)

        elif opcion == "5":

            salir = True

            print("Programa finalizado.")

        else:

            print("Opción invalida.")


if __name__ == "__main__":

    main()