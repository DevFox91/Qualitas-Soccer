package com.example.appqs.webConstructors;

import com.example.appqs.AppQsApplication;
import com.example.appqs.views.Alumnos;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

public class gridRelacionFamiliarMenu extends HorizontalLayout {

    private Button toggleMenuButton;

    public gridRelacionFamiliarMenu(AppQsApplication.MainUI mainUI, int alumnoId) {
        this.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

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


        addComponents(toggleMenuButton, relacionarButton, volverButton);
    }
}
