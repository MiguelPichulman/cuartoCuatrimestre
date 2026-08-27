public class TelegramNotificacion implements Notificacion {
    @Override
    public void enviar(String mensaje) {
        System.out.println("Enviando Telegram: " + mensaje);
    }
}