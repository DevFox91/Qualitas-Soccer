package com.example.appqs.webConstructors;

import com.example.appqs.AppQsApplication;
import com.example.appqs.views.Alumnos;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class gridRelacionFamiliarMenu extends HorizontalLayout {

    private Button toggleMenuButton;

    public gridRelacionFamiliarMenu(AppQsApplication.MainUI mainUI, int alumnoId) {
        this.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

        toggleMenuButton = new Button("<<");

        // Configurar el listener para el botón toggleMenuButton
        toggleMenuButton.addClickListener(event -> mainUI.toggleMenuVisibility());

        // Crear el botón "Volver"
        Button volverButton = new Button("Volver");
        volverButton.addClickListener(event -> {
            // Crear la vista Alumnos y mostrarla en el contentPanel
            Alumnos alumnosView = new Alumnos();
            UI currentUI = UI.getCurrent();
            if (currentUI instanceof AppQsApplication.MainUI) {
                ((AppQsApplication.MainUI) currentUI).showView(alumnosView);
            }
        });

        // Crear el botón "Relacionar"
        Button relacionarButton = new Button("Relacionar");
        relacionarButton.addClickListener(event -> {
            // Buscar los datos del alumno en la base de datos
            try {
                Alumno alumno = buscarAlumnoPorId(alumnoId);
                if (alumno != null) {
                    relacionarAlumnoTutor relacionarView = new relacionarAlumnoTutor(alumno.getId(), alumno.getNombre(), alumno.getApellido1(), alumno.getApellido2());
                    mainUI.showView(relacionarView);
                } else {
                    System.out.println("No se encontraron datos para el alumno con ID: " + alumnoId);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        addComponents(toggleMenuButton, relacionarButton, volverButton);
    }

    private Alumno buscarAlumnoPorId(int alumnoId) throws SQLException {
        String url = "jdbc:postgresql://localhost/QSoccer";
        String user = "QSUser";
        String password = "12345";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT nombre, apellido1, apellido2 FROM personal WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, alumnoId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String nombre = resultSet.getString("nombre");
                        String apellido1 = resultSet.getString("apellido1");
                        String apellido2 = resultSet.getString("apellido2");
                        return new Alumno(alumnoId, nombre, apellido1, apellido2);
                    }
                }
            }
        }
        return null;
    }

    private class Alumno {
        private int id;
        private String nombre;
        private String apellido1;
        private String apellido2;

        public Alumno(int id, String nombre, String apellido1, String apellido2) {
            this.id = id;
            this.nombre = nombre;
            this.apellido1 = apellido1;
            this.apellido2 = apellido2;
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
    }
}
