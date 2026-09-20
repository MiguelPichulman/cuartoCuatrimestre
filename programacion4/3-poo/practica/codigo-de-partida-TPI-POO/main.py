"""main.py — Demo ejecutable del Trabajo Práctico Integrador."""

from figuras import Triangulo, Cuadrado, Pentagono, Hexagono, Lado, Etiqueta, Taller, exportar_todo, Poligono, Exportable
from libreria_externa import PlanoCAD
from abc import ABC


def ejecutar_demo() -> None:
    print("=== 1. DEMOSTRACIÓN DE FALLA TEMPRANA (ABC) ===")
    try:
        print("Intentando instanciar una clase polígono incompleta sin 'lados_esperados'...")
        
        class PoligonoIncompleto(Poligono):
            pass  # Olvidamos implementar el método abstracto obligatorio
        
        _ = PoligonoIncompleto("Roto", "gris") #type: ignore[abstract]
    except TypeError as e:
        print(f"-> ¡Éxito! La ABC frenó la ejecución al construir (Falla temprana): {e}\n")

    print("=== 2. CREACIÓN DE POLÍGONOS Y ETIQUETADO (Composición y Asociación) ===")
    # Creamos etiquetas (objeto-valor inmutable con @dataclass)
    etq1 = Etiqueta("Base Principal")
    etq2 = Etiqueta("Lateral Reforzado")

    # Creamos lados con asociación opcional a Etiqueta (0..1)
    lados_triangulo = [Lado(3.0, etq1), Lado(4.0, etq2), Lado(5.0)]
    t = Triangulo("Triángulo de Pruebas", "rojo", lados_triangulo)
    c = Cuadrado("Cuadrado Base", "azul", [Lado(2.0), Lado(2.0), Lado(2.0), Lado(2.0)])
    p = Pentagono("Pentágono Estelar", "verde", [Lado(1.5) for _ in range(5)])
    h = Hexagono("Hexágono Industrial", "amarillo", [Lado(2.5) for _ in range(6)])

    print(f"Creado: {t._nombre} con perímetro {t.perimetro}")
    print(f"Creado: {c._nombre} con perímetro {c.perimetro}")

    print("\n=== 3. TALLER Y AGREGACIÓN (Inventario y Copia Defensiva) ===")
    taller = Taller()
    taller.recibir(t)
    taller.recibir(c)
    taller.recibir(p)
    taller.recibir(h)

    print(f"Inventario actual del taller ({len(taller.inventario)} polígonos):")
    for poly in taller.inventario:
        print(f" - {poly._nombre} ({poly.lados_esperados()} lados)")

    print("\n=== 4. EXPORTACIÓN POLIMÓRFICA CON PROTOCOL (Polígonos + PlanoCAD) ===")
    plano_cad = PlanoCAD("CAD-9988", "1:50")
    
    # Listas mixtas operando juntas gracias al Protocol estructural de tipado
    elementos_exportables: list[Exportable] = [t, c, p, h, plano_cad]
    
    resultados = exportar_todo(elementos_exportables)
    for res in resultados:
        print(f"Exportado -> {res}")

    print("\n=== 5. DEMOSTRACIÓN DE CICLO DE VIDA (Composición vs Agregación) ===")
    # Composición: El lado no sobrevive de forma independiente al borrado del polígono.
    print(f"Lados de {t._nombre} antes de eliminar el objeto: {len(t.lados)}")
    del t
    print("-> El polígono 't' fue eliminado de memoria; sus lados asociados mueren con él (Composición estricta).")

    # Agregación: El polígono sobrevive si se remueve del taller.
    print(f"\nPolígonos en el taller antes de restaurar 'c': {len(taller.inventario)}")
    taller.restaurar(c)
    print(f"Polígonos en el taller después de restaurar 'c': {len(taller.inventario)}")
    print(f"El polígono 'c' sigue existiendo de forma autónoma: {c._nombre} (Perímetro: {c.perimetro})")


if __name__ == "__main__":
    ejecutar_demo()