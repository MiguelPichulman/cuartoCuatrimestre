"""main.py — Demo ejecutable del Trabajo Practico Integrador."""

from figuras import Triangulo, Cuadrado, Pentagono, Hexagono, Lado, Etiqueta, Taller, exportar_todo, Poligono, Exportable
from libreria_externa import PlanoCAD
from abc import ABC


def ejecutar_demo() -> None:
    print("=== 1. DEMOSTRACION DE FALLA TEMPRANA (ABC) ===")
    try:
        print("Intentando instanciar una clase poligono incompleta sin 'lados_esperados'...")
        
        class PoligonoIncompleto(Poligono):
            pass
        
        _ = PoligonoIncompleto("Roto", "gris")  # type: ignore[abstract]
    except TypeError as e:
        print(f"-> ¡Exito! La ABC freno la ejecucion al construir: {e}\n")

    print("=== 2. CREACION DE POLIGONOS Y ETIQUETADO ===")
    etq1 = Etiqueta("Base Principal")
    etq2 = Etiqueta("Lateral Reforzado")

    lados_triangulo = [Lado(3.0, etq1), Lado(4.0, etq2), Lado(5.0)]
    t = Triangulo("Triangulo de Pruebas", "rojo", lados_triangulo)
    c = Cuadrado("Cuadrado Base", "azul", [Lado(2.0), Lado(2.0), Lado(2.0), Lado(2.0)])
    p = Pentagono("Pentagono Estelar", "verde", [Lado(1.5) for _ in range(5)])
    h = Hexagono("Hexagono Industrial", "amarillo", [Lado(2.5) for _ in range(6)])

    print(f"Creado: {t._nombre} con perimetro {t.perimetro}")
    print(f"Creado: {c._nombre} con perimetro {c.perimetro}")

    print("\n=== 3. TALLER Y AGREGACION ===")
    taller = Taller()
    taller.recibir(t)
    taller.recibir(c)
    taller.recibir(p)
    taller.recibir(h)

    print(f"Inventario actual del taller ({len(taller.inventario)} poligonos):")
    for poly in taller.inventario:
        print(f" - {poly._nombre} ({poly.lados_esperados()} lados)")

    print("\n=== 4. EXPORTACION POLIMORFICA CON PROTOCOL ===")
    plano_cad = PlanoCAD("CAD-9988", "1:50")
    
    elementos_exportables: list[Exportable] = [t, c, p, h, plano_cad]
    
    resultados = exportar_todo(elementos_exportables)
    for res in resultados:
        print(f"Exportado -> {res}")

    print("\n=== 5. DEMOSTRACION DE CICLO DE VIDA ===")
    print(f"Lados de {t._nombre} antes de eliminar el objeto: {len(t.lados)}")
    del t
    print("-> El poligono 't' fue eliminado de memoria; sus lados asociados mueren con el.")

    print(f"\nPoligonos en el taller antes de restaurar 'c': {len(taller.inventario)}")
    taller.restaurar(c)
    print(f"Poligonos en el taller despues de restaurar 'c': {len(taller.inventario)}")
    print(f"El poligono 'c' sigue existiendo de forma autonoma: {c._nombre} (Perimetro: {c.perimetro})")


if __name__ == "__main__":
    ejecutar_demo()