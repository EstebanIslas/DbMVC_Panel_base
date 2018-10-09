/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Salvador Hernandez Mendoza
 */
public class ModelAgenda {

    private Connection conexion;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;

    private int id_contacto;
    private String nombre;
    private String email;
    private String telefono;

    /////////////////////////////
    private String descicion = "";
    ////////////////////////////

    public int getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(int id_contacto) {
        this.id_contacto = id_contacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDescicion() {
        return descicion;
    }

    public void setDescicion(String descicion) {
        this.descicion = descicion;
    }

    /**
     * Método que realiza las siguietnes acciones: 1- Conecta con la base
     * agenda_mvc, 2- Consulta todo los registros de la tabla contactos, 3-
     * Obtiene el nombre y el email y los guarda en las variables globales
     * nombre y email.
     */
    public Connection conectarDB() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_mvc", "root", "");
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM contactos");
            rs.next();

            setValues(); //Atrae los valores obtenidos de la consulta y los guarda en las variables
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Model_Agenda 001: " + ex.getMessage());
        }
        return conexion;
    }

    /**
     * Lee los valores del registro seleccionado y los asigna a las variables
     * miembre nombre y email.
     */
    public void setValues() {
        try {
            id_contacto = rs.getInt("id_contacto");
            nombre = rs.getString("nombre");
            email = rs.getString("email");
            telefono = rs.getString("telefono");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error model 002: " + err.getMessage());

        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al primer registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverPrimerRegistro() {
        System.out.println("moverPrimerRegistro");
        try {
            if (rs.isFirst() == false) {
                rs.first();
                setValues();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Model 003: " + ex.getMessage());
        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al siguiente
     * registro 2.- obtener el valor del nombre de rs y guardarlo en la variable
     * nombre 3.- obtener el valor del email de rs y guardarlo en la variable
     * email
     */
    public void moverSiguienteRegistro() {
        System.out.println("moverSiguienteRegistro");
        try {
            if (rs.isLast() == false) {
                rs.next();
                setValues();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Model 004" + ex.getMessage());
        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al anterior
     * registro 2.- obtener el valor del nombre de rs y guardarlo en la variable
     * nombre 3.- obtener el valor del email de rs y guardarlo en la variable
     * email
     */
    public void moverAnteriorRegistro() {
        System.out.println("moverAnteriorRegistro");
        try {
            if (rs.isFirst() == false) {
                rs.previous();
                setValues();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Model 005" + ex.getMessage());
        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al ultimo registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la ariable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverUltimoRegistro() {
        System.out.println("moverUltimoRegistro");
        try {
            if (rs.isLast() == false) {
                rs.last();
                setValues();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Model 006" + ex.getMessage());
        }

    }

    public void consultaContactos() {
        try {
            conexion = conectarDB();
            ps = conexion.prepareStatement("Select * from contactos");
            rs = ps.executeQuery();
            rs.next();

            setValues();
            System.out.println("Consulta realizada!!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Consulta 007 " + ex.getMessage());
        }
    }

    ///**
    // * Método que limpia para un nuevo registro a la Base de Datos. 1. Limpia lo
    // * que contengan las variables nombre, email
    // */
    /*
    public void nuevoRegistro() {
        System.out.println("Limpia para Nuevo Registro");
        System.out.println(this.getNombre());
        System.out.println(this.getEmail());
    }*/
    
    /**
     * Método que guarda un nuevo o actualiza registro a la Base de Datos.
     * @param nombre Conocer el valor que tiene de la View de Agenda nombre
     * @param email Conocer el valor que tiene de la View de Agenda email
     * @param telefono Conocer el valor que tiene de la View de Agenda telefono
     * @param id_contacto Solo se utiliza para la actualizacion de informacion no se puede editar
     */
    public void guardarRegistro(String nombre, String email, String telefono, int id_contacto) {
        if (this.getDescicion() == "nuevo") {
            try {
                System.out.println("Insertar nuevo registro");
                Connection con = null;
                con = conectarDB();
                ps = con.prepareStatement("Insert into contactos (nombre, email, telefono) values (?,?,?)");
                ps.setString(1, nombre);
                ps.setString(2, email);
                ps.setString(3, telefono);
                int res = ps.executeUpdate();
                consultaContactos();
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "Registro guardado Exitosamente!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar registro");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Model 006" + ex.getMessage());
            }
        } else if (this.getDescicion() == "editar") {
            try {
                System.out.println("Actualizar registro");
                Connection con = null;
                con = conectarDB();
                //"UPDATE contactos SET nombre=?,email=? WHERE id_contactos=?");
                ps = con.prepareStatement("Update contactos set nombre=?, email=?, telefono=? Where id_contacto=?");
                ps.setString(1, nombre);
                ps.setString(2, email);
                ps.setString(3, telefono);
                ps.setInt(4, id_contacto);
                int res = ps.executeUpdate();
                consultaContactos();
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "Registro actualizado Exitosamente!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar registro");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Model 006" + ex.getMessage());
            }
        }
    }
    /**
     * Método que borra un registro de la base de datos
     * Contiene un cuadro de confirmacion para realizar la ejecucion. 
     * @param id_contacto Conocer cual es el dato que se quiere eliminar ubicandolo por identificador
     */
    public void borrarRegistro(int id_contacto) {
        int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro?", "Alerta!", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            try {
                System.out.println("Elimina un registro");
                Connection con = null;
                con = conectarDB();
                ps = con.prepareStatement("Delete from contactos where id_contacto=?");
                ps.setInt(1, id_contacto);
                int res = ps.executeUpdate();
                consultaContactos();
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado Exitosamente!!");

                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar registro");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Model 007" + ex.getMessage());
            }
        }else
            JOptionPane.showMessageDialog(null, "Accion Cancelada!!");
    }
}
