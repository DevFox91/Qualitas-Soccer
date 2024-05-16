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

    private static final String CONTENT_PANEL_ATTRIBUTE = "contentPanel"; 
    private static final String CURRENT_VIEW_ATTRIBUTE = "currentView";

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

            MenuSuperior menuSuperior = new MenuSuperior();
            mainLayout.addComponent(menuSuperior);

            menuSuperior.setAlumnosButtonListener(menuItem -> showView(new Alumnos()));
            menuSuperior.setTutoresButtonListener(menuItem -> showView(new Tutores()));
            menuSuperior.setPersonalButtonListener(menuItem -> showView(new Personal()));

            Panel contentPanel = (Panel) VaadinSession.getCurrent().getAttribute(CONTENT_PANEL_ATTRIBUTE);
            if (contentPanel == null) {
                contentPanel = new Panel();
                VaadinSession.getCurrent().setAttribute(CONTENT_PANEL_ATTRIBUTE, contentPanel);
            }

            mainLayout.addComponent(contentPanel);
            mainLayout.setExpandRatio(contentPanel, 1.0f);

            // Cargar la vista previa si está disponible, de lo contrario, cargar Alumnos
            String currentViewName = (String) VaadinSession.getCurrent().getAttribute(CURRENT_VIEW_ATTRIBUTE);
            if (currentViewName != null) {
                try {
                    Class<?> viewClass = Class.forName("com.example.appqs.views." + currentViewName);
                    showView((VerticalLayout) viewClass.getDeclaredConstructor().newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                showView(new Alumnos());
            }
        }

        public void showView(VerticalLayout view) {
            if (view != null) {
                Panel contentPanel = (Panel) VaadinSession.getCurrent().getAttribute(CONTENT_PANEL_ATTRIBUTE);
                if (contentPanel != null) {
                    contentPanel.setContent(view);
                    VaadinSession.getCurrent().setAttribute(CURRENT_VIEW_ATTRIBUTE, view.getClass().getSimpleName());
                } else {
                    System.out.println("El contentPanel es null");
                }
            }
        }
    }
}
