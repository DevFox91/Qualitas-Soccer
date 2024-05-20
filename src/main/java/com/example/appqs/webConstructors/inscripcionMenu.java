package com.example.appqs.webConstructors;

import com.example.appqs.AppQsApplication.MainUI;
import com.example.appqs.actions.formAlumnos;
import com.example.appqs.views.Inscripcion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

public class inscripcionMenu extends HorizontalLayout {

    private Button toggleMenuButton;
    private Button newAlumno;

    public inscripcionMenu(MainUI mainUI, formAlumnos form, pushEnviar pushSender) {
        // Establecer la alineación de este HorizontalLayout a la derecha
        this.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

        // Crear botones y añadirlos horizontalmente
        toggleMenuButton = new Button("<<");
        newAlumno = new Button("Añadir Alumno");

        // Añadir botones al layout horizontal
        addComponent(toggleMenuButton);
        addComponent(newAlumno);

        // Configurar el listener para el botón toggleMenuButton
        toggleMenuButton.addClickListener(event -> mainUI.toggleMenuVisibility());

        // Configurar el listener para el botón "Añadir Alumno"
        newAlumno.addClickListener(event -> mainUI.showView(new Inscripcion()));
    }

    public void setnewAlumnoListener(Button.ClickListener listener) {
        newAlumno.addClickListener(listener);
    }
}