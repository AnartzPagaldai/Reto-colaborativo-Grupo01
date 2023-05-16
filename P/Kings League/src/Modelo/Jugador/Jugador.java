package Modelo.Jugador;

import Modelo.BaseDeDatos.ResultSetObjeto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Jugador implements ResultSetObjeto {
    private int id;
    private String nombre;
    private String apellidos;
    private String dni;
    private int telefono;
    private TipoPosicion tipoPosicion;



    public enum TipoPosicion{
        PORTERO,
        DEFENSA,
        MEDIO,
        DELANTERO
    }
    private String img;
    private TipoJugador tipoJugador;
    private enum TipoJugador{
        Draft,
        WildCard,
    }
    private int velocidad;
    private int fisico;
    private int tiro;
    private int pase;
    private int talento;
    private int defensa;

    public Jugador() {
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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public TipoPosicion getTipoPosicion() {
        return tipoPosicion;
    }

    public void setTipoPosicion(TipoPosicion tipoPosicion) {
        this.tipoPosicion = tipoPosicion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public TipoJugador getTipoJugador() {
        return tipoJugador;
    }

    public void setTipoJugador(TipoJugador tipoJugador) {
        this.tipoJugador = tipoJugador;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getFisico() {
        return fisico;
    }

    public void setFisico(int fisico) {
        this.fisico = fisico;
    }

    public int getTiro() {
        return tiro;
    }

    public void setTiro(int tiro) {
        this.tiro = tiro;
    }

    public int getPase() {
        return pase;
    }

    public void setPase(int pase) {
        this.pase = pase;
    }

    public int getTalento() {
        return talento;
    }

    public void setTalento(int talento) {
        this.talento = talento;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public void resultSetObjeto(ResultSet resultSet) throws SQLException {
        try {
            id = resultSet.getInt("id_jugador");
            nombre = resultSet.getString("nombre_jugador");
        } catch (Exception e) {
            id = resultSet.getInt("id");
            nombre = resultSet.getString("nombre");
        }
        img = resultSet.getString("img");
        apellidos = resultSet.getString("apellidos");
        tipoPosicion = Jugador.TipoPosicion.valueOf(resultSet.getString("posicion"));
        velocidad = resultSet.getInt("velocidad");
        fisico = resultSet.getInt("fisico");
        tiro = resultSet.getInt("tiro");
        pase = resultSet.getInt("pase");
        talento = resultSet.getInt("talento");
        defensa = resultSet.getInt("defensa");

    }

}
