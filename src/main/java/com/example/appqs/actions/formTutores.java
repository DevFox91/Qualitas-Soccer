package com.example.appqs.actions;

import java.util.Date;
import java.time.ZoneId;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class formTutores extends VerticalLayout {
    // Defino las variables necesarias para los métodos
    private TextField nombreField;
    private TextField apellido1Field;
    private TextField apellido2Field;
    private DateField fechaNacimientoField;
    private TextField direccionField;
    private TextField codigoPostalField;
    private TextField profesionField;
    private TextField nifField;
    private TextField ibanField;
    private int id;

    public TextField getNombreField() {
        return nombreField;
    }

    public Integer getIdField() {
        return id;
    }

    public TextField getApellido1Field() {
        return apellido1Field;
    }

    public TextField getApellido2Field() {
        return apellido2Field;
    }

    public DateField getFechaNacimientoField() {
        return fechaNacimientoField;
    }

    public TextField getDireccionField() {
        return direccionField;
    }

    public TextField getCodigoPostalField() {
        return codigoPostalField;
    }

    public TextField getNifField() {
        return nifField;
    }

    public TextField getProfesionField() {
        return profesionField;
    }

    public TextField getIbanField() {
        return ibanField;
    }

    // Primer constructor, no se le pasan parámetros porque construye formulario
    // vacío para añadir un nuevo alumno.
    public formTutores() {
        initForm();

    }

    // Segundo constructor, se trae los datos de la base de datos, según la id del
    public formTutores(int id, String nombre, String apellido1, String apellido2,
            Date fechaNacimiento, String direccion, int codigoPostal,
            String nif, String profesion, String iban) {

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
        this.nifField = new TextField("NIF", nif);
        this.profesionField = new TextField("Profesión", profesion);
        this.ibanField = new TextField("IBAN", iban);

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
                nifField,
                profesionField,
                ibanField);

        // Llamada al método que lee los datos de una "id" en la base de datos
        updateTutor();

        // Agregar el formulario y el botón al diseño vertical
        addComponents(formLayout);
    }

    public void updateTutor() {
        
    }

    private void initForm() {
        this.nombreField = new TextField("Nombre");
        this.apellido1Field = new TextField("Primer Apellido");
        this.apellido2Field = new TextField("Segundo Apellido");
        this.fechaNacimientoField = new DateField("Fecha de Nacimiento");
        this.direccionField = new TextField("Dirección");
        this.codigoPostalField = new TextField("Código Postal");
        this.nifField = new TextField("DNI");
        this.profesionField = new TextField("Profesión");
        this.ibanField = new TextField("IBAN");

        FormLayout formLayout = new FormLayout();
        formLayout.addComponents(
                this.nombreField,
                this.apellido1Field,
                this.apellido2Field,
                this.fechaNacimientoField,
                this.direccionField,
                this.codigoPostalField,
                this.nifField,
                this.profesionField,
                this.ibanField);

        addComponent(formLayout);
    }

}
