// package patrones_de_diseño_proyecto;

public abstract class BebidaDecorator implements Bebida {
    protected final Bebida bebidaEnvuelta;

    public BebidaDecorator(Bebida bebida) {
        this.bebidaEnvuelta = bebida;
    }

    @Override public String getIcono() { return bebidaEnvuelta.getIcono(); }
    @Override public String getDescripcion() { return bebidaEnvuelta.getDescripcion(); }
}

class ExtraShot extends BebidaDecorator {
    public ExtraShot(Bebida bebida) { super(bebida); }
    @Override public String getNombre() { return bebidaEnvuelta.getNombre() + " + Extra Shot"; }
    @Override public double getPrecio() { return bebidaEnvuelta.getPrecio() + 15.00; }
}

class LecheDeslactosada extends BebidaDecorator {
    public LecheDeslactosada(Bebida bebida) { super(bebida); }
    @Override public String getNombre() { return bebidaEnvuelta.getNombre() + " (Deslactosada)"; }
    @Override public double getPrecio() { return bebidaEnvuelta.getPrecio() + 10.00; }
}

class CremaBatida extends BebidaDecorator {
    public CremaBatida(Bebida bebida) { super(bebida); }
    @Override public String getNombre() { return bebidaEnvuelta.getNombre() + " + Crema Batida"; }
    @Override public double getPrecio() { return bebidaEnvuelta.getPrecio() + 12.00; }
}
