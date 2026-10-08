# Backend

Java + Spring Boot

Se completa a partir de la etapa de implementación (esta entrega es solo de análisis y diseño,
por eso no incluye `pom.xml` ni código todavía). La organización prevista, según
`docs/arquitectura-proyecto.md`, es un paquete por módulo funcional, cada uno con su propio
controller, service y repository:

```
src/main/java/.../
├── auth/           # Autenticación y roles (Spring Security)
├── guardia/        # Guardia / Admisión
├── internacion/    # Internación
├── diagnostico/    # Diagnóstico
├── evolucion/      # Evolución (timeline)
├── medicacion/     # Indicación y administración de medicación
├── permiso/        # Permiso de salida
├── egreso/         # Egreso / Alta
└── auditoria/      # Auditoría transversal
```
