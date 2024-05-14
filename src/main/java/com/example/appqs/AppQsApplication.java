package com.example.appqs;

import com.example.appqs.views.Alumnos;
import com.example.appqs.views.Personal;
import com.example.appqs.views.Tutores;
import com.example.appqs.webConstructors.MenuSuperior;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppQsApplication {
    private static Panel contentPanel; // Define contentPanel como un campo de la clase

    public static void main(String[] args) {
        SpringApplication.run(AppQsApplication.class, args);
    }

    @StyleSheet("/com/example/appqs/CSS/styles.css")
    @SpringUI
    public static class MainUI extends UI {

        @Override
        protected void init(VaadinRequest request) {
            VerticalLayout mainLayout = new VerticalLayout();
            mainLayout.setSizeFull();
            setContent(mainLayout);

            // Crear y agregar el menú superior
            MenuSuperior menuSuperior = new MenuSuperior();
            mainLayout.addComponent(menuSuperior);

            // Configurar listeners de los botones del menú
            menuSuperior.setAlumnosButtonListener(menuItem -> showView(new Alumnos()));
            menuSuperior.setTutoresButtonListener(menuItem -> showView(new Tutores()));
            menuSuperior.setPersonalButtonListener(menuItem -> showView(new Personal()));

            if (contentPanel == null) {
                contentPanel = new Panel(); // Inicializa contentPanel
                mainLayout.addComponent(contentPanel);
                mainLayout.setExpandRatio(contentPanel, 1.0f);
            }

            // Por defecto, muestra la vista de Alumnos al iniciar la aplicación
            showView(new Alumnos());
        }

        public void showView(VerticalLayout view) {
            if (view != null) {
                if (contentPanel != null) {
                    // Reemplaza el contenido con la nueva vista
                    contentPanel.setContent(view);

                } else {
                    System.out.println("La vista es null");
                }
            }
        }
    }
}
