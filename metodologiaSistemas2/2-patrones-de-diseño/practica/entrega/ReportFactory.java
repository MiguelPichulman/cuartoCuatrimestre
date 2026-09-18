/**
  Justificacion tecnica:
  1. Por que Factory Method y no Abstract Factory?
     Abstract Factory se utiliza para crear familias de objetos relacionados que deben construirse
     juntos. En este escenario solo manejamos un unico tipo de objeto (Report) con distintas variantes,
     por lo que Factory Method (en su variante Simple/Static Factory) es perfectamente suficiente y evita sobreingenieria.

  2. Static Factory
     Se eligio Static Factory porque en este problema particular solo varia el tipo de producto a instanciar
     y no el comportamiento global de la clase creadora. Centralizar la creacion mediante un meetodo estatico
     mantiene el codigo limpio, liviano y evita jerarquias innecesarias de creadores abstractos.
 */
public class ReportFactory {

    public static Report create(String formatType) {
        if (formatType == null) {
            throw new IllegalArgumentException("El tipo de formato no puede ser nulo.");
        }

        switch (formatType.toLowerCase()) {
            case "pdf":
                return new PDFReport();
            case "excel":
                return new ExcelReport();
            case "csv":
                return new CSVReport();
            case "html":
                return new HTMLReport(); // Incorporacion del nuevo formato sin afectar al servicio
            default:
                throw new IllegalArgumentException("Formato no soportado: " + formatType);
        }
    }
}