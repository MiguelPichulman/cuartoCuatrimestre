# main.py
from catalogo import (
    UnidadMedida,
    Categoria,
    ProductoSimple,
    ProductoPorPeso,
    ProductoCombo,
    exportar_catalogo,
    ErrorDeDominio,
    Exportable
)
from libreria_externa import FichaPuntoDeVenta

def ejecutar_demo() -> None:
    print("=== CATALOGO FOOD STORE ===\n")

    # 1. Crear unidades de medida (Inmutables con @dataclass frozen=True)
    u_unidad = UnidadMedida("Unidad", "u", "unidad")
    u_masa = UnidadMedida("Kilogramo", "kg", "masa")

    # 2. Crear categorías
    c_bebidas = Categoria("Bebidas", "Bebidas y refrescos")
    c_fiambreria = Categoria("Fiambrería", "Fiambres y picadas")
    c_almacen = Categoria("Almacén", "Productos secos de almacén")
    c_combos = Categoria("Promociones", "Combos especiales")

    # 3. Instanciar al menos 4 productos independientes (sin contar los componentes del combo)
    prod_agua = ProductoSimple(
        nombre="Agua Mineral 500ml",
        precio_base=1200.0,
        stock_cantidad=50.0,
        unidad_venta=u_unidad,
        categoria_principal=c_bebidas
    )

    prod_queso = ProductoPorPeso(
        nombre="Queso Tybo",
        precio_base=8500.0,  # por kg
        stock_cantidad=10.0, # kg
        unidad_venta=u_masa,
        categoria_principal=c_fiambreria
    )

    prod_fideos = ProductoSimple(
        nombre="Fideos Matarazzo 500g",
        precio_base=1800.0,
        stock_cantidad=30.0,
        unidad_venta=u_unidad,
        categoria_principal=c_almacen
    )

    prod_pan = ProductoSimple(
        nombre="Pan Flauta",
        precio_base=2500.0,
        stock_cantidad=20.0,
        unidad_venta=u_unidad,
        categoria_principal=c_almacen
    )

    # ProductoCombo (agrupa componentes ya construidos)
    prod_combo = ProductoCombo(
        nombre="Combo Picada Express",
        componentes=[prod_agua, prod_queso],
        descuento=0.15,
        categoria_principal=c_combos
    )

    # 4. Demostrar operaciones y cálculos
    prod_agua.clasificar_en(Categoria("Destacados"), es_principal=False)
    print(f"Producto 1: {prod_agua.nombre} | Categoría Principal: {prod_agua.categoria_principal().nombre}")
    print(f"Precio Publicado: {prod_agua.precio_publicado} | Disponible: {prod_agua.disponible}")
    
    print(f"Precio final para 3 unidades de agua: $ {prod_agua.precio_final(3):.2f}")
    print(f"Precio final para 0.750 kg de queso: $ {prod_queso.precio_final(0.750):.2f}")
    print(f"Precio final para 2 combos: $ {prod_combo.precio_final(2):.2f}\n")

    # 5. Integración con FichaPuntoDeVenta (Exportable Protocol)
    ficha_externa = FichaPuntoDeVenta("POS-9988", "Caja Registradora Central")
    
    # Lista polimórfica con los 4 productos + combo + ficha externa
        
    items_exportables: list[Exportable] = [prod_agua, prod_queso, prod_fideos, prod_pan, prod_combo, ficha_externa]


    print("--- EXPORTANDO CATÁLOGO COMPLETO AL PUNTO DE VENTA ---")
    resultados_exportacion = exportar_catalogo(items_exportables)
    for linea in resultados_exportacion:
        print(linea)

    # 6. Demostrar falla temprana (TypeError al intentar instanciar un ABC incompleto)
    print("\n--- DEMOSTRANDO FALLA TEMPRANA (ABC) ---")
    try:
        from abc import ABC, abstractmethod
        class ProductoIncompleto(ABC):
            @abstractmethod
            def precio_final(self, cantidad: float) -> float:
                pass
        
        _ = ProductoIncompleto() #type: ignore[abstract]
    except TypeError as e:
        print(f"Falla temprana capturada correctamente al construir: {e}")

if __name__ == "__main__":
    ejecutar_demo()