// MOVE METHOD: aplicar descuentos
public class CalculadoraDescuentos {

    private static final double DESCUENTO_OBRA_SOCIAL = 0.10;
    private static final double DESCUENTO_FIJO = 100.0;
    private static final int TIPO_DESCUENTO = 3;

    public double aplicarDescuentos(double subtotal, DatosFacturacion datos) {
        double total = subtotal;

        if (datos.tieneObraSocial()) {
            total *= (1.0 - DESCUENTO_OBRA_SOCIAL);
        }

        if (datos.getTipoCliente() > TIPO_DESCUENTO) {
            total -= DESCUENTO_FIJO;
        }

        return total;
    }
}