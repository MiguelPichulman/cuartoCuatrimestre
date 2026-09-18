"""
ACTIVIDAD 3 - El punto y coma aplastado: como NO escribir Python

En Python, la INDENTACION es la sintaxis -reemplaza a las llaves {} de
Java. El ";" es legal pero esta proscripto por convencion (PEP 8 lo dice
explicitamente). Comprimir el codigo en una linea no solo afea el
estilo: esconde la estructura que hacia visible el diseno.
"""

from typing import Optional


class RolDocente:
    pass


class RolInvestigador:
    pass


class RolAdmin:
    pass


# INCORRECTO - Java sin llaves, no es Python idiomatico:
#
# class Empleado: def __init__(self): self._rol_docente = None;
# self._rol_investigador = None; self._rol_admin = None


# CORRECTO:
class Empleado:
    def __init__(self) -> None:
        self._rol_docente: Optional[RolDocente] = None
        self._rol_investigador: Optional[RolInvestigador] = None
        self._rol_admin: Optional[RolAdmin] = None

    def como_docente(self) -> RolDocente:
        if self._rol_docente is None:
            self._rol_docente = RolDocente()
        return self._rol_docente


if __name__ == "__main__":
    e = Empleado()
    print(e.como_docente())
