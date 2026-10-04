package citasmedicas;
import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GestorCitas implements Persistente {
    public Map<String, Doctor> doctores = new HashMap<>();
    public Map<String, Paciente> pacientes = new HashMap<>();
    public Map<String, Cita> citas = new HashMap<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void registrarDoctor(Doctor doc) { doctores.put(doc.getId(), doc); }
    public void registrarPaciente(Paciente pac) { pacientes.put(pac.getId(), pac); }

    public void crearCita(String idCita, String fecha, String motivo, String idDoc, String idPac) throws Exception {
        if (!doctores.containsKey(idDoc)) throw new Exception("Doctor no encontrado.");
        if (!pacientes.containsKey(idPac)) throw new Exception("Paciente no encontrado.");

        LocalDateTime dateTime = LocalDateTime.parse(fecha, formatter);
        Cita nuevaCita = new Cita(idCita, dateTime, motivo, doctores.get(idDoc), pacientes.get(idPac));
        citas.put(idCita, nuevaCita);
    }

    @Override
    public void guardar() {
        try {

            BufferedWriter bwD = new BufferedWriter(new FileWriter("db/doctores.csv"));
            for (Doctor d : doctores.values()) { bwD.write(d.getId() + "," + d.getNombre() + "," + d.getEspecialidad() + "\n"); }
            bwD.close();

            BufferedWriter bwP = new BufferedWriter(new FileWriter("db/pacientes.csv"));
            for (Paciente p : pacientes.values()) { bwP.write(p.getId() + "," + p.getNombre() + "," + p.getHistorial() + "\n"); }
            bwP.close();

            BufferedWriter bwC = new BufferedWriter(new FileWriter("db/citas.csv"));
            for (Cita c : citas.values()) {
                String fechaFormateada = c.getFechaHora().format(formatter);

                bwC.write(c.getId() + "," + fechaFormateada + "," + c.getMotivo() + "," +
                        c.getDoctor().getId() + "," + c.getPaciente().getId() + "\n");
            }
            bwC.close();
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    @Override
    public void cargar() {

    }
}