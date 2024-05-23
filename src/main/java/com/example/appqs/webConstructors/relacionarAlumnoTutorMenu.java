package com.example.appqs.webConstructors;

import com.example.appqs.AppQsApplication;
import com.example.appqs.actions.gridRelacionFamiliar;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class relacionarAlumnoTutorMenu extends HorizontalLayout {

    private Button toggleMenuButton;

    public relacionarAlumnoTutorMenu(AppQsApplication.MainUI mainUI, int alumnoId) {
        this.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

        toggleMenuButton = new Button("<<");

        // Configurar el listener para el botón toggleMenuButton
        toggleMenuButton.addClickListener(event -> mainUI.toggleMenuVisibility());

        // Crear el botón "Volver"
        Button volverButton = new Button("Volver");
        volverButton.addClickListener(event -> {
            // Crear la vista gridRelacionarAlumno y mostrarla en el contentPanel
            gridRelacionFamiliar gridRelacionAlumno = new gridRelacionFamiliar(alumnoId);
            mainUI.showView(gridRelacionAlumno);
        });

        // Crear el botón "Relacionar"
        Button relacionarButton = new Button("Relacionar");
        relacionarButton.addClickListener(event -> {
            // Obtener la ID del tutor seleccionado en la clase relacionarAlumnoTutor
            int tutorId = obtenerTutorSeleccionadoId();

            // Comprobar si se ha seleccionado un tutor
            if (tutorId != -1) {
                // Intentar realizar la relación en la base de datos
                boolean relacionCreada = crearRelacion(alumnoId, tutorId);
                if (relacionCreada) {
                    Notification.show("Relación creada exitosamente", Notification.Type.HUMANIZED_MESSAGE);
                } else {
                    Notification.show("La relación ya existe", Notification.Type.WARNING_MESSAGE);
                }
            } else {
                Notification.show("Por favor, seleccione un tutor", Notification.Type.WARNING_MESSAGE);
            }
        });

        addComponents(toggleMenuButton, relacionarButton, volverButton);
    }

    private int obtenerTutorSeleccionadoId() {
        // Aquí deberías implementar la lógica para obtener la ID del tutor seleccionado en la clase relacionarAlumnoTutor
        // Por ahora, devolveré un valor arbitrario
        return 1;
    }

    private boolean crearRelacion(int alumnoId, int tutorId) {
        String url = "jdbc:postgresql://localhost/QSoccer";
        String user = "QSUser";
        String password = "12345";
    
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO relaciones (idtipo1, idtipo2, idtipo3) VALUES (?, ?, NULL)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, alumnoId);
                statement.setInt(2, tutorId);
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
