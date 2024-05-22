package com.example.appqs.webConstructors;

import com.example.appqs.AppQsApplication;
import com.example.appqs.AppQsApplication.MainUI;
import com.example.appqs.views.Alumnos;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;


public class relacionFamiliarMenu extends HorizontalLayout {

    private Button toggleMenuButton;
    private Button enviarDatosButton; // Botón para enviar los datos del formulario a la base de datos

    public relacionFamiliarMenu(MainUI mainUI) {
        this.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

        toggleMenuButton = new Button("<<");
        enviarDatosButton = new Button("Guardar datos");

        // Configurar el listener para el botón toggleMenuButton
        toggleMenuButton.addClickListener(event -> mainUI.toggleMenuVisibility());

        // Crear el botón "Volver"
        Button volverButton = new Button("Volver");
        volverButton.addClickListener(event -> {
            // Crear la vista Alumnos y mostrarla en el contentPanel
            Alumnos alumnosView = new Alumnos();
            UI currentUI = UI.getCurrent();
            if (currentUI instanceof AppQsApplication.MainUI) {
                ((AppQsApplication.MainUI) currentUI).showView(alumnosView);
            }
        });

        // Comportamiento al hacer clic en el botón "Enviar Datos"
        

        addComponents(toggleMenuButton, enviarDatosButton, volverButton);
    }
}