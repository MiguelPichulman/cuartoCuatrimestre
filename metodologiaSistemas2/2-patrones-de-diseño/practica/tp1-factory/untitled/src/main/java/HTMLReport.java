public class HTMLReport implements Report {
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
        // Simula formato HTML sin modificar la logica del servicio
        this.output = "<html><body><h1>" + header + "</h1><p>" + data + "</p><footer>" + footer + "</footer></body></html>";
    }

    @Override
    public String getOutput() { return output; }
}