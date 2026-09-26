public class AnalyticsObserver implements StockObserver {
    @Override
    public void onLowStock(String productId, int quantity) {
        System.out.println("[Analytics] Evento de stock bajo registrado para el producto: " + productId);
    }
}