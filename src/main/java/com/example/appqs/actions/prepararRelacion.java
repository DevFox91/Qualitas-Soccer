package com.example.appqs.actions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.vaadin.ui.Notification;


public class prepararRelacion {

    public static void prepararRelacionIds(int idtipo1, Integer idtipo2) {
        String URL = "jdbc:postgresql://localhost/QSoccer";
        String USER = "QSUser";
        String PASSWORD = "12345";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Comprobar si ya existe una fila con esos datos
            String query = "SELECT COUNT(*) FROM relaciones WHERE idtipo1 = ? AND idtipo2 = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, idtipo1);
                statement.setInt(2, idtipo2 != null ? idtipo2 : 0); // Si idtipo2 es null, usar 0
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) > 0) {
                        Notification.show("Ya existe una fila con esos datos en la tabla relaciones", Notification.Type.WARNING_MESSAGE);
                        return; // Salir del método si la fila ya existe
                    }
                }
            }

            // Insertar una nueva fila con los datos proporcionados
            query = "INSERT INTO relaciones (idtipo1, idtipo2, idtipo3) VALUES (?, ?, null)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, idtipo1);
                statement.setInt(2, idtipo2 != null ? idtipo2 : 0); // Si idtipo2 es null, usar 0
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    Notification.show("Se ha creado una nueva fila en la tabla relaciones", Notification.Type.HUMANIZED_MESSAGE);
                } else {
                    Notification.show("No se ha podido crear una nueva fila en la tabla relaciones", Notification.Type.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            Notification.show("Error al conectar con la base de datos: " + e.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }
}