package com.example.appqs.actions;

import java.util.Date;
import java.time.ZoneId;
import com.vaadin.ui.Notification;
import com.example.appqs.AppQsApplication;
import com.example.appqs.dbconnections.formAlumnosToDb;
import com.example.appqs.views.Alumnos;
import com.example.appqs.webConstructors.ControlUI;
import com.example.appqs.webConstructors.pushEnviar;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class formAlumnos extends VerticalLayout {
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
    private pushEnviar pushSender;

    // Primer constructor, sin parámetros
    public formAlumnos() {
        this.pushSender = new pushEnviar();
        initForm();
        setupEnviarButton();
    }

    // Segundo constructor, con parámetros
    public formAlumnos(int id, String nombre, String apellido1, String apellido2,
                       Date fechaNacimiento, String direccion, int codigoPostal,
                       String alergias, String colegio, String equipoAnterior) {
        this.pushSender = new pushEnviar();

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

        this.guardarButton = ControlUI.createButtonGuardar();
        setupGuardarButton();

        addComponent(this.guardarButton);
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

    public void setupEnviarButton() {
        this.enviarButton = ControlUI.createButtonEnviar();
        this.enviarButton.addClickListener(event -> {
            this.pushSender.pushButtonEnviar(
                    this.nombreField, this.apellido1Field, this.apellido2Field, this.fechaNacimientoField,
                    this.direccionField, this.codigoPostalField, this.alergiasField, this.colegioField,
                    this.equipoAnteriorField);
        });
        addComponent(this.enviarButton);
    }

    private void setupGuardarButton() {
        this.guardarButton.addClickListener(event -> {
            formAlumnosToDb.updatePersonalData(
                    this.id, this.nombreField.getValue(), this.apellido1Field.getValue(), this.apellido2Field.getValue(),
                    Date.from(this.fechaNacimientoField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    this.direccionField.getValue(), Integer.parseInt(this.codigoPostalField.getValue()),
                    this.alergiasField.getValue(), this.colegioField.getValue(), this.equipoAnteriorField.getValue());
            Notification.show("Datos modificados con éxito", Notification.Type.HUMANIZED_MESSAGE);

            UI currentUI = UI.getCurrent();
            if (currentUI instanceof AppQsApplication.MainUI) {
                ((AppQsApplication.MainUI) currentUI).showView(new Alumnos());
            }
        });
    }
}

