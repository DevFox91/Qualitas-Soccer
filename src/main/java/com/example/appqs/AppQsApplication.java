package com.example.appqs;

import com.example.appqs.actions.gridRelacionFamiliar;
import com.example.appqs.dbconnections.formAlumnosToDb;
import com.example.appqs.dbconnections.formTutoresToDb;
import com.example.appqs.views.Alumnos;
import com.example.appqs.views.FTutor;
import com.example.appqs.views.Inscripcion;
import com.example.appqs.views.Personal;
import com.example.appqs.views.Tutores;
import com.example.appqs.views.editAlumno;
import com.example.appqs.views.editTutor;
import com.example.appqs.views.relacionarAlumnoTutor;
import com.example.appqs.webConstructors.AlumnosMenu;
import com.example.appqs.webConstructors.FTutorMenu;
import com.example.appqs.webConstructors.TutoresMenu;
import com.example.appqs.webConstructors.editAlumnoMenu;
import com.example.appqs.webConstructors.editTutorMenu;
import com.example.appqs.webConstructors.inscripcionMenu;
import com.example.appqs.webConstructors.leftMenu;
import com.example.appqs.webConstructors.pushEnviarAlumno;
import com.example.appqs.webConstructors.pushEnviarTutor;
import com.example.appqs.webConstructors.relacionarAlumnoTutorMenu;
import com.example.appqs.webConstructors.gridRelacionFamiliarMenu;
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
    private static final String ALUMNO_ID_ATTRIBUTE = "alumnoId";

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
    // Verifica si la vista recibida no es nula
    if (view != null) {
        // Obtiene el panel de contenido actual de la sesión de Vaadin
        Panel contentPanel = (Panel) VaadinSession.getCurrent().getAttribute(CONTENT_PANEL_ATTRIBUTE);
        // Verifica si el panel de contenido no es nulo
        if (contentPanel != null) {
            // Establece la vista recibida como el contenido del panel
            contentPanel.setContent(view);
            // Guarda el nombre de la clase de la vista actual en un atributo de la sesión
            VaadinSession.getCurrent().setAttribute(CURRENT_VIEW_ATTRIBUTE, view.getClass().getSimpleName());
            // Verifica si la vista es una instancia de gridRelacionFamiliar
            if (view instanceof gridRelacionFamiliar) {
                // Realiza acciones específicas si la vista es de ese tipo
                gridRelacionFamiliar relacionFamiliarView = (gridRelacionFamiliar) view;
                // Guarda el ID del alumno de la vista en un atributo de la sesión
                VaadinSession.getCurrent().setAttribute(ALUMNO_ID_ATTRIBUTE, relacionFamiliarView.getAlumnoId());
            } else if (view instanceof relacionarAlumnoTutor) {
                // Realiza acciones específicas si la vista es de tipo relacionarAlumnoTutor
                // Obtiene el alumnoId y el tutorId
                // Hacer lo que necesites con alumnoId y tutorId
            }
            // Actualiza el menú de la aplicación de acuerdo con la vista actual
            updateMenu(view);
        } else {
            // Imprime un mensaje si el panel de contenido es nulo
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
            } else if ("FTutor".equals(currentViewName)) {
                pushEnviarTutor pushSender = new pushEnviarTutor();
                FTutorMenu menuFTutor = new FTutorMenu(this, (FTutor) view, pushSender);
                menuActionsLayout.addComponent(menuFTutor);
            } else if ("editAlumno".equals(currentViewName)) {
                formAlumnosToDb pushSender = new formAlumnosToDb();
                editAlumnoMenu editAlumnoMenu = new editAlumnoMenu(this, (editAlumno) view, pushSender);
                menuActionsLayout.addComponent(editAlumnoMenu);
            } else if ("editTutor".equals(currentViewName)) {
                formTutoresToDb pushSender = new formTutoresToDb();
                editTutorMenu editTutorMenu = new editTutorMenu(this, (editTutor) view, pushSender);
                menuActionsLayout.addComponent(editTutorMenu);
            } else if ("gridRelacionFamiliar".equals(currentViewName)) {
                Integer alumnoId = (Integer) VaadinSession.getCurrent().getAttribute(ALUMNO_ID_ATTRIBUTE);
                gridRelacionFamiliarMenu relacionFamiliarMenu = new gridRelacionFamiliarMenu(this, alumnoId != null ? alumnoId : -1);
                menuActionsLayout.addComponent(relacionFamiliarMenu);
            } else if ("relacionarAlumnoTutor".equals(currentViewName)) {
                relacionarAlumnoTutor relacionarAlumnoTutorView = (relacionarAlumnoTutor) view;
                relacionarAlumnoTutorMenu relacionarAlumnoTutorMenu = new relacionarAlumnoTutorMenu(this, relacionarAlumnoTutorView);
                menuActionsLayout.addComponent(relacionarAlumnoTutorMenu);
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
