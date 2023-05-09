package Modelo.Personal;

import Modelo.Equipos.EquiposEntity;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "CONTRATOS_PERSONAL", schema = "EQDAW01", catalog = "")
public class ContratosPersonalEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private byte id;
    @Basic
    @Column(name = "FECHA_INICIO")
    private Date fechaInicio;
    @Basic
    @Column(name = "FECHA_FIN")
    private Date fechaFin;
    @Basic
    @Column(name = "SUELDO")
    private Integer sueldo;
    @ManyToOne
    @JoinColumn(name = "ID_PERSONAL", referencedColumnName = "ID")
    private PersonalesEntity personalesByIdPersonal;
    @ManyToOne
    @JoinColumn(name = "ID_EQUIPO", referencedColumnName = "ID")
    private EquiposEntity equiposByIdEquipo;

    public ContratosPersonalEntity() {

    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getSueldo() {
        return sueldo;
    }

    public void setSueldo(Integer sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContratosPersonalEntity that = (ContratosPersonalEntity) o;

        if (id != that.id) return false;
        if (fechaInicio != null ? !fechaInicio.equals(that.fechaInicio) : that.fechaInicio != null) return false;
        if (fechaFin != null ? !fechaFin.equals(that.fechaFin) : that.fechaFin != null) return false;
        if (sueldo != null ? !sueldo.equals(that.sueldo) : that.sueldo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (fechaInicio != null ? fechaInicio.hashCode() : 0);
        result = 31 * result + (fechaFin != null ? fechaFin.hashCode() : 0);
        result = 31 * result + (sueldo != null ? sueldo.hashCode() : 0);
        return result;
    }

    public PersonalesEntity getPersonalesByIdPersonal() {
        return personalesByIdPersonal;
    }

    public void setPersonalesByIdPersonal(PersonalesEntity personalesByIdPersonal) {
        this.personalesByIdPersonal = personalesByIdPersonal;
    }

    public EquiposEntity getEquiposByIdEquipo() {
        return equiposByIdEquipo;
    }

    public void setEquiposByIdEquipo(EquiposEntity equiposByIdEquipo) {
        this.equiposByIdEquipo = equiposByIdEquipo;
    }
}
