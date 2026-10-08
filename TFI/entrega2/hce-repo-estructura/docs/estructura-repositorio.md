# Estructura del Repositorio

## Árbol de carpetas

```
.
├── README.md
├── .gitignore
├── frontend/
│   └── README.md
├── backend/
│   └── README.md
└── database/
│   ├── modelo-datos-hce.md
│   └── schema.sql
└── docs/
    ├── listado-modulos.md
    ├── arquitectura-proyecto.md
    └── estructura-repositorio.md
```

## Qué corresponde subir en esta entrega (Segunda entrega — Diseño y módulos)

| Carpeta/archivo | Contenido | Estado |
|---|---|---|
| `database/modelo-datos-hce.md` | Diagrama entidad-relación + justificación de diseño | ✅ Listo |
| `database/schema.sql` | DDL del esquema (definición de tablas, no lógica de aplicación) | ✅ Listo |
| `docs/listado-modulos.md` | Módulos funcionales con prioridad P0/P1 | ✅ Listo |
| `docs/arquitectura-proyecto.md` | Arquitectura elegida y justificación técnica | ✅ Listo (falta completar tabla de conocimiento del equipo) |
| `frontend/README.md`, `backend/README.md` | Carpetas creadas con su organización prevista, sin código | ✅ Listo |
| `README.md` raíz | Descripción general y navegación a la documentación | ✅ Listo |

## Qué queda para la próxima etapa (no incluido a propósito)

La consigna es explícita: en esta instancia **no se sube código ni implementación** (ni
frontend, ni backend, ni lógica de negocio). Por eso, a propósito, **no** se incluyó:

- `pom.xml` ni la clase principal de Spring Boot (`Application.java`).
- Templates de Thymeleaf ni assets de Bootstrap.
- Cualquier controller, service o repository.

Esto se agrega recién cuando el tutor apruebe esta entrega y comience la etapa de
implementación, siguiendo la organización por módulo que ya está descripta en
`backend/README.md` y en `docs/arquitectura-proyecto.md`.
