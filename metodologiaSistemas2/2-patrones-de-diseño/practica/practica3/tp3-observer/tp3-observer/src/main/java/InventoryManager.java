import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryManager {
    private List<StockObserver> observers = new ArrayList<>();
    private Map<String, Integer> stock = new HashMap<>();

    public void subscribe(StockObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(StockObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String productId, int quantity) {
        for (StockObserver observer : observers) {
            try {
                observer.onLowStock(productId, quantity);
            } catch (Exception e) {
                // El bloque catch esta DENTRO del loop para que un fallo individual no corte la cadena
                System.err.println("[Error] Falló el observador (" + observer.getClass().getSimpleName() + "): " + e.getMessage());
            }
        }
    }

    public void updateStock(String productId, int quantity) {
        stock.put(productId, quantity);
        if (quantity < 10) {
            notifyObservers(productId, quantity);
        }
    }
}