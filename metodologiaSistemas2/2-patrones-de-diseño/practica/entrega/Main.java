public class Main {
    public static void main(String[] args) {
        ReportService service = new ReportService();

        // Prueba con PDF
        String pdfResult = service.generate("Datos de ventas Q1", "pdf");
        System.out.println(pdfResult);

        // Prueba con Excel
        String excelResult = service.generate("Datos de stock", "excel");
        System.out.println(excelResult);

        // Prueba con HTML (Nuevo formato incorporado sin tocar ReportService)
        String htmlResult = service.generate("Datos de usuarios activos", "html");
        System.out.println(htmlResult);
    }
}
