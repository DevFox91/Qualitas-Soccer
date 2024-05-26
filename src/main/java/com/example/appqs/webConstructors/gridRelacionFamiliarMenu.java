package com.example.appqs.webConstructors;

import com.example.appqs.AppQsApplication;
import com.example.appqs.views.Alumnos;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
            // Imprimir la ID del alumno por pantalla
            System.out.println("ID del Alumno: " + alumnoId);

            // Obtener y mostrar la lista de personas con tipopersona = 2
            List<Personal> personas = obtenerPersonas();

            // Imprimir la lista por pantalla
            for (Personal persona : personas) {
                System.out.println("ID: " + persona.getId() + " | Nombre: " + persona.getNombre());
            }
        });

        addComponents(toggleMenuButton, relacionarButton, volverButton);
    }

    private List<Personal> obtenerPersonas() {
        List<Personal> personas = new ArrayList<>();

        String url = "jdbc:postgresql://localhost/QSoccer";
        String user = "QSUser";
        String password = "12345";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, nombre, apellido1, apellido2 FROM personal WHERE tipopersona = 2 AND delete = false")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre") + " " + rs.getString("apellido1") + " " + rs.getString("apellido2");
                personas.add(new Personal(id, nombre));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return personas;
    }

    public static class Personal {
        private int id;
        private String nombre;

        public Personal(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public int getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }
    }
}
