# Bitácora Grupal — Semana 1: Ingesta Confiable

**Integrantes:** Sofia Piza

## Decisiones y Resultados
En esta primera etapa nos enfocamos en que el programa no colapse ante datos corruptos. 
- Implementamos validaciones de dominio (ej. la humedad no puede ser > 100%).
- Usamos `try/catch` para capturar errores de formato numérico.
- Separamos responsabilidades en métodos cortos para evitar un método `main` monolítico.
- Cualquier sensor que reporte basura ahora es detectado, se informa el motivo del rechazo, y el programa continúa procesando las demás lecturas sin detenerse.