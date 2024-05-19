package com.example.appqs.views;

import com.example.appqs.actions.gridAlumnos;
import com.vaadin.ui.VerticalLayout;

public class Alumnos extends VerticalLayout {

    public Alumnos() {
        
        // Crear una instancia de gridAlumnos
        gridAlumnos gridAlumnos = new gridAlumnos();

        // Agregar el gridAlumnos al diseño vertical
        addComponent(gridAlumnos);
    }
}
