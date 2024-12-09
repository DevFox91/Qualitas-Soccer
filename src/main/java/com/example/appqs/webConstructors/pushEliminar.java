package com.example.appqs.webConstructors;

import com.example.appqs.dbconnections.dbToTutores;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class pushEliminar {

    public static void attachDeleteListener(Button deleteButton, Grid<Object[]> grid) {
        deleteButton.addClickListener(event -> {
            Object[] rowData = (Object[]) deleteButton.getData(); // Obtener los datos de la fila asociada al botón
            
            // Obtener el ID del registro que se va a eliminar
            int idValue = (int) rowData[0];

            // Realizar la conexión a la base de datos y ejecutar la consulta para eliminar el registro
            try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/QSoccer",
                    "QSUser", "12345")) {
                // Preparar la consulta SQL para eliminar el registro con el ID específico
                String sql = "DELETE FROM personal WHERE id = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, idValue);

                    // Ejecutar la consulta para eliminar el registro
                    int rowsDeleted = statement.executeUpdate();

                    // Verificar si se eliminó correctamente el registro
                    if (rowsDeleted > 0) {
                        // Eliminación exitosa, actualizar la vista del Grid
                        grid.setItems(dbToTutores.getAllPersonalData());

                        // Mostrar mensaje de éxito
                        Notification.show("Registro eliminado correctamente", Notification.Type.HUMANIZED_MESSAGE);
                    } else {
                        // No se pudo eliminar el registro, mostrar mensaje de error
                        Notification.show("No se pudo eliminar el registro", Notification.Type.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException e) {
                // Manejar cualquier error de SQL
                System.err.println("Error al eliminar el registro: " + e.getMessage());
                Notification.show("Error al eliminar el registro", Notification.Type.ERROR_MESSAGE);
            }
        });
    }
}
