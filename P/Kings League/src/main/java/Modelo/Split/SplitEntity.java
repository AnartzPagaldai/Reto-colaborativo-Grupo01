package Modelo.Split;

import Modelo.Jornadas.JornadasEntity;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "SPLIT", schema = "EQDAW01", catalog = "")
public class SplitEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private byte id;
    @Basic
    @Column(name = "ANIO")
    private Date anio;
    @Basic
    @Column(name = "TIPO")
    private String tipo;
    @Basic
    @Column(name = "ESTADO")
    private String estado;
    @OneToMany(mappedBy = "splitByIdSplit")
    private Collection<JornadasEntity> jornadasById;

    public SplitEntity() {

    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public Date getAnio() {
        return anio;
    }

    public void setAnio(Date anio) {
        this.anio = anio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SplitEntity that = (SplitEntity) o;

        if (id != that.id) return false;
        if (anio != null ? !anio.equals(that.anio) : that.anio != null) return false;
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) return false;
        if (estado != null ? !estado.equals(that.estado) : that.estado != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (anio != null ? anio.hashCode() : 0);
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        return result;
    }

    public Collection<JornadasEntity> getJornadasById() {
        return jornadasById;
    }

    public void setJornadasById(Collection<JornadasEntity> jornadasById) {
        this.jornadasById = jornadasById;
    }
}
