public interface StockObserver {
    void onLowStock(String productId, int quantity);
}