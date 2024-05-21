package com.example.appqs.actions;

import java.util.Date;
import java.time.ZoneId;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class formAlumnos extends VerticalLayout {
    private TextField nombreField;
    private TextField apellido1Field;
    private TextField apellido2Field;
    private DateField fechaNacimientoField;
    private TextField direccionField;
    private TextField codigoPostalField;
    private TextField alergiasField;
    private TextField colegioField;
    private TextField equipoAnteriorField;
    private Integer id;

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

    public TextField getAlergiasField() {
        return alergiasField;
    }

    public TextField getColegioField() {
        return colegioField;
    }

    public TextField getEquipoAnteriorField() {
        return equipoAnteriorField;
    }

    // Primer constructor, sin parámetros
    public formAlumnos() {
        initForm();
    }

    // Segundo constructor, con parámetros
    public formAlumnos(int id, String nombre, String apellido1, String apellido2,
                       Date fechaNacimiento, String direccion, int codigoPostal,
                       String alergias, String colegio, String equipoAnterior) {


        // Inicializar campos del formulario con valores recibidos
        this.id = id;
        TextField idField = new TextField("ID", String.valueOf(id));
        idField.setEnabled(false);

        this.nombreField = new TextField("Nombre", nombre);
        this.apellido1Field = new TextField("Primer Apellido", apellido1);
        this.apellido2Field = new TextField("Segundo Apellido", apellido2);
        this.fechaNacimientoField = new DateField("Fecha de Nacimiento");
        if (fechaNacimiento != null) {
            this.fechaNacimientoField.setValue(fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        this.direccionField = new TextField("Dirección", direccion);
        this.codigoPostalField = new TextField("Código Postal", String.valueOf(codigoPostal));
        this.alergiasField = new TextField("Alergias", alergias);
        this.colegioField = new TextField("Colegio", colegio);
        this.equipoAnteriorField = new TextField("Equipo Anterior", equipoAnterior);

        FormLayout formLayout = new FormLayout();
        formLayout.addComponents(
                idField,
                this.nombreField,
                this.apellido1Field,
                this.apellido2Field,
                this.fechaNacimientoField,
                this.direccionField,
                this.codigoPostalField,
                this.alergiasField,
                this.colegioField,
                this.equipoAnteriorField);

        addComponent(formLayout);
    }

    private void initForm() {
        this.nombreField = new TextField("Nombre");
        this.apellido1Field = new TextField("Primer Apellido");
        this.apellido2Field = new TextField("Segundo Apellido");
        this.fechaNacimientoField = new DateField("Fecha de Nacimiento");
        this.direccionField = new TextField("Dirección");
        this.codigoPostalField = new TextField("Código Postal");
        this.alergiasField = new TextField("Alergias");
        this.colegioField = new TextField("Colegio");
        this.equipoAnteriorField = new TextField("Equipo Anterior");

        FormLayout formLayout = new FormLayout();
        formLayout.addComponents(
                this.nombreField,
                this.apellido1Field,
                this.apellido2Field,
                this.fechaNacimientoField,
                this.direccionField,
                this.codigoPostalField,
                this.alergiasField,
                this.colegioField,
                this.equipoAnteriorField);

        addComponent(formLayout);
    }

    

    

    public String getFormValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombreField.getValue()).append("\n");
        sb.append("Apellido1: ").append(apellido1Field.isEmpty() ? "campo vacío" : apellido1Field.getValue()).append("\n");
        sb.append("Apellido2: ").append(apellido2Field.getValue()).append("\n");
        sb.append("Fecha de Nacimiento: ").append(fechaNacimientoField.getValue()).append("\n");
        sb.append("Dirección: ").append(direccionField.getValue()).append("\n");
        sb.append("Código Postal: ").append(codigoPostalField.getValue()).append("\n");
        sb.append("Alergias: ").append(alergiasField.getValue()).append("\n");
        sb.append("Colegio: ").append(colegioField.getValue()).append("\n");
        sb.append("Equipo Anterior: ").append(equipoAnteriorField.getValue()).append("\n");
        return sb.toString();
    }
    
    
}
