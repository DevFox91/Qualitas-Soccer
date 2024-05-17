package com.example.appqs.webConstructors;

import com.example.appqs.AppQsApplication.MainUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

public class AlumnosMenu extends HorizontalLayout {

    private Button alumnosButton;
    public Button toggleMenuButton;

    public AlumnosMenu(MainUI mainUI) {
        // Establecer la alineación de este HorizontalLayout a la derecha
        this.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

        // Crear botones y añadirlos horizontalmente
        toggleMenuButton = new Button("<<");
        alumnosButton = new Button("Añadir Alumno");

        // Añadir botones al layout horizontal
        addComponent(toggleMenuButton);
        addComponent(alumnosButton);

        // Configurar el listener para el botón toggleMenuButton
        toggleMenuButton.addClickListener(event -> mainUI.toggleMenuVisibility());
    }

    public void setAlumnosButtonListener(Button.ClickListener listener) {
        alumnosButton.addClickListener(listener);
    }
}
