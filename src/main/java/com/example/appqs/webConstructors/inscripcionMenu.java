package com.example.appqs.webConstructors;

import com.example.appqs.AppQsApplication.MainUI;
import com.example.appqs.actions.formAlumnos;
import com.example.appqs.views.Inscripcion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

public class inscripcionMenu extends HorizontalLayout {

    private Button toggleMenuButton;
    private Button showFormValuesButton; // Botón para mostrar los valores del formulario
    private Button enviarDatosButton; // Botón para enviar los datos del formulario a la base de datos

    public inscripcionMenu(MainUI mainUI, Inscripcion inscripcion, pushEnviar pushSender) {
    this.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

    // Obtener el formulario dentro de Inscripcion
    formAlumnos form = inscripcion.getForm();

    toggleMenuButton = new Button("<<");

    showFormValuesButton = new Button("Mostrar Valores del Formulario");
    enviarDatosButton = new Button("Enviar Datos");

    // Comportamiento al hacer clic en el botón "Mostrar Valores del Formulario"
    showFormValuesButton.addClickListener(event -> {
        String formValues = form.getFormValues();
        Notification.show(formValues, Notification.Type.HUMANIZED_MESSAGE);
    });

    // Comportamiento al hacer clic en el botón "Enviar Datos"
    enviarDatosButton.addClickListener(event -> {
        // Obtener los valores del formulario y enviarlos
        pushSender.pushButtonEnviar(
            form.getNombreField(), form.getApellido1Field(), form.getApellido2Field(),
            form.getFechaNacimientoField(), form.getDireccionField(), form.getCodigoPostalField(),
            form.getAlergiasField(), form.getColegioField(), form.getEquipoAnteriorField()
        );
    });

    addComponents(toggleMenuButton, enviarDatosButton);
}

}
