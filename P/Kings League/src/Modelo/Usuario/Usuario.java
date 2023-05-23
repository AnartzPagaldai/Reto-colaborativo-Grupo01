package Modelo.Usuario;

/**
 * Generar la clase Usuario.
 */
public class Usuario {
    private int id;
    private String nombre;
    private String contrasena;
    private String correo;
    private TipoUsuario tipoUsuario;
    public enum TipoUsuario{
        USUARIO,
        ADMIN
    }

    /**
     * Generar un objeto Usuario vacío.
     */
    public Usuario() {
    }

    /**
     * Generar un objeto Usuario con tres parámetros.
     *
     * @param nombre String
     * @param contrasena String
     * @param correo String
     */
    public Usuario(String nombre, String contrasena, String correo) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
