package com.example.appqs.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.example.appqs.dbconnections.buscaRelaciones.Personal;

public class relacionarAlumnoTutor extends VerticalLayout {
    private static int alumnoId;
    private static List<Personal> personas;
    private static List<CheckBox> checkBoxes;

    public relacionarAlumnoTutor(String nombreAlumno, int alumnoId, List<Personal> personas) {
        // Inicializar la lista de checkboxes
        checkBoxes = new ArrayList<>();

        // Crear el título
        Label titulo = new Label("Nombre del Alumno a relacionar: " + nombreAlumno + " (" + alumnoId + ")");
        titulo.setStyleName("h2");

        // Crear el grid
        Grid<Personal> grid = new Grid<>();
        grid.setItems(personas);

        // Añadir columnas
        grid.addColumn(Personal::getId).setCaption("ID");
        grid.addColumn(Personal::getNombre).setCaption("Nombre Completo");

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
        System.out.println(alumnoId);
    }

    public static int getAlumnoId() {
        System.out.println(alumnoId);
        return alumnoId;
    }

    public static Integer getIdRelacionado() {
        // Utilizar streams para buscar el checkbox marcado y obtener su ID correspondiente
        Integer tutorId = null;
        Optional<CheckBox> checkBoxOptional = checkBoxes.stream().filter(CheckBox::getValue).findFirst();
        if (checkBoxOptional.isPresent()) {
            CheckBox checkBox = checkBoxOptional.get();
            int index = checkBoxes.indexOf(checkBox);
            tutorId = personas.get(index).getId();
        }
        System.out.println(tutorId);
        return tutorId;
    }
}
