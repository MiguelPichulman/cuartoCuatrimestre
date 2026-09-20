from typing import List, Tuple

class Figura:
    def __init__(self, nombre: str, color: str) -> None:
        self._nombre = nombre
        self._color = color
        self._construida = True


class Lado:
    def __init__(self, longitud: float) -> None:
        self._longitud = longitud

    @property
    def longitud(self) -> float:
        return self._longitud

    @longitud.setter
    def longitud(self, valor: float) -> None:
        if valor <= 0:
            raise ValueError("La longitud debe ser positiva")
        self._longitud = valor


class Poligono(Figura):
    def __init__(self, nombre: str, color: str, lados: List[Lado] | None = None, observaciones: List[str] | None = None) -> None:
        super().__init__(nombre, color)
        self._lados: List[Lado] = lados if lados is not None else []
        self._observaciones: List[str] = observaciones if observaciones is not None else []

    def lados_esperados(self) -> int:
        return 0

    @property
    def perimetro(self) -> float:
        return sum(l.longitud for l in self._lados)

    def area(self) -> float:
        return 0.0

    def agregar_observacion(self, texto: str) -> None:
        self._observaciones.append(texto)

    @property
    def lados(self) -> Tuple[Lado, ...]:
        return tuple(self._lados)


class Triangulo(Poligono):
    def __init__(self, nombre: str = "Triangulo", color: str = "negro", lados: List[Lado] | None = None) -> None:
        super().__init__(nombre, color, lados)

    def lados_esperados(self) -> int:
        return 3


class Cuadrado(Poligono):
    def __init__(self, nombre: str = "Cuadrado", color: str = "negro", lados: List[Lado] | None = None) -> None:
        super().__init__(nombre, color, lados)

    def lados_esperados(self) -> int:
        return 4


class PoligonoRegular(Poligono):
    def __init__(self, nombre: str, color: str, medida: float, cantidad: int) -> None:
        super().__init__(nombre, color, [Lado(medida) for _ in range(cantidad)])
        self._cantidad = cantidad

    def lados_esperados(self) -> int:
        return self._cantidad


if __name__ == "__main__":
    activo = True
    if activo:
        t = Triangulo("Triangulo", "rojo", [Lado(3.0), Lado(4.0), Lado(5.0)])
        c = Cuadrado("Cuadrado", "azul", [Lado(2.0), Lado(2.0), Lado(2.0), Lado(2.0)])
        
        print(f"Perimetro del triangulo: {t.perimetro}")
        print(f"Perimetro del cuadrado: {c.perimetro}")
        
        t.agregar_observacion("revisar el vertice A")
        
        r = PoligonoRegular("Pentagono", "verde", 4.0, 5)
        print(f"Perimetro del pentagono: {r.perimetro}")