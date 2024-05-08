package com.example.appqs.actions;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import com.vaadin.ui.Notification;
import com.example.appqs.dbconnections.formAlumnosToDb;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class formAlumnos extends VerticalLayout {

    private Button enviarButton;

    public formAlumnos() {
        // Crear los campos del formulario
        TextField nombreField = new TextField("Nombre");
        TextField apellido1Field = new TextField("Primer Apellido");
        TextField apellido2Field = new TextField("Segundo Apellido");
        DateField fechaNacimientoField = new DateField("Fecha de Nacimiento");
        TextField direccionField = new TextField("Dirección");
        TextField codigoPostalField = new TextField("Código Postal");
        TextField alergiasField = new TextField("Alergias");
        TextField colegioField = new TextField("Colegio");
        TextField equipoAnteriorField = new TextField("Equipo Anterior");

        // Crear el formulario
        FormLayout formLayout = new FormLayout();
        formLayout.addComponents(
                nombreField,
                apellido1Field,
                apellido2Field,
                fechaNacimientoField,
                direccionField,
                codigoPostalField,
                alergiasField,
                colegioField,
                equipoAnteriorField);

        // Crear el botón para enviar el formulario
        enviarButton = new Button("Enviar");

        // Agregar el listener para el evento del botón de enviar
        enviarButton.addClickListener(event -> {
            // Obtener los valores de los campos del formulario
            String nombre = nombreField.isEmpty() ? "" : nombreField.getValue();
            String apellido1 = apellido1Field.isEmpty() ? "" : apellido1Field.getValue();
            String apellido2 = apellido2Field.isEmpty() ? "" : apellido2Field.getValue();
            Date fechaNacimiento = fechaNacimientoField.isEmpty()
                    ? Date.from(LocalDate.of(1111, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant())
                    : Date.from(fechaNacimientoField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            String direccion = direccionField.isEmpty() ? "" : direccionField.getValue();
            int codigoPostal = codigoPostalField.isEmpty() ? 0 : Integer.parseInt(codigoPostalField.getValue());
            String alergias = alergiasField.isEmpty() ? "" : alergiasField.getValue();
            String colegio = colegioField.isEmpty() ? "" : colegioField.getValue();
            String equipoAnterior = equipoAnteriorField.isEmpty() ? "" : equipoAnteriorField.getValue();
            // Insertar los datos en la tabla PERSONAL
            int tipoPersona = 1; // Asignación inicial
            formAlumnosToDb.insertPersonalData(nombre, apellido1, apellido2, fechaNacimiento,
        direccion, codigoPostal, alergias, colegio, tipoPersona, equipoAnterior);

            // Limpiar los campos del formulario después de enviar los datos
            nombreField.clear();
            apellido1Field.clear();
            apellido2Field.clear();
            fechaNacimientoField.clear();
            direccionField.clear();
            codigoPostalField.clear();
            alergiasField.clear();
            colegioField.clear();
            equipoAnteriorField.clear();
        });

        // Agregar el formulario y el botón al diseño vertical
        addComponents(formLayout, enviarButton);
    }

    public formAlumnos(int id, String nombre, String apellido1, String apellido2,
                       Date fechaNacimiento, String direccion, int codigoPostal,
                       String alergias, String colegio, String equipoAnterior) {

        // Crear el campo para el ID
        TextField idField = new TextField("ID", String.valueOf(id));
        idField.setEnabled(false); // El campo de ID no es editable

        // Crear los campos del formulario y establecerles los valores recibidos
        TextField nombreField = new TextField("Nombre", nombre);
        TextField apellido1Field = new TextField("Primer Apellido", apellido1);
        TextField apellido2Field = new TextField("Segundo Apellido", apellido2);
        DateField fechaNacimientoField = new DateField("Fecha de Nacimiento");
        if (fechaNacimiento != null) {
            fechaNacimientoField.setValue(fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        TextField direccionField = new TextField("Dirección", direccion);
        TextField codigoPostalField = new TextField("Código Postal", String.valueOf(codigoPostal));
        TextField alergiasField = new TextField("Alergias", alergias);
        TextField colegioField = new TextField("Colegio", colegio);
        TextField equipoAnteriorField = new TextField("Equipo Anterior", equipoAnterior);

        // Crear el formulario
        FormLayout formLayout = new FormLayout();
        formLayout.addComponents(
                idField,
                nombreField,
                apellido1Field,
                apellido2Field,
                fechaNacimientoField,
                direccionField,
                codigoPostalField,
                alergiasField,
                colegioField,
                equipoAnteriorField);

        // Crear el botón para enviar el formulario
        enviarButton = new Button("Guardar");

        // Agregar el listener para el evento del botón de enviar
        enviarButton.addClickListener(event -> {
            // Actualizar los datos en la base de datos
            formAlumnosToDb.updatePersonalData(id, nombreField.getValue(), apellido1Field.getValue(), apellido2Field.getValue(),
                    Date.from(fechaNacimientoField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    direccionField.getValue(), Integer.parseInt(codigoPostalField.getValue()), alergiasField.getValue(),
                    colegioField.getValue(), equipoAnteriorField.getValue());
                // Mostrar mensaje de éxito
    Notification.show("Datos modificados con éxito", Notification.Type.HUMANIZED_MESSAGE);
        });

        // Agregar el formulario y el botón al diseño vertical
        addComponents(formLayout, enviarButton);
    }

    public void setEnviarButtonListener(Button.ClickListener listener) {
        // Configura el listener para el botón de enviar
        enviarButton.addClickListener(listener);
    }
}
