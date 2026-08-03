package org.example;

import java.util.ArrayList;
import java.util.List;

public class TurnoManagerRefact {

    private static final String OSDE = "OSDE";
    private static final String SWISS = "SWISS";
    private static final String PUBLICA = "PUBLICA";

    private static final String PREMIUM = "Paciente premium";
    private static final String PUBLICO = "Paciente publico";

    private final List<String> turnos = new ArrayList<>();

    public void procesar(
            String nombrePaciente,
            int edad,
            String obraSocial,
            boolean urgente) {

        if (!datosValidos(nombrePaciente, edad)) {
            return;
        }

        mostrarTipoPaciente(obraSocial);

        String turno = construirTurno(
                nombrePaciente,
                edad,
                obraSocial,
                urgente);

        turnos.add(turno);

        System.out.println("Turno agregado");
    }

    private boolean datosValidos(String nombre, int edad) {
        return nombre != null &&
                !nombre.isEmpty() &&
                edad > 0;
    }

    private void mostrarTipoPaciente(String obraSocial) {

        if (OSDE.equals(obraSocial) || SWISS.equals(obraSocial)) {
            System.out.println(PREMIUM);
        } else if (PUBLICA.equals(obraSocial)) {
            System.out.println(PUBLICO);
        }
    }

    private String construirTurno(
            String nombre,
            int edad,
            String obraSocial,
            boolean urgente) {

        String turno = nombre + "-" + edad + "-" + obraSocial;

        if (urgente) {
            turno += "-URGENTE";
        }

        return turno;
    }

    public void mostrar() {
        for (String turno : turnos) {
            System.out.println(turno);
        }
    }
}
