package com.example.appqs.views;

import com.example.appqs.actions.gridAlumnos;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Window;

public class Alumnos extends VerticalLayout {

    public Alumnos() {
        // Crear botón "Añadir"
        Button addButton = new Button("Añadir");
        addButton.addClickListener(event -> {
            // Crear un panel para el popup
            Panel popup = new Panel("FORMULARIO PARA AÑADIR UN NUEVO ALUMNO");

            // Crear un botón de cierre para el popup
            Button closeButton = new Button("Cerrar");
            closeButton.addClickListener(closeEvent -> {
                // Cerrar la ventana que contiene el panel
                ((Window) popup.getParent()).close();
            });

            // Agregar el botón de cierre y la vista de Inscripcion al panel
            popup.setContent(new VerticalLayout(new HorizontalLayout(closeButton), new Inscripcion()));

            // Crear y abrir una ventana con el panel como contenido
            Window popupWindow = new Window(null, popup);
            UI.getCurrent().addWindow(popupWindow);
        });

        // Agregar el botón al diseño vertical
        addComponent(addButton);

        // Establecer la alineación del botón a la derecha
        setComponentAlignment(addButton, Alignment.TOP_RIGHT);

        // Crear una instancia de gridAlumnos
        gridAlumnos gridAlumnos = new gridAlumnos();

        // Agregar el gridAlumnos al diseño vertical
        addComponent(gridAlumnos);
    }
}
