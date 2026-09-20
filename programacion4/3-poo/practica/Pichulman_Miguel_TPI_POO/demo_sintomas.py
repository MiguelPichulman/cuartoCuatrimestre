class LadoDemo:
    def __init__(self, longitud):
        self._longitud = longitud


class PoligonoConDefecto:
    catalogo = []

    def __init__(self, lados=[]):
        self._lados = lados
        PoligonoConDefecto.catalogo.append(self)

    def agregar_lado(self, lado):
        self._lados.append(lado)


def demostrar_default_mutable() -> None:
    print("=== SINTOMA 1: Default mutable compartido ===")
    p1 = PoligonoConDefecto()
    p2 = PoligonoConDefecto()
    
    mismo_objeto = (p1._lados is p2._lados)
    print(f"p1._lados y p2._lados comparten el mismo objeto en memoria: {mismo_objeto}")
    
    if mismo_objeto:
        print("-> Evidencia: Al ser el mismo objeto, agregar un elemento a p1 afectara a p2.")


def demostrar_atributo_clase_mutable() -> None:
    print("\n=== SINTOMA 2: Atributo de clase mutable accidental (catalogo) ===")
    tamano_inicial = len(PoligonoConDefecto.catalogo)
    print(f"Elementos en PoligonoConDefecto.catalogo antes de instanciar: {tamano_inicial}")
    
    _ = PoligonoConDefecto()
    _ = PoligonoConDefecto()
    
    tamano_final = len(PoligonoConDefecto.catalogo)
    print(f"Elementos en PoligonoConDefecto.catalogo despues de instanciar dos objetos: {tamano_final}")
    print("-> Evidencia: La lista global acumula instancias a nivel de clase.")


if __name__ == "__main__":
    demostrar_default_mutable()
    demostrar_atributo_clase_mutable()