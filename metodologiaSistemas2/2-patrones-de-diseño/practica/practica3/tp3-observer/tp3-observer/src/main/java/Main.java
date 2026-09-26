public class Main {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();

        manager.subscribe(new EmailAlertObserver());
        manager.subscribe(new AnalyticsObserver());
        manager.subscribe(new BrokenObserver()); // Fallara pero no detendra a los demas
        manager.subscribe(new ReplenishObserver());
        manager.subscribe(new PushNotificationObserver()); // Demuestra extensibilidad

        System.out.println("--- Actualizando stock ---");
        manager.updateStock("PROD-001", 5);
    }
}