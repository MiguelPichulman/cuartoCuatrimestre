import java.time.LocalDate;

public class ReportService {

    public String generate(String data, String formatType) {
        // La creacion se delega a la fabrica; el servicio desconoce el formato
        Report report = ReportFactory.create(formatType);

        report.setData(data);
        report.addHeader("Reporte Mensual");
        report.addFooter("Generado el " + LocalDate.now());
        report.render();

        return report.getOutput();
    }
}
