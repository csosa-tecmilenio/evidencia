package citasmedicas;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- CREAR CITAS Y PERSISTENCIA ---");

        ValidadorArchivos.verificarArchivos();

        GestorCitas gestor = new GestorCitas();

        // Se registran los actores
        System.out.println("\nRegistrando actores en el sistema...");
        gestor.registrarDoctor(new Doctor("DOC-01", "Dr. Simi", "Cardiologia"));
        gestor.registrarPaciente(new Paciente("PAC-01", "Juan Perez", "Ninguno"));
        System.out.println("Actores registrados en memoria.");

        // Creación de cita
        try {
            System.out.println("\nIntentando crear cita...");
            gestor.crearCita("CITA-01", "2026-10-15 10:30", "Chequeo de rutina", "DOC-01", "PAC-01");

            Cita citaCreada = gestor.citas.get("CITA-01");
            System.out.println("Cita creada para el paciente " + citaCreada.getPaciente().getNombre() +
                    " con el " + citaCreada.getDoctor().getNombre());
        } catch (Exception e) {
            System.out.println("Error al crear cita: " + e.getMessage());
        }

        // Guardado en CSV
        System.out.println("\nEscribiendo datos en archivos CSV...");
        gestor.guardar();
        System.out.println("Proceso finalizado. Revisa los archivos dentro de la carpeta db/");
    }
}