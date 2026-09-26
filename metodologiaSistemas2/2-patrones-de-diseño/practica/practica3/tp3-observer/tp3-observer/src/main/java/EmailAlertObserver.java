public class EmailAlertObserver implements StockObserver {
    @Override
    public void onLowStock(String productId, int quantity) {
        System.out.println("[EmailAlert] Alerta enviada: Stock bajo para " + productId + " (Quedan: " + quantity + ")");
    }
}