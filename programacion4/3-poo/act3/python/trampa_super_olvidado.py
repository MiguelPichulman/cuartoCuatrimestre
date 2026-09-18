"""
ACTIVIDAD 3 - Trampa 3: super().__init__() olvidado

Java inserta AUTOMATICAMENTE una llamada al constructor sin argumentos
del padre si no la escribis vos -es invisible, pero esta. Python no hace
nada de eso: si no escribis super().__init__(), el constructor del
padre SIMPLEMENTE NO CORRE. El objeto queda a medio construir.
"""


class Figura:
    def __init__(self, nombre: str) -> None:
        self.nombre = nombre


class PoligonoIncorrecto(Figura):
    def __init__(self) -> None:
        # BUG: falta super().__init__("poligono")
        self._lados: list = []


class PoligonoCorrecto(Figura):
    def __init__(self) -> None:
        super().__init__("poligono")  # <- primera linea, sin excepciones
        self._lados: list = []


if __name__ == "__main__":
    p_malo = PoligonoIncorrecto()
    try:
        print(p_malo.nombre)  # AttributeError: nunca se inicializo
    except AttributeError as e:
        print("Error esperado (Figura nunca se construyo):", e)

    p_bueno = PoligonoCorrecto()
    print("nombre correctamente inicializado:", p_bueno.nombre)
