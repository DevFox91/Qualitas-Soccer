package com.example.appqs.webConstructors;

import java.util.Date;

import com.example.appqs.AppQsApplication.MainUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class inscripcionMenu extends HorizontalLayout {


    public Button toggleMenuButton;
    public Button enviarButton;
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

    public inscripcionMenu(MainUI mainUI) {
        // Establecer la alineación de este HorizontalLayout a la derecha
        this.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

        // Crear botones y añadirlos horizontalmente
        toggleMenuButton = new Button("<<");
        // Llamada al método que crea un botón "enviar"
        enviarButton = ControlUI.createButtonEnviar();

        // Agregar el listener para el evento del botón de enviar
        enviarButton.addClickListener(event -> {
            pushSender.pushButtonEnviar(nombreField, apellido1Field, apellido2Field, fechaNacimientoField,
                    direccionField, codigoPostalField, alergiasField, colegioField,
                    equipoAnteriorField);
            
        });
        

        // Añadir botones al layout horizontal
        addComponent(toggleMenuButton);
        addComponent(enviarButton);
        

        // Configurar el listener para el botón toggleMenuButton
        toggleMenuButton.addClickListener(event -> mainUI.toggleMenuVisibility());

    }


}
