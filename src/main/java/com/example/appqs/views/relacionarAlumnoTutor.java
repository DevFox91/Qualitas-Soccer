package com.example.appqs.views;

import com.example.appqs.dbconnections.buscaRelaciones.Personal;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

public class relacionarAlumnoTutor extends VerticalLayout {

    public relacionarAlumnoTutor(String nombreAlumno, int alumnoId, List<Personal> personas) {
        // Crear el título
        Label titulo = new Label("Nombre del Alumno a relacionar: " + nombreAlumno + " (" + alumnoId + ")");
        titulo.setStyleName("h2");

        // Crear el grid
        Grid<Personal> grid = new Grid<>();
        grid.setItems(personas);

        // Añadir columnas
        grid.addColumn(Personal::getId).setCaption("ID");
        grid.addColumn(Personal::getNombre).setCaption("Nombre Completo");

        // Lista para almacenar los checkboxes
        List<CheckBox> checkBoxes = new ArrayList<>();

        // Añadir columna de verificación
        grid.addComponentColumn(personal -> {
            CheckBox checkBox = new CheckBox();
            checkBoxes.add(checkBox);
            checkBox.addValueChangeListener(event -> {
                if (checkBox.getValue()) {
                    checkBoxes.forEach(cb -> {
                        if (cb != checkBox) {
                            cb.setValue(false);
                        }
                    });
                }
            });
            return checkBox;
        }).setCaption("Relacionar");

        // Añadir el título y el grid al layout
        addComponent(titulo);
        addComponent(grid);
    }
}
