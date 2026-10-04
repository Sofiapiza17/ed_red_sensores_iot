import java.util.Arrays;

public class Ordenador {

    public static long[] burbuja(LecturaSensor[] arreglo) {
        long[] contadores = {0, 0};
        int tope = arreglo.length - 1;
        boolean continuar;
        
        do {
            continuar = false;
            for (int i = 0; i < tope; i++) {
                contadores[0]++;
                if (arreglo[i].getTimestamp().compareTo(arreglo[i + 1].getTimestamp()) > 0) {
                    LecturaSensor temporal = arreglo[i];
                    arreglo[i] = arreglo[i + 1];
                    arreglo[i + 1] = temporal;
                    contadores[1]++;
                    continuar = true;
                }
            }
            tope--;
        } while (continuar);
        
        return contadores;
    }

    public static long[] seleccion(LecturaSensor[] elementos) {
        long comparaciones = 0, intercambios = 0;
        int maxIndex = elementos.length;
        
        for (int i = 0; i < maxIndex - 1; i++) {
            int indiceMenor = i;
            for (int k = i + 1; k < maxIndex; k++) {
                comparaciones++;
                if (elementos[k].getTimestamp().compareTo(elementos[indiceMenor].getTimestamp()) < 0) {
                    indiceMenor = k;
                }
            }
            if (indiceMenor != i) {
                LecturaSensor respaldo = elementos[i];
                elementos[i] = elementos[indiceMenor];
                elementos[indiceMenor] = respaldo;
                intercambios++;
            }
        }
        return new long[]{comparaciones, intercambios};
    }

    public static long[] insercion(LecturaSensor[] datos) {
        long[] metricas = {0, 0};
        for (int p = 1; p < datos.length; p++) {
            LecturaSensor valorActual = datos[p];
            int idx = p - 1;
            
            while (idx >= 0) {
                metricas[0]++;
                if (datos[idx].getTimestamp().compareTo(valorActual.getTimestamp()) > 0) {
                    datos[idx + 1] = datos[idx];
                    metricas[1]++;
                    idx--;
                } else {
                    break;
                }
            }
            datos[idx + 1] = valorActual;
        }
        return metricas;
    }

    public static long[] mergeSort(LecturaSensor[] arr) {
        long[] stats = {0, 0};
        if (arr.length < 2) return stats;
        
        int mitad = arr.length / 2;
        LecturaSensor[] mitadIzquierda = new LecturaSensor[mitad];
        LecturaSensor[] mitadDerecha = new LecturaSensor[arr.length - mitad];
        
        System.arraycopy(arr, 0, mitadIzquierda, 0, mitad);
        System.arraycopy(arr, mitad, mitadDerecha, 0, arr.length - mitad);
        
        long[] statIzq = mergeSort(mitadIzquierda);
        long[] statDer = mergeSort(mitadDerecha);
        
        long[] statMerge = fusionar(arr, mitadIzquierda, mitadDerecha);
        
        stats[0] = statIzq[0] + statDer[0] + statMerge[0];
        stats[1] = statIzq[1] + statDer[1] + statMerge[1];
        return stats;
    }

    private static long[] fusionar(LecturaSensor[] principal, LecturaSensor[] izq, LecturaSensor[] der) {
        long comp = 0, mov = 0;
        int a = 0, b = 0, c = 0;
        
        while (a < izq.length && b < der.length) {
            comp++;
            if (izq[a].getTimestamp().compareTo(der[b].getTimestamp()) <= 0) {
                principal[c++] = izq[a++];
            } else {
                principal[c++] = der[b++];
            }
            mov++;
        }
        while (a < izq.length) { principal[c++] = izq[a++]; mov++; }
        while (b < der.length) { principal[c++] = der[b++]; mov++; }
        
        return new long[]{comp, mov};
    }

    public static long[] heapSort(LecturaSensor[] conjunto) {
        long[] res = {0, 0};
        int tam = conjunto.length;
        
        for (int i = tam / 2 - 1; i >= 0; i--) {
            ajustarMonticulo(conjunto, tam, i, res);
        }
        
        for (int i = tam - 1; i > 0; i--) {
            LecturaSensor t = conjunto[0];
            conjunto[0] = conjunto[i];
            conjunto[i] = t;
            res[1]++;
            ajustarMonticulo(conjunto, i, 0, res);
        }
        return res;
    }

    private static void ajustarMonticulo(LecturaSensor[] arr, int n, int raiz, long[] rep) {
        int mayor = raiz;
        int ramaIzq = 2 * raiz + 1;
        int ramaDer = 2 * raiz + 2;
        
        if (ramaIzq < n) {
            rep[0]++;
            if (arr[ramaIzq].getTimestamp().compareTo(arr[mayor].getTimestamp()) > 0) {
                mayor = ramaIzq;
            }
        }
        if (ramaDer < n) {
            rep[0]++;
            if (arr[ramaDer].getTimestamp().compareTo(arr[mayor].getTimestamp()) > 0) {
                mayor = ramaDer;
            }
        }
        if (mayor != raiz) {
            LecturaSensor aux = arr[raiz];
            arr[raiz] = arr[mayor];
            arr[mayor] = aux;
            rep[1]++;
            ajustarMonticulo(arr, n, mayor, rep);
        }
    }

    public static long[] quickSort(LecturaSensor[] arreglo, int inicio, int fin) {
        long[] totals = {0, 0};
        if (inicio < fin) {
            Object[] part = particionar(arreglo, inicio, fin);
            int indicePivote = (int) part[0];
            totals[0] += (long) part[1];
            totals[1] += (long) part[2];
            
            long[] resI = quickSort(arreglo, inicio, indicePivote - 1);
            long[] resD = quickSort(arreglo, indicePivote + 1, fin);
            
            totals[0] += resI[0] + resD[0];
            totals[1] += resI[1] + resD[1];
        }
        return totals;
    }

    private static Object[] particionar(LecturaSensor[] arr, int low, int high) {
        long cs = 0, is = 0;
        int med = low + (high - low) / 2;
        
        cs++; if (arr[med].getTimestamp().compareTo(arr[low].getTimestamp()) < 0) { swap(arr, low, med); is++; }
        cs++; if (arr[high].getTimestamp().compareTo(arr[low].getTimestamp()) < 0) { swap(arr, low, high); is++; }
        cs++; if (arr[high].getTimestamp().compareTo(arr[med].getTimestamp()) < 0) { swap(arr, med, high); is++; }
        
        swap(arr, med, high); is++;
        String valorPivote = arr[high].getTimestamp();
        int puntero = low - 1;
        
        for (int j = low; j < high; j++) {
            cs++;
            if (arr[j].getTimestamp().compareTo(valorPivote) <= 0) {
                puntero++;
                swap(arr, puntero, j);
                is++;
            }
        }
        swap(arr, puntero + 1, high);
        is++;
        
        return new Object[]{puntero + 1, cs, is};
    }

    private static void swap(LecturaSensor[] a, int x, int y) {
        LecturaSensor tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }

    public static void ordenarPorPm25(LecturaSensor[] data) {
        Arrays.sort(data, (s1, s2) -> Double.compare(s1.getPm25(), s2.getPm25()));
    }
}