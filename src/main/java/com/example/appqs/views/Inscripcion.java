package com.example.appqs.views;

import com.example.appqs.actions.formAlumnos;
import com.vaadin.ui.VerticalLayout;

public class Inscripcion extends VerticalLayout {
    private formAlumnos form;

    public Inscripcion() {
        form = new formAlumnos(); // Crear una instancia de formAlumnos
        addComponent(form); // Agregar el formulario a la vista Inscripcion
    }

    // Método para obtener el formulario
    public formAlumnos getForm() {
        return form;
    }
}
