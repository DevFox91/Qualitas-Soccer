package com.example.appqs.actions;

import java.sql.Date;
import java.time.LocalDate;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;

public class obtenerTutor {
    // Defino las variables necesarias para los métodos
    String apellido1;
    String apellido2;
    Date fechaNacimiento;
    String direccion;
    int codigoPostal;
    String nombre;
    int tipoPersona;

    public Object[] obtenerTutores(TextField nombreField, TextField apellido1Field,
            TextField apellido2Field, DateField fechaNacimientoField,
            TextField direccionField, TextField codigoPostalField,
            TextField nifField, TextField profesionField,
            TextField ibanField) {
        String nombre = nombreField.isEmpty() ? "" : nombreField.getValue();
        String apellido1 = apellido1Field.isEmpty() ? "" : apellido1Field.getValue();
        String apellido2 = apellido2Field.isEmpty() ? "" : apellido2Field.getValue();
        Date fechaNacimiento;
        if (!fechaNacimientoField.isEmpty()) {
            LocalDate fechaNacimientoLocalDate = fechaNacimientoField.getValue();
            fechaNacimiento = Date.valueOf(fechaNacimientoLocalDate);
        } else {
            fechaNacimiento = Date.valueOf(LocalDate.of(1900, 1, 1));
        }
        String direccion = direccionField.isEmpty() ? "" : direccionField.getValue();
        int codigoPostal = codigoPostalField.isEmpty() ? 0 : Integer.parseInt(codigoPostalField.getValue());
        String nif = nifField.isEmpty() ? "" : nifField.getValue();
        String profesion = profesionField.isEmpty() ? "" : profesionField.getValue();
        String iban = ibanField.isEmpty() ? "" : ibanField.getValue();

        return new Object[] { nombre, apellido1, apellido2, fechaNacimiento, direccion, codigoPostal, nif, profesion,
                iban };
    }

}
