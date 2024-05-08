package com.example.appqs.views;

import com.example.appqs.actions.formAlumnos;
import com.vaadin.ui.VerticalLayout;



public class Inscripcion extends VerticalLayout {
    private formAlumnos formAlumnos;

    public Inscripcion() {
        // Crear una instancia de formAlumnos
        formAlumnos = new formAlumnos();

        // Agregar formAlumnos al diseño vertical
        addComponent(formAlumnos);
    }

    public formAlumnos getFormAlumnos() {
        return formAlumnos;
    }
}
