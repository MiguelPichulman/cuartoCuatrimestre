# Food Store - Primera Evaluación Parcial | Programación IV (UTN)

## Descripción del Proyecto
Sistema de gestión de catálogo en memoria para el comercio "Food Store", desarrollado en **Python 3.12+**. Implementa un modelo orientado a objetos robusto enfocado en encapsulamiento estricto, relaciones estructurales (composición, agregación, asociación), polimorfismo mediante clases abstractas (`ABC`) y contratos estructurales mediante `Protocol`.

---

## Estructura del Proyecto y Qué Resuelve Cada Archivo

* **`catalogo.py`**
  * Modela todo el dominio del negocio. Contiene la unidad de medida inmutable (`@dataclass frozen=True`), las categorías, la clase abstracta base `Producto` con sus respectivas subclases de venta (`ProductoSimple`, `ProductoPorPeso`, `ProductoCombo`), la lógica de composición interna para las clasificaciones y el contrato estructural `Exportable` (Protocol) junto con la función `exportar_catalogo()`.
  
* **`libreria_externa.py`**
  * Archivo provisto por la cátedra (**sin modificaciones**). Contiene la clase de terceros `FichaPuntoDeVenta`, la cual es integrada polimórficamente al catálogo mediante el contrato estructural `Exportable`.

* **`main.py`**
  * Script ejecutable de demostración. Arma un catálogo completo cubriendo todas las subclases de venta, aplica clasificaciones, calcula precios finales con distintas cantidades, demuestra validaciones de dominio y realiza la exportación unificada junto a la ficha externa del punto de venta.

* **`uml/modelo_final.md`**
  * Contiene el diagrama de clases final modelado en notación Mermaid, reflejando fielmente la estructura, atributos, relaciones y multiplicidades del código

* **`link_video.txt`** 
  * https://drive.google.com/file/d/1JxEPSV9zF9ZKj639ZtjAxbxtkzWn5yOx/view?usp=drive_link

---

## Instrucciones de Ejecución

Este proyecto no requiere bases de datos, librerías de terceros ni entornos virtuales armados. Funciona de manera nativa con cualquier intérprete limpio de **Python 3.12 o superior**.

1. Abre tu terminal posicionada en la raíz del proyecto (`Pichulman_Miguel_P1_P00/`).
2. Ejecuta el script de demostración con el siguiente comando:
   ```bash
   python main.py