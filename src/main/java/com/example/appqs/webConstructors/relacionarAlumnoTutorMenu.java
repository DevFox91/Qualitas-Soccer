package com.example.appqs.webConstructors;

import com.example.appqs.AppQsApplication;
import com.example.appqs.actions.gridRelacionFamiliar;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
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

        addComponents(toggleMenuButton, relacionarButton, volverButton);
    }
}
