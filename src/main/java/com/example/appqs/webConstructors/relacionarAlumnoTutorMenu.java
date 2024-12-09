package com.example.appqs.webConstructors;

import com.example.appqs.AppQsApplication.MainUI;
import com.example.appqs.actions.prepararRelacion;
import com.example.appqs.views.relacionarAlumnoTutor;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

public class relacionarAlumnoTutorMenu extends HorizontalLayout {

    private Button alumnosButton;
    public Button toggleMenuButton;

    public relacionarAlumnoTutorMenu(MainUI mainUI, relacionarAlumnoTutor relacionarAlumnoTutorView) {
        // Establecer la alineación de este HorizontalLayout a la derecha
        this.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

        // Crear botones y añadirlos horizontalmente
        toggleMenuButton = new Button("<<");
        alumnosButton = new Button("Añadir Relación");

        // Añadir botones al layout horizontal
        addComponent(toggleMenuButton);
        addComponent(alumnosButton);

        // Configurar el listener para el botón toggleMenuButton
        toggleMenuButton.addClickListener(event -> mainUI.toggleMenuVisibility());

        // Configurar el listener para el botón Añadir Relacion
        alumnosButton.addClickListener(event -> {
            int alumnoId = relacionarAlumnoTutor.getAlumnoId();
            Integer tutorIdSeleccionado = relacionarAlumnoTutor.getIdRelacionado();
            prepararRelacion.prepararRelacionIds(alumnoId, tutorIdSeleccionado);
        });
    }
}
