package jit.adud3grafica;

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

public class Metodos {

    private static final String rutaJson = System.getProperty("user.dir") + File.separator + "Provincias.json";
    public static final String rutaBBDD = System.getProperty("user.dir") + File.separator + "BBDDsqlite";

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

    public static String selectProvincia(Connection con, int id) {
        String provincia = null;
        try {
            String sql = " select * from provincia where id =" + id;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                provincia = rs.getString("nome");
            }

            return provincia;

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
            return null;
        }

    }

    public static void insertProvincia(Connection con, int id, String nome) {

        try {
            String sql = "INSERT INTO provincia(id,nome) VALUES(?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, nome);
            pstmt.executeUpdate();
            System.out.println("Insert" + nome);

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
        }
    }

    public static String selectProvincias(Connection con) {
        String provincias = "";
        try {
            String sql = " select * from provincia Order by id";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                provincias += rs.getInt("id");
                provincias += "\t" + rs.getString("nome") + "\n";
            }

            return provincias;

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
            return null;
        }

    }

    public static String selectTenda(Connection con, int idProvincia) {

        String tendas = "";
        try {
            String sql = " select * from tenda where idProvincia = " + idProvincia + " Order by cidade";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                tendas += rs.getInt("id");
                tendas += "\t" + rs.getString("cidade");
                tendas += "\t" + rs.getString("nome") + "\n";
            }

            return tendas;

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
            return "No hay tiendas";
        }
    }

    public static String selectTendas(Connection con) {

        String tendas = "";
        try {
            String sql = " select * from tenda  Order by idProvincia,cidade";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                tendas += rs.getInt("idProvincia");
                tendas += "\t" + rs.getString("Provincia");
                tendas += "\t" + rs.getString("cidade");
                tendas += "\t" + rs.getString("nome") + "\n";
            }

            return tendas;

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
            return "No hay tiendas";
        }
    }

    public static void insertTenda(Connection con, int idProvincia, String nome, String cidade) {

        try {
            String sql = "INSERT INTO tenda(nome,idProvincia,Provincia,cidade) VALUES(?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, nome);
            pstmt.setInt(2, idProvincia);
            pstmt.setString(3, selectProvincia(con, idProvincia));
            pstmt.setString(4, cidade);

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
        }
    }

    public static void deleteTenda(Connection con, int idTenda, int idProvincia) {

        try {
            String sql = "DELETE FROM tenda WHERE id = ? and idProvincia = ? ";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idTenda);
            pstmt.setInt(2, idProvincia);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

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

    public static void leerJson() {

        System.out.println(rutaJson);
        System.out.println(rutaBBDD);

        //leemos el json
        Gson gson = new Gson();
        java.lang.reflect.Type tipoProvincias = new TypeToken<ArrayList<Provincia>>() {
        }.getType();

        // serializamos el archivo en un array de objetos provincia
        if (new File(rutaJson).exists()) {

            ArrayList<Provincia> provincias = gson.fromJson(leerJson(new File(rutaJson)), tipoProvincias);

            //creamos la conexion a bd y tablas
            Connection con = conexion();
            CrearTablas.crearTablas(con);

            //recprrecos cada provincia del array e insertamos en la tabla si no existe 
            for (Provincia e : provincias) {
                if (selectProvincia(con, e.getId()) == null) {
                    insertProvincia(con, e.getId(), e.getNome());
                }
            }
            desconexion(con);
        }
    }

    public static String leerJson(File archivo) {
        String entrada = "";
        try {
            InputStream stream = new FileInputStream(archivo);
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");

            int caracter;
            while ((caracter = reader.read()) != -1) {
                entrada += (char) caracter;
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("No se ha podido leer el fichero");
        }
        return entrada;
    }

    public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }

}
