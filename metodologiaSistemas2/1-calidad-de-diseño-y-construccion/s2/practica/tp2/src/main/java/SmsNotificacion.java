public class SmsNotificacion implements Notificacion {
    @Override
    public void enviar(String mensaje) {
        System.out.println("Enviando sms:" +  mensaje);
    }
}