package com.example.appqs.webConstructors;

import com.example.appqs.AppQsApplication.MainUI;
import com.example.appqs.actions.formAlumnos;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

public class inscripcionMenu extends HorizontalLayout {

    private Button toggleMenuButton;

    public inscripcionMenu(MainUI mainUI, formAlumnos form, pushEnviar pushSender) {
        // Establecer la alineación de este HorizontalLayout a la derecha
        this.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

        // Crear botones y añadirlos horizontalmente
        toggleMenuButton = new Button("<<");

        // Añadir botones al layout horizontal
        addComponent(toggleMenuButton);

        // Configurar el listener para el botón toggleMenuButton
        toggleMenuButton.addClickListener(event -> {
            mainUI.toggleMenuVisibility();
            form.setupEnviarButton(); // Configurar el botón de envío con los datos de formAlumnos
        });
    }
}
