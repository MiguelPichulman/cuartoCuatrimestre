import java.util.List;

public class FacturacionP_A {
    private static final double DESCUENTO_PARTICULAR = 0.5;
    private static final double DESCUENTO_OBRA_SOCIAL = 0.1;
    private static final double DESCUENTO_FIJO = 100;
    private static final int TIPO_DESCUENTO = 3;

    public double calcularTotal(List<Double> costosEstudios,
                       boolean esParticular,
                       boolean tieneObraSocial,
                       int tipoPaciente)
    {
        validarEntrada(costosEstudios);
        double total = calcularSubtotalEstudios(costosEstudios, esParticular);
        total = aplicarDescuentoObraSocial(total, tieneObraSocial);
        total = aplicarDescuentoTipoPaciente(total, tipoPaciente);

        return total;
    }

    private void validarEntrada(List<Double> costosEstudios) {
        if (costosEstudios == null) {
            throw new IllegalArgumentException("El costo estudio no puede ser nulo.");
        }
    }

    private double calcularSubtotalEstudios(List<Double> costosEstudios, boolean esParticular) {
        double subbtotal = 0;
        for (Double costo : costosEstudios) {
            if (costo != null) {
                subbtotal += esParticular ? costo * DESCUENTO_PARTICULAR : costo;
            }
        }
        return subbtotal;
    }

    private double aplicarDescuentoObraSocial(double total, boolean tieneObraSocial) {
        if (tieneObraSocial) {
            return total * (1-DESCUENTO_OBRA_SOCIAL);
        }
        return total;
    }

    private double aplicarDescuentoTipoPaciente(double total, int tipoPaciente) {
        if (tipoPaciente > TIPO_DESCUENTO) {
            return total - DESCUENTO_FIJO;
        }
        return total;
    }

}