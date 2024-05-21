package com.example.appqs;

import com.example.appqs.dbconnections.formAlumnosToDb;
import com.example.appqs.views.Alumnos;
import com.example.appqs.views.Inscripcion;
import com.example.appqs.views.Personal;
import com.example.appqs.views.Tutores;
import com.example.appqs.views.editAlumno;
import com.example.appqs.webConstructors.AlumnosMenu;
import com.example.appqs.webConstructors.TutoresMenu;
import com.example.appqs.webConstructors.editAlumnoMenu;
import com.example.appqs.webConstructors.inscripcionMenu;
import com.example.appqs.webConstructors.leftMenu;
import com.example.appqs.webConstructors.pushEnviarAlumno;
import com.vaadin.annotations.Theme;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Theme("themeQSoccer")
public class AppQsApplication {

    private static final String CONTENT_PANEL_ATTRIBUTE = "contentPanel";
    private static final String CURRENT_VIEW_ATTRIBUTE = "currentView";

    public static void main(String[] args) {
        SpringApplication.run(AppQsApplication.class, args);
    }

    @SpringUI
    public static class MainUI extends UI {

        private HorizontalLayout mainLayout;
        private VerticalLayout menuLayout;
        private VerticalLayout menuActionsLayout;
        private boolean isMenuVisible = true;

        public String getCurrentViewNames() {
            return (String) VaadinSession.getCurrent().getAttribute(CURRENT_VIEW_ATTRIBUTE);
        }

        @Override
        protected void init(VaadinRequest request) {
            Page.getCurrent().getStyles().add(".menu-button { margin-left: -10px; width: 200px;}");

            mainLayout = new HorizontalLayout();
            mainLayout.setSizeFull();
            setContent(mainLayout);

            menuLayout = new VerticalLayout();
            menuLayout.setWidth("300px");
            mainLayout.addComponent(menuLayout);

            VerticalLayout contentLayout = new VerticalLayout();
            contentLayout.setSizeFull();
            mainLayout.addComponent(contentLayout);
            mainLayout.setExpandRatio(contentLayout, 1.0f);

            menuActionsLayout = new VerticalLayout();
            menuActionsLayout.setWidth("100%");
            contentLayout.addComponent(menuActionsLayout);

            Panel contentPanel = (Panel) VaadinSession.getCurrent().getAttribute(CONTENT_PANEL_ATTRIBUTE);
            if (contentPanel == null) {
                contentPanel = new Panel();
                VaadinSession.getCurrent().setAttribute(CONTENT_PANEL_ATTRIBUTE, contentPanel);
            }

            contentLayout.addComponent(contentPanel);
            contentLayout.setExpandRatio(contentPanel, 1.0f);

            leftMenu leftMenu = new leftMenu();
            menuLayout.addComponent(leftMenu);

            leftMenu.setAlumnosButtonListener(menuItem -> showView(new Alumnos()));
            leftMenu.setTutoresButtonListener(menuItem -> showView(new Tutores()));
            leftMenu.setPersonalButtonListener(menuItem -> showView(new Personal()));

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
                    updateMenu(view);
                } else {
                    System.out.println("El contentPanel es null");
                }
            }
        }

        private void updateMenu(VerticalLayout view) {
            menuActionsLayout.removeAllComponents();
            String currentViewName = getCurrentViewName();
            if ("Alumnos".equals(currentViewName)) {
                AlumnosMenu alumnosMenu = new AlumnosMenu(this);
                menuActionsLayout.addComponent(alumnosMenu);
            } else if ("Tutores".equals(currentViewName)) {
                TutoresMenu tutoresMenu = new TutoresMenu(this);
                menuActionsLayout.addComponent(tutoresMenu);
            } else if ("Inscripcion".equals(currentViewName)) {
                pushEnviarAlumno pushSender = new pushEnviarAlumno();
                inscripcionMenu menuInscripcion = new inscripcionMenu(this, (Inscripcion) view, pushSender);
                menuActionsLayout.addComponent(menuInscripcion);
            } else if ("editAlumno".equals(currentViewName)) { // Verifica si la vista actual es editAlumno
                // Crea una instancia del menú editAlumnoMenu y agrégalo al layout
                formAlumnosToDb pushSender = new formAlumnosToDb();
                editAlumnoMenu editAlumnoMenu = new editAlumnoMenu(this, (editAlumno) view, pushSender);
                menuActionsLayout.addComponent(editAlumnoMenu);
            }
        }
        

        private String getCurrentViewName() {
            return (String) VaadinSession.getCurrent().getAttribute(CURRENT_VIEW_ATTRIBUTE);
        }

        public void toggleMenuVisibility() {
            if (isMenuVisible) {
                mainLayout.removeComponent(menuLayout);
            } else {
                mainLayout.addComponent(menuLayout, 0);
            }
            isMenuVisible = !isMenuVisible;
        }
    }
}
