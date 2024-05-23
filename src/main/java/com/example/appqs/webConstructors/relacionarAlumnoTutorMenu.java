package com.example.appqs.webConstructors;

import com.example.appqs.AppQsApplication;
import com.example.appqs.actions.gridRelacionFamiliar;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class relacionarAlumnoTutorMenu extends HorizontalLayout {

    private Button toggleMenuButton;

    public relacionarAlumnoTutorMenu(AppQsApplication.MainUI mainUI, int alumnoId) {
        this.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

        toggleMenuButton = new Button("<<");

        // Configurar el listener para el botón toggleMenuButton
        toggleMenuButton.addClickListener(event -> mainUI.toggleMenuVisibility());

        // Crear el botón "Volver"
        Button volverButton = new Button("Volver");
        volverButton.addClickListener(event -> {
            // Crear la vista gridRelacionarAlumno y mostrarla en el contentPanel
            gridRelacionFamiliar gridRelacionAlumno = new gridRelacionFamiliar(alumnoId);
            mainUI.showView(gridRelacionAlumno);
        });

        // Crear el botón "Relacionar"
        Button relacionarButton = new Button("Relacionar");
        relacionarButton.addClickListener(event -> {
            // Lógica para relacionar alumno y tutor
            boolean relacionCreada = crearRelacion(alumnoId);
            if (relacionCreada) {
                Notification.show("Relación creada exitosamente");
            } else {
                Notification.show("Relación ya creada", Notification.Type.WARNING_MESSAGE);
            }
        });

        addComponents(toggleMenuButton, relacionarButton, volverButton);
    }

    private boolean crearRelacion(int alumnoId) {
        // Implementa la lógica para crear una nueva relación entre el alumno y el tutor
        // Verifica si ya existe una relación igual antes de crearla
        // Devuelve true si la relación se creó con éxito, false si ya existía una relación igual
        return true; // Por ahora, devuelve true para simular que la relación se crea siempre
    }
}
