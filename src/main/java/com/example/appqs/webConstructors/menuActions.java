package com.example.appqs.webConstructors;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class menuActions extends HorizontalLayout {

    private Button alumnosButton;
    private Button tutoresButton;
    private Button personalButton;

    public menuActions() {
        // Establecer la alineación de este HorizontalLayout a la derecha
        this.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

        // Crear un componente Label para el título
        Label menuTitle = new Label("MENÚ ACTIONS");
        menuTitle.setStyleName("menu-title"); // Ajusta el estilo según tus necesidades
        addComponent(menuTitle); // Agregar el título al layout horizontal

        // Crear botones y añadirlos horizontalmente
        alumnosButton = new Button("Botones");
        tutoresButton = new Button("Acciones");
        personalButton = new Button("Variables");

        // Añadir botones al layout horizontal
        addComponent(alumnosButton);
        addComponent(tutoresButton);
        addComponent(personalButton);

        // Establecer alineación de los botones a la derecha
        setComponentAlignment(menuTitle, Alignment.MIDDLE_LEFT); // Alinea el título a la izquierda
    }

    public void setAlumnosButtonListener(Button.ClickListener listener) {
        alumnosButton.addClickListener(listener);
    }

    public void setTutoresButtonListener(Button.ClickListener listener) {
        tutoresButton.addClickListener(listener);
    }

    public void setPersonalButtonListener(Button.ClickListener listener) {
        personalButton.addClickListener(listener);
    }
}
