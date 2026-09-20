# Informe TPI — Programación Orientada a Objetos (Unidad 3)

## 1. Tabla de los 8 Java-isms (Parte 1)

| # | Java-ismo                            | Dónde (clase.método)                       | Inversión que lo explica         | Síntoma observable                                                                                                                        |
| - | ------------------------------------ | ------------------------------------------ | -------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------- |
| 1 | Getters preventivos innecesarios     | `Figura.getNombre()`, `Lado.getLongitud()` | De la declaración al *runtime*   | Métodos vacíos repetitivos; en Python se utilizan atributos directos o `@property` cuando existe lógica real.                             |
| 2 | Argumentos por defecto mutables      | `Poligono.__init__()`                      | De la declaración al *runtime*   | Instancias distintas comparten la misma lista en memoria (`p1._lados is p2._lados` da `True`).                                            |
| 3 | Atributo de clase mutable accidental | `Poligono.catalogo`                        | De la declaración al *runtime*   | Una variable declarada a nivel de clase actúa como un repositorio estático global compartido.                                             |
| 4 | Olvido de `super().__init__()`       | `Poligono.__init__()`                      | Del compilador al acuerdo        | Los atributos del padre no se inicializan automáticamente, arriesgando un `AttributeError` posterior.                                     |
| 5 | Sobrecarga de constructores simulada | `Triangulo.__init__()`                     | De la declaración al *runtime*   | Uso de `*args` con validaciones `isinstance` en lugar de constructores alternativos mediante `@classmethod`.                              |
| 6 | Bucle acumulador manual              | `Poligono.perimetro()`                     | Traducción por reflejo           | Recorrido explícito con una variable temporal en lugar de funciones más idiomáticas como `sum()`.                                         |
| 7 | *Type hint* engañoso                 | `Poligono.area()`                          | De la declaración al *runtime*   | La firma declara retornar `int`, pero el método retorna un `str`, generando falsa confianza si no se utiliza una herramienta como `mypy`. |
| 8 | Fuga de encapsulamiento              | `Poligono.getLados()`                      | De garantía a contrato de equipo | Se retorna directamente una colección interna mutable que el cliente puede modificar desde el exterior.                                   |

## 2. Tabla de equivalencias sobre el propio código

| Elemento en Java                           | Cómo quedó en tu código Python                            | ¿Traducción directa o rediseño? | Por qué                                                                                                                                                  |
| ------------------------------------------ | --------------------------------------------------------- | ------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `private List<Lado> lados`                 | `self._lados` con *property* de copia defensiva (`tuple`) | Rediseño                        | Python no posee modificadores privados estructurales; el encapsulamiento se gestiona mediante convenciones como `_` y estructuras inmutables defensivas. |
| `public abstract class Poligono`           | `class Poligono(Figura, ABC)`                             | Traducción directa              | Ambas tecnologías permiten representar clases abstractas. Python lo implementa mediante el módulo `abc` y `@abstractmethod`.                             |
| Getters masivos (`getNombre()`)            | Atributo público directo o `@property` puntual            | Rediseño                        | Python evita el *boilerplate* de getters cuando no existe lógica adicional y permite incorporar `@property` cuando sea necesario.                        |
| `interface Comparable` / contrato estricto | `typing.Protocol` (tipado estructural)                    | Rediseño                        | Permite definir contratos abstractos sin obligar a la clase a declarar una herencia explícita mediante `implements`.                                     |
| `ArrayList<T>` / `List<T>`                 | `list[T]` / `tuple[T, ...]`                               | Traducción directa              | Las colecciones genéricas de Java encuentran una correspondencia directa con las listas y tuplas tipadas de Python.                                      |

## 3. Pregunta de las tres relaciones (Parte 2)

### ¿Cómo se diferencia agregación de composición en el código si la asignación es idéntica (`self._algo = algo`)?

* **Composición (`Poligono` → `Lado`):** se identifica porque el todo fabrica la parte internamente, instanciándola dentro de su constructor. Por ejemplo:

  ```python
  self._lados = [Lado(m) for m in medidas]
  ```

  En este caso, `Poligono` controla la creación y el ciclo de vida de sus `Lado`.

* **Agregación (`Taller` → `Poligono`):** se identifica porque la parte llega ya construida desde el exterior mediante un parámetro. Por ejemplo:

  ```python
  def recibir(self, poligono):
      self._poligonos.append(poligono)
  ```

  En este caso, el `Taller` recibe un `Poligono` que existe independientemente del taller. Si el taller desaparece, el polígono puede continuar existiendo.

## 4. Decisión sobre `PoligonoRegular` (Parte 3)

Se mantiene heredando de `Poligono`. Aunque en Java algunas jerarquías pueden haberse creado por exigencias sintácticas del lenguaje, en este caso el dominio afirma genuinamente que un polígono regular **es un polígono**.

Por lo tanto, la herencia está justificada por la semántica del dominio y no por una limitación técnica del lenguaje.

## 5. ¿Lo decide el lenguaje o el dominio? (Parte 4)

La elección entre `ABC` y `Protocol` responde principalmente a una decisión de diseño del dominio y de la arquitectura.

El lenguaje proporciona las herramientas técnicas, pero la elección depende de la relación que se desea establecer:

* Se utiliza **`ABC`** cuando se controla la jerarquía de clases y se busca establecer una relación explícita de herencia junto con un contrato común.
* Se utiliza **`Protocol`** cuando se busca un contrato estructural y un mayor desacoplamiento, especialmente cuando las clases que cumplen el contrato no necesitan pertenecer a una misma jerarquía.

## 6. Cierre conceptual: Cambio vs. Identidad

### Lo que cambió

Cambió el mecanismo utilizado para garantizar determinadas reglas del diseño. Se pasó de un compilador estático con restricciones explícitas a un modelo basado en convenciones de equipo, tipado opcional mediante herramientas como `mypy` y propiedades dinámicas.

Además, se eliminó *boilerplate* innecesario de Java, como getters y constructores sobrecargados simulados cuando Python ofrece mecanismos más idiomáticos.

### Lo que se mantuvo idéntico

Se mantuvo el modelo conceptual y el diseño orientado a objetos representado en el UML.

La semántica del dominio —clases, generalización, composición, agregación y asociaciones— permanece esencialmente invariable porque el análisis del problema y sus relaciones conceptuales son independientes del lenguaje de programación utilizado.
