package citasmedicas;

public class Administrador {
    private String usuario = "admin";
    private String password = "password123";

    public boolean autenticar(String usr, String pass) {
        return this.usuario.equals(usr) && this.password.equals(pass);
    }
}