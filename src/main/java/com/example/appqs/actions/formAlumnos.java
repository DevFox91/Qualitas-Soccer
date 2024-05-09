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
    // Defino las clases necesarias para los métodos 
    private Button enviarButton;
    private Button guardarButton;
    private TextField nombreField;
    private TextField apellido1Field;
    private TextField apellido2Field;
    private DateField fechaNacimientoField;
    private TextField direccionField;
    private TextField codigoPostalField;
    private TextField alergiasField;
    private TextField colegioField;
    private TextField equipoAnteriorField;
    private int id;

    //Primer constructor, no se le pasan parámetros porque construye formulario vacío para añadir un nuevo alumno.
    public formAlumnos() {
        // Crear los campos del formulario
        this.nombreField = new TextField("Nombre");
        this.apellido1Field = new TextField("Primer Apellido");
        this.apellido2Field = new TextField("Segundo Apellido");
        this.fechaNacimientoField = new DateField("Fecha de Nacimiento");
        this.direccionField = new TextField("Dirección");
        this.codigoPostalField = new TextField("Código Postal");
        this.alergiasField = new TextField("Alergias");
        this.colegioField = new TextField("Colegio");
        this.equipoAnteriorField = new TextField("Equipo Anterior");

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

        // Llamada al método que crea un botón "enviar"
        createButtonEnviar();

        // Llamada al método que lee los datos del formulario de añadir un nuevo Alumno
        leerFormularioEnviarAlumno();

        // Agregar el formulario y el botón al diseño vertical
        addComponents(formLayout, enviarButton);
    }

    //Segundo constructor, se trae los datos de la base de datos, según la id del alumno sobre la cual ejecutamos
    public formAlumnos(int id, String nombre, String apellido1, String apellido2,
            Date fechaNacimiento, String direccion, int codigoPostal,
            String alergias, String colegio, String equipoAnterior) {

        // Crear el campo para el ID
        this.id = id;
        TextField idField = new TextField("ID", String.valueOf(id));
        idField.setEnabled(false); // El campo de ID no es editable

        // Crear los campos del formulario y establecerles los valores recibidos
        this.nombreField = new TextField("Nombre", nombre);
        this.apellido1Field = new TextField("Primer Apellido", apellido1);
        this.apellido2Field = new TextField("Segundo Apellido", apellido2);
        this.fechaNacimientoField = new DateField("Fecha de Nacimiento");
        if (fechaNacimiento != null) {
            fechaNacimientoField.setValue(fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        this.direccionField = new TextField("Dirección", direccion);
        this.codigoPostalField = new TextField("Código Postal", String.valueOf(codigoPostal));
        this.alergiasField = new TextField("Alergias", alergias);
        this.colegioField = new TextField("Colegio", colegio);
        this.equipoAnteriorField = new TextField("Equipo Anterior", equipoAnterior);

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

        // Llamada al método que crea un botón "guardar"
        createButtonGuardar();

        // Llamada al método que lee los datos de una "id" en la base de datos
        updateAlumno();

        // Agregar el formulario y el botón al diseño vertical
        addComponents(formLayout, guardarButton);
    }


    public void createButtonEnviar() {
        // Crear el botón para enviar el formulario
        enviarButton = new Button("Enviar");
    }

    public void createButtonGuardar() {
        // Crear el botón para enviar el formulario
        guardarButton = new Button("Guardar");
    }

    public void leerFormularioEnviarAlumno() {
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
    }
    public void updateAlumno(){

        // Agregar el listener para el evento del botón de enviar
        guardarButton.addClickListener(event -> {
            // Actualizar los datos en la base de datos
            formAlumnosToDb.updatePersonalData(id, nombreField.getValue(), apellido1Field.getValue(), apellido2Field.getValue(),
                    Date.from(fechaNacimientoField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    direccionField.getValue(), Integer.parseInt(codigoPostalField.getValue()), alergiasField.getValue(),
                    colegioField.getValue(), equipoAnteriorField.getValue());
            // Mostrar mensaje de éxito
            Notification.show("Datos modificados con éxito", Notification.Type.HUMANIZED_MESSAGE);
        });
    }
}
