package com.example.appqs.actions;
import java.util.Date;

import com.example.appqs.webConstructors.ControlUI;
import com.example.appqs.webConstructors.pushEnviar;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;


public class logicEnviar extends VerticalLayout {
// Defino las variables necesarias para los métodos
    private Button enviarButton;
    private TextField nombreField;
    private TextField apellido1Field;
    private TextField apellido2Field;
    private DateField fechaNacimientoField;
    private TextField direccionField;
    private TextField codigoPostalField;
    private TextField alergiasField;
    private TextField colegioField;
    private TextField equipoAnteriorField;

    String apellido1;
    String apellido2;
    Date fechaNacimiento;
    String direccion;
    int codigoPostal;
    String alergias;
    String colegioString;
    String equipoAnterior;
    String colegio;
    String nombre;
    int tipoPersona;
    private pushEnviar pushSender;

    public logicEnviar(){
    // Llamada al método que crea un botón "enviar"
    enviarButton = ControlUI.createButtonEnviar();

    // Agregar el listener para el evento del botón de enviar
    enviarButton.addClickListener(event -> {
        pushSender.pushButtonEnviar(nombreField, apellido1Field, apellido2Field, fechaNacimientoField,
                direccionField, codigoPostalField, alergiasField, colegioField,
                equipoAnteriorField);
        
    });

    // Agregar el formulario y el botón al diseño vertical
    addComponents(enviarButton);

    }

    public Button getEnviarButton() {
    return enviarButton;
}



}
