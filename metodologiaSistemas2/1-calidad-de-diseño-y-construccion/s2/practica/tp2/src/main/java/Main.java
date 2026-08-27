public class Main {
    public static void main(String[] args) {

        Notificacion email = new EmailNotificacion();
        GestorTurnos gestor = new GestorTurnos(email);
        gestor.registrarTurno("Juan Perez", "28/08/2026 10:00", "Cardiologia");

        gestor.setNotificador(new WhatsappNotificacion());
        gestor.registrarTurno("Maria Lopez", "28/08/2026 11:30", "Dermatologia");

        gestor.setNotificador(new TelegramNotificacion());
        gestor.registrarTurno("Carlos Gomez", "29/08/2026 09:00", "Traumatologia");
    }
}