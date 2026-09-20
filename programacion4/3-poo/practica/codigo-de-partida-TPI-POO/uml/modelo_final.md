# Diagrama UML Final — Modelo Orientado a Objetos

```mermaid
classDiagram
class Exportable {
    <<Protocol>>
    +exportar() str
}

class Figura {
    #_nombre str
    #_color str
}

class Poligono {
    <<abstract>>
    #_lados list~Lado~
    +lados_esperados()* int
    +perimetro() float
    +lados() tuple~Lado~
    +exportar() str
}

class Lado {
    #_longitud float
    #_etiqueta Etiqueta | None
    +longitud float
    +etiqueta Etiqueta | None
}

class Etiqueta {
    <<frozen dataclass>>
    +texto str
}

class Taller {
    #_poligonos list~Poligono~
    +recibir(poligono)
    +restaurar(poligono)
    +inventario() tuple~Poligono~
}

class Triangulo {
    +lados_esperados() int
}

class Cuadrado {
    +lados_esperados() int
}

class Pentagono {
    +lados_esperados() int
}

class Hexagono {
    +lados_esperados() int
}

class PoligonoRegular {
    +lados_esperados() int
}

class PlanoCAD {
    <<librería externa>>
    +exportar() str
}

Figura <|-- Poligono : herencia
Poligono <|-- Triangulo : herencia
Poligono <|-- Cuadrado : herencia
Poligono <|-- Pentagono : herencia
Poligono <|-- Hexagono : herencia
Poligono <|-- PoligonoRegular : herencia

Poligono "1" *-- "3..*" Lado : composición
Lado "1" --> "0..1" Etiqueta : asociación
Taller "1" o-- "0..*" Poligono : agregación

Poligono ..> Exportable : cumple (Protocol)
PlanoCAD ..> Exportable : cumple sin saberlo (Protocol)