package com.example.appqs.webConstructors;

import com.example.appqs.actions.obtenerAlumno;
import com.example.appqs.AppQsApplication;
import com.example.appqs.actions.limpiarFormulario;
import java.util.Date;
import com.example.appqs.dbconnections.formAlumnosToDb;
import com.example.appqs.views.Alumnos;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class pushEnviar {
    // Defino las variables necesarias para los métodos
    String apellido1;
    String apellido2;
    Date fechaNacimiento;
    String direccion;
    int codigoPostal;
    String alergias;
    String colegioString;
    String equipoAnterior;
    String colegio;
    String nombre;
    int tipoPersona;

    public void pushButtonEnviar(TextField nombreField, TextField apellido1Field,
            TextField apellido2Field, DateField fechaNacimientoField,
            TextField direccionField, TextField codigoPostalField,
            TextField alergiasField, TextField colegioField,
            TextField equipoAnteriorField) {
        // Crear una instancia de obtenerAlumno para poder llamar a su método
        // obtenerAlumnos()
        obtenerAlumno obtenerAlumnoInstance = new obtenerAlumno();

        // Llamar al método obtenerAlumnos() a través de la instancia creada
        Object[] datosAlumno = obtenerAlumnoInstance.obtenerAlumnos(nombreField, apellido1Field, apellido2Field,
                fechaNacimientoField,
                direccionField, codigoPostalField, alergiasField, colegioField,
                equipoAnteriorField);
        String nombre = (String) datosAlumno[0];
        String apellido1 = (String) datosAlumno[1];
        String apellido2 = (String) datosAlumno[2];
        Date fechaNacimiento = (Date) datosAlumno[3];
        String direccion = (String) datosAlumno[4];
        int codigoPostal = (int) datosAlumno[5];
        String alergias = (String) datosAlumno[6];
        String colegio = (String) datosAlumno[7];
        String equipoAnterior = (String) datosAlumno[8];

        // Insertar los datos en la base de datos
        formAlumnosToDb.insertPersonalData(nombre, apellido1, apellido2, fechaNacimiento,
                direccion, codigoPostal, alergias, colegio, tipoPersona, equipoAnterior);

        // Limpiar los campos del formulario después de enviar los datos
        limpiarFormulario.limpiarFormAlumno(nombreField, apellido1Field, apellido2Field,
                fechaNacimientoField, direccionField, codigoPostalField,
                alergiasField, colegioField, equipoAnteriorField);

        // Obtener el UI actual y cambiar la vista del content panel a "Alumnos"
        UI currentUI = UI.getCurrent();
        if (currentUI instanceof AppQsApplication.MainUI) {
            ((AppQsApplication.MainUI) currentUI).showView(new Alumnos());
        }

        // Cerrar el popup si está abierto
        popUpManager.closePopupIfOpen();
    }
}
