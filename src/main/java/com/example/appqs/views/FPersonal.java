package com.example.appqs.views;

import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class FPersonal extends VerticalLayout {

    public FPersonal() {
        // Crear los campos del formulario
        TextField nombreField = new TextField("Nombre");
        TextField apellido1Field = new TextField("Primer Apellido");
        TextField apellido2Field = new TextField("Segundo Apellido");
        DateField fechaNacimientoField = new DateField("Fecha de Nacimiento");
        TextField direccionField = new TextField("Dirección");
        TextField codigoPostalField = new TextField("Código Postal");
        TextField tituloField = new TextField("Título");
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
            tituloField, 
            equipoAnteriorField
        );

        // Crear el botón para enviar el formulario (aún no implementado)
        Button enviarButton = new Button("Enviar");

        // Agregar el formulario y el botón al diseño vertical
        addComponents(formLayout, enviarButton);
    }
}
