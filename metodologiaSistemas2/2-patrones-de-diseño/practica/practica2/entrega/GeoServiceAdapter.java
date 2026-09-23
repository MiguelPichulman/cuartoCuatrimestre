import java.util.HashMap;
import java.util.Map;

/**
 * 1. ¿Por qué Adapter y no otra solución (refactorización masiva, fachada, etc.)?
 *    Se eligió Adapter porque el sistema cliente ya está acoplado a una interfaz existente (OldGeoService)
 *    presente en 40 archivos. Una refactorización masiva de todo el código base invocaría un costo
 *    inviable y alto riesgo de errores, mientras que una Facade se usa para simplificar todo un subsistema
 *    y no para emular una interfaz ya dictada que requiere traducción de estructuras de datos.
 * 2. ¿Qué tendría que cambiar si llega un tercer proveedor mañana?
 *    Absolutamente nada en el código cliente (los 40 archivos). Gracias al polimorfismo y al desacoplamiento,
 *    si llega un "FutureGeoProvider", solo modificaríamos o crearíamos un nuevo adaptador que traduzca
 *    las nuevas estructuras al formato esperado, manteniendo la transparencia total del sistema.
 */
public class GeoServiceAdapter extends OldGeoService {
    private NewGeoProvider newGeoProvider;

    public GeoServiceAdapter() {
        this.newGeoProvider = new NewGeoProvider();
    }

    @Override
    public Map<String, Object> get_location(ip) { // O el tipo de dato correspondiente según la implementación
        //Invoca al nuevo proveedor con su interfaz incompatible
        Object rawResponse = this.newGeoProvider.locate(ip);

        // Simulación de la estructura del nuevo proveedor para traducir los datos:
        // .coordinates.latitude, .coordinates.longitude, .address.locality, .address.nation[cite: 5]
        //Traduce la respuesta al formato viejo que el sistema espera[cite: 5]
        Map<String, Object> oldFormatData = new HashMap<>();
        // (Asumiendo extracción de datos del objeto recibido del nuevo proveedor)
        // oldFormatData.put("lat", rawResponse.getCoordinates().getLatitude());
        // oldFormatData.put("lng", rawResponse.getCoordinates().getLongitude());
        // oldFormatData.put("city", rawResponse.getAddress().getLocality());
        // oldFormatData.put("country", rawResponse.getAddress().getNation());

        return oldFormatData;
    }
}