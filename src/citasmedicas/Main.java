package citasmedicas;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        ValidadorArchivos.verificarArchivos();
        Scanner sc = new Scanner(System.in);
        Administrador admin = new Administrador();
        GestorCitas gestor = new GestorCitas();

        System.out.println("--- INICIO DE SESION ---");

        int intentos = 0;
        boolean autenticado = false;

        while (intentos < 3 && !autenticado) {
            System.out.print("Usuario: ");
            String usr = sc.nextLine();
            System.out.print("Contrasena: ");
            String pass = sc.nextLine();

            if (admin.autenticar(usr, pass)) {
                autenticado = true;
                System.out.println("Acceso concedido.");
            } else {
                intentos++;
                System.out.println("Credenciales incorrectas. Intentos restantes: " + (3 - intentos));
            }
        }

        if (!autenticado) {
            System.out.println("Numero maximo de intentos alcanzado. Cerrando programa...");
            sc.close();
            return;
        }

        int opcion = 0;

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        while (opcion != 5) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Dar de alta Doctor");
            System.out.println("2. Dar de alta Paciente");
            System.out.println("3. Crear Cita");
            System.out.println("4. Mostrar Citas Actuales"); // <-- Nombre de la opción actualizado
            System.out.println("5. Guardar y Salir");
            System.out.print("Opcion: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        System.out.print("ID: "); String idD = sc.nextLine();
                        System.out.print("Nombre: "); String nomD = sc.nextLine();
                        System.out.print("Especialidad: "); String espD = sc.nextLine();
                        gestor.registrarDoctor(new Doctor(idD, nomD, espD));
                        System.out.println("Doctor registrado.");
                        break;
                    case 2:
                        System.out.print("ID: "); String idP = sc.nextLine();
                        System.out.print("Nombre: "); String nomP = sc.nextLine();
                        System.out.print("Historial: "); String hist = sc.nextLine();
                        gestor.registrarPaciente(new Paciente(idP, nomP, hist));
                        System.out.println("Paciente registrado.");
                        break;
                    case 3:
                        System.out.print("ID Cita: "); String idC = sc.nextLine();
                        System.out.print("Fecha y Hora (yyyy-MM-dd HH:mm): "); String fecha = sc.nextLine();
                        System.out.print("Motivo: "); String mot = sc.nextLine();
                        System.out.print("ID Doctor: "); String doc = sc.nextLine();
                        System.out.print("ID Paciente: "); String pac = sc.nextLine();
                        gestor.crearCita(idC, fecha, mot, doc, pac);
                        System.out.println("Cita creada y relacionada exitosamente.");
                        break;
                    case 4:
                        System.out.println("\n--- CITAS ACTUALES REGISTRADAS ---");
                        if (gestor.citas.isEmpty()) {
                            System.out.println("No hay citas registradas en el sistema en este momento.");
                        } else {
                            for (Cita c : gestor.citas.values()) {
                                System.out.println("ID Cita  : " + c.getId());
                                System.out.println("Fecha    : " + c.getFechaHora().format(formatoFecha));
                                System.out.println("Motivo   : " + c.getMotivo());
                                System.out.println("Doctor   : " + c.getDoctor().getNombre() + " (" + c.getDoctor().getEspecialidad() + ")");
                                System.out.println("Paciente : " + c.getPaciente().getNombre());
                                System.out.println("----------------------------------");
                            }
                        }
                        break;
                    case 5:
                        gestor.guardar();
                        System.out.println("Datos guardados en CSV. Adios.");
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + ". El programa continuara ejecutandose.");
            }
        }
        sc.close();
    }
}