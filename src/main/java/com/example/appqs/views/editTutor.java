package com.example.appqs.views;

import java.util.Date;

import com.example.appqs.actions.formTutores;
import com.vaadin.annotations.StyleSheet;

import com.vaadin.ui.Label;

import com.vaadin.ui.VerticalLayout;

@StyleSheet("/com/example/appqs/CSS/styles.css")
public class editTutor extends VerticalLayout {
    private formTutores formTutores;

    public editTutor(int id, String nombre, String apellido1, String apellido2,
            Date fechaNacimiento, String direccion, int codigoPostal,
            String nif, String profesion, String iban) {
        // Crear un título para la vista de edición
        Label titleLabel = new Label("MENÚ DE EDICIÓN DE TUTORES");
        titleLabel.setStyleName("title-label");
        addComponent(titleLabel);

        // Crear una instancia de formTutores con los datos proporcionados
        formTutores = new formTutores(id, nombre, apellido1, apellido2, fechaNacimiento,
                direccion, codigoPostal, nif, profesion, iban);

        // Agregar formTutores al diseño vertical

        addComponent(formTutores);
    }

    public formTutores getFormTutores() {
        return formTutores;
    }
}
