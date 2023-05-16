package Modelo.Personal;

import Modelo.BaseDeDatos.ResultSetObjeto;
import Modelo.Enumeraciones.TipoPersonal;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Personal implements ResultSetObjeto {
    private int id;
    private String nombre;
    private String apellidos;
    private String dni;
    private int telefono;
    private TipoPersonal oficio;
    private String img;

    public Personal() {
    }

    public Personal(int id, TipoPersonal oficio) {
        this.id = id;
        this.oficio = oficio;
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

    public TipoPersonal getOficio() {
        return oficio;
    }

    public void setOficio(TipoPersonal oficio) {
        this.oficio = oficio;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void resultSetObjeto(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt("id");
        nombre = resultSet.getString("nombre");
        apellidos = resultSet.getString("apellidos");
        dni = resultSet.getString("dni");
        telefono = resultSet.getInt("telefono");
        oficio = TipoPersonal.valueOf(resultSet.getString("oficio"));
        img = resultSet.getString("img");
    }
}
