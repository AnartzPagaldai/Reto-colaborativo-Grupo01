package Modelo.Equipos;

import Modelo.Jugadores.ContratosJugadoresEntity;
import Modelo.Partidos.PartidosEntity;
import Modelo.Personal.ContratosPersonalEntity;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "EQUIPOS", schema = "EQDAW01", catalog = "")
public class EquiposEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private byte id;
    @Basic
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic
    @Column(name = "PRESUPUESTO_ANUAL")
    private Integer presupuestoAnual;
    @OneToMany(mappedBy = "equiposByIdEquipo")
    private Collection<ContratosJugadoresEntity> contratosJugadoresById;
    @OneToMany(mappedBy = "equiposByIdEquipo")
    private Collection<ContratosPersonalEntity> contratosPersonalsById;
    @OneToMany(mappedBy = "equiposByIdEquipo1")
    private Collection<PartidosEntity> partidosById;
    @OneToMany(mappedBy = "equiposByIdEquipo2")
    private Collection<PartidosEntity> partidosById_0;

    public EquiposEntity() {
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

    public Integer getPresupuestoAnual() {
        return presupuestoAnual;
    }

    public void setPresupuestoAnual(Integer presupuestoAnual) {
        this.presupuestoAnual = presupuestoAnual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EquiposEntity that = (EquiposEntity) o;

        if (id != that.id) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (presupuestoAnual != null ? !presupuestoAnual.equals(that.presupuestoAnual) : that.presupuestoAnual != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (presupuestoAnual != null ? presupuestoAnual.hashCode() : 0);
        return result;
    }

    public Collection<ContratosJugadoresEntity> getContratosJugadoresById() {
        return contratosJugadoresById;
    }

    public void setContratosJugadoresById(Collection<ContratosJugadoresEntity> contratosJugadoresById) {
        this.contratosJugadoresById = contratosJugadoresById;
    }

    public Collection<ContratosPersonalEntity> getContratosPersonalsById() {
        return contratosPersonalsById;
    }

    public void setContratosPersonalsById(Collection<ContratosPersonalEntity> contratosPersonalsById) {
        this.contratosPersonalsById = contratosPersonalsById;
    }

    public Collection<PartidosEntity> getPartidosById() {
        return partidosById;
    }

    public void setPartidosById(Collection<PartidosEntity> partidosById) {
        this.partidosById = partidosById;
    }

    public Collection<PartidosEntity> getPartidosById_0() {
        return partidosById_0;
    }

    public void setPartidosById_0(Collection<PartidosEntity> partidosById_0) {
        this.partidosById_0 = partidosById_0;
    }
    public void contarJugadores(){

    }
    public void ficharJugador(){

    }
    public void venderJugador(){

    }
}
