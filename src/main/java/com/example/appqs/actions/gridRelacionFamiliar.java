package com.example.appqs.actions;

import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class gridRelacionFamiliar extends VerticalLayout {

    private int alumnoId;

    public gridRelacionFamiliar(int alumnoId) {
        this.alumnoId = alumnoId;
        
        // Crea un Grid
        Grid<Object[]> grid = new Grid<>();

        // Tamaño del grid
        grid.setSizeFull();

        // Agregar columnas al Grid con los nombres de las columnas de la db
        grid.addColumn(row -> row[0]).setCaption("Alumno");
        grid.addColumn(row -> row[1]).setCaption("Tutor");
        grid.addColumn(row -> row[2]).setCaption("Entrenador");

        // Obtener los datos de la base de datos
        List<Object[]> relacionesData = getRelacionesData(alumnoId);

        // Agregar los datos al Grid
        grid.setItems(relacionesData);

        // Agregar el Grid al diseño vertical
        addComponent(grid);

        // Ajustar el tamaño del Grid al espacio disponible
        setExpandRatio(grid, 1);
    }

    private List<Object[]> getRelacionesData(int alumnoId) {
        List<Object[]> data = new ArrayList<>();
        String url = "jdbc:postgresql://localhost/QSoccer";
        String user = "QSUser";
        String password = "12345";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT idtipo1, idtipo2, idtipo3 FROM relaciones WHERE idtipo1 = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, alumnoId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int idAlumno = resultSet.getInt("idtipo1");
                        int idTutor = resultSet.getInt("idtipo2");
                        int idEntrenador = resultSet.getInt("idtipo3");

                        // Obtener nombre completo del alumno
                        String nombreCompletoAlumno = getNombreCompletoById(connection, "personal", idAlumno);

                        // Obtener nombres de tutor y entrenador
                        String nombreTutor = getTutorById(connection, idTutor);
                        String nombreEntrenador = getEntrenadorById(connection, idEntrenador);

                        data.add(new Object[] { nombreCompletoAlumno, nombreTutor, nombreEntrenador });
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los datos de relaciones familiares: " + e.getMessage());
        }

        return data;
    }

    private String getNombreCompletoById(Connection connection, String tableName, int id) throws SQLException {
        String sql = "SELECT nombre, apellido1, apellido2 FROM " + tableName + " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    String apellido1 = resultSet.getString("apellido1");
                    String apellido2 = resultSet.getString("apellido2");
                    // Concatenar nombre completo
                    return nombre + " " + apellido1 + " " + apellido2;
                }
            }
        }
        return null;
    }

    private String getTutorById(Connection connection, int id) throws SQLException {
        return getNombreCompletoById(connection, "personal", id);
    }

    private String getEntrenadorById(Connection connection, int id) throws SQLException {
        return getNombreCompletoById(connection, "personal", id);
    }

    public int getAlumnoId() {
        return alumnoId;
    }

}
