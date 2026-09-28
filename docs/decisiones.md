# Decisiones de diseño — Semana 3

## Búsqueda binaria por timestamp
**Precondición:** La búsqueda binaria requiere que las lecturas estén ordenadas ascendentemente por timestamp.
**Condición actual del proyecto:** `GeneradorDatos` produce timestamps en orden cronológico.
**Decisión:** Utilizar búsqueda binaria para consultas por timestamp.
**Justificación:** Reduce el número de comparaciones de un crecimiento O(n) a O(log n).

## PM2.5
No se utilizará búsqueda binaria sobre PM2.5 mientras los datos no estén ordenados por este campo. El experimento demuestra experimentalmente que un algoritmo correcto aplicado sobre una precondición falsa arroja resultados incorrectos.