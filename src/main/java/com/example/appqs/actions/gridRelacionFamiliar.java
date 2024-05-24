package com.example.appqs.actions;

import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.example.appqs.dbconnections.buscaRelaciones;
import com.example.appqs.dbconnections.buscaRelaciones.Relacion;

import java.util.List;

public class gridRelacionFamiliar extends VerticalLayout {

    private int alumnoId;

    public gridRelacionFamiliar(int alumnoId) {
        this.alumnoId = alumnoId;
        mostrarRelaciones(); // Llama al método mostrarRelaciones
    }

    private void mostrarRelaciones() {
        List<Relacion> relaciones = buscaRelaciones.obtenerRelaciones(alumnoId);
        
        // Obtener el nombre completo del alumno
        String nombreCompletoAlumno = buscaRelaciones.obtenerNombreAlumno(alumnoId);

        // Crear el encabezado
        Label encabezado = new Label("Relaciones familiares del Alumno: " + nombreCompletoAlumno + " (" + alumnoId + ")");
        encabezado.setStyleName("h2"); // Añade un estilo a tu encabezado si quieres

        // Crear el grid
        Grid<Relacion> grid = new Grid<>();
        grid.setItems(relaciones);
        
        // Añadir columnas con títulos personalizados
        grid.addColumn(Relacion::getIdTipo2).setCaption("Id");
        grid.addColumn(Relacion::getNombreTipo2).setCaption("Tutor");

        // Añadir componentes al layout
        addComponent(encabezado);
        addComponent(grid);
    }

    public int getAlumnoId() {
        return alumnoId;
    }
}
