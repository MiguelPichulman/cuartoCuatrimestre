// ACTIVIDAD 3 - Streams (version Java del ejemplo real de catedra)
//
// flatMap "aplana" una coleccion de colecciones: cada Evento tiene una
// lista de Comisarios, y queremos una unica coleccion con todos ellos.
// collect(groupingBy(c -> c, counting())) agrupa por identidad y cuenta
// cuantas veces aparece cada Comisario.

import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class ConteoComisarios {

    public static void main(String[] args) {
        List<Evento> eventos = List.of(
                new Evento(List.of("Perez", "Gomez")),
                new Evento(List.of("Perez", "Diaz")),
                new Evento(List.of("Perez"))
        );

        Map<String, Long> conteo = eventos.stream()
                .flatMap(e -> e.getComisarios().stream())
                .collect(groupingBy(c -> c, counting()));

        System.out.println(conteo);
    }
}

class Evento {
    private final List<String> comisarios;

    public Evento(List<String> comisarios) {
        this.comisarios = comisarios;
    }

    public List<String> getComisarios() {
        return comisarios;
    }
}
