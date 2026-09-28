import java.io.BufferedReader;
import java.io.FileReader;

public class IngestaSensores {
    // Constantes de validación (Semana 1)
    private static final double TEMP_MIN = -40.0, TEMP_MAX = 60.0;
    private static final double HUM_MIN = 0.0, HUM_MAX = 100.0;
    private static final double PM25_MIN = 0.0;

    public static void main(String[] args) {
        RepositorioLecturas repositorio = new RepositorioLecturas();
        AnalizadorMatriz analizador = new AnalizadorMatriz();

        cargarArchivo(repositorio, analizador);

        System.out.println("=== INGESTA ===");
        System.out.println("Lecturas almacenadas: " + repositorio.tamano());
        System.out.println("Hora más contaminada: " + analizador.horaMasContaminada() + ":00");

        System.out.println("\n=== EXPERIMENTOS SEMANA 3 ===");
        BancoDePruebas.experimentoUno();
        BancoDePruebas.experimentoDos();
        BancoDePruebas.experimentoTres();
        BancoDePruebas.experimentoCuatro();
    }

    public static void cargarArchivo(RepositorioLecturas repo, AnalizadorMatriz matriz) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/lecturas_ampliadas.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length != 5) continue; // Descarta número incorrecto de campos

                try {
                    String id = campos[0];
                    String timestamp = campos[1];
                    double temp = Double.parseDouble(campos[2]);
                    double hum = Double.parseDouble(campos[3]);
                    double pm25 = Double.parseDouble(campos[4]);

                    // Validación de dominio (Semana 1)
                    if (temp >= TEMP_MIN && temp <= TEMP_MAX &&
                        hum >= HUM_MIN && hum <= HUM_MAX &&
                        pm25 >= PM25_MIN) {
                        
                        // Agregar al arreglo (Semana 2)
                        repo.agregar(new LecturaSensor(id, timestamp, temp, hum, pm25));

                        // Agregar a la matriz (Semana 2)
                        // Extrae índice estación (ej: "EST-001" -> 0)
                        int indexEstacion = Integer.parseInt(id.split("-")[1]) - 1;
                        // Extrae hora (ej: "2026-09-07 08:00" -> 8)
                        int hora = Integer.parseInt(timestamp.substring(11, 13));
                        
                        matriz.registrar(indexEstacion, hora, pm25);
                    }
                } catch (NumberFormatException e) {
                    // Falla de parseo capturada en silencio para no detener el sistema (Semana 1)
                }
            }
        } catch (Exception e) {
            System.out.println("Error leyendo el archivo. Asegúrate de que 'lecturas_ampliadas.csv' esté en la carpeta 'data/'.");
        }
    }
}