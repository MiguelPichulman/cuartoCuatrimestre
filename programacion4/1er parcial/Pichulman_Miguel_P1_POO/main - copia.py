# main.py
from catalogo import (
    UnidadMedida,
    Categoria,
    ProductoSimple,
    ProductoPorPeso,
    ProductoCombo,
    exportar_catalogo,
    ErrorDeDominio
)
from libreria_externa import FichaPuntoDeVenta

def ejecutar_demo() -> None:
    print("=== INICIANDO DEMO DEL CATÁLOGO FOOD STORE ==-\n")

    # 1. Crear unidades de medida (Inmutables con @dataclass frozen=True)
    u_unidad = UnidadMedida("Unidad", "u", "unidad")
    u_masa = UnidadMedida("Kilogramo", "kg", "masa")

    # 2. Crear categorías
    c_bebidas = Categoria("Bebidas", "Bebidas y refrescos")
    c_fiambreria = Categoria("Fiambrería", "Fiambres y picadas")
    c_combos = Categoria("Promociones", "Combos especiales")

    # 3. Instanciar productos cubriendo las subclases de venta
    # ProductoSimple (por pieza, cantidad entera >= 1)
    prod_simple = ProductoSimple(
        nombre="Agua Mineral 500ml",
        precio_base=1200.0,
        stock_cantidad=50.0,
        unidad_venta=u_unidad,
        categoria_principal=c_bebidas
    )

    # ProductoPorPeso (permite decimales, redondea a 2 decimales)
    prod_peso = ProductoPorPeso(
        nombre="Queso Tybo",
        precio_base=8500.0,  # por kg
        stock_cantidad=10.0, # kg
        unidad_venta=u_masa,
        categoria_principal=c_fiambreria
    )

    # ProductoCombo (agrupa entre 2 y N componentes ya construidos con descuento en [0, 1))
    prod_combo = ProductoCombo(
        nombre="Combo Picada Express",
        componentes=[prod_simple, prod_peso],
        descuento=0.15, # 15% de descuento
        categoria_principal=c_combos
    )

    # 4. Demostrar clasificaciones adicionales y composición
    prod_simple.clasificar_en(Categoria("Destacados"), es_principal=False)
    print(f"Producto: {prod_simple.nombre} | Categoría Principal: {prod_simple.categoria_principal().nombre}")
    print(f"Precio Publicado: {prod_simple.precio_publicado} | Disponible: {prod_simple.disponible}")
    
    # Calcular precio final con cantidades distintas
    print(f"Precio final para 3 unidades de agua: $ {prod_simple.precio_final(3):.2f}")
    print(f"Precio final para 0.750 kg de queso: $ {prod_peso.precio_final(0.750):.2f}")
    print(f"Precio final para 2 combos: $ {prod_combo.precio_final(2):.2f}\n")

    # 5. Integración con FichaPuntoDeVenta (Exportable Protocol - Req. 4)
    ficha_externa = FichaPuntoDeVenta("POS-9988", "Caja Registradora Central")
    
    # Lista polimórfica que acepta productos y fichas externas sin herencia compartida
    items_exportables = [prod_simple, prod_peso, prod_combo, ficha_externa]
    
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
        
        # Intentar instanciarla debe fallar de inmediato al construir
        _ = ProductoIncompleto()
    except TypeError as e:
        print(f"Falla temprana capturada correctamente al construir: {e}")

if __name__ == "__main__":
    ejecutar_demo()