package com.example.appqs.views;


import com.example.appqs.actions.gridTutores;
import com.vaadin.ui.VerticalLayout;

public class Tutores extends VerticalLayout {

    public Tutores() {
        // Crear una instancia de gridTutores
        gridTutores gridTutores = new gridTutores();

        // Agregar el gridTutores al diseño vertical
        addComponent(gridTutores);
    }
}
