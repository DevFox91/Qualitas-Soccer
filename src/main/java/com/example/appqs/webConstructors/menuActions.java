package com.example.appqs.webConstructors;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

public class menuActions extends HorizontalLayout {

    public Button toggleMenuButton; // Modificado a public

    public menuActions() {
        // Establecer la alineación de este HorizontalLayout a la derecha
        setDefaultComponentAlignment(Alignment.TOP_RIGHT);

        // Crear botón para ocultar el menú
        toggleMenuButton = new Button("<<");

        // Añadir el botón al layout horizontal
        addComponent(toggleMenuButton);
    }

    public void setToggleMenuButtonListener(Button.ClickListener listener) {
        toggleMenuButton.addClickListener(listener);
    }

    // Método para cambiar el menú según la vista actual
    public void updateMenu(String currentViewName) {
        removeAllComponents(); // Elimina todos los componentes actuales

        if (currentViewName.equals("Alumnos")) {
            AlumnosMenu alumnosMenu = new AlumnosMenu();
            alumnosMenu.setToggleMenuButtonListener(e -> toggleMenu());
            addComponents(toggleMenuButton, alumnosMenu);
        } else if (currentViewName.equals("Tutores")) {
            TutoresMenu tutoresMenu = new TutoresMenu();
            tutoresMenu.setToggleMenuButtonListener(e -> toggleMenu());
            addComponents(toggleMenuButton, tutoresMenu);
        } else {
            // Si la vista actual no coincide con "Alumnos" ni "Tutores", solo agrega el botón de toggle
            addComponent(toggleMenuButton);
        }
    }

    private void toggleMenu() {
        UI.getCurrent().access(() -> UI.getCurrent().getNavigator().navigateTo(""));
    }
}
