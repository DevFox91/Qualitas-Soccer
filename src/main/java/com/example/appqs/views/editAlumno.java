package com.example.appqs.views;

import java.util.Date;

import com.example.appqs.actions.formAlumnos;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@StyleSheet("/com/example/appqs/CSS/styles.css")
public class editAlumno extends VerticalLayout {
    private formAlumnos formAlumnos;

    public editAlumno(int id, String nombre, String apellido1, String apellido2,
            Date fechaNacimiento, String direccion, int codigoPostal,
            String alergias, String colegio, String equipoAnterior) {
        // Crear un título para la vista de edición
        Label titleLabel = new Label("MENÚ DE EDICIÓN DE ALUMNOS");
        titleLabel.setStyleName("title-label");
        addComponent(titleLabel);

        // Crear una instancia de formAlumnos con los datos proporcionados
        formAlumnos = new formAlumnos(id, nombre, apellido1, apellido2, fechaNacimiento,
                direccion, codigoPostal, alergias, colegio, equipoAnterior);

        // Agregar formAlumnos al diseño vertical
        addComponent(formAlumnos);

    }

    public formAlumnos getFormAlumnos() {
        System.out.println(formAlumnos);
        return formAlumnos;
    }
}
