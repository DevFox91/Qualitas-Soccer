package com.example.appqs.webConstructors;

import com.example.appqs.AppQsApplication;
import com.example.appqs.AppQsApplication.MainUI;
import com.example.appqs.actions.formAlumnos;
import com.example.appqs.views.Alumnos;
import com.example.appqs.views.editAlumno;
import com.example.appqs.dbconnections.formAlumnosToDb;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

import java.time.LocalDate;
import java.sql.Date;

public class editAlumnoMenu extends HorizontalLayout {

    private Button toggleMenuButton;
    private Button enviarDatosButton; // Botón para enviar los datos del formulario a la base de datos

    public editAlumnoMenu(MainUI mainUI, editAlumno editAlumno, formAlumnosToDb pushSender) {
        this.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

        // Obtener el formulario dentro de Inscripcion
        formAlumnos form = editAlumno.getFormAlumnos();

        toggleMenuButton = new Button("<<");
        enviarDatosButton = new Button("Guardar datos");

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

        // Comportamiento al hacer clic en el botón "Enviar Datos"
        enviarDatosButton.addClickListener(event -> {
            try {
                // Obtener los valores del formulario y convertirlos dentro de la llamada a
                // updatePersonalData
                Integer id = form.getIdField();
                String nombre = form.getNombreField().getValue();
                String apellido1 = form.getApellido1Field().getValue();
                String apellido2 = form.getApellido2Field().getValue();
                LocalDate fechaNacimientoLocal = form.getFechaNacimientoField().getValue();
                Date fechaNacimiento = (fechaNacimientoLocal != null) ? Date.valueOf(fechaNacimientoLocal) : null;
                String direccion = form.getDireccionField().getValue();
                String codigoPostalString = form.getCodigoPostalField().getValue();
                int codigoPostal = 0; // Valor predeterminado o un valor que tenga sentido en tu aplicación
                if (codigoPostalString != null && !codigoPostalString.isEmpty()) {
                    codigoPostal = Integer.parseInt(codigoPostalString);
                } else {
                    System.err.println("El campo Código Postal está vacío o es nulo");
                }
                String alergias = form.getAlergiasField().getValue();
                String colegio = form.getColegioField().getValue();
                String equipoAnterior = form.getEquipoAnteriorField().getValue();

                // Imprimir los valores obtenidos para depuración
                System.out.println("ID: " + id);
                System.out.println("Nombre: " + nombre);
                System.out.println("Apellido1: " + apellido1);
                System.out.println("Apellido2: " + apellido2);
                System.out.println("Fecha de Nacimiento: " + fechaNacimiento);
                System.out.println("Dirección: " + direccion);
                System.out.println("Código Postal: " + codigoPostal);
                System.out.println("Alergias: " + alergias);
                System.out.println("Colegio: " + colegio);
                System.out.println("Equipo Anterior: " + equipoAnterior);

                // Enviar los datos al método updatePersonalData
                formAlumnosToDb.updatePersonalData(id, nombre, apellido1, apellido2, fechaNacimiento,
                        direccion, codigoPostal, alergias, colegio, equipoAnterior);
            } catch (NumberFormatException e) {
                // Manejar la excepción en caso de que la conversión de ID o código postal falle
                e.printStackTrace();
                System.err.println("Error en la conversión de un número: " + e.getMessage());
            }
            Notification.show("Datos modificados con éxito", Notification.Type.HUMANIZED_MESSAGE);

            UI currentUI = UI.getCurrent();
            if (currentUI instanceof AppQsApplication.MainUI) {
                ((AppQsApplication.MainUI) currentUI).showView(new Alumnos());
            }
        });

        addComponents(toggleMenuButton, enviarDatosButton, volverButton);
    }
}