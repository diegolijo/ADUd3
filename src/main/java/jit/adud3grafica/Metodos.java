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
import static jit.adud3grafica.OperacionesBD.*;

public class Metodos {

    private static final String rutaJson = System.getProperty("user.dir") + File.separator + "Provincias.json";
    public static final String rutaBBDD = System.getProperty("user.dir") + File.separator + "BBDDsqlite" + File.separator ;



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
            for (Provincia p : provincias) {
                if (selectProvincia(con, p.getId()) == null) {
                    insertProvincia(con, p);
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

    public static boolean isFloat(String cadena) {

        boolean resultado;

        try {
            Float.parseFloat(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }

}
