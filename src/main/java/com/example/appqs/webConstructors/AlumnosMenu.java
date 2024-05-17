package com.example.appqs.webConstructors;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

public class AlumnosMenu extends HorizontalLayout {

    private Button alumnosButton;
    private Button tutoresButton;
    private Button personalButton;
    public Button toggleMenuButton;

    public AlumnosMenu() {
        // Establecer la alineación de este HorizontalLayout a la derecha
        this.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

        // Crear botones y añadirlos horizontalmente
        toggleMenuButton = new Button("<<");
        alumnosButton = new Button("Añadir Alumno");

        // Añadir botones al layout horizontal
        addComponent(toggleMenuButton);
        addComponent(alumnosButton);

    }

    public void setToggleMenuButtonListener(Button.ClickListener listener) {
        toggleMenuButton.addClickListener(listener);
    }

    public void setAlumnosButtonListener(Button.ClickListener listener) {
        alumnosButton.addClickListener(listener);
    }

    public void setTutoresButtonListener(Button.ClickListener listener) {
        tutoresButton.addClickListener(listener);
    }

    public void setPersonalButtonListener(Button.ClickListener listener) {
        personalButton.addClickListener(listener);
    }
}
