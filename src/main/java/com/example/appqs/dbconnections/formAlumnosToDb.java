package com.example.appqs.dbconnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.sql.ResultSet;


public class formAlumnosToDb {

    public static void insertPersonalData(String nombre, String apellido1, String apellido2, Date fechaNacimiento,
            String direccion, int codigoPostal, String alergias, String colegio, int tipoPersona,
            String equipoAnterior) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        tipoPersona = 1;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/QSoccer", "QSUser", "12345");

            String sql = "INSERT INTO PERSONAL (nombre, apellido1, apellido2, fnacimiento, direccion, cpostal, alergias, colegio, anterior, tipopersona)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    // Método para actualizar datos en la base de datos de postgresql
    public static void updatePersonalData(int id, String nombre, String apellido1, String apellido2,
            Date fechaNacimiento,
            String direccion, int codigoPostal, String alergias, String colegio, String equipoAnterior) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/QSoccer", "QSUser", "12345");

            // Verificar si la ID existe en la base de datos
            String verifySql = "SELECT COUNT(*) FROM PERSONAL WHERE id = ?";
            pstmt = conn.prepareStatement(verifySql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            rs.next();
            int rowCount = rs.getInt(1);
            if (rowCount == 0) {
                System.out.println("La ID proporcionada no existe en la base de datos.");
                return;
            }

            // Si la ID existe, actualizamos los datos
            String sql = "UPDATE PERSONAL SET nombre = ?, apellido1 = ?, apellido2 = ?, fnacimiento = ?, direccion = ?, cpostal = ?, alergias = ?, colegio = ?, anterior = ? WHERE id = ?";
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
            pstmt.setInt(10, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
}