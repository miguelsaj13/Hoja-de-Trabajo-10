import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class GrafoTest {

    @Test
    public void testAgregarVertice() {

        Grafo<String> grafo = new Grafo<>(3);

        grafo.agregarVertice("A");

        assertTrue(grafo.existeVertice("A"));
    }

    @Test
    public void testAgregarArista() {

        Grafo<String> grafo = new Grafo<>(3);

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");

        grafo.agregarArista("A", "B", 5);

        grafo.floyd();

        assertEquals(5, grafo.getDistancia("A", "B"));
    }

    @Test
    public void testEliminarArista() {

        Grafo<String> grafo = new Grafo<>(3);

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");

        grafo.agregarArista("A", "B", 5);

        grafo.floyd();

        grafo.eliminarArista("A", "B");

        grafo.floyd();

        List<String> ruta = grafo.getRuta("A", "B");

        assertTrue(ruta.isEmpty());
    }

    @Test
    public void testFloyd() {

        Grafo<String> grafo = new Grafo<>(3);

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");

        grafo.agregarArista("A", "B", 2);
        grafo.agregarArista("B", "C", 3);

        grafo.floyd();

        assertEquals(5,grafo.getDistancia("A", "C"));
    }

    @Test
    public void testRuta() {

        Grafo<String> grafo = new Grafo<>(4);

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");

        grafo.agregarArista("A", "B", 2);
        grafo.agregarArista("B", "C", 1);
        grafo.agregarArista("C", "D", 1);

        grafo.floyd();

        List<String> ruta = grafo.getRuta("A", "D");

        assertEquals(4, ruta.size());

        assertEquals("A", ruta.get(0));
        assertEquals("B", ruta.get(1));
        assertEquals("C", ruta.get(2));
        assertEquals("D", ruta.get(3));
    }

    @Test
    public void testCentroGrafo() {

        Grafo<String> grafo = new Grafo<>(4);

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");

        grafo.agregarArista("A", "B", 2);
        grafo.agregarArista("A", "C", 5);
        grafo.agregarArista("B", "C", 1);
        grafo.agregarArista("B", "D", 4);
        grafo.agregarArista("C", "D", 1);
        grafo.agregarArista("D", "A", 7);

        grafo.floyd();

        assertEquals("A", grafo.getCentroGrafo());
    }

    @Test
    public void testNoExisteRuta() {

        Grafo<String> grafo = new Grafo<>(3);

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");

        grafo.agregarArista("A", "B", 5);

        grafo.floyd();

        List<String> ruta = grafo.getRuta("A", "C");

        assertTrue(ruta.isEmpty());
    }

    @Test
    public void testNoTieneCentro() {

        Grafo<String> grafo = new Grafo<>(3);

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");

        grafo.agregarArista("A", "B", 5);

        grafo.floyd();

        assertNull(grafo.getCentroGrafo());
    }
}