public class BrokenObserver implements StockObserver {
    @Override
    public void onLowStock(String productId, int quantity) {
        throw new RuntimeException("Error de red simulado en el observador roto");
    }
}