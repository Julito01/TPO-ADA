package resources;

public interface ColaPrioridadTDA {
    void inicializarCola();

    void acolarPrioridad(int var1, int var2, int var3);

    void desacolar();

    boolean colaVacia();

    int nodoInicio();

    int nodoDestino();

    int peso();
}
