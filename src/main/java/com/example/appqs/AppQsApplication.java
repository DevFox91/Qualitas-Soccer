package com.example.appqs;

import com.example.appqs.views.Alumnos;
import com.example.appqs.views.Personal;
import com.example.appqs.views.Tutores;
import com.example.appqs.webConstructors.MenuSuperior;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppQsApplication {
    private static final String CONTENT_PANEL_ATTRIBUTE = "contentPanel"; // Define el nombre del atributo de la sesión para el contentPanel

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

            // Obtener o inicializar el contentPanel desde la sesión del usuario
            Panel contentPanel = (Panel) VaadinSession.getCurrent().getAttribute(CONTENT_PANEL_ATTRIBUTE);
            if (contentPanel == null) {
                contentPanel = new Panel();
                VaadinSession.getCurrent().setAttribute(CONTENT_PANEL_ATTRIBUTE, contentPanel);
            }

            // Agregar contentPanel al layout principal
            mainLayout.addComponent(contentPanel);
            mainLayout.setExpandRatio(contentPanel, 1.0f);

            // Por defecto, muestra la vista de Alumnos al iniciar la aplicación
            showView(new Alumnos());
            
        }

        public void showView(VerticalLayout view) {
            if (view != null) {
                Panel contentPanel = (Panel) VaadinSession.getCurrent().getAttribute(CONTENT_PANEL_ATTRIBUTE);
                if (contentPanel != null) {
                    // Reemplazar el contenido con la nueva vista
                    contentPanel.setContent(view);
                } else {
                    System.out.println("El contentPanel es null");
                }
            }
        }
    }
}
