# Listado de Módulos — HCE Pabellón de Salud Mental

Organizado según los recorridos verticales sugeridos por Oscar (login→paciente→internación→
consulta / profesional→evolución→timeline→permisos / antecedentes→egreso→auditoría), en
vez del esquema por capas (BD, backend, frontend) que se venía usando.

## P0 — Imprescindibles para el MVP

| # | Módulo | Descripción breve | Sprint |
|---|---|---|---|
| 1 | **Autenticación y roles** | Login, gestión de usuarios/profesionales, control de acceso por rol (médico de guardia, profesional tratante, enfermería). Transversal a todo el sistema. | 1 |
| 2 | **Guardia / Admisión** | Registro de datos iniciales del paciente (alta si es la primera vez) y de la entrevista de guardia; decisión de internar o no. | 1 |
| 3 | **Internación** | Apertura de un episodio de internación al derivar a pabellón; asignación del profesional tratante; estado de la internación (en guardia / internado / de alta). | 1 |
| 4 | **Consulta de internación activa** | Ver el estado y los datos de una internación en curso. Es lo mínimo para cerrar el primer recorrido vertical. | 1 |
| 5 | **Diagnóstico** | Alta y actualización de diagnósticos durante la internación, con historial (nunca se pisa un diagnóstico anterior). | 2 |
| 6 | **Evolución (timeline)** | Registro de evoluciones médicas y de enfermería (comportamiento diario) sobre la línea de tiempo de la internación, con autor y fecha/hora. | 2 |
| 7 | **Indicación de medicación** | Alta, modificación y suspensión de indicaciones médicas, con historial versionado (sin DELETE). | 2 |
| 8 | **Administración de medicación** | Registro por parte de enfermería de que una indicación fue efectivamente suministrada (o no, con motivo). | 2 |
| 9 | **Permiso de salida transitorio** | Versión simple: solicitar, autorizar o denegar un permiso de salida. Sin flujos administrativos adicionales por ahora. | 2 |
| 10 | **Consulta histórica / antecedentes** | Ver, desde un paciente, todas sus internaciones anteriores y navegar a los datos de cada una. | 3 |
| 11 | **Egreso / Alta** | Cierre formal de la internación: fecha, profesional que da el alta, motivo, indicaciones post-alta. Prioridad explícita del tutor. | 3 |
| 12 | **Auditoría** | Bitácora de acciones relevantes (creación/modificación de diagnósticos, indicaciones, evoluciones, cambios de estado de internación) con autor y fecha, para complementar el versionado propio de cada entidad. | 3 |

## P1 — Candidatos a diferir

| Módulo | Por qué queda en P1 | Qué se pierde si no está en el MVP |
|---|---|---|
| **Consulta guardia (circuito de excepción)** | Es un caso de borde del flujo de medicación (enfermería↔médico de guardia cuando no hay médico en pabellón), no el camino principal. | Ese caso puntual quedaría sin registrar en el sistema (se seguiría resolviendo como hoy, informalmente) hasta P1. No afecta el circuito normal de indicación/administración de medicación, que sí está en P0. |
| **Reportes y estadísticas** (ausentismo, ocupación, indicadores) | No forma parte del recorrido clínico núcleo que pidió validar el tutor. | Ninguna funcionalidad clínica se ve afectada; solo se posterga la capa de análisis agregado. |
| **Asignación automática / balanceo de profesional tratante** | Hoy se asigna manualmente y no fue mencionado como problema en el relevamiento. | Se sigue asignando "a mano" desde el módulo de Internación (campo simple, no algoritmo). |
| **Notificaciones/alertas automáticas** | No mencionado en el relevamiento como una necesidad actual. | El personal sigue revisando la HC manualmente, como ya lo hace hoy (mencionado explícitamente en el relevamiento). |
| **Multi-pabellón / multi-institución** | El alcance relevado es un único pabellón. | Ninguna, el sistema no necesita generalizar a otras instituciones para este TPF. |

## Nota sobre el criterio usado

Para cada candidato a P1 apliqué la misma pregunta que planteó Oscar: *¿qué parte del
problema inicial dejaríamos de resolver si esta funcionalidad pasa a P1?* En los cinco casos la
respuesta es "nada del recorrido clínico núcleo (admisión → internación → evolución → egreso
→ consulta histórica)", que es justamente el criterio que pidió priorizar.

**Abierto a discusión:** dejé "Permiso de salida transitorio" en P0 porque Oscar lo incluyó en su
propio Sprint 2 sugerido, pero si el equipo evalúa que insume demasiado tiempo, es el
candidato más razonable para bajar a P1 sin romper el recorrido vertical (quedaría
"profesional→evolución→timeline", sin permisos, y se suma en una iteración posterior).
