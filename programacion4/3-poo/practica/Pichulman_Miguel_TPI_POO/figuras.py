from abc import ABC, abstractmethod
from dataclasses import dataclass
from typing import List, Tuple, Optional, Protocol


class Exportable(Protocol):
    def exportar(self) -> str:
        ...


class Figura:
    def __init__(self, nombre: str, color: str) -> None:
        self._nombre = nombre
        self._color = color
        self._construida = True


@dataclass(frozen=True)
class Etiqueta:
    texto: str


class Lado:
    def __init__(self, longitud: float, etiqueta: Optional[Etiqueta] = None) -> None:
        self._longitud = longitud
        self._etiqueta = etiqueta

    @property
    def longitud(self) -> float:
        return self._longitud

    @longitud.setter
    def longitud(self, valor: float) -> None:
        if valor <= 0:
            raise ValueError("La longitud debe ser positiva")
        self._longitud = valor

    @property
    def etiqueta(self) -> Optional[Etiqueta]:
        return self._etiqueta


class Poligono(Figura, ABC):
    def __init__(
        self, 
        nombre: str, 
        color: str, 
        lados: Optional[List[Lado]] = None, 
        observaciones: Optional[List[str]] = None
    ) -> None:
        super().__init__(nombre, color)
        self._lados: List[Lado] = lados if lados is not None else []
        self._observaciones: List[str] = observaciones if observaciones is not None else []

    @abstractmethod
    def lados_esperados(self) -> int:
        pass

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

    def exportar(self) -> str:
        return f"Poligono[{self._nombre} ({len(self._lados)} lados) - Perimetro: {self.perimetro}]"


class Triangulo(Poligono):
    def __init__(self, nombre: str = "Triangulo", color: str = "negro", lados: Optional[List[Lado]] = None) -> None:
        super().__init__(nombre, color, lados)

    def lados_esperados(self) -> int:
        return 3


class Cuadrado(Poligono):
    def __init__(self, nombre: str = "Cuadrado", color: str = "negro", lados: Optional[List[Lado]] = None) -> None:
        super().__init__(nombre, color, lados)

    def lados_esperados(self) -> int:
        return 4


class Pentagono(Poligono):
    def __init__(self, nombre: str = "Pentagono", color: str = "negro", lados: Optional[List[Lado]] = None) -> None:
        super().__init__(nombre, color, lados)

    def lados_esperados(self) -> int:
        return 5


class Hexagono(Poligono):
    def __init__(self, nombre: str = "Hexagono", color: str = "negro", lados: Optional[List[Lado]] = None) -> None:
        super().__init__(nombre, color, lados)

    def lados_esperados(self) -> int:
        return 6


class PoligonoRegular(Poligono):
    def __init__(self, nombre: str, color: str, medida: float, cantidad: int) -> None:
        super().__init__(nombre, color, [Lado(medida) for _ in range(cantidad)])
        self._cantidad = cantidad

    def lados_esperados(self) -> int:
        return self._cantidad


class Taller:
    def __init__(self) -> None:
        self._poligonos: List[Poligono] = []

    def recibir(self, poligono: Poligono) -> None:
        self._poligonos.append(poligono)

    def restaurar(self, poligono: Poligono) -> None:
        if poligono in self._poligonos:
            self._poligonos.remove(poligono)

    @property
    def inventario(self) -> Tuple[Poligono, ...]:
        return tuple(self._poligonos)


def exportar_todo(items: List[Exportable]) -> List[str]:
    return [item.exportar() for item in items]