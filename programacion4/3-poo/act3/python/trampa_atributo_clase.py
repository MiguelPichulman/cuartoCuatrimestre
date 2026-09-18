"""
ACTIVIDAD 3 - Trampa 2: atributo de clase mutable (el "static" accidental)

En Java, la diferencia entre un campo de instancia y uno estatico es la
palabra clave "static", visible e imposible de pasar por alto. En Python
la diferencia es la INDENTACION y el "self" -dos senales que el ojo
entrenado en Java no lee como significativas.
"""


class PoligonoIncorrecto:
    lados: list = []  # <- BUG: esto es un atributo de CLASE (static), no de instancia

    def agregar_lado(self, lado) -> None:
        self.lados.append(lado)


class PoligonoCorrecto:
    def __init__(self) -> None:
        self._lados: list = []  # atributo de INSTANCIA (correcto)

    def agregar_lado(self, lado) -> None:
        self._lados.append(lado)


if __name__ == "__main__":
    print("--- Version CON el bug (atributo de clase) ---")
    a = PoligonoIncorrecto()
    b = PoligonoIncorrecto()
    a.agregar_lado("lado de a")
    print("b.lados tambien tiene el lado de a:", b.lados)  # comparten la lista!

    print("--- Version CORRECTA (atributo de instancia) ---")
    x = PoligonoCorrecto()
    y = PoligonoCorrecto()
    x.agregar_lado("lado de x")
    print("y._lados esta vacia:", y._lados)  # cada instancia es independiente
