import java.util.List;

// PARAMETER OBJECT
public class DatosFacturacion {
    private final List<Double> costosEstudios;
    private final boolean esParticular;
    private final boolean tieneObraSocial;
    private final int tipoCliente;

    public DatosFacturacion(List<Double> costosEstudios, boolean esParticular, boolean tieneObraSocial, int tipoCliente) {
        if (costosEstudios == null) {
            throw new IllegalArgumentException("La lista de costos no puede ser nula.");
        }
        this.costosEstudios = costosEstudios;
        this.esParticular = esParticular;
        this.tieneObraSocial = tieneObraSocial;
        this.tipoCliente = tipoCliente;
    }

    public List<Double> getCostosEstudios() {
        return costosEstudios;
    }

    public boolean esParticular() {
        return esParticular;
    }

    public boolean tieneObraSocial() {
        return tieneObraSocial;
    }

    public int getTipoCliente() {
        return tipoCliente;
    }
}