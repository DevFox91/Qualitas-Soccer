package com.example.appqs.actions;

import java.sql.Date;
import java.time.LocalDate;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;

public class obtenerAlumno {
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

    public Object[] obtenerAlumnos(TextField nombreField, TextField apellido1Field,
            TextField apellido2Field, DateField fechaNacimientoField,
            TextField direccionField, TextField codigoPostalField,
            TextField alergiasField, TextField colegioField,
            TextField equipoAnteriorField) {
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
    String alergias = alergiasField.isEmpty() ? "" : alergiasField.getValue();
    String colegio = colegioField.isEmpty() ? "" : colegioField.getValue();
    String equipoAnterior = equipoAnteriorField.isEmpty() ? "" : equipoAnteriorField.getValue();

    return new Object[] {nombre, apellido1, apellido2, fechaNacimiento, direccion, codigoPostal, alergias, colegio, equipoAnterior};
}

}
