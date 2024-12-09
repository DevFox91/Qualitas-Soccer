package com.example.appqs.actions;

import com.vaadin.ui.TextField;
import com.vaadin.ui.DateField;


public class limpiarFormulario {

    public static void limpiarFormAlumno(TextField nombreField, TextField apellido1Field,
            TextField apellido2Field, DateField fechaNacimientoField,
            TextField direccionField, TextField codigoPostalField,
            TextField alergiasField, TextField colegioField,
            TextField equipoAnteriorField) {
        nombreField.clear();
        apellido1Field.clear();
        apellido2Field.clear();
        fechaNacimientoField.clear();
        direccionField.clear();
        codigoPostalField.clear();
        alergiasField.clear();
        colegioField.clear();
        equipoAnteriorField.clear();
    }
}
