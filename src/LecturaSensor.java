public class LecturaSensor {
    private String idEstacion;
    private String timestamp;
    private double temperatura;
    private double humedad;
    private double pm25;

    public LecturaSensor(String idEstacion, String timestamp, double temperatura, double humedad, double pm25) {
        this.idEstacion = idEstacion;
        this.timestamp = timestamp;
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.pm25 = pm25;
    }

    public String getIdEstacion() { return idEstacion; }
    public String getTimestamp() { return timestamp; }
    public double getTemperatura() { return temperatura; }
    public double getHumedad() { return humedad; }
    public double getPm25() { return pm25; }
}