package com.example.appqs.dbconnections;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class buscaRelaciones {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/QSoccer";
    static final String USER = "QSUser";
    static final String PASS = "12345";

    public static List<Relacion> obtenerRelaciones(int alumnoId) {
        List<Relacion> relaciones = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT idtipo2, idtipo3 FROM relaciones WHERE idtipo1 = " + alumnoId;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int idtipo2 = rs.getInt("idtipo2");
                int idtipo3 = rs.getInt("idtipo3");
                relaciones.add(new Relacion(idtipo2, obtenerNombreAlumno(conn, idtipo2), idtipo3,
                        obtenerNombreAlumno(conn, idtipo3)));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return relaciones;
    }

    public static String obtenerNombreAlumno(Connection conn, int alumnoId) throws SQLException {
        String nombreCompleto = "";
        Statement stmt = conn.createStatement();
        String sql = "SELECT nombre, apellido1, apellido2 FROM personal WHERE id = " + alumnoId;
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            String nombre = rs.getString("nombre");
            String apellido1 = rs.getString("apellido1");
            String apellido2 = rs.getString("apellido2");
            nombreCompleto = nombre + " " + apellido1 + " " + apellido2;
        }
        rs.close();
        stmt.close();
        return nombreCompleto;
    }

    public static class Relacion {
        private int idTipo2;
        private String nombreTipo2;
        private int idTipo3;
        private String nombreTipo3;

        public Relacion(int idTipo2, String nombreTipo2, int idTipo3, String nombreTipo3) {
            this.idTipo2 = idTipo2;
            this.nombreTipo2 = nombreTipo2;
            this.idTipo3 = idTipo3;
            this.nombreTipo3 = nombreTipo3;
        }

        public int getIdTipo2() {
            return idTipo2;
        }

        public void setIdTipo2(int idTipo2) {
            this.idTipo2 = idTipo2;
        }

        public String getNombreTipo2() {
            return nombreTipo2;
        }

        public void setNombreTipo2(String nombreTipo2) {
            this.nombreTipo2 = nombreTipo2;
        }

        public int getIdTipo3() {
            return idTipo3;
        }

        public void setIdTipo3(int idTipo3) {
            this.idTipo3 = idTipo3;
        }

        public String getNombreTipo3() {
            return nombreTipo3;
        }

        public void setNombreTipo3(String nombreTipo3) {
            this.nombreTipo3 = nombreTipo3;
        }
    }

    public static String obtenerNombreAlumno(int alumnoId) {
        String nombreCompleto = "";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT nombre, apellido1, apellido2 FROM personal WHERE id = " + alumnoId;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido1 = rs.getString("apellido1");
                String apellido2 = rs.getString("apellido2");
                nombreCompleto = nombre + " " + apellido1 + " " + apellido2;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return nombreCompleto;
    }

    public static class Personal {
        private int id;
        private String nombre;
        private boolean selected;
    
        public Personal(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
            this.selected = false;
        }
    
        public int getId() {
            return id;
        }
    
        public String getNombre() {
            return nombre;
        }
    
        public boolean isSelected() {
            return selected;
        }
    
        public void setSelected(boolean selected) {
            this.selected = selected;
        }
    }
    

    public static List<Personal> obtenerPersonas() {
        List<Personal> personas = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT id, nombre, apellido1, apellido2 FROM personal WHERE tipopersona = 2 AND delete = false";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre") + " " + rs.getString("apellido1") + " " + rs.getString("apellido2");
                personas.add(new Personal(id, nombre));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return personas;
    }
    
}
    
