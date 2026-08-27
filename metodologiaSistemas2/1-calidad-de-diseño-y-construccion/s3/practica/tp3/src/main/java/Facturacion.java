//  EXTRACT METHOD
public class Facturacion {

    private static final double DESCUENTO_PARTICULAR = 0.5;
    private final CalculadoraDescuentos calculadoraDescuentos;

    public Facturacion() {
        this.calculadoraDescuentos = new CalculadoraDescuentos();
    }

    public Facturacion(CalculadoraDescuentos calculadoraDescuentos) {
        this.calculadoraDescuentos = calculadoraDescuentos;
    }

    public double calcular(DatosFacturacion datos) {
        double subtotal = calcularSubtotalEstudios(datos);
        return calculadoraDescuentos.aplicarDescuentos(subtotal, datos);
    }

    // EXTRACT METHOD: Separar calculo del subtotal inicial
    private double calcularSubtotalEstudios(DatosFacturacion datos) {
        double subtotal = 0;
        for (Double costo : datos.getCostosEstudios()) {
            if (costo != null) {
                subtotal += datos.esParticular() ? (costo * DESCUENTO_PARTICULAR) : costo;
            }
        }
        return subtotal;
    }
}