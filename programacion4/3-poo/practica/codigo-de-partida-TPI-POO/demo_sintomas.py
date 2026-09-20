"""demo_sintomas.py — Demostración de dos síntomas de los java-ismos en la Parte 1."""

# Importamos las clases originales del código de diagnóstico de partida
from parte1_diagnostico import Poligono, Triangulo


def demostrar_default_mutable() -> None:
    print("=== SÍNTOMA 1: Default mutable compartido ===")
    p1 = Triangulo()
    p2 = Triangulo()
    
    # Comprobamos si las listas internas apuntan exactamente al mismo objeto en memoria
    mismo_objeto = (p1._lados is p2._lados)
    print(f"p1._lados y p2._lados comparten el mismo objeto en memoria: {mismo_objeto}")
    
    if mismo_objeto:
        print("-> Evidencia: Al ser el mismo objeto, agregar un elemento a p1 afectará silenciosamente a p2.")


def demostrar_atributo_clase_mutable() -> None:
    print("\n=== SÍNTOMA 2: Atributo de clase mutable accidental (catalogo) ===")
    tamano_inicial = len(Poligono.catalogo)
    print(f"Elementos en Poligono.catalogo antes de instanciar: {tamano_inicial}")
    
    _ = Triangulo()
    _ = Triangulo()
    
    tamano_final = len(Poligono.catalogo)
    print(f"Elementos en Poligono.catalogo después de instanciar dos objetos: {tamano_final}")
    print("-> Evidencia: La lista global acumula instancias de manera incontrolada a nivel de clase.")


if __name__ == "__main__":
    demostrar_default_mutable()
    demostrar_atributo_clase_mutable()