public class AnalizadorMatriz {
    private Double[][] pm25;

    public AnalizadorMatriz() {
        pm25 = new Double[9][24]; // 9 estaciones, 24 horas
    }

    public void registrar(int estacion, int hora, double valorPm25) {
        if (estacion >= 0 && estacion < 9 && hora >= 0 && hora < 24) {
            pm25[estacion][hora] = valorPm25;
        }
    }

    public double promedioDeHora(int hora) {
        double suma = 0;
        int cantidadReportes = 0;
        for (int estacion = 0; estacion < 9; estacion++) {
            if (pm25[estacion][hora] != null) {
                suma += pm25[estacion][hora];
                cantidadReportes++;
            }
        }
        if (cantidadReportes == 0) return 0;
        return suma / cantidadReportes;
    }

    public double promedioDeEstacion(int estacion) {
        double suma = 0;
        int cantidadReportes = 0;
        for (int hora = 0; hora < 24; hora++) {
            if (pm25[estacion][hora] != null) {
                suma += pm25[estacion][hora];
                cantidadReportes++;
            }
        }
        if (cantidadReportes == 0) return 0;
        return suma / cantidadReportes;
    }

    public int horaMasContaminada() {
        int mejorHora = -1;
        double mayorPromedio = -1;
        for (int hora = 0; hora < 24; hora++) {
            double promedio = promedioDeHora(hora);
            if (promedio > mayorPromedio) {
                mayorPromedio = promedio;
                mejorHora = hora;
            }
        }
        return mejorHora;
    }
}