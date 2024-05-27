package com.example.appqs.views;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.example.appqs.dbconnections.buscaRelaciones.Personal;
import com.example.appqs.actions.prepararRelacion;

public class relacionarAlumnoTutor extends VerticalLayout {
    private static int alumnoId;
    private static List<Personal> personas;
    private static Map<CheckBox, Personal> checkBoxPersonalMap;
    private static Integer tutorIdSeleccionado; // Variable para almacenar la ID del tutor seleccionado

    public relacionarAlumnoTutor(String nombreAlumno, int alumnoId, List<Personal> personas) {
        // Inicializar el mapa de checkboxes
        checkBoxPersonalMap = new HashMap<>();
        tutorIdSeleccionado = null;

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
            checkBoxPersonalMap.put(checkBox, personal);
            checkBox.addValueChangeListener(event -> {
                if (checkBox.getValue()) {
                    checkBoxPersonalMap.keySet().forEach(cb -> {
                        if (cb != checkBox) {
                            cb.setValue(false);
                        }
                    });
                    tutorIdSeleccionado = personal.getId(); // Almacenar la ID del tutor seleccionado
                    System.out.println("ID seleccionado: " + tutorIdSeleccionado); // Imprimir la ID seleccionada
                } else {
                    tutorIdSeleccionado = null; // Desmarcar si el usuario desmarca la casilla
                    System.out.println("Ninguna casilla seleccionada"); // Imprimir que ninguna casilla está seleccionada
                }
                // Print the status of all checkboxes whenever one changes
                checkBoxPersonalMap.forEach((cb, p) -> {
                    System.out.println("Checkbox for " + p.getNombre() + " is " + (cb.getValue() ? "checked" : "unchecked"));
                });
            });
            return checkBox;
        }).setCaption("Relacionar");

        // Añadir el título y el grid al layout
        addComponent(titulo);
        addComponent(grid);
        System.out.println("Alumno ID: " + alumnoId);

        // Añadir botón para preparar la relación
        Button prepararRelacionButton = new Button("Preparar Relación");
        prepararRelacionButton.addClickListener(event -> {
            prepararRelacion.prepararRelacionIds(getAlumnoId(), getIdRelacionado());
        });
        addComponent(prepararRelacionButton);

        // Asignar personas al campo de clase personas
        relacionarAlumnoTutor.personas = personas;
        relacionarAlumnoTutor.alumnoId = alumnoId;
    }

    public static int getAlumnoId() {
        return alumnoId;
    }

    public static Integer getIdRelacionado() {
        // Retornar directamente la ID del tutor seleccionado
        System.out.println("Relacionado ID: " + tutorIdSeleccionado);
        return tutorIdSeleccionado;
    }
}
