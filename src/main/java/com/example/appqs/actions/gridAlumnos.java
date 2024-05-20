package com.example.appqs.actions;

import com.example.appqs.AppQsApplication;
import com.example.appqs.dbconnections.dbToAlumnos;
import com.example.appqs.views.editAlumno;
import com.example.appqs.webConstructors.hideRegistro;

import java.util.List;
import com.vaadin.ui.*;
import java.util.Optional;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class gridAlumnos extends VerticalLayout {

    public gridAlumnos() {
        // Crea un Grid
        Grid<Object[]> grid = new Grid<>();

        // Tamaño del grid
        grid.setSizeFull();

        // Agregar una columna de acciones
        grid.addComponentColumn(row -> {
            HorizontalLayout layout = new HorizontalLayout();

            // Botón para editar (teclado)
            Button editButton = new Button("⌨");
            editButton.addClickListener(event -> {
                // Obtener el objeto de la fila seleccionada
                Object[] rowData = row;

                // Obtener el valor de la columna "ID"
                int idValue = (int) rowData[0]; // Suponiendo que el ID está en la primera columna

                // Realizar la conexión a la base de datos
                try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/QSoccer",
                        "QSUser", "12345")) {
                    // Preparar la consulta SQL
                    String sql = "SELECT nombre, apellido1, apellido2, fnacimiento, direccion, cpostal, alergias, colegio, anterior FROM personal WHERE id = ?";
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setInt(1, idValue);
                        try (ResultSet resultSet = statement.executeQuery()) {
                            // Procesar los resultados
                            if (resultSet.next()) {
                                String nombre = resultSet.getString("nombre");
                                String apellido1 = resultSet.getString("apellido1");
                                String apellido2 = resultSet.getString("apellido2");
                                Date fnacimientoSql = resultSet.getDate("fnacimiento");
                                java.util.Date fnacimiento = fnacimientoSql != null ? new java.util.Date(fnacimientoSql.getTime()) : null;
                                String direccion = resultSet.getString("direccion");
                                int cpostal = resultSet.getInt("cpostal");
                                String alergias = resultSet.getString("alergias");
                                String colegio = resultSet.getString("colegio");
                                String anterior = resultSet.getString("anterior");

                                // Crear la vista editAlumno y enviar los datos
                                editAlumno editAlumnoView = new editAlumno(idValue, nombre, apellido1, apellido2, fnacimiento,
                                        direccion, cpostal, alergias, colegio, anterior);
                                Optional<UI> currentUI = Optional.ofNullable(UI.getCurrent());
                                currentUI.ifPresent(ui -> {
                                    if (ui instanceof AppQsApplication.MainUI) {
                                        ((AppQsApplication.MainUI) ui).showView(editAlumnoView);
                                    }
                                });
                            } else {
                                System.out.println("No se encontraron datos para el ID proporcionado.");
                            }
                        }
                    }
                } catch (SQLException e) {
                    System.err.println("Error al realizar la consulta a la base de datos: " + e.getMessage());
                }
            });

            layout.addComponent(editButton);

            // Botón para eliminar (borrar)
            Button deleteButton = new Button("⌫");
            deleteButton.setData(row); // Guardar los datos de la fila asociada al botón
            hideRegistro.attachHideListener(deleteButton, grid);
            layout.addComponent(deleteButton);

            // Botón para ver relaciones familiares
            Button familyButton = new Button("👨‍👩‍👧‍👦");
            familyButton.addClickListener(event -> {
                // Lógica para ver relaciones familiares
            });
            layout.addComponent(familyButton);

            return layout;
        }).setCaption("Acciones");

        // Agregar columnas al Grid con los nombres de las columnas de la db

        grid.addColumn(row -> row[0]).setCaption("ID");
        grid.addColumn(row -> row[1]).setCaption("Nombre");
        grid.addColumn(row -> row[2]).setCaption("Apellido 1");
        grid.addColumn(row -> row[3]).setCaption("Apellido 2");
        grid.addColumn(row -> row[4]).setCaption("Fecha de Nacimiento");
        grid.addColumn(row -> row[5]).setCaption("Dirección");
        grid.addColumn(row -> row[6]).setCaption("Código Postal");
        grid.addColumn(row -> row[7]).setCaption("Alergias");
        grid.addColumn(row -> row[8]).setCaption("Colegio");
        grid.addColumn(row -> row[9]).setCaption("Equipo Anterior");

        // Obtener los datos de la base de datos
        List<Object[]> alumnosData = dbToAlumnos.getAllPersonalData();

        // Agregar los datos al Grid
        grid.setItems(alumnosData);

        // Agregar el Grid al diseño vertical
        addComponent(grid);

        // Ajustar el tamaño del Grid al espacio disponible
        setExpandRatio(grid, 1);
    }
}
