package buscaminas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author oquintansocampo
 */
public class BaseData {

    static Connection con = null;
    Statement s = null;
    ResultSet rs = null;
    static String usur = "root", pass = "";

    public static Connection conDerby() {

        try {
            //Cargar Driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //Conectar a la base en derby
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/BuscaMinasDB", usur, pass);
            //Confirmamos conexion
            System.out.println("Conexión establecida con el servidor Derby.");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("ERROR DE CONEXION ---> " + ex);
        }
        return con;
    }

    public void select(String dif) {
        ArrayList<Score> scoreBoard = new ArrayList<>();
        try {
            //Declarar consulta
            s = con.createStatement();
            //Ejecutar consulta
            rs = s.executeQuery("select * from Score where dificultad='" + dif + "'order by tiempo");
            /**
             * while (rs.next()) { scoreBoard.add(new Score(
             * rs.getString("nombre"),
             * Integer.parseInt(rs.getString("tiempo")))); }*
             */
            JOptionPane.showMessageDialog(null, rs.getString("nombre") + rs.getString("tiempo"), "mejores tiempos", JOptionPane.PLAIN_MESSAGE, Tablero.boom);
        } catch (SQLException ex) {
            System.out.println("ERROR ---> " + ex);
        }
        //return scoreBoard;
    }

    public void insert(int tiempo, String nomb, String dif) {
        try {
            //Declarar consulta
            s = con.createStatement();
            //Ejecutar consulta
            s.executeUpdate("INSERT INTO Score values ('" + nomb + "'," + tiempo + ",'" + dif + "')");
            //Confirmacion
            System.out.println("Inserción realizada");
        } catch (SQLException ex) {
            System.out.println("ERROR ---> " + ex);
        }
    }

}
