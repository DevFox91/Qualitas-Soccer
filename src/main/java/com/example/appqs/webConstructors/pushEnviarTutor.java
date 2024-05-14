package com.example.appqs.webConstructors;

import com.example.appqs.actions.obtenerTutor;
import com.example.appqs.actions.limpiarFormulario;
import java.util.Date;
import com.example.appqs.dbconnections.formTutoresToDb;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;

public class pushEnviarTutor {
    // Defino las variables necesarias para los métodos
    String apellido1;
    String apellido2;
    Date fechaNacimiento;
    String direccion;
    int codigoPostal;
    String alergias;
    String nif;
    String profesion;
    String iban;
    String nombre;
    int tipoPersona;

    public void pushButtonEnviar(TextField nombreField, TextField apellido1Field,
    TextField apellido2Field, DateField fechaNacimientoField,
    TextField direccionField, TextField codigoPostalField,
    TextField nifField, TextField profesionField,
    TextField ibanField) {
        // Crear una instancia de obtenerTutor para poder llamar a su método obtenerTutores()
        obtenerTutor obtenerAlumnoInstance = new obtenerTutor();
    
        // Llamar al método obtenerTutores() a través de la instancia creada
        Object[] datosAlumno = obtenerAlumnoInstance.obtenerTutores(nombreField, apellido1Field, apellido2Field, fechaNacimientoField,
                                          direccionField, codigoPostalField, nifField, profesionField,
                                          ibanField);
        String nombre = (String) datosAlumno[0];
        String apellido1 = (String) datosAlumno[1];
        String apellido2 = (String) datosAlumno[2];
        Date fechaNacimiento = (Date) datosAlumno[3];
        String direccion = (String) datosAlumno[4];
        int codigoPostal = (int) datosAlumno[5];
        String nif = (String) datosAlumno[6];
        String profesion = (String) datosAlumno[7];
        String iban = (String) datosAlumno[8];
        
        // Insertar los datos en la base de datos
        formTutoresToDb.insertPersonalData(nombre, apellido1, apellido2, fechaNacimiento,
                    direccion, codigoPostal, nif, profesion, tipoPersona, iban);
    
        // Limpiar los campos del formulario después de enviar los datos
        limpiarFormulario.limpiarFormAlumno(nombreField, apellido1Field, apellido2Field,
                fechaNacimientoField, direccionField, codigoPostalField,
                nifField, profesionField, ibanField);
    }
}
