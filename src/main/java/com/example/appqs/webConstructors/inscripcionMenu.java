package com.example.appqs.webConstructors;

import com.example.appqs.AppQsApplication.MainUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class inscripcionMenu extends HorizontalLayout {

    private Button toggleMenuButton;
    private Button enviarButton;
    private TextField nombreField;
    private TextField apellido1Field;
    private TextField apellido2Field;
    private DateField fechaNacimientoField;
    private TextField direccionField;
    private TextField codigoPostalField;
    private TextField alergiasField;
    private TextField colegioField;
    private TextField equipoAnteriorField;
    private pushEnviar pushSender;

    public inscripcionMenu(MainUI mainUI, pushEnviar pushSender) {
        this.pushSender = pushSender;

        // Establecer la alineación de este HorizontalLayout a la derecha
        this.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

        // Crear botones y añadirlos horizontalmente
        toggleMenuButton = new Button("<<");


        // Añadir botones al layout horizontal
        addComponent(toggleMenuButton);

        // Configurar el listener para el botón toggleMenuButton
        toggleMenuButton.addClickListener(event -> mainUI.toggleMenuVisibility());
    }
}
