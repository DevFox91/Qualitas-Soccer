package com.example.appqs.dbconnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dbToAlumnos {
    private static final Logger logger = Logger.getLogger(dbToAlumnos.class.getName());

    public static List<Object[]> getAllPersonalData() {
        List<Object[]> personalData = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/QSoccer", "QSUser", "12345");

            String sql = "SELECT * FROM personal WHERE delete = false AND tipoPersona = 1";

            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] row = new Object[10];
                row[0] = rs.getInt("id");
                row[1] = rs.getString("nombre");
                row[2] = rs.getString("apellido1");
                row[3] = rs.getString("apellido2");
                row[4] = rs.getDate("fnacimiento");
                row[5] = rs.getString("direccion");
                row[6] = rs.getInt("cpostal");
                row[7] = rs.getString("alergias");
                row[8] = rs.getString("colegio");
                row[9] = rs.getString("anterior");
                personalData.add(row);
            }
        } catch (SQLException e) {
            // Handle error
        } finally {
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
                logger.log(Level.SEVERE, "Error al cerrar recursos de base de datos", e);
            }
        }
        return personalData;
    }
}
