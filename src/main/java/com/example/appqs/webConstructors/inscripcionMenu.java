package com.example.appqs.webConstructors;

import com.example.appqs.AppQsApplication.MainUI;
import com.example.appqs.actions.formAlumnos;
import com.example.appqs.views.Inscripcion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

public class inscripcionMenu extends HorizontalLayout {

    private Button toggleMenuButton;

    private Button enviarDatosButton; // Botón para enviar los datos del formulario a la base de datos

    public inscripcionMenu(MainUI mainUI, Inscripcion inscripcion, pushEnviar pushSender) {
        this.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

        // Obtener el formulario dentro de Inscripcion
        formAlumnos form = inscripcion.getForm();

        toggleMenuButton = new Button("<<");

        enviarDatosButton = new Button("Enviar Datos");

        // Configurar el listener para el botón toggleMenuButton
        toggleMenuButton.addClickListener(event -> mainUI.toggleMenuVisibility());

        // Comportamiento al hacer clic en el botón "Enviar Datos"
        enviarDatosButton.addClickListener(event -> {
            // Obtener los valores del formulario y enviarlos
            pushSender.pushButtonEnviar(
                    form.getNombreField(), form.getApellido1Field(), form.getApellido2Field(),
                    form.getFechaNacimientoField(), form.getDireccionField(), form.getCodigoPostalField(),
                    form.getAlergiasField(), form.getColegioField(), form.getEquipoAnteriorField());
        });

        addComponents(toggleMenuButton, enviarDatosButton);
    }

}
