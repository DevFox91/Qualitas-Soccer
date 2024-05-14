package com.example.appqs.webConstructors;

import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class hideRegistro {

    public static void attachHideListener(Button button, Grid<Object[]> grid) {
        button.addClickListener(event -> {
            Object[] rowData = (Object[]) button.getData();
            int idValue = (int) rowData[0]; // Suponiendo que el ID está en la primera columna

            // Realizar la conexión a la base de datos
            try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/QSoccer",
                    "QSUser", "12345")) {
                // Preparar la consulta SQL
                String sql = "UPDATE personal SET delete = true WHERE id = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, idValue);
                    int affectedRows = statement.executeUpdate();
                    if (affectedRows > 0) {
                        // Actualización exitosa
                        grid.getDataProvider().refreshItem(rowData);
                    } else {
                        System.out.println("No se encontró ningún registro con el ID proporcionado.");
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al actualizar el registro en la base de datos: " + e.getMessage());
            }
        });
    }
}
