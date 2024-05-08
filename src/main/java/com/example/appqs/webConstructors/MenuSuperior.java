package com.example.appqs.webConstructors;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;

public class MenuSuperior extends VerticalLayout {

    private MenuBar menuBar;

    public MenuSuperior() {
        menuBar = new MenuBar();
        addComponent(menuBar);
    }

    public void setAlumnosButtonListener(MenuBar.Command command) {
        menuBar.addItem("Mostrar Alumnos", command);
    }

    public void setTutoresButtonListener(MenuBar.Command command) {
        menuBar.addItem("Mostrar Tutores", command);
    }

    public void setPersonalButtonListener(MenuBar.Command command) {
        menuBar.addItem("Mostrar Personal", command);
    }

    public void setInscripcionButtonListener(MenuBar.Command command) {
        menuBar.addItem("Formulario Alumno", command);
    }

    public void setFPersonalButtonListener(MenuBar.Command command) {
        menuBar.addItem("Formulario Personal", command);
    }

    public void setFTutorButtonListener(MenuBar.Command command) {
        menuBar.addItem("Formulario Tutor", command);
    }
}
