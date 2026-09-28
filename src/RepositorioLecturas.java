public class RepositorioLecturas {
    private LecturaSensor[] lecturas;
    private int cantidad;

    public RepositorioLecturas() {
        this.lecturas = new LecturaSensor[10]; // Capacidad inicial
        this.cantidad = 0;
    }

    public boolean agregar(LecturaSensor lectura) {
        if (cantidad >= lecturas.length) {
            redimensionar();
        }
        lecturas[cantidad] = lectura;
        cantidad++;
        return true;
    }

    private void redimensionar() {
        // Estrategia: duplicar capacidad
        LecturaSensor[] nuevo = new LecturaSensor[lecturas.length * 2];
        for (int i = 0; i < cantidad; i++) {
            nuevo[i] = lecturas[i];
        }
        lecturas = nuevo;
    }

    public LecturaSensor obtener(int posicion) {
        if (posicion < 0 || posicion >= cantidad) return null;
        return lecturas[posicion];
    }

    public LecturaSensor buscarPorEstacion(String idEstacion) {
        for (int i = 0; i < cantidad; i++) {
            if (lecturas[i].getIdEstacion().equals(idEstacion)) {
                return lecturas[i];
            }
        }
        return null;
    }

    public boolean actualizar(int posicion, LecturaSensor nueva) {
        if (posicion < 0 || posicion >= cantidad) return false;
        lecturas[posicion] = nueva;
        return true;
    }

    public boolean eliminar(int posicion) {
        if (posicion < 0 || posicion >= cantidad) return false;
        // Estrategia: Compactar
        for (int i = posicion; i < cantidad - 1; i++) {
            lecturas[i] = lecturas[i + 1];
        }
        lecturas[cantidad - 1] = null; // Evitar duplicado en la última posición
        cantidad--;
        return true;
    }

    public int tamano() {
        return cantidad;
    }
}