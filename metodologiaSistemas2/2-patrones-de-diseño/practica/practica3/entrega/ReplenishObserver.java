public class ReplenishObserver implements StockObserver {
    @Override
    public void onLowStock(String productId, int quantity) {
        System.out.println("[Replenishment] Orden de reposicion generada por 100 unidades para: " + productId);
    }
}