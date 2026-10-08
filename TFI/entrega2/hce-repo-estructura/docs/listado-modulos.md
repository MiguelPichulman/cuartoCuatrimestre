# Listado de Módulos — HCE Pabellón de Salud Mental


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