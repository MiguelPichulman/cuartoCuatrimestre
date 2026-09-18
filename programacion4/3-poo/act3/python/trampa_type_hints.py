"""
ACTIVIDAD 3 - Trampa 4: los type hints no validan nada

def agregar_lado(self, lado: Lado) acepta un str sin protestar. Acepta
None, un int, cualquier cosa. Los hints son documentacion para el
lector y metadatos para herramientas (el interprete los guarda en
__annotations__ y sigue de largo, sin verificar nada en runtime).

La unica forma de convertir esto en una verificacion real es corriendo
mypy (idealmente en CI):

    mypy --strict trampa_type_hints.py
"""


class Lado:
    def __init__(self, longitud: float) -> None:
        self.longitud = longitud


class Poligono:
    def __init__(self) -> None:
        self._lados: list[Lado] = []

    def agregar_lado(self, lado: Lado) -> None:
        # El type hint dice "Lado", pero en RUNTIME no se verifica nada:
        self._lados.append(lado)


if __name__ == "__main__":
    p = Poligono()

    # Esto viola el type hint (se pasa un str en vez de un Lado) y,
    # sin embargo, el interprete lo ejecuta sin quejarse:
    p.agregar_lado("esto no es un Lado, pero Python lo deja pasar")
    print("Se agrego igual:", p._lados)
    print("Solo mypy --strict hubiese detectado este error ANTES de correr.")
