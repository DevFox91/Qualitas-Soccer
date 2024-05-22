package com.example.appqs.views;

import com.example.appqs.actions.gridRelacionFamiliar;
import com.vaadin.ui.VerticalLayout;

public class relacionFamiliar extends VerticalLayout {

    public relacionFamiliar(int alumnoId) {
        gridRelacionFamiliar grid = new gridRelacionFamiliar(alumnoId);
        addComponent(grid);
    }
}
