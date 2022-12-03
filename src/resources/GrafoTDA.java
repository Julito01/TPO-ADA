package resources;
import apis.*;

public interface GrafoTDA {

    // Inicializa el grafo
    void inicializarGrafo();

    // Agrega un vertice
    void agregarVertice(int var1);

    // Borra un vertice
    void eliminarVertice(int var1);

    // Devuelve un conjunto de los vertices del grafo
    ConjuntoTDA vertices();

    // Agregar una arista
    void agregarArista(int var1, int var2, int var3);

    // Elimina una arista
    void eliminarArista(int var1, int var2);

    // Verifica si existe arista entre dos vertices
    boolean existeArista(int var1, int var2);

    // Devuelve el peso de la arista entre dos vertices
    int pesoArista(int var1, int var2);

    // Devuelve un conjunto de adyacentes de un vertice
    ConjuntoTDA adyacentes(int v);

    void crearArista(GrafoTDA grafo, int i, int i1, int i2);

    void crearVertice(GrafoTDA grafo, int i);
}
