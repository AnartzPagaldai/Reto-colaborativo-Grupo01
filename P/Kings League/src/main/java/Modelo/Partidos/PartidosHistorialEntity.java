package Modelo.Partidos;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "PARTIDOS_HISTORIAL", schema = "EQDAW01", catalog = "")
public class PartidosHistorialEntity {
    @Basic
    @Column(name = "GOLES_EQUIPO1")
    private Byte golesEquipo1;
    @Basic
    @Column(name = "GOLES_EQUIPO2")
    private Byte golesEquipo2;
    @Basic
    @Column(name = "FECHA")
    private Date fecha;
    @Basic
    @Column(name = "LUGAR")
    private String lugar;
    @Basic
    @Column(name = "ID_JORNADA")
    private Byte idJornada;
    @Basic
    @Column(name = "ID_EQUIPO1")
    private Byte idEquipo1;
    @Basic
    @Column(name = "ID_EQUIPO2")
    private Byte idEquipo2;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private short id;

    public Byte getGolesEquipo1() {
        return golesEquipo1;
    }

    public void setGolesEquipo1(Byte golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    public Byte getGolesEquipo2() {
        return golesEquipo2;
    }

    public void setGolesEquipo2(Byte golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Byte getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Byte idJornada) {
        this.idJornada = idJornada;
    }

    public Byte getIdEquipo1() {
        return idEquipo1;
    }

    public void setIdEquipo1(Byte idEquipo1) {
        this.idEquipo1 = idEquipo1;
    }

    public Byte getIdEquipo2() {
        return idEquipo2;
    }

    public void setIdEquipo2(Byte idEquipo2) {
        this.idEquipo2 = idEquipo2;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartidosHistorialEntity that = (PartidosHistorialEntity) o;

        if (id != that.id) return false;
        if (golesEquipo1 != null ? !golesEquipo1.equals(that.golesEquipo1) : that.golesEquipo1 != null) return false;
        if (golesEquipo2 != null ? !golesEquipo2.equals(that.golesEquipo2) : that.golesEquipo2 != null) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;
        if (lugar != null ? !lugar.equals(that.lugar) : that.lugar != null) return false;
        if (idJornada != null ? !idJornada.equals(that.idJornada) : that.idJornada != null) return false;
        if (idEquipo1 != null ? !idEquipo1.equals(that.idEquipo1) : that.idEquipo1 != null) return false;
        if (idEquipo2 != null ? !idEquipo2.equals(that.idEquipo2) : that.idEquipo2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = golesEquipo1 != null ? golesEquipo1.hashCode() : 0;
        result = 31 * result + (golesEquipo2 != null ? golesEquipo2.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        result = 31 * result + (lugar != null ? lugar.hashCode() : 0);
        result = 31 * result + (idJornada != null ? idJornada.hashCode() : 0);
        result = 31 * result + (idEquipo1 != null ? idEquipo1.hashCode() : 0);
        result = 31 * result + (idEquipo2 != null ? idEquipo2.hashCode() : 0);
        result = 31 * result + (int) id;
        return result;
    }
}
