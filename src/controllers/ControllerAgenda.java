/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.ModelAgenda;
import views.ViewAgenda;

/**
 *
 * @author Salvador Hernandez Mendoza //Clonacion y trabajo sobre el
 */
public class ControllerAgenda {

    public ModelAgenda modelAgenda;
    public ViewAgenda viewAgenda;

    /**
     * Objeto de tipo ActionListener para atrapar los eventos actionPerformed y
     * llamar a los metodos para ver los registros almacenados en la bd.
     */
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewAgenda.jbtn_primero) {
                jbtn_primero_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_anterior) {
                jbtn_anterior_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_siguiente) {
                jbtn_siguiente_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_ultimo) {
                jbtn_ultimo_actionPerformed();
            }
            /////////////////////////////////////////////////////////
            else if (e.getSource() == viewAgenda.jb_nuevo) {
                jb_nuevo_actionPerformed();
            }else if (e.getSource() == viewAgenda.jb_editar) {
                jb_editar_actionPerformed();
            }else if (e.getSource() == viewAgenda.jb_guardar) {
                jb_guardar_actionPerformed();
            }else if (e.getSource() == viewAgenda.jb_eliminar) {
                jb_eliminar_actionPerformed();
            }

        }

    };

    /**
     * Constructor de Controlador para unir el ModelAgenda y ViewAgenda
     *
     * @param modelAgenda objeto de tipo ModelAgenda
     * @param viewAgenda objeto de tipo ViewAgenda
     */
    public ControllerAgenda(ModelAgenda modelAgenda, ViewAgenda viewAgenda) {
        this.modelAgenda = modelAgenda;
        this.viewAgenda = viewAgenda;
        setActionListener();
        initDB();
    }

    /**
     * Método que llama al método conectarBD del modelo y muestra el nombre y
     * email del primer registro en las cajas de texto de ViewAgenda.
     */
    private void initDB() {
        modelAgenda.conectarDB();
        viewAgenda.jtf_id_contacto.setText(String.valueOf(modelAgenda.getId_contacto()));
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        viewAgenda.jtf_telefono.setText(modelAgenda.getTelefono());
        habilitarCajas(false);
        habilitarDesplazamiento(true);
        //## No es visible, pero se utilizara para el crud ##//
        viewAgenda.jtf_id_contacto.setVisible(false);
        viewAgenda.jtf_id_contacto.setEnabled(false);
        
        viewAgenda.jb_eliminar.setEnabled(true);
        viewAgenda.jb_nuevo.setEnabled(true);
        viewAgenda.jb_editar.setEnabled(true);
        viewAgenda.jb_guardar.setEnabled(false);
        
    }
    private void habilitarCajas(boolean descicion){
        viewAgenda.jtf_nombre.setEditable(descicion);
        viewAgenda.jtf_email.setEditable(descicion);
        viewAgenda.jtf_telefono.setEditable(descicion);
    }
    
    private void habilitarDesplazamiento(boolean descicion){
        viewAgenda.jbtn_primero.setEnabled(descicion);
        viewAgenda.jbtn_anterior.setEnabled(descicion);
        viewAgenda.jbtn_siguiente.setEnabled(descicion);
        viewAgenda.jbtn_ultimo.setEnabled(descicion);
    }

//    /**
//     * Metodo para inicializar la ViewAgenda
//     */
    /*
    public void initComponents() {
        viewAgenda.setLocationRelativeTo(null);
        viewAgenda.setTitle("Agenda MVC");
        viewAgenda.setVisible(true);
    }*/

    /**
     * Método para agregar el actionListener a cada boton de la vista
     */
    private void setActionListener() {
        viewAgenda.jbtn_primero.addActionListener(actionListener);
        viewAgenda.jbtn_anterior.addActionListener(actionListener);
        viewAgenda.jbtn_siguiente.addActionListener(actionListener);
        viewAgenda.jbtn_ultimo.addActionListener(actionListener);
        
        viewAgenda.jb_nuevo.addActionListener(actionListener);
        viewAgenda.jb_editar.addActionListener(actionListener);
        viewAgenda.jb_guardar.addActionListener(actionListener);
        viewAgenda.jb_eliminar.addActionListener(actionListener);
    }

    /**
     * Método para ver el primer registro de la tabla contactos
     */
    private void jbtn_primero_actionPerformed() {
        System.out.println("Action del boton jbtn_primero");
        modelAgenda.moverPrimerRegistro();
        setValues();
    }

    /**
     * Método para ver el registro anterior de la tabla contactos.
     */
    private void jbtn_anterior_actionPerformed() {
        System.out.println("Action del boton jbtn_anterior");
        modelAgenda.moverAnteriorRegistro();
        setValues();
    }

    /**
     * Método para ver el último registro de la tabla contactos.
     */
    private void jbtn_ultimo_actionPerformed() {
        System.out.println("Action del boton jbtn_ultimo");
        modelAgenda.moverUltimoRegistro();
        setValues();
    }

    /**
     * Método para ver el siguiente registro de la tabla contactos.
     */
    private void jbtn_siguiente_actionPerformed() {
        System.out.println("Action del boton jbtn_siguiente");
        modelAgenda.moverSiguienteRegistro();
        setValues();
    }

    /**
     * Muestra el nombre, email, telefono e identificador id_contacto almacenados en el modelAgenda en el viewAgenda.
     */
    private void setValues() {
        viewAgenda.jtf_id_contacto.setText(String.valueOf(modelAgenda.getId_contacto()));
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        viewAgenda.jtf_telefono.setText(modelAgenda.getTelefono());
    }
    
    /**
     * Método que habilita la edicion de las cajas de texto
     * Deshabilita los botones de dezplazamiento
     * A la variable Descicion se le asigna el valor nuevo para saber que se va a crear un nuevo registro
     */
    private void jb_nuevo_actionPerformed(){
        System.err.println("Action del boton nuevo");
        habilitarCajas(true);
        habilitarDesplazamiento(false);
        modelAgenda.setDescicion("nuevo");
        //modelAgenda.nuevoRegistro();
        viewAgenda.jtf_nombre.setText("");
        viewAgenda.jtf_email.setText("");
        viewAgenda.jtf_telefono.setText("");
        viewAgenda.jb_eliminar.setEnabled(false);
        viewAgenda.jb_nuevo.setEnabled(false);
        viewAgenda.jb_editar.setEnabled(false);
        viewAgenda.jb_guardar.setEnabled(true);
        
    }
    
    /**
     * Método que realiza el guardado de datos sean nuevos o actualizaciones
     * Llama al metodo guardarRegistro del modelo como parametro lo que contienen sus cajas de texto
     * Hibilitar y deshabilitar botones de CRUD
     */
    public void jb_guardar_actionPerformed() {
        System.err.println("Action del boton jb_insertar");
        modelAgenda.guardarRegistro(viewAgenda.jtf_nombre.getText(), viewAgenda.jtf_email.getText(), viewAgenda.jtf_telefono.getText(), Integer.parseInt(viewAgenda.jtf_id_contacto.getText()));
        habilitarDesplazamiento(true);
        habilitarCajas(false);
        viewAgenda.jb_eliminar.setEnabled(true);
        viewAgenda.jb_nuevo.setEnabled(true);
        viewAgenda.jb_editar.setEnabled(true);
        viewAgenda.jb_guardar.setEnabled(false);
    }
    
    /**
     * Método que elimina un dato de la BD
     * Llama al metodo borrarRegistro del modelo como parametro el id del dato que queremos borrar
     * Llama al metodo jbtn_ultimo_actionPerformed para indicar al usuario el cambio y actualizacion de datos
     */
    private void jb_eliminar_actionPerformed(){
        System.err.println("Action del boton jb_eliminar");
        modelAgenda.borrarRegistro(Integer.parseInt(viewAgenda.jtf_id_contacto.getText()));
        jbtn_ultimo_actionPerformed();
    }
    
    /**
     * Método para realizar actualizaciones de datos
     * A la variable Descicion se le asigna el valor editar para saber que se esta actualizando el dato
     * Habilitar cajas de texto para su edicion 
     * Deshabilita los botones de dezplazamiento
     * Habilita y deshabilita botones del CRUD
     */
    public void jb_editar_actionPerformed() {
        System.err.println("Action del boton jb_modificar");
        modelAgenda.setDescicion("editar");
        habilitarCajas(true);
        habilitarDesplazamiento(true);
        viewAgenda.jb_eliminar.setEnabled(false);
        viewAgenda.jb_nuevo.setEnabled(false);
        viewAgenda.jb_editar.setEnabled(false);
        viewAgenda.jb_guardar.setEnabled(true);
     }
}