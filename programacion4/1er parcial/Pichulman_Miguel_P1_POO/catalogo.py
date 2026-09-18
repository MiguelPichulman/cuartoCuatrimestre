from dataclasses import dataclass
from abc import ABC, abstractmethod
from typing import Final, Protocol

class ErrorDeDominio(ValueError):
    """Excepción de dominio para reglas de negocio del catálogo (hereda de ValueError)."""
    pass

@dataclass(frozen=True)
class UnidadMedida:
    """Objeto de datos inmutable para las unidades de venta (kg, g, L, u)."""
    nombre: str
    simbolo: str
    tipo: str  # "masa", "volumen", "unidad"


class Categoria:
    """Agrupa productos del catálogo. La descripción es opcional."""
    def __init__(self, nombre: str, descripcion: str = "") -> None:
        if not nombre or not nombre.strip():
            raise ErrorDeDominio("El nombre de la categoría no puede estar vacío.")
        self._nombre: str = nombre
        self._descripcion: str = descripcion

    @property
    def nombre(self) -> str:
        return self._nombre

    @property
    def descripcion(self) -> str:
        return self._descripcion


class ProductoCategoria:
    """Vínculo de composición entre un Producto y una Categoria."""
    def __init__(self, categoria: Categoria, es_principal: bool) -> None:
        self._categoria: Categoria = categoria
        self._es_principal: bool = es_principal

    @property
    def categoria(self) -> Categoria:
        return self._categoria

    @property
    def es_principal(self) -> bool:
        return self._es_principal

    def _marcar_principal(self, valor: bool) -> None:
        self._es_principal = valor


class Producto(ABC):
    """Clase abstracta base del catálogo."""
    def __init__(self, nombre: str, precio_base: float, stock_cantidad: float, unidad_venta: UnidadMedida | None, categoria_principal: Categoria) -> None:
        if not nombre or not nombre.strip():
            raise ErrorDeDominio("El nombre del producto no puede estar vacío.")
        if precio_base < 0:
            raise ErrorDeDominio("El precio base no puede ser negativo.")
        if stock_cantidad < 0:
            raise ErrorDeDominio("El stock no puede ser negativo.")
        if categoria_principal is None:
            raise ErrorDeDominio("Todo producto debe tener una categoría principal obligatoria.")

        self._nombre: str = nombre
        self._precio_base: float = precio_base
        self._stock_cantidad: float = stock_cantidad
        self._habilitado: bool = True
        self._unidad_venta: UnidadMedida | None = unidad_venta
        self._orden_vidriera: int | None = None
        
        # Composición: El producto fabrica su primer vínculo principal
        self._clasificaciones: list[ProductoCategoria] = []
        self._clasificaciones.append(ProductoCategoria(categoria_principal, es_principal=True))

    @property
    def es_destacado(self) -> bool:
        return self._orden_vidriera is not None

    def destacar(self, orden: int) -> None:
        if orden < 0:
            raise ErrorDeDominio("El orden en vidriera no puede ser negativo.")
        self._orden_vidriera = orden

    @property
    def nombre(self) -> str:
        return self._nombre

    @property
    def precio_base(self) -> float:
        return self._precio_base

    @property
    def unidad_venta(self) -> UnidadMedida | None:
        return self._unidad_venta

    @property
    def disponible(self) -> bool:
        return self._habilitado and self._stock_cantidad > 0

    @property
    def precio_publicado(self) -> str:
        if self._unidad_venta is not None:
            return f"$ {self._precio_base:.2f}/{self._unidad_venta.simbolo}"
        return f"$ {self._precio_base:.2f}"

    def habilitar(self) -> None:
        self._habilitado = True

    def deshabilitar(self) -> None:
        self._habilitado = False

    def clasificar_en(self, categoria: Categoria, es_principal: bool = False) -> None:
        for clasificacion in self._clasificaciones:
            if clasificacion.categoria.nombre == categoria.nombre:
                raise ErrorDeDominio(f"El producto ya se encuentra clasificado en la categoría '{categoria.nombre}'.")

        if es_principal:
            for clasificacion in self._clasificaciones:
                if clasificacion.es_principal:
                    clasificacion._marcar_principal(False)
        
        self._clasificaciones.append(ProductoCategoria(categoria, es_principal))

    def categorias(self) -> tuple[ProductoCategoria, ...]:
        return tuple(self._clasificaciones)

    def categoria_principal(self) -> Categoria:
        for clasificacion in self._clasificaciones:
            if clasificacion.es_principal:
                return clasificacion.categoria
        raise ErrorDeDominio("El producto no tiene una categoría principal válida.")

    @abstractmethod
    def precio_final(self, cantidad: float) -> float:
        pass

    def exportar(self) -> str:
        """Cumple con el contrato estructural Protocol Exportable."""
        return f"PRODUCTO {self._nombre} | {self.precio_publicado} | Stock: {self._stock_cantidad}"


class ProductoSimple(Producto):
    """Se vende por pieza. Cantidad entera >= 1."""
    def precio_final(self, cantidad: float) -> float:
        if not isinstance(cantidad, (int, float)) or cantidad < 1 or cantidad % 1 != 0:
            raise ErrorDeDominio("La cantidad para un ProductoSimple debe ser un valor entero mayor o igual a 1.")
        return self._precio_base * int(cantidad)


class ProductoPorPeso(Producto):
    """Se vende por peso. Cantidad > 0, admite decimales y redondea a 2 decimales."""
    def precio_final(self, cantidad: float) -> float:
        if not isinstance(cantidad, (int, float)) or cantidad <= 0:
            raise ErrorDeDominio("La cantidad para un ProductoPorPeso debe ser mayor a 0.")
        return round(self._precio_base * float(cantidad), 2)


class ProductoCombo(Producto):
    """Agrupa entre 2 y N productos ya construidos y aplica un descuento sobre la suma."""
    def __init__(self, nombre: str, componentes: list[Producto], descuento: float, categoria_principal: Categoria) -> None:
        if len(componentes) < 2:
            raise ErrorDeDominio("Un ProductoCombo debe agrupar al menos 2 componentes.")
        if not (0 <= descuento < 1):
            raise ErrorDeDominio("El descuento debe estar en el rango [0, 1).")
        
        self._componentes: list[Producto] = componentes
        self._descuento: float = descuento
        
        precio_base_derivado = sum(c.precio_final(1) for c in componentes) * (1 - descuento)
        stock_derivado = min(c._stock_cantidad for c in componentes) if componentes else 0.0
        
        super().__init__(nombre, precio_base_derivado, stock_derivado, unidad_venta=None, categoria_principal=categoria_principal)

    def componentes(self) -> tuple[Producto, ...]:
        return tuple(self._componentes)

    def precio_final(self, cantidad: float) -> float:
        if not isinstance(cantidad, (int, float)) or cantidad < 1 or cantidad % 1 != 0:
            raise ErrorDeDominio("La cantidad para un ProductoCombo debe ser un valor entero mayor o igual a 1.")
        
        suma_componentes = sum(c.precio_final(1) for c in self._componentes)
        precio_unitario_combo = suma_componentes * (1 - self._descuento)
        return precio_unitario_combo * int(cantidad)


class Exportable(Protocol):
    """Contrato estructural (Protocol) para exportación."""
    def exportar(self) -> str:
        ...


def exportar_catalogo(items: list[Exportable]) -> list[str]:
    """Recibe productos y fichas de punto de venta en una misma lista polimórfica."""
    return [item.exportar() for item in items]