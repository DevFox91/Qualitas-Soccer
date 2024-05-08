package com.example.appqs.views;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class Personal extends VerticalLayout {

    public Personal() {
        initComponents();
    }

    private void initComponents() {
        addComponent(new Label("Contenido de la vista de Personal"));
    }
}
