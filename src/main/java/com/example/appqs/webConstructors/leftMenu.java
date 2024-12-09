package com.example.appqs.webConstructors;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class leftMenu extends VerticalLayout {

    private Button alumnosButton;
    private Button tutoresButton;
    private Button personalButton;

    public leftMenu() {
        // Crear un componente Label para el título
        Label menuTitle = new Label("MENÚ NAVEGACIÓN");
        menuTitle.setStyleName("menu-title"); // Ajusta el estilo según tus necesidades
        addComponent(menuTitle); // Agregar el título al layout vertical

        alumnosButton = new Button("Mostrar Alumnos");
        tutoresButton = new Button("Mostrar Tutores");
        personalButton = new Button("Mostrar Personal");

        // Aplicar la clase CSS a cada botón
        alumnosButton.addStyleName("menu-button");
        tutoresButton.addStyleName("menu-button");
        personalButton.addStyleName("menu-button");

        // Añadir botones al layout vertical para que se dispongan uno debajo del otro
        addComponent(alumnosButton);
        addComponent(tutoresButton);
        addComponent(personalButton);
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
