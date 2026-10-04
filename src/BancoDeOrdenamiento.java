import java.util.Arrays;

public class BancoDeOrdenamiento {

    @FunctionalInterface
    interface TareaOrdenamiento {
        long[] ejecutar();
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("  BANCO DE PRUEBAS H1 - ORDENAMIENTO DE LECTURAS  ");
        System.out.println("==================================================\n");

        ejecutarTestBasico();
        ejecutarTestDatosPrevios();
        ejecutarAnalisisEscalabilidad();
        ejecutarTestQuickSort();
        ejecutarTestEfectosSecundarios();
    }

    private static void ejecutarTestBasico() {
        System.out.println("[ TEST 1 ] -> Algoritmos Cuadráticos (10,000 registros aleatorios)");
        LecturaSensor[] setA = GeneradorDatos.generar(10000);
        LecturaSensor[] setB = Arrays.copyOf(setA, 10000);
        LecturaSensor[] setC = Arrays.copyOf(setA, 10000);

        medirYMostrar("Burbuja Optimizada", () -> Ordenador.burbuja(setA));
        medirYMostrar("Selección", () -> Ordenador.seleccion(setB));
        medirYMostrar("Inserción", () -> Ordenador.insercion(setC));
        System.out.println();
    }

    private static void ejecutarTestDatosPrevios() {
        System.out.println("[ TEST 2 ] -> Algoritmos Cuadráticos (Datos pre-ordenados)");
        LecturaSensor[] base = GeneradorDatos.generar(10000);
        Ordenador.quickSort(base, 0, base.length - 1);
        
        LecturaSensor[] c1 = Arrays.copyOf(base, 10000);
        LecturaSensor[] c2 = Arrays.copyOf(base, 10000);
        LecturaSensor[] c3 = Arrays.copyOf(base, 10000);

        medirYMostrar("Burbuja Optimizada", () -> Ordenador.burbuja(c1));
        medirYMostrar("Selección", () -> Ordenador.seleccion(c2));
        medirYMostrar("Inserción", () -> Ordenador.insercion(c3));
        System.out.println();
    }

    private static void ejecutarAnalisisEscalabilidad() {
        System.out.println("[ TEST 3 ] -> Comparativa de Complejidad (N=1.000, 10.000, 100.000)");
        int[] escalas = {1000, 10000, 100000};
        
        for (int n : escalas) {
            System.out.printf("--- Evaluando N = %d ---%n", n);
            LecturaSensor[] m1 = GeneradorDatos.generar(n);
            LecturaSensor[] m2 = Arrays.copyOf(m1, n);
            LecturaSensor[] m3 = Arrays.copyOf(m1, n);

            medirYMostrar("Inserción", () -> Ordenador.insercion(m1));
            medirYMostrar("MergeSort", () -> Ordenador.mergeSort(m2));
            medirYMostrar("HeapSort", () -> Ordenador.heapSort(m3));
        }
        System.out.println();
    }

    private static void ejecutarTestQuickSort() {
        System.out.println("[ TEST 4 ] -> Rendimiento de QuickSort con Mediana de 3");
        LecturaSensor[] datosAleatorios = GeneradorDatos.generar(50000);
        System.out.println(">> Escenario A: 50.000 lecturas desordenadas");
        medirYMostrar("QuickSort", () -> Ordenador.quickSort(datosAleatorios, 0, datosAleatorios.length - 1));

        LecturaSensor[] datosCronologicos = Arrays.copyOf(datosAleatorios, datosAleatorios.length);
        System.out.println(">> Escenario B: 50.000 lecturas ordenadas cronológicamente");
        try {
            medirYMostrar("QuickSort", () -> Ordenador.quickSort(datosCronologicos, 0, datosCronologicos.length - 1));
        } catch (StackOverflowError e) {
            System.out.println("   -> FALLA: Se detectó StackOverflowError.");
        }
        System.out.println();
    }

    private static void ejecutarTestEfectosSecundarios() {
        System.out.println("[ TEST 5 ] -> Impacto de variables secundarias sobre Búsqueda Binaria");
        LecturaSensor[] repositorio = GeneradorDatos.generar(100000);
        Ordenador.quickSort(repositorio, 0, repositorio.length - 1);
        
        int targetIndex = Math.min(73412, repositorio.length - 1);
        String targetTime = repositorio[targetIndex].getTimestamp();
        
        System.out.println("1. Búsqueda con arreglo original (Ordenado por Timestamp):");
        int posicionA = busquedaBinariaInterna(repositorio, targetTime);
        System.out.printf("   Resultado: Índice %d%n%n", posicionA);
        
        System.out.println("2. Reestructurando repositorio por concentración PM2.5...");
        Ordenador.ordenarPorPm25(repositorio);
        
        System.out.println("3. Búsqueda con arreglo modificado:");
        int posicionB = busquedaBinariaInterna(repositorio, targetTime);
        System.out.printf("   Resultado: Índice %d (Ruptura de precondición)%n", posicionB);
    }

    private static int busquedaBinariaInterna(LecturaSensor[] arr, String objetivo) {
        int izq = 0;
        int der = arr.length - 1;
        while (izq <= der) {
            int med = izq + (der - izq) / 2;
            int cmp = arr[med].getTimestamp().compareTo(objetivo);
            if (cmp == 0) {
                return med;
            }
            if (cmp < 0) {
                izq = med + 1;
            } else {
                der = med - 1;
            }
        }
        return -1;
    }

    private static void medirYMostrar(String nombreAlgoritmo, TareaOrdenamiento tarea) {
        long inicio = System.currentTimeMillis();
        long[] resultados = tarea.ejecutar();
        long fin = System.currentTimeMillis();
        
        long delta = fin - inicio;
        System.out.printf(" * %-18s -> Comparaciones: %-12d | Movimientos: %-10d | Tiempo: %d ms%n", 
                          nombreAlgoritmo, resultados[0], resultados[1], delta);
    }
}
