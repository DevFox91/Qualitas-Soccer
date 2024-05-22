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
import java.util.ArrayList;
import java.util.List;

public class relacionFamiliarMenu extends HorizontalLayout {

    private Button toggleMenuButton;

    public relacionFamiliarMenu(AppQsApplication.MainUI mainUI) {
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
            // Obtener el nombre del alumno
            String nombreAlumno = obtenerNombreAlumno();

            // Obtener tutores disponibles
            List<String> tutoresDisponibles = obtenerTutoresDisponibles();

            // Crear la vista RelacionarAlumnoTutor y mostrarla en el contentPanel
            relacionarAlumnoTutor relacionarAlumnoTutorView = new relacionarAlumnoTutor(nombreAlumno, tutoresDisponibles);
            UI currentUI = UI.getCurrent();
            if (currentUI instanceof AppQsApplication.MainUI) {
                ((AppQsApplication.MainUI) currentUI).showView(relacionarAlumnoTutorView);
            }
        });

        addComponents(toggleMenuButton, relacionarButton, volverButton);
    }

    // Método para obtener el nombre del alumno
    private String obtenerNombreAlumno() {
        // Implementa la lógica para obtener el nombre del alumno
        // Puedes acceder a la base de datos u otro origen de datos para obtener esta información
        return "Nombre del Alumno"; // Ejemplo de nombre de alumno
    }

    // Método para obtener los tutores disponibles
    private List<String> obtenerTutoresDisponibles() {
        List<String> tutoresDisponibles = new ArrayList<>();
        
        // Conectar a la base de datos y obtener los nombres completos de los tutores disponibles
        String url = "jdbc:postgresql://localhost/QSoccer";
        String user = "QSUser";
        String password = "12345";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT nombre, apellido1, apellido2 FROM personal WHERE tipopersona = 2 AND delete = false";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String nombreCompleto = resultSet.getString("nombre") + " " +
                                               resultSet.getString("apellido1") + " " +
                                               resultSet.getString("apellido2");
                        tutoresDisponibles.add(nombreCompleto);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tutoresDisponibles;
    }
}
