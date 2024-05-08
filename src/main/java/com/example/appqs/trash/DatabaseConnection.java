package com.example.appqs.trash;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class DatabaseConnection {
    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());// Para poder incluir logs

    //Este método es para grabar los datos del formulario en la base de datos
    public static void insertPersonalData(String nombre, String apellido1, String apellido2, Date fechaNacimiento,
            String direccion, int codigoPostal, String alergias, String colegio, int tipoPersona,
            String equipoAnterior) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        tipoPersona = 1;
        try {
            // Conecto con mi base de datos
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/QSoccer", "QSUser", "12345");

            // Consulto a la BD para incluir los datos que recojo del formulario
            String sql = "INSERT INTO PERSONAL (nombre, apellido1, apellido2, fnacimiento, direccion, cpostal, alergias, colegio, anterior, tipopersona)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Recojo los datos para mandarlos a la bd, el numero corresponde con el orden
            // que puse arriba
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido1);
            pstmt.setString(3, apellido2);
            pstmt.setDate(4, new java.sql.Date(fechaNacimiento.getTime()));
            pstmt.setString(5, direccion);
            pstmt.setInt(6, codigoPostal);
            pstmt.setString(7, alergias);
            pstmt.setString(8, colegio);
            pstmt.setString(9, equipoAnterior);
            pstmt.setInt(10, tipoPersona);

            // Consulto
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerramos (no tenía ni idea de que había que cerrar nada xD)
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Object[]> getAllPersonalData() { // Este método es para traerme el grid principal de alumnos
        List<Object[]> personalData = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Conecto con BD
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/QSoccer", "QSUser", "12345");

            // Consulto la tabla
            String sql = "SELECT * FROM personal";

            // Preparo la consulta
            stmt = conn.prepareStatement(sql);

            // Me traigo los datos
            rs = stmt.executeQuery();

            // Ahora me quedo con lo que necesito
            while (rs.next()) {
                Object[] data = new Object[9];
                data[0] = rs.getString("nombre");
                data[1] = rs.getString("apellido1");
                data[2] = rs.getString("apellido2");
                data[3] = rs.getDate("fnacimiento");
                data[4] = rs.getString("direccion");
                data[5] = rs.getInt("cpostal");
                data[6] = rs.getString("alergias");
                data[7] = rs.getString("colegio");
                data[8] = rs.getString("anterior");
                personalData.add(data);
            }
        } catch (SQLException e) {
            // Por si hay error
        } finally {
            // Cerrando...
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                    logger.log(Level.INFO, "Conexión cerrada correctamente.");
                }
            } catch (SQLException e) {
                // Por si hay error
                logger.log(Level.SEVERE, "Error al cerrar recursos de base de datos", e);
            }
        }
        return personalData;
    }

}
