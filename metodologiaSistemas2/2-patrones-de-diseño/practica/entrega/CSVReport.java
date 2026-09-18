public class CSVReport implements Report {
    private String data;
    private String header;
    private String footer;
    private String output;

    @Override
    public void setData(String data) { this.data = data; }

    @Override
    public void addHeader(String text) { this.header = text; }

    @Override
    public void addFooter(String text) { this.footer = text; }

    @Override
    public void render() {
        // Simula renderizado CSV
        this.output = "[CSV Format] Header: " + header + " | Contenido: " + data + " | " + footer;
    }

    @Override
    public String getOutput() { return output; }
}