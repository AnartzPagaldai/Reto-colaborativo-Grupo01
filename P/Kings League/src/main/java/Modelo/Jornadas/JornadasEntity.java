package Modelo.Jornadas;

import Modelo.Partidos.PartidosEntity;
import Modelo.Split.SplitEntity;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "JORNADAS", schema = "EQDAW01", catalog = "")
public class JornadasEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private byte id;
    @Basic
    @Column(name = "NUM_JORNADA")
    private Byte numJornada;
    @Basic
    @Column(name = "TIPO")
    private String tipo;
    @Basic
    @Column(name = "ID_SPLIT")
    private Byte idSplit;
    @ManyToOne
    @JoinColumn(name = "ID_SPLIT", referencedColumnName = "ID")
    private SplitEntity splitByIdSplit;
    @OneToMany(mappedBy = "jornadasByIdJornada")
    private Collection<PartidosEntity> partidosById;

    public JornadasEntity() {
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public Byte getNumJornada() {
        return numJornada;
    }

    public void setNumJornada(Byte numJornada) {
        this.numJornada = numJornada;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Byte getIdSplit() {
        return idSplit;
    }

    public void setIdSplit(Byte idSplit) {
        this.idSplit = idSplit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JornadasEntity that = (JornadasEntity) o;

        if (id != that.id) return false;
        if (numJornada != null ? !numJornada.equals(that.numJornada) : that.numJornada != null) return false;
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) return false;
        if (idSplit != null ? !idSplit.equals(that.idSplit) : that.idSplit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (numJornada != null ? numJornada.hashCode() : 0);
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (idSplit != null ? idSplit.hashCode() : 0);
        return result;
    }

    public SplitEntity getSplitByIdSplit() {
        return splitByIdSplit;
    }

    public void setSplitByIdSplit(SplitEntity splitByIdSplit) {
        this.splitByIdSplit = splitByIdSplit;
    }

    public Collection<PartidosEntity> getPartidosById() {
        return partidosById;
    }
    public void generarJornada(){

    }
    public void setPartidosById(Collection<PartidosEntity> partidosById) {
        this.partidosById = partidosById;
    }
}
