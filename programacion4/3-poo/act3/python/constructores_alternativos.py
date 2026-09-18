"""
ACTIVIDAD 3 - Sobrecarga de constructores -> @classmethod

Python NO tiene sobrecarga: si escribis dos __init__, el segundo pisa
al primero EN SILENCIO. El idioma correcto es un unico __init__ con lo
esencial, mas @classmethod con nombre descriptivo para cada forma
alternativa de construccion.

"cls" (en vez del nombre de la clase) hace que las subclases hereden
los constructores alternativos y devuelvan instancias del tipo correcto
-algo que en Java requiere reescribir el metodo en cada subclase.
"""


class Lado:
    def __init__(self, longitud: float) -> None:
        self.longitud = longitud

    def __repr__(self) -> str:
        return f"Lado({self.longitud})"


class Figura:
    def __init__(self, lados: list[Lado]) -> None:
        self._lados = lados

    @classmethod
    def vacia(cls) -> "Figura":
        return cls([])

    @classmethod
    def desde_medidas(cls, *medidas: float) -> "Figura":
        return cls([Lado(m) for m in medidas])

    def __repr__(self) -> str:
        return f"Figura({self._lados})"


if __name__ == "__main__":
    f1 = Figura.vacia()
    f2 = Figura.desde_medidas(3, 4, 5)

    print(f1)  # Figura([])
    print(f2)  # Figura([Lado(3), Lado(4), Lado(5)])
