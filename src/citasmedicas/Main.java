package citasmedicas;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- ALTA DE ACTORES ---");

        // Se crean las instancias
        Doctor doctor = new Doctor("DOC-01", "Dr. Simi", "Cardiologia");
        Paciente paciente = new Paciente("PAC-01", "Juan Perez", "Alergia al paracetamol");

        // Se validan que los métodos y la herencia funcionan
        System.out.println("Doctor instanciado exitosamente:");
        System.out.println("ID: " + doctor.getId() + " | Nombre: " + doctor.getNombre() + " | Especialidad: " + doctor.getEspecialidad());

        System.out.println("\nPaciente instanciado exitosamente:");
        System.out.println("ID: " + paciente.getId() + " | Nombre: " + paciente.getNombre() + " | Historial: " + paciente.getHistorial());
    }
}