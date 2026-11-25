package patrones_de_diseño_proyecto;

public interface EstrategiaPago {
    double calcularTotalFinal(double subtotal);
    String getDetallePago(double subtotal);
}

class PagoEfectivo implements EstrategiaPago {
    @Override
    public double calcularTotalFinal(double subtotal) {
        return subtotal * 0.95; // 5% de descuento en efectivo
    }

    @Override
    public String getDetallePago(double subtotal) {
        return String.format("Pago en Efectivo (5%% desc. aplicado)\nSubtotal: $%.2f MXN\nTotal a pagar: $%.2f MXN",
                subtotal, calcularTotalFinal(subtotal));
    }
}

class PagoTarjeta implements EstrategiaPago {
    @Override
    public double calcularTotalFinal(double subtotal) {
        return subtotal; // Precio regular sin descuento
    }

    @Override
    public String getDetallePago(double subtotal) {
        return String.format("Pago con Tarjeta (Sin comisión)\nTotal a pagar: $%.2f MXN",
                calcularTotalFinal(subtotal));
    }
}