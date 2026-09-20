# PARTE 1
## Punto 1
## Java-ismos en Python


| # | Java-ismo | Dónde (clase.método) | Inversión que lo explica | Síntoma observable |
|---|---|---|---|---|
| 1 | Getters preventivos innecesarios | `Figura.getNombre`, `Lado.getLongitud`, etc. | Declaración al *runtime* / Dogma de visibilidad de Java | Exceso de ruido sintáctico y métodos vacíos que no protegen nada, ya que en Python la interfaz de atributos es directa o se usa `@property` bajo demanda. |
| 2 | Argumentos por defecto mutables | `Poligono.__init__` | Declaración al *runtime* (evaluación única al definir la función) | Múltiples instancias comparten la misma lista en memoria (`p1._lados is p2._lados` da `True`), provocando que agregar un elemento a uno afecte al otro. |
| 3 | Atributo de clase mutable accidental (*Static* accidental) | `Poligono.catalogo` | Declaración al *runtime* / Confusión de indentación y alcance | Una lista declarada a nivel de clase se comporta como un campo estático global, acumulando objetos de todas las instancias creadas sin control de instancia. |
| 4 | Olvido de `super().__init__()` | `Poligono.__init__` | Del compilador al acuerdo (ausencia de chequeo estático) | Los atributos del padre (`Figura`) no se inicializan de forma heredada, obligando a reasignarlos a mano y arriesgando un `AttributeError` futuro. |
| 5 | Sobrecarga de constructores simulada | `Triangulo.__init__`, `Cuadrado.__init__` | Declaración al *runtime* / Múltiples firmas de Java | Uso de `*args` y validaciones manuales con `isinstance` dentro de un único `__init__`, en lugar de emplear constructores alternativos (`@classmethod`). |
| 6 | Bucle acumulador manual en vez de *comprehension* | `Poligono.perimetro` | Traducción por reflejo / Hábitos de Java 7 o inferior | Código verboso de recorrido explícito (`for` con acumulador manual) donde Python idiomático utiliza funciones de reducción o expresiones limpias. |
| 7 | *Type hint* engañoso (*miente*) | `Poligono.area` | Declaración al *runtime* / Tipado opcional sin herramientas | La firma declara retornar `int` (`-> int`) pero devuelve un string (`"area sin calcular"`), generando falsa confianza si no se ejecuta `mypy`. |
| 8 | Fuga de encapsulamiento por retorno de referencias internas | `Poligono.getLados` / `_lados` | De garantía de compilador a contrato de equipo | El llamador recibe la lista interna y puede modificarla desde afuera (`lados.append(...)`) rompiendo las invariantes del objeto sin pasar por los métodos de control. |

## Punto 2:
- 1. Sobre los getters preventivos: En Java se escriben métodos get por las dudas de que el día de mañana necesites agregar lógica. En Python esto no hace falta porque existe @property: puedes empezar usando un atributo común y corriente, y si el día de mañana necesitas validarlo, lo conviertes en propiedad sin que el código que lo usa tenga que cambiar ni una sola línea.

- 2. Sobre los argumentos por defecto mutables (=[]): En Python, si pones una lista vacía en los parámetros de una función, esa lista se crea una sola vez cuando el archivo se lee por primera vez, no cada vez que creas un objeto. Si haces esto, todos tus objetos terminarán compartiendo la misma lista en la memoria por accidente. Por eso se usa None por defecto y se crea una lista nueva adentro del constructor para cada objeto.

- 3. Sobre el atributo de clase mutable (catalogo): En Python, si declaras una variable suelta dentro de la clase pero fuera del __init__, se convierte en un dato global compartido por todas las instancias (equivalente al static de Java). Si quieres que cada objeto tenga su propia lista, la variable debe inicializarse obligatoriamente dentro del constructor usando self..

- 4. Sobre el olvido de super().__init__(): A diferencia de Java (donde el compilador llama al padre automáticamente), en Python el hijo es totalmente responsable de invocar al padre. Si te olvidas de poner super().__init__() en la primera línea del constructor, los atributos del padre jamás se inicializan y tu objeto nacerá "a medio armar".

- 5. Sobre la sobrecarga de constructores (*args e isinstance): Como Python no permite tener varios métodos con el mismo nombre variando los tipos de sus parámetros como Java, intentar simularlo con un bloque gigante de if/else y isinstance es una mala práctica. Lo correcto en Python es usar constructores alternativos limpios mediante @classmethod.

- 6. Sobre el bucle acumulador manual: Escribir un bucle for tradicional sumando elemento por elemento con una variable auxiliar es una costumbre de lenguajes más rígidos. Python nos da herramientas integradas súper potentes y legibles como la función sum() combinada con expresiones generadoras para resolverlo en una sola línea.

- 7. Sobre los type hints que mienten: Poner pistas de tipos (como -> int) pero devolver otra cosa (como un str) confunde a quien lee el código y a las herramientas de análisis. En Python los tipos no frenan la ejecución por sí solos (no son un compilador estático estricto a menos que uses mypy), por lo que escribir mal el tipo arruina el propósito de documentar el código.

- 8. Sobre la fuga de encapsulamiento (getLados()): Retornar la lista interna de un objeto tal cual permite que cualquier código externo la modifique a escondidas (haciendo .append() o .pop()) rompiendo las reglas internas del objeto. La solución es devolver siempre una copia defensiva (como una tupla inmutable) para que de afuera se pueda leer, pero no alterar la estructura interna.


# PARTE 2
## Pregunta
***Si la sintaxis de guardar la referencia es idéntica en los tres casos (self._algo = algo), ¿cómo se ve en el código la diferencia entre agregación y composición? Respondé para las tres relaciones, señalando la línea exacta que lo delata.***

- <p>Composición (Poligono → Lado): Se delata en el código porque el todo fabrica la parte adentro, ejecutando el constructor de la parte (Lado(...)) directamente dentro de su propio constructor o método de creación.<br>
   Línea que lo delata: <br>
   self._lados = [Lado(medida) for _ in range(cantidad)] (o cuando se instancia el objeto parte dentro del __init__ del todo). La parte no se recibe hecha desde afuera.
- <p>Agregación (Taller → Poligono): Se delata en el código porque la parte entra ya construida por parámetro. El todo no fabrica la parte, solo la almacena en una lista o referencia que recibió desde el exterior.<br>
   Línea que lo delata: <br>
   def recibir(self, poligono: Poligono): self._poligonos.append(poligono). Aquí el parámetro poligono viene creado de afuera.
- <p>Asociación (Lado → Etiqueta): Se asemeja a la agregación en que el objeto colaborador se recibe desde afuera (típicamente opcional con valor por defecto None), pero representa un vínculo más débil entre objetos independientes donde un dato cumple un rol de referencia puntual.<br>
   Línea que lo delata: <br>
   def __init__(self, longitud: float, etiqueta: Optional[Etiqueta] = None): self._etiqueta = etiqueta. La etiqueta se crea de forma independiente y se acopla opcionalmente por referencia.

# Parte 3
**PoligonoRegular** se mantiene heredando de Poligono. Aunque en Java muchas jerarquías existían meramente para satisfacer al compilador con un tipo común, en este caso el dominio afirma de manera genuina que un polígono regular "es un" polígono. Por lo tanto, la herencia está respaldada por la semántica del negocio y no por una limitación técnica.

# Parte 4
***Explicar por qué una ABC no hubiera servido para el caso de PlanoCAD sin modificarla.***
<p>Porque las clases base abstractas (ABC) imponen un contrato de tipo nominal: la clase debe heredar explícitamente de la ABC para cumplirlo. Al estar `PlanoCAD` en un módulo externo e intocable (`libreria_externa.py`), no podemos alterar su código fuente para añadirle una cláusula de herencia. El `Protocol`, en cambio, es estructural: verifica desde afuera que los métodos existan, permitiendo integrar clases de terceros de forma retroactiva y sin acoplamiento.

***La elección entre ABC y Protocol para Poligono, ¿la decide el lenguaje o la decide el dominio?***
<p>La decide puramente el dominio. El lenguaje proporciona ambos mecanismos como opciones sintácticas, pero la decisión de diseño se basa en la naturaleza de la relación: si controlamos la jerarquía y buscamos control estricto e instanciación segura (falla temprana), optamos por una ABC; si el contrato debe aplicarse a código ajeno o transversal sin forzar dependencias, optamos por un Protocol.