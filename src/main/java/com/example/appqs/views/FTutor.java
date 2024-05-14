package com.example.appqs.views;

import com.example.appqs.actions.formTutores;
import com.vaadin.ui.VerticalLayout;

public class FTutor extends VerticalLayout {
        private formTutores formTutores;

    public FTutor() {
        // Crear una instancia de formTutores
        formTutores = new formTutores();

        // Agregar formTutores al diseño vertical
        addComponent(formTutores);
    }

    public formTutores getFormTutores() {
        return formTutores;
    }
}
