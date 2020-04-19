/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jit.adud3grafica;

import Objetos.Provincia;
import Objetos.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

/**
 *
 * @author jit
 */
public class OperacionesBD {

    public static final String rutaBBDD = System.getProperty("user.dir") + File.separator + "BBDDsqlite" + File.separator;

    public static Connection conexion() {

        File carpetaBBDD = new File(rutaBBDD);

        if (!carpetaBBDD.exists()) {
            carpetaBBDD.mkdirs();
        }

        try {
            Connection con;
            con = DriverManager.getConnection("jdbc:sqlite:" + rutaBBDD + "Tarea3.db");

            return con;

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + " no conecta");
            return null;
        }
    }

    public static void desconexion(Connection con) {
        try {
            con.close();

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
        }
    }

    public static Provincia selectProvincia(Connection con, int id) {
        Provincia provincia = null;
        try {
            String sql = " select * from provincia where id =" + id;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                provincia = new Provincia(rs.getInt("id"), rs.getString("nome"));
            }

            return provincia;

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
            return null;
        }

    }

    public static void insertProvincia(Connection con, Provincia provincia) {

        try {
            String sql = "INSERT INTO provincia(id,nome) VALUES(?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, provincia.getId());
            pstmt.setString(2, provincia.getNome());
            pstmt.executeUpdate();
            System.out.println("Insert" + provincia.getNome());

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
        }
    }

    public static ArrayList<Provincia> selectProvincias(Connection con) {
        ArrayList<Provincia> provincias = new ArrayList();
        try {
            String sql = " select * from provincia Order by id";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                provincias.add(new Provincia(rs.getInt("id"), rs.getString("nome")));

            }

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
        }
        return provincias;
    }

    public static ArrayList<Tenda> selectTendas(Connection con) {

        ArrayList<Tenda> tendas = new ArrayList();
        try {
            String sql = " select * from tenda  Order by idProvincia,cidade";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Tenda tenda = new Tenda(rs.getInt("id"), rs.getString("nome"), rs.getString("cidade"), rs.getInt("idProvincia"));
                tendas.add(tenda);
            }

            return tendas;

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
            return null;
        }
    }

    public static ArrayList<Tenda> selectTendaxProv(Connection con, int idProvincia) {

        ArrayList<Tenda> tendas = new ArrayList();
        try {
            String sql = " select * from tenda where idProvincia = " + idProvincia + " Order by cidade";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Tenda tenda = new Tenda(rs.getInt("id"), rs.getString("nome"), rs.getString("cidade"), rs.getInt("idProvincia"));
                tendas.add(tenda);
            }

            return tendas;

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
            return null;
        }
    }
    
    
    public static Tenda selectTenda(Connection con, int idTenda) {

        Tenda tenda= null;
        try {
            String sql = " select * from producto  Where id = " + idTenda;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                tenda= new Tenda(idTenda, rs.getString("nome"), rs.getString("idProvincia"), rs.getInt("cidade"));

            }

            return tenda;

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
            return null;
        }
    }

    public static void insertTenda(Connection con, Tenda tenda) {

        try {
            String sql = "INSERT INTO tenda(nome,idProvincia,cidade) VALUES(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, tenda.getNome());
            pstmt.setInt(2, tenda.getIdProvincia());
            pstmt.setString(3, tenda.getCidade());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
        }
    }

    public static void deleteTenda(Connection con, Tenda tenda) {

        try {
            String sql = "DELETE FROM tenda WHERE id = ? and idProvincia = ? ";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, tenda.getIdTenda());
            pstmt.setInt(2, tenda.getIdProvincia());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    //--------------------------------------------------------
    public static String selectProductos(Connection con) {

        String productos = "";
        try {
            String sql = "select * from producto Order by id ";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                productos += rs.getInt("id");
                productos += "\t" + rs.getString("nome");
                productos += "\t" + rs.getString("descripcion");
                productos += "\t" + rs.getString("prezo") + " €\n";
            }

            return productos;

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
            return "No hay Productos";
        }
    }

    public static String selectProducto(Connection con, int idProducto) {

        String producto = "";
        try {
            String sql = " select * from producto  Where id = " + idProducto;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                producto = rs.getString("nome");

            }

            return producto;

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
            return "";
        }
    }

    public static String selectStockProducto(Connection con, int idProducto) {
        String productos = "";

        try {
            String sql = " select * from stockproducto Where id = " + idProducto + " Order by idTenda ";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                productos += rs.getInt("id");
                productos += "\t" + rs.getString("idTenda");
                productos += "\t" + rs.getInt("cantidad") + " Uns.\n";
            }

            return productos;

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
            return "No hay stock";
        }
    }

    public static String selectStockTenda(Connection con, int idTenda) {
        String productos = "";

        try {
            String sql = " select * from stockproducto Where idTenda = " + idTenda + " Order by id ";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                productos += rs.getInt("id");
                productos += "\t" + selectProducto(con, rs.getInt("id"));
                productos += "\t" + rs.getInt("cantidad") + " Uns.\n";
            }

            return productos;

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
            return "No hay stock";
        }
    }

    public static void insertProducto(Connection con, String nome, String descripcion, int prezo) {

        try {
            String sql = "INSERT INTO producto(nome,descripcion,prezo) VALUES(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, nome);
            pstmt.setString(2, descripcion);
            pstmt.setInt(3, prezo);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
