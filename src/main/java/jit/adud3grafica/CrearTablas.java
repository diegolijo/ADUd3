package jit.adud3grafica;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrearTablas {

    public static void crearTablas(Connection con) {
        createProvincia(con);
        createTenda(con);
        createProducto(con);
        createStockProducto(con);
       createCliente(con);
    }

    public static void createProvincia(Connection con) {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS provincia "
                    + "(id INTEGER PRIMARY KEY, "
                    + "nome TEXT NOT NULL )";
            Statement stmt = con.createStatement();
            stmt.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void createTenda(Connection con) {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS tenda "
                    + "(id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "nome TEXT NOT NULL , "
                    + "idProvincia INTEGER NOT NULL, "
                    + "cidade TEXT NOT NULL )";
            Statement stmt = con.createStatement();
            stmt.execute(sql);

        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void createProducto(Connection con) {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS producto ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "nome TEXT NOT NULL UNIQUE, "
                    + "descripcion TEXT , "
                    + "prezo INTEGER NOT NULL)";
            Statement stmt = con.createStatement();
            stmt.execute(sql);

        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void createEmpleado(Connection con) {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS empleado ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "nome TEXT NOT NULL , "
                    + "apellidos TEXT NOT NULL)";
            Statement stmt = con.createStatement();
            stmt.execute(sql);

        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void createCliente(Connection con) {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS cliente ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "nome TEXT NOT NULL , "
                    + "apellidos TEXT NOT NULL, "
                    + "email TEXT NOT NULL UNIQUE)";
            Statement stmt = con.createStatement();
            stmt.execute(sql);

        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void createStockProducto(Connection con) {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS stockproducto ("
                    + "id INTEGER NOT NULL, "
                    + "idTenda INTEGER NOT NULL, "
                    + "cantidad INTEGER NOT NULL)";
            Statement stmt = con.createStatement();
            stmt.execute(sql);

        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void createHorasEmpleado(Connection con) {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS horasEmpleado ("
                    + "idEmpleado INTEGER NOT NULL , "
                    + "idTenda INTEGER NOT NULL, "
                    + "horas INTEGER NOT NULL)";
            Statement stmt = con.createStatement();
            stmt.execute(sql);

        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
