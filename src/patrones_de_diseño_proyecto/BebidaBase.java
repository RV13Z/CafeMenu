package patrones_de_diseño_proyecto;

public class BebidaBase implements Bebida {
    private final String nombre;
    private final String descripcion;
    private final String icono;
    private final double precio;

    public BebidaBase(String nombre, String descripcion, String icono, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.icono = icono;
        this.precio = precio;
    }

    @Override public String getNombre() { return nombre; }
    @Override public String getDescripcion() { return descripcion; }
    @Override public String getIcono() { return icono; }
    @Override public double getPrecio() { return precio; }
}