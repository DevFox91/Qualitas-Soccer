package com.example.appqs.webConstructors;

import com.example.appqs.AppQsApplication.MainUI;
import com.example.appqs.views.FTutor;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

public class TutoresMenu extends HorizontalLayout {

    private Button tutoresButton;
    public Button toggleMenuButton;

    public TutoresMenu(MainUI mainUI) {
        // Establecer la alineación de este HorizontalLayout a la derecha
        this.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

        // Crear botones y añadirlos horizontalmente
        toggleMenuButton = new Button("<<");
        tutoresButton = new Button("Añadir Tutor");

        // Añadir botones al layout horizontal
        addComponent(toggleMenuButton);
        addComponent(tutoresButton);

        // Configurar el listener para el botón toggleMenuButton
        toggleMenuButton.addClickListener(event -> mainUI.toggleMenuVisibility());

        // Configurar el listener para el botón "Añadir Alumno"
        tutoresButton.addClickListener(event -> mainUI.showView(new FTutor()));
    }

    public void setAlumnosButtonListener(Button.ClickListener listener) {
        tutoresButton.addClickListener(listener);
    }
}
