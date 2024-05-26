package com.example.appqs.webConstructors;

import com.example.appqs.AppQsApplication;
import com.example.appqs.dbconnections.buscaRelaciones;
import com.example.appqs.dbconnections.buscaRelaciones.Personal;
import com.example.appqs.views.Alumnos;
import com.example.appqs.views.relacionarAlumnoTutor;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

import java.util.List;

public class gridRelacionFamiliarMenu extends HorizontalLayout {

    private Button toggleMenuButton;
    private int alumnoId;

    public gridRelacionFamiliarMenu(AppQsApplication.MainUI mainUI, int alumnoId) {
        this.setDefaultComponentAlignment(Alignment.TOP_RIGHT);
        this.alumnoId = alumnoId;

        toggleMenuButton = new Button("<<");

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

        // Crear el botón "Relacionar"
        Button relacionarButton = new Button("Relacionar");
        relacionarButton.addClickListener(event -> {
            // Obtener la información del alumno
            String nombreAlumno = buscaRelaciones.obtenerNombreAlumno(alumnoId);
            // Obtener la lista de personas
            List<Personal> personas = buscaRelaciones.obtenerPersonas();

            // Navegar a la nueva vista relacionarAlumnoTutor
            relacionarAlumnoTutor relacionarView = new relacionarAlumnoTutor(nombreAlumno, alumnoId, personas);
            UI currentUI = UI.getCurrent();
            if (currentUI instanceof AppQsApplication.MainUI) {
                ((AppQsApplication.MainUI) currentUI).showView(relacionarView);
            }
        });

        addComponents(toggleMenuButton, relacionarButton, volverButton);
    }
}
