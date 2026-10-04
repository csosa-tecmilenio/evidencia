package citasmedicas;
import java.io.File;
import java.io.IOException;

public class ValidadorArchivos {
    public static void verificarArchivos() {
        File dir = new File("db");
        if (!dir.exists()) dir.mkdirs();

        String[] archivos = {"doctores.csv", "pacientes.csv", "citas.csv"};
        for (String arch : archivos) {
            File f = new File(dir, arch);
            if (!f.exists()) {
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    System.out.println("Error creando archivo: " + e.getMessage());
                }
            }
        }
    }
}