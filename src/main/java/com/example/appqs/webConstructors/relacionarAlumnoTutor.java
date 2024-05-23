package com.example.appqs.webConstructors;

import com.vaadin.ui.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class relacionarAlumnoTutor extends VerticalLayout {

    private int alumnoId; // Ahora alumnoId es un campo de instancia
    private Grid<Tutor> tutorGrid;
    private TextField searchField;

    public relacionarAlumnoTutor(int alumnoId, String nombre, String apellido1, String apellido2) {
        this.alumnoId = alumnoId; // Asignamos el valor de alumnoId al campo de instancia
        Label alumnoInfo = new Label("El alumno seleccionado es: " + nombre + " " + apellido1 + " " + apellido2 + " (Id: " + alumnoId + ")");
        addComponent(alumnoInfo);

        searchField = new TextField();
        searchField.setPlaceholder("Buscar tutor...");
        searchField.addValueChangeListener(event -> filterTutors(event.getValue()));
        addComponent(searchField);

        tutorGrid = new Grid<>();
        tutorGrid.addColumn(Tutor::getNombreCompleto).setCaption("Nombre Completo");
        tutorGrid.addComponentColumn(tutor -> {
            CheckBox checkBox = new CheckBox();
            checkBox.setValue(tutor.isSelected());
            checkBox.addValueChangeListener(e -> tutor.setSelected(checkBox.getValue()));
            return checkBox;
        }).setCaption("Seleccionar");
        addComponent(tutorGrid);

        List<Tutor> tutores = obtenerTutores(); // Ahora podemos acceder directamente a alumnoId
        tutorGrid.setItems(tutores);
    }

    public int getAlumnoId() {
        return alumnoId;
    }

    private List<Tutor> obtenerTutores() {
        List<Tutor> tutores = new ArrayList<>();
        String url = "jdbc:postgresql://localhost/QSoccer";
        String user = "QSUser";
        String password = "12345";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT id, nombre, apellido1, apellido2 FROM personal WHERE tipopersona = 2 AND delete = false";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nombre = resultSet.getString("nombre");
                        String apellido1 = resultSet.getString("apellido1");
                        String apellido2 = resultSet.getString("apellido2");
                        tutores.add(new Tutor(id, nombre, apellido1, apellido2)); // No es necesario pasar alumnoId aquí
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tutores;
    }

    private void filterTutors(String filterText) {
        List<Tutor> filteredTutors = obtenerTutores(); // Ahora podemos acceder directamente a alumnoId
        if (filterText != null && !filterText.isEmpty()) {
            filteredTutors.removeIf(tutor -> !tutor.getNombreCompleto().toLowerCase().contains(filterText.toLowerCase()));
        }
        tutorGrid.setItems(filteredTutors);
    }

    private static class Tutor {
        private int id;
        private String nombre;
        private String apellido1;
        private String apellido2;
        private boolean selected;

        public Tutor(int id, String nombre, String apellido1, String apellido2) {
            this.id = id;
            this.nombre = nombre;
            this.apellido1 = apellido1;
            this.apellido2 = apellido2;
            this.selected = false;
        }

        public int getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public String getApellido1() {
            return apellido1;
        }

        public String getApellido2() {
            return apellido2;
        }

        public String getNombreCompleto() {
            return nombre + " " + apellido1 + " " + apellido2;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }
    }
}
