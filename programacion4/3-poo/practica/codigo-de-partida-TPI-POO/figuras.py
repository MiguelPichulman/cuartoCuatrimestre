"""figuras.py — Dominio completo de POO (Partes 1 a 4 integradas)."""

from abc import ABC, abstractmethod
from dataclasses import dataclass
from typing import List, Tuple, Optional, Protocol


# --- PARTE 4: CONTRATO ESTRUCTURAL ---
class Exportable(Protocol):
    """Protocolo de tipado estructural: quien tenga 'exportar()', cumple."""
    def exportar(self) -> str:
        ...


class Figura:
    def __init__(self, nombre: str, color: str) -> None:
        self._nombre = nombre
        self._color = color
        self._construida = True


# --- PARTE 2: ETIQUETA ---
@dataclass(frozen=True)
class Etiqueta:
    """Objeto-valor inmutable que identifica a un lado."""
    texto: str


class Lado:
    def __init__(self, longitud: float, etiqueta: Optional[Etiqueta] = None) -> None:
        self._longitud = longitud
        self._etiqueta = etiqueta  # Asociación 0..1

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


# --- PARTE 3: CLASE ABSTRACTA (ABC) ---
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
        """Fuerza a las subclases a indicar cuántos lados esperan (falla temprana)."""
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
        """Copia defensiva para proteger el encapsulamiento interno."""
        return tuple(self._lados)

    # Implementación del contrato Exportable (Parte 4)
    def exportar(self) -> str:
        return f"Poligono[{self._nombre} ({len(self._lados)} lados) - Perímetro: {self.perimetro}]"


class Triangulo(Poligono):
    def __init__(self, nombre: str = "Triángulo", color: str = "negro", lados: Optional[List[Lado]] = None) -> None:
        super().__init__(nombre, color, lados)

    def lados_esperados(self) -> int:
        return 3


class Cuadrado(Poligono):
    def __init__(self, nombre: str = "Cuadrado", color: str = "negro", lados: Optional[List[Lado]] = None) -> None:
        super().__init__(nombre, color, lados)

    def lados_esperados(self) -> int:
        return 4


class Pentagono(Poligono):
    def __init__(self, nombre: str = "Pentágono", color: str = "negro", lados: Optional[List[Lado]] = None) -> None:
        super().__init__(nombre, color, lados)

    def lados_esperados(self) -> int:
        return 5


class Hexagono(Poligono):
    def __init__(self, nombre: str = "Hexágono", color: str = "negro", lados: Optional[List[Lado]] = None) -> None:
        super().__init__(nombre, color, lados)

    def lados_esperados(self) -> int:
        return 6


class PoligonoRegular(Poligono):
    def __init__(self, nombre: str, color: str, medida: float, cantidad: int) -> None:
        super().__init__(nombre, color, [Lado(medida) for _ in range(cantidad)])
        self._cantidad = cantidad

    def lados_esperados(self) -> int:
        return self._cantidad


# --- PARTE 2: TALLER (AGREGACIÓN) ---
class Taller:
    def __init__(self) -> None:
        self._poligonos: List[Poligono] = []

    def recibir(self, poligono: Poligono) -> None:
        """Agregación: recibe el polígono creado afuera."""
        self._poligonos.append(poligono)

    def restaurar(self, poligono: Poligono) -> None:
        """Remueve el polígono del taller sin destruirlo."""
        if poligono in self._poligonos:
            self._poligonos.remove(poligono)

    @property
    def inventario(self) -> Tuple[Poligono, ...]:
        """Copia defensiva: retorna una tupla inmutable con los polígonos."""
        return tuple(self._poligonos)


# --- FUNCIÓN POLIMÓRFICA DE LA PARTE 4 ---
def exportar_todo(items: List[Exportable]) -> List[str]:
    """Recibe una lista mixta (Polígonos y PlanoCAD) y los exporta polimórficamente."""
    return [item.exportar() for item in items]