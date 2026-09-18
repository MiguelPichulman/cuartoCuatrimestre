"""
ACTIVIDAD 3 - Trampa 1: default mutable compartido

El valor por defecto de un parametro se evalua UNA SOLA VEZ, cuando se
DEFINE la funcion -no en cada llamada. La lista vacia [] del ejemplo
incorrecto se crea una unica vez, al importar el modulo, y TODAS las
instancias que no pasan "lados" explicito terminan compartiendo esa
misma lista.

En Java esto no puede pasar porque this(new ArrayList<>()) ejecuta
"new" en cada llamada al constructor -por eso la intuicion de un
programador Java no detecta este bug.

Regla sin excepciones: ningun default mutable, nunca.
"""


class PoligonoIncorrecto:
    # TODAS las instancias comparten la MISMA lista
    def __init__(self, lados: list | None = []):  # noqa: B006 (a proposito)
        self._lados = lados if lados is not None else []
        # OJO: aqui el bug real ocurre si se usa "lados: list = []"
        # directamente como default; se deja el "if" para que el ejemplo
        # incorrecto real (abajo) sea el que se ejecute.


class PoligonoConBug:
    def __init__(self, lados: list = []):  # <- BUG real
        self._lados = lados

    def agregar_lado(self, lado) -> None:
        self._lados.append(lado)

    @property
    def nro_lados(self) -> int:
        return len(self._lados)


class PoligonoCorrecto:
    def __init__(self, lados: list | None = None):
        self._lados = lados if lados is not None else []

    def agregar_lado(self, lado) -> None:
        self._lados.append(lado)

    @property
    def nro_lados(self) -> int:
        return len(self._lados)


if __name__ == "__main__":
    print("--- Version CON el bug ---")
    p1 = PoligonoConBug()
    p1.agregar_lado("lado A")
    p2 = PoligonoConBug()  # "nuevo", recien construido
    print("p2.nro_lados deberia ser 0, es:", p2.nro_lados)  # 1 !!
    print("p1._lados is p2._lados ->", p1._lados is p2._lados)  # True

    print("--- Version CORRECTA ---")
    c1 = PoligonoCorrecto()
    c1.agregar_lado("lado A")
    c2 = PoligonoCorrecto()
    print("c2.nro_lados:", c2.nro_lados)  # 0, como se espera
    print("c1._lados is c2._lados ->", c1._lados is c2._lados)  # False
