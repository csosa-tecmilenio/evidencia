package citasmedicas;

public class Paciente extends Persona {
    private String historialMedico;

    public Paciente(String id, String nombreCompleto, String historialMedico) {
        super(id, nombreCompleto);
        this.historialMedico = historialMedico;
    }
    public String getHistorial() { return historialMedico; }
}