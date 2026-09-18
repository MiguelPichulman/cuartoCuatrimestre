"""
ACTIVIDAD 3 - Streams -> comprehensions (traduccion idiomatica)

.flatMap(e -> e.getComisarios().stream())  ->  doble "for" en la comprehension
.collect(groupingBy(c -> c, counting()))   ->  NO se traduce: se REEMPLAZA
                                                por Counter, que ya resuelve
                                                exactamente ese problema.

Regla general: casi todo pipeline de streams tiene una comprehension
equivalente mas corta. Cuando no la tiene, el idioma correcto en Python
es un "for" explicito -Python no considera al "for" una derrota.
"""

from collections import Counter


class Evento:
    def __init__(self, comisarios: list[str]) -> None:
        self.comisarios = comisarios


if __name__ == "__main__":
    eventos = [
        Evento(["Perez", "Gomez"]),
        Evento(["Perez", "Diaz"]),
        Evento(["Perez"]),
    ]

    # eventos.stream()                         -> generador
    # .flatMap(...)                            -> doble for
    # .collect(groupingBy(c -> c, counting()))  -> Counter(...)
    conteo = Counter(c for e in eventos for c in e.comisarios)

    maximo = max(conteo.values())
    mas_frecuentes = [c for c, n in conteo.items() if n == maximo]

    print("Conteo:", conteo)
    print("Mas frecuentes:", mas_frecuentes)
