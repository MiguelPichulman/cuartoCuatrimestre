public class NotificadorService {
    private Notificacion notificacion;
    public NotificadorService(Notificacion notificacion) {
        this.notificacion = notificacion;
    }
    public void setNotificacion(Notificacion notificacion) {
        this.notificacion = notificacion;
    }
    public void enviarNotificacion(String mensaje) {
        this.notificacion.enviar(mensaje);
    }
}