package com.example.appqs.trash;

import com.example.appqs.webConstructors.ControlUI;
import com.example.appqs.webConstructors.pushEnviarAlumno;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.DateField;
import com.vaadin.ui.VerticalLayout;

public class SetupEnviarButton {

    public static void setupEnviarButton(VerticalLayout layout, TextField nombreField, TextField apellido1Field,
                                         TextField apellido2Field, DateField fechaNacimientoField, TextField direccionField,
                                         TextField codigoPostalField, TextField alergiasField, TextField colegioField,
                                         TextField equipoAnteriorField, pushEnviarAlumno pushSender) {
        Button enviarButton = ControlUI.createButtonEnviar();
        enviarButton.addClickListener(event -> {
            pushSender.pushButtonEnviar(
                    nombreField, apellido1Field, apellido2Field, fechaNacimientoField,
                    direccionField, codigoPostalField, alergiasField, colegioField,
                    equipoAnteriorField);
        });
        layout.addComponent(enviarButton);
    }
}
