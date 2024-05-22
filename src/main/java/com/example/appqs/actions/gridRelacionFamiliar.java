package com.example.appqs.actions;

import com.example.appqs.webConstructors.hideRegistro;


import com.vaadin.ui.*;


public class gridRelacionFamiliar extends VerticalLayout {

    public gridRelacionFamiliar() {
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

        grid.addColumn(row -> row[0]).setCaption("Alumno");
        grid.addColumn(row -> row[1]).setCaption("Tutor");
        grid.addColumn(row -> row[2]).setCaption("Entrenador");

        // Agregar el Grid al diseño vertical
        addComponent(grid);

        // Ajustar el tamaño del Grid al espacio disponible
        setExpandRatio(grid, 1);
    }
}
