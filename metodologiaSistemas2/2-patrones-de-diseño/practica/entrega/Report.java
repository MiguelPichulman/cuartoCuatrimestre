public interface Report {
    void setData(String data);
    void addHeader(String header);
    void addFooter(String footer);
    void render();
    String getOutput();
}
