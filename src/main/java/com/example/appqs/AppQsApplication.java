package com.example.appqs;

import com.example.appqs.views.Alumnos;
import com.example.appqs.views.Personal;
import com.example.appqs.views.Tutores;
import com.example.appqs.webConstructors.AlumnosMenu;
import com.example.appqs.webConstructors.TutoresMenu;
import com.example.appqs.webConstructors.leftMenu;
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

    // Atributos para almacenar el panel de contenido y el nombre de la vista actual
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

        @Override
        protected void init(VaadinRequest request) {
            // Ajustes de estilo para los botones del menú
            Page.getCurrent().getStyles().add(".menu-button { margin-left: -10px; width: 200px;}");

            // Layout principal horizontal
            mainLayout = new HorizontalLayout();
            mainLayout.setSizeFull();
            setContent(mainLayout);

            // Layout vertical para el menú
            menuLayout = new VerticalLayout();
            menuLayout.setWidth("300px"); // Ajusta el ancho del menú según tus necesidades
            mainLayout.addComponent(menuLayout);

            // Capa adicional para ocupar el espacio restante
            VerticalLayout contentLayout = new VerticalLayout();
            contentLayout.setSizeFull(); // Hace que la capa ocupe todo el espacio disponible
            mainLayout.addComponent(contentLayout);
            mainLayout.setExpandRatio(contentLayout, 1.0f); // Hace que la capa ocupe todo el espacio restante

            // Capa para las acciones de menú
            menuActionsLayout = new VerticalLayout();
            menuActionsLayout.setWidth("100%"); // Ajusta el ancho según tus necesidades
            contentLayout.addComponent(menuActionsLayout);

            // Crear o recuperar el panel de contenido
            Panel contentPanel = (Panel) VaadinSession.getCurrent().getAttribute(CONTENT_PANEL_ATTRIBUTE);
            if (contentPanel == null) {
                contentPanel = new Panel();
                VaadinSession.getCurrent().setAttribute(CONTENT_PANEL_ATTRIBUTE, contentPanel);
            }

            // Agregar el panel de contenido a la capa contentLayout
            contentLayout.addComponent(contentPanel);
            contentLayout.setExpandRatio(contentPanel, 1.0f); // Hace que el panel de contenido ocupe todo el espacio restante

            // Crear el menú lateral
            leftMenu leftMenu = new leftMenu();
            menuLayout.addComponent(leftMenu);

            // Configurar listeners para los botones del menú
            leftMenu.setAlumnosButtonListener(menuItem -> showView(new Alumnos()));
            leftMenu.setTutoresButtonListener(menuItem -> showView(new Tutores()));
            leftMenu.setPersonalButtonListener(menuItem -> showView(new Personal()));

            // Cargar la vista previa si está disponible, de lo contrario, cargar Alumnos
            String currentViewName = (String) VaadinSession.getCurrent().getAttribute(CURRENT_VIEW_ATTRIBUTE);
            if (currentViewName != null) {
                try {
                    // Intentar cargar la clase de vista previa
                    Class<?> viewClass = Class.forName("com.example.appqs.views." + currentViewName);
                    showView((VerticalLayout) viewClass.getDeclaredConstructor().newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // Si no hay vista previa, cargar la vista de Alumnos
                showView(new Alumnos());
            }
        }

        // Método para cambiar la vista de contenido
        public void showView(VerticalLayout view) {
            if (view != null) {
                Panel contentPanel = (Panel) VaadinSession.getCurrent().getAttribute(CONTENT_PANEL_ATTRIBUTE);
                if (contentPanel != null) {
                    // Establecer el contenido del panel como la nueva vista
                    contentPanel.setContent(view);
                    VaadinSession.getCurrent().setAttribute(CURRENT_VIEW_ATTRIBUTE, view.getClass().getSimpleName());

                    // Actualizar el menú de acciones según la vista actual
                    updateMenu();
                } else {
                    // Manejar el caso en que el panel de contenido es nulo
                    System.out.println("El contentPanel es null");
                }
            }
        }

        // Método para actualizar el menú de acciones según la vista actual
        private void updateMenu() {
            // Limpiar el menú de acciones actual
            menuActionsLayout.removeAllComponents();

            // Crear el menú de acciones correspondiente a la vista actual
            String currentViewName = getCurrentViewName();
            if ("Alumnos".equals(currentViewName)) {
                AlumnosMenu alumnosMenu = new AlumnosMenu(this);
                menuActionsLayout.addComponent(alumnosMenu);
            } else if ("Tutores".equals(currentViewName)) {
                TutoresMenu tutoresMenu = new TutoresMenu(this);
                menuActionsLayout.addComponent(tutoresMenu);
            }
        }

        // Método para obtener el nombre de la vista actual
        private String getCurrentViewName() {
            return (String) VaadinSession.getCurrent().getAttribute(CURRENT_VIEW_ATTRIBUTE);
        }

        // Método para alternar la visibilidad del menú
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
