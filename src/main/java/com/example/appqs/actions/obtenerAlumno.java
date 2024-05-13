package com.example.appqs.actions;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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

    public void obtenerAlumnos(TextField nombreField, TextField apellido1Field,
        TextField apellido2Field, DateField fechaNacimientoField,
        TextField direccionField, TextField codigoPostalField,
        TextField alergiasField, TextField colegioField,
        TextField equipoAnteriorField) {
    this.nombre = nombreField.isEmpty() ? "" : nombreField.getValue();
    this.apellido1 = apellido1Field.isEmpty() ? "" : apellido1Field.getValue();
    this.apellido2 = apellido2Field.isEmpty() ? "" : apellido2Field.getValue();
    
    // Obtener la fecha de nacimiento, establecer una fecha predeterminada si está vacía
    if (fechaNacimientoField.isEmpty()) {
        this.fechaNacimiento = Date.from(LocalDate.of(1991, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
    } else {
        this.fechaNacimiento = Date.from(fechaNacimientoField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    this.direccion = direccionField.isEmpty() ? "" : direccionField.getValue();
    this.codigoPostal = codigoPostalField.isEmpty() ? 0 : Integer.parseInt(codigoPostalField.getValue());
    this.alergias = alergiasField.isEmpty() ? "" : alergiasField.getValue();
    this.colegio = colegioField.isEmpty() ? "" : colegioField.getValue();
    this.equipoAnterior = equipoAnteriorField.isEmpty() ? "" : equipoAnteriorField.getValue();
}

}
