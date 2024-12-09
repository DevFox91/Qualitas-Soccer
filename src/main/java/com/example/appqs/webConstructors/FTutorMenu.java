package com.example.appqs.webConstructors;

import com.example.appqs.AppQsApplication;
import com.example.appqs.AppQsApplication.MainUI;
import com.example.appqs.actions.formTutores;
import com.example.appqs.views.FTutor;
import com.example.appqs.views.Tutores;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

public class FTutorMenu extends HorizontalLayout {

    private Button toggleMenuButton;
    private Button enviarDatosButton; // Botón para enviar los datos del formulario a la base de datos

    public FTutorMenu(MainUI mainUI, FTutor ftutor, pushEnviarTutor pushSender) {
        this.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

        // Obtener el formulario dentro de Inscripcion
        formTutores form = ftutor.getForm();

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
                    form.getNifField(), form.getProfesionField(), form.getIbanField());
        });

        // Crear el botón "Volver"
        Button volverButton = new Button("Volver");
        volverButton.addClickListener(event -> {
            // Crear la vista Alumnos y mostrarla en el contentPanel
            Tutores tutoresView = new Tutores();
            UI currentUI = UI.getCurrent();
            if (currentUI instanceof AppQsApplication.MainUI) {
                ((AppQsApplication.MainUI) currentUI).showView(tutoresView);
            }
        });

        addComponents(toggleMenuButton, enviarDatosButton, volverButton);
    }

}
