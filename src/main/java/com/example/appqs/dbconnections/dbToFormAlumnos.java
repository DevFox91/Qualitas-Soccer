package com.example.appqs.dbconnections;

import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import java.time.ZoneId;
import java.util.Date;

public class dbToFormAlumnos {

    public static void fillFormWithData(Object[] rowData, TextField nombreField, TextField apellido1Field,
                                        TextField apellido2Field, DateField fechaNacimientoField,
                                        TextField direccionField, TextField codigoPostalField,
                                        TextField alergiasField, TextField colegioField,
                                        TextField equipoAnteriorField) {

        nombreField.setValue((String) rowData[1]);
        apellido1Field.setValue((String) rowData[2]);
        apellido2Field.setValue((String) rowData[3]);

        Date date = (Date) rowData[4];
        if (date != null) {
            fechaNacimientoField.setValue(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        } else {
            fechaNacimientoField.clear();
        }

        direccionField.setValue((String) rowData[5]);
        codigoPostalField.setValue(String.valueOf((int) rowData[6]));
        alergiasField.setValue((String) rowData[7]);
        colegioField.setValue((String) rowData[8]);
        equipoAnteriorField.setValue((String) rowData[9]);
    }
}
