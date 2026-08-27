public class GestorTurnos {
    private Notificacion notificador;

    public GestorTurnos(Notificacion notificador) {
        this.notificador = notificador;
    }

    public void setNotificador(Notificacion notificador) {
        this.notificador = notificador;
    }

    public void registrarTurno(String paciente, String fecha, String especialidad) {

        String mensaje = "Turno confirmado para " + paciente + " el dia " + fecha + " en " + especialidad + ".";

        this.notificador.enviar(mensaje);
    }
}