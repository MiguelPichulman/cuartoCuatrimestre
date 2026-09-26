public class PushNotificationObserver implements StockObserver {
    @Override
    public void onLowStock(String productId, int quantity) {
        System.out.println("[PushNotification] Notificacion movil enviada al administrador sobre " + productId);
    }
}