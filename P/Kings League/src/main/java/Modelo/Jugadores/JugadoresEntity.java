package Modelo.Jugadores;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "JUGADORES", schema = "EQDAW01", catalog = "")
public class JugadoresEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private byte id;
    @Basic
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Basic
    @Column(name = "DNI")
    private String dni;
    @Basic
    @Column(name = "TELEFONO")
    private String telefono;
    @Basic
    @Column(name = "POSICION")
    private String posicion;
    @Basic
    @Column(name = "IMG")
    private String img;
    @Basic
    @Column(name = "TIPO")
    private String tipo;
    @Basic
    @Column(name = "VELOCIDAD")
    private Byte velocidad;
    @Basic
    @Column(name = "FISICO")
    private Byte fisico;
    @Basic
    @Column(name = "TIRO")
    private Byte tiro;
    @Basic
    @Column(name = "PASE")
    private Byte pase;
    @Basic
    @Column(name = "TALENTO")
    private Byte talento;
    @Basic
    @Column(name = "DEFENSA")
    private Byte defensa;
    @OneToMany(mappedBy = "jugadoresByIdJugador")
    private Collection<ContratosJugadoresEntity> contratosJugadoresById;

    public JugadoresEntity() {

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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Byte getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Byte velocidad) {
        this.velocidad = velocidad;
    }

    public Byte getFisico() {
        return fisico;
    }

    public void setFisico(Byte fisico) {
        this.fisico = fisico;
    }

    public Byte getTiro() {
        return tiro;
    }

    public void setTiro(Byte tiro) {
        this.tiro = tiro;
    }

    public Byte getPase() {
        return pase;
    }

    public void setPase(Byte pase) {
        this.pase = pase;
    }

    public Byte getTalento() {
        return talento;
    }

    public void setTalento(Byte talento) {
        this.talento = talento;
    }

    public Byte getDefensa() {
        return defensa;
    }

    public void setDefensa(Byte defensa) {
        this.defensa = defensa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JugadoresEntity that = (JugadoresEntity) o;

        if (id != that.id) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (apellidos != null ? !apellidos.equals(that.apellidos) : that.apellidos != null) return false;
        if (dni != null ? !dni.equals(that.dni) : that.dni != null) return false;
        if (telefono != null ? !telefono.equals(that.telefono) : that.telefono != null) return false;
        if (posicion != null ? !posicion.equals(that.posicion) : that.posicion != null) return false;
        if (img != null ? !img.equals(that.img) : that.img != null) return false;
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) return false;
        if (velocidad != null ? !velocidad.equals(that.velocidad) : that.velocidad != null) return false;
        if (fisico != null ? !fisico.equals(that.fisico) : that.fisico != null) return false;
        if (tiro != null ? !tiro.equals(that.tiro) : that.tiro != null) return false;
        if (pase != null ? !pase.equals(that.pase) : that.pase != null) return false;
        if (talento != null ? !talento.equals(that.talento) : that.talento != null) return false;
        if (defensa != null ? !defensa.equals(that.defensa) : that.defensa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (dni != null ? dni.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (posicion != null ? posicion.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (velocidad != null ? velocidad.hashCode() : 0);
        result = 31 * result + (fisico != null ? fisico.hashCode() : 0);
        result = 31 * result + (tiro != null ? tiro.hashCode() : 0);
        result = 31 * result + (pase != null ? pase.hashCode() : 0);
        result = 31 * result + (talento != null ? talento.hashCode() : 0);
        result = 31 * result + (defensa != null ? defensa.hashCode() : 0);
        return result;
    }

    public Collection<ContratosJugadoresEntity> getContratosJugadoresById() {
        return contratosJugadoresById;
    }

    public void setContratosJugadoresById(Collection<ContratosJugadoresEntity> contratosJugadoresById) {
        this.contratosJugadoresById = contratosJugadoresById;
    }
}
