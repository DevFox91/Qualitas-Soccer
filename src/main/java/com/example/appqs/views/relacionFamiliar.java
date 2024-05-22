package com.example.appqs.views;

import com.example.appqs.actions.gridRelacionFamiliar;
import com.vaadin.ui.VerticalLayout;

public class relacionFamiliar extends VerticalLayout {

    public relacionFamiliar() {
        
        // Crear una instancia de gridAlumnos
        gridRelacionFamiliar gridRelacionFamiliar = new gridRelacionFamiliar();

        // Agregar el gridAlumnos al diseño vertical
        addComponent(gridRelacionFamiliar);
    }
}
