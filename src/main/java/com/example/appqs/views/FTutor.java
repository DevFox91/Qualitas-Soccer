package com.example.appqs.views;

import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class FTutor extends VerticalLayout {

    public FTutor() {
        // Crear los campos del formulario
        TextField nombreField = new TextField("Nombre");
        TextField apellido1Field = new TextField("Primer Apellido");
        TextField apellido2Field = new TextField("Segundo Apellido");
        DateField fechaNacimientoField = new DateField("Fecha de Nacimiento");
        TextField direccionField = new TextField("Dirección");
        TextField codigoPostalField = new TextField("Código Postal");
        TextField nifField = new TextField("DNI");
        TextField profesionField = new TextField("Profesión");
        TextField ibanField = new TextField("Número IBAN");
        ibanField.setWidth("400px"); // Establecer el ancho del campo del IBAN a 400 píxeles
        ibanField.setMaxLength(34); // Establecer la longitud máxima del IBAN (generalmente 34 caracteres)
        ibanField.setPlaceholder("Ejemplo: ES9121000418450200051332");
        ibanField.setDescription("El formato debe ser válido según el estándar IBAN");
             
        // Crear el formulario
        FormLayout formLayout = new FormLayout();
        formLayout.addComponents(
            nombreField, 
            apellido1Field, 
            apellido2Field, 
            fechaNacimientoField, 
            direccionField, 
            codigoPostalField, 
            nifField, 
            profesionField,
            ibanField
        );

        // Crear el botón para enviar el formulario (aún no implementado)
        Button enviarButton = new Button("Enviar");

        // Agregar el formulario y el botón al diseño vertical
        addComponents(formLayout, enviarButton);
    }
}
