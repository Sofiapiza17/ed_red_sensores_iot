# Bitácora Grupal — Semana 3: Búsqueda y Eficiencia

**Integrantes:** Sofia Piza

## Respuestas de pensamiento crítico

1. **Millón de registros, 5 búsquedas al día:** No tiene sentido diseñar la estrategia basándose en búsqueda binaria en este caso. El altísimo costo computacional de mantener un millón de registros estrictamente ordenados tras cada inserción supera por mucho el beneficio de optimizar solo 5 búsquedas.
2. **Corrección vs Eficiencia:** La corrección es prioridad absoluta. Un algoritmo extremadamente rápido que devuelve respuestas incorrectas o falsos negativos destruye la confiabilidad de la plataforma. De nada sirve la velocidad si el dato es mentira.
3. **Organizar por un solo campo:** Si organizamos todo asumiendo consultas por `timestamp`, estas serán muy rápidas (O(log n)), pero penalizaremos gravemente las búsquedas ocasionales por `PM2.5`, las cuales tendrán que hacer recorridos secuenciales (O(n)) obligatoriamente.
4. **Modificar registros rompiendo el orden:** Si se pierde el orden y se usa búsqueda binaria, el algoritmo descartará mitades del arreglo donde el dato realmente podría estar. Esto generará "falsos negativos" (el sistema dirá que el dato no existe, aunque sí esté guardado).
5. **Que funcione no significa buena solución:** En la Semana 1 vimos que procesar a ciegas falla con datos corruptos. En la Semana 2 vimos que guardar datos funciona, pero elegir mal la estructura agota la memoria. En la Semana 3 vimos que un algoritmo rápido falla si no se cumplen sus precondiciones. Una buena solución en ingeniería requiere que sea correcta, mantenible, escalable y con un costo justificado.