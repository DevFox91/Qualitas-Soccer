package com.example.appqs.webConstructors;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import java.util.List;
import java.util.stream.Collectors;

public class relacionarAlumnoTutor extends VerticalLayout {

    private List<String> tutoresDisponibles;
    private VerticalLayout tutorLayout;
    private TextField searchField;

    public relacionarAlumnoTutor(String nombreAlumno, List<String> tutoresDisponibles) {
        this.tutoresDisponibles = tutoresDisponibles;

        // Mostrar el nombre del alumno
        Label nombreAlumnoLabel = new Label("Alumno: " + nombreAlumno);
        addComponent(nombreAlumnoLabel);

        // Crear el campo de búsqueda
        searchField = new TextField();
        searchField.setPlaceholder("Buscar tutor...");
        searchField.addValueChangeListener(event -> updateTutorList());
        addComponent(searchField);

        // Mostrar la lista de tutores disponibles
        tutorLayout = new VerticalLayout();
        addComponent(tutorLayout);

        // Actualizar la lista de tutores disponibles
        updateTutorList();

        // Botón para confirmar la relación
        Button confirmarButton = new Button("Confirmar Relación");
        addComponent(confirmarButton);

        // Alinear los componentes al centro
        setComponentAlignment(nombreAlumnoLabel, Alignment.MIDDLE_CENTER);
        setComponentAlignment(searchField, Alignment.MIDDLE_CENTER);
        setComponentAlignment(tutorLayout, Alignment.MIDDLE_CENTER);
        setComponentAlignment(confirmarButton, Alignment.MIDDLE_CENTER);
    }

    // Método para actualizar la lista de tutores según el texto de búsqueda
    private void updateTutorList() {
        tutorLayout.removeAllComponents();
        String searchTerm = searchField.getValue().toLowerCase();

        List<String> filteredTutors = tutoresDisponibles.stream()
                .filter(tutor -> tutor.toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());

        for (String tutor : filteredTutors) {
            Label tutorLabel = new Label(tutor);
            tutorLayout.addComponent(tutorLabel);
        }
    }
}
