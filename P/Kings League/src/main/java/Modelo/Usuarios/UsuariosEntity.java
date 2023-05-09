package Modelo.Usuarios;

import jakarta.persistence.*;

@Entity
@Table(name = "USUARIOS", schema = "EQDAW01", catalog = "")
public class UsuariosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private byte id;
    @Basic
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic
    @Column(name = "CONTRASE�A")
    private String contraseA;
    @Basic
    @Column(name = "CORREO")
    private String correo;
    @Basic
    @Column(name = "TIPO")
    private String tipo;

    public UsuariosEntity() {

    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseA() {
        return contraseA;
    }

    public void setContraseA(String contraseA) {
        this.contraseA = contraseA;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuariosEntity that = (UsuariosEntity) o;

        if (id != that.id) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (contraseA != null ? !contraseA.equals(that.contraseA) : that.contraseA != null) return false;
        if (correo != null ? !correo.equals(that.correo) : that.correo != null) return false;
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (contraseA != null ? contraseA.hashCode() : 0);
        result = 31 * result + (correo != null ? correo.hashCode() : 0);
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        return result;
    }
}
