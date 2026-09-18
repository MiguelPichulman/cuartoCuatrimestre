# catalogo.py (Módulo de Dominio - Requerimientos 1, 2, 3 y 4):
## Qué resuelve:
Modela todo el dominio del negocio de Food Store en memoria. Contiene las clases de soporte iniciales como UnidadMedida (implementada como un objeto de datos inmutable @dataclass(frozen=True)) y la clase Categoria con validaciones y propiedades de solo lectura, asegurando que los datos del catálogo nazcan siempre en un estado válido mediante excepciones de dominio (ErrorDeDominio).
##  Cómo se ejecuta:
No se ejecuta de forma independiente; es importado y utilizado por main.py y por cualquier script que requiera interactuar con las reglas del catálogo.

## Composición y Validación (Req. 1 y 2):
Se detalla que el Producto gestiona de manera interna y privada la lista de ProductoCategoria, aplicando el patrón de composición (la parte nace y muere con el todo) y entregando colecciones inmutables en forma de tupla para evitar modificaciones externas indebidas.  

## Jerarquía, Polimorfismo y Precios (Req. 3):
Implementación de la clase abstracta base Producto con su método abstracto @abstractmethod precio_final(cantidad). Se desarrollan las subclases ProductoSimple, ProductoPorPeso (con redondeo a 2 decimales) y ProductoCombo (con agregación de componentes y cálculo recursivo de precios), eliminando por completo los condicionales por tipo mediante polimorfismo puro. Respecto a ProductoDestacado, se resolvió mediante diseño por atributos de estado en lugar de herencia forzada, permitiendo destacar cualquier tipo de producto en la vidriera.

## Contratos Estructurales y Exportación (Req. 4):
Se define el contrato Exportable mediante un Protocol (exportar() -> str). Se implementa la función polimórfica exportar_catalogo(items: list[Exportable]) capaz de procesar conjuntamente los productos propios del catálogo y las instancias de FichaPuntoDeVenta provistas por la librería externa, respetando estrictamente la condición de no modificar el código de terceros. 

# libreria_externa.py
## Qué resuelve:
Provee la clase de terceros FichaPuntoDeVenta simulando el sistema de caja externo del comercio.
## Cómo se ejecuta:
No se ejecuta de forma independiente; sus instancias son consumidas y exportadas conjuntamente con el catálogo mediante exportar_catalogo().


# main.py
## Qué resuelve:
Script ejecutable de demostración (main.py). Construye el catálogo de Food Store utilizando productos simples, por peso y combos, valida reglas de negocio, demuestra el polimorfismo y exporta de manera unificada los datos junto a la ficha externa.
## Cómo se ejecuta:
Mediante la terminal posicionada en la raíz del proyecto con el comando: python main.py.  