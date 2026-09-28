# Bitácora Grupal — Semana 2: Almacenamiento y Matrices

**Integrantes:** Sofia Piza, 

## Respuestas a las preguntas de comprobación

1. **Definición de TAD:** Es un contrato que define qué acciones se pueden hacer con un grupo de datos y las reglas que deben cumplir, sin importar los detalles técnicos internos de cómo se guardan físicamente en la memoria.
2. **Crecimiento de arreglos:** Crecer de uno en uno requiere miles de copias excesivas, ya que el arreglo se recrea y copia en cada inserción. Duplicar la capacidad requirió drásticamente menos operaciones. Concluimos que duplicar es exponencialmente más eficiente para el procesamiento.
3. **Estrategia para eliminar:** Elegimos **compactar**. Justificación: Nos permite mantener los datos continuos. Aunque cueste mover elementos, nos evita la complejidad de validar "huecos" o posiciones nulas en cada recorrido futuro.
4. **El Cero Fantasma:** Lo resolvimos utilizando el objeto `Double` en lugar del tipo primitivo `double` en la matriz. Esto nos permite asignar `null` a las horas donde el sensor no reportó, diferenciando claramente una ausencia de dato de una lectura real de `0.0`.
5. **Búsqueda secuencial en 8,000 estaciones:** No seguiría siendo adecuada. En el peor de los casos, requeriría hacer 8,000 comparaciones por cada búsqueda (complejidad O(n)). Si el sistema escala, el costo computacional sería insostenible.