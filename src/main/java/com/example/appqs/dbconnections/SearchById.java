package com.example.appqs.dbconnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchById {
    private static final Logger logger = Logger.getLogger(SearchById.class.getName());

    public static Object[] getDataById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Object[] rowData = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/QSoccer", "QSUser", "12345");

            String sql = "SELECT * FROM personal WHERE id = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                rowData = new Object[10];
                rowData[0] = rs.getInt("id");
                rowData[1] = rs.getString("nombre");
                rowData[2] = rs.getString("apellido1");
                rowData[3] = rs.getString("apellido2");
                rowData[4] = rs.getDate("fnacimiento");
                rowData[5] = rs.getString("direccion");
                rowData[6] = rs.getInt("cpostal");
                rowData[7] = rs.getString("alergias");
                rowData[8] = rs.getString("colegio");
                rowData[9] = rs.getString("anterior");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al buscar datos por ID en la base de datos", e);
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
        return rowData;
    }
}
