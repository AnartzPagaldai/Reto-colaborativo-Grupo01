package Modelo.Personal;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "PERSONALES", schema = "EQDAW01", catalog = "")
public class PersonalesEntity {
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
    @Column(name = "OFICIO")
    private String oficio;
    @Basic
    @Column(name = "IMG")
    private String img;
    @OneToMany(mappedBy = "personalesByIdPersonal")
    private Collection<ContratosPersonalEntity> contratosPersonalsById;

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

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonalesEntity that = (PersonalesEntity) o;

        if (id != that.id) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (apellidos != null ? !apellidos.equals(that.apellidos) : that.apellidos != null) return false;
        if (dni != null ? !dni.equals(that.dni) : that.dni != null) return false;
        if (telefono != null ? !telefono.equals(that.telefono) : that.telefono != null) return false;
        if (oficio != null ? !oficio.equals(that.oficio) : that.oficio != null) return false;
        if (img != null ? !img.equals(that.img) : that.img != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (dni != null ? dni.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (oficio != null ? oficio.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        return result;
    }

    public Collection<ContratosPersonalEntity> getContratosPersonalsById() {
        return contratosPersonalsById;
    }

    public void setContratosPersonalsById(Collection<ContratosPersonalEntity> contratosPersonalsById) {
        this.contratosPersonalsById = contratosPersonalsById;
    }
}
