package Modelo.Split;

import Modelo.BaseDeDatos.ResultSetObjeto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Split implements ResultSetObjeto {
    private int id;
    private Date anio;
    private TipoSplit tipoSplit;
    private EstadoSplit estadoSplit;



    private enum TipoSplit{
        INVIERNO,
        VERANO
    }
    private enum EstadoSplit{
        ABIERTO,
        CERRADO
    }

    public Split() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getAnio() {
        return anio;
    }

    public void setAnio(Date anio) {
        this.anio = anio;
    }

    public TipoSplit getTipoSplit() {
        return tipoSplit;
    }

    public void setTipoSplit(TipoSplit tipoSplit) {
        this.tipoSplit = tipoSplit;
    }

    public EstadoSplit getEstadoSplit() {
        return estadoSplit;
    }

    public void setEstadoSplit(EstadoSplit estadoSplit) {
        this.estadoSplit = estadoSplit;
    }
    public void resultSetObjeto(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt("id");
        anio = resultSet.getDate("anio");
        tipoSplit = TipoSplit.valueOf(resultSet.getString("tipo"));
        estadoSplit = EstadoSplit.valueOf(resultSet.getString("estado"));
    }
}
