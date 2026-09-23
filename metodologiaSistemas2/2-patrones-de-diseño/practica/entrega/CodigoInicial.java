public class CodigoInicial {
    public String generate(String data, String formatType) {
        // Problema: Violacion de SRP y OCP. El servicio decide que clase instanciar
        Report report;
        if (formatType.equals("pdf")) {
            report = new PDFReport();
        } else if (formatType.equals("excel")) {
            report = new ExcelReport();
        } else if (formatType.equals("csv")) {
            report = new CSVReport();
        } else {
            throw new IllegalArgumentException("Formato no soportado");
        }

        report.setData(data);
        report.addHeader("Reporte Mensual");
        report.addFooter("Generado hoy");
        report.render();
        return report.getOutput();
    }
}