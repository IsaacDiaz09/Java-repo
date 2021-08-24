package Reto4.src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * @author: Isaac Diaz G15 
 * Reto 4: Consultas SELECT mySql
 */
class Reto4 {
    /**
     * Se declara global la conexion para ser usada en todas las consultas(metodos)
     */
    private static Connection connSql = null;

    /**
     * metodo de la consulta 1
     */
    private static void consulta1() {
        String selectConsulta1 = "SELECT nombre,inventario FROM productos WHERE precio >= 9000 ORDER BY nombre";
        try {
            Statement sqlSelect = connSql.createStatement();
            ResultSet resultado = sqlSelect.executeQuery(selectConsulta1);

            while (resultado.next()) {
                String nombre = resultado.getString("nombre");
                int inventario = resultado.getInt("inventario");
                System.out.println(nombre + "\t" + inventario);
            }

        } catch (SQLException ex) {
            System.out.println("Error en la ejecución " + ex.getErrorCode() + " " + ex.getMessage());
        }
    }

    /**
     * metodo de la consulta 2
     */
    private static void consulta2() {

        String selectConsulta2 = "SELECT avg(precio) as promedio FROM productos";
        try {
            Statement sqlSelect = connSql.createStatement();
            ResultSet resultado = sqlSelect.executeQuery(selectConsulta2);

            while (resultado.next()) {
                float promedio = resultado.getFloat("promedio");
                System.out.println("promedio\t" + promedio);
            }

        } catch (SQLException ex) {
            System.out.println("Error en la ejecución " + ex.getErrorCode() + " " + ex.getMessage());
        }
    }

    /**
     * metodo de la consulta 3
     */
    private static void consulta3() {
        String selectConsulta3 = "SELECT nombre,precio FROM productos WHERE nombre like 'A%' or nombre like 'P%' ORDER BY nombre";
        try {
            Statement sqlSelect = connSql.createStatement();
            ResultSet resultado = sqlSelect.executeQuery(selectConsulta3);

            while (resultado.next()) {
                String nombre = resultado.getString("nombre");
                float precio = resultado.getFloat("precio");

                System.out.println("nombre" + "\t" + nombre + "\t" + "precio" + "\t" + precio);
            }

        } catch (SQLException ex) {
            System.out.println("Error en la ejecución " + ex.getErrorCode() + " " + ex.getMessage());
        }

    }

    /**
     * metodo de la consulta 4
     */
    private static void consulta4() {
        String selectConsulta4 = "SELECT count(nombre) as total FROM productos WHERE (precio BETWEEN 3000 and 10000)";
        try {
            Statement sqlSelect = connSql.createStatement();
            ResultSet resultado = sqlSelect.executeQuery(selectConsulta4);

            while (resultado.next()) {
                int total = resultado.getInt("total");

                System.out.println("total\t" + total);
            }

        } catch (SQLException ex) {
            System.out.println("Error en la ejecución " + ex.getErrorCode() + " " + ex.getMessage());
        }
    }

    /**
     * metodo de la consulta 5
     */
    private static void consulta5() {
        String selectConsulta5 = "SELECT sum(precio*inventario) as total_inventario FROM productos";
        try {
            Statement sqlSelect = connSql.createStatement();
            ResultSet resultado = sqlSelect.executeQuery(selectConsulta5);

            while (resultado.next()) {
                float total_inventario = resultado.getFloat("total_inventario");

                System.out.println("total_inventario\t" + total_inventario);
            }

        } catch (SQLException ex) {
            System.out.println("Error en la ejecución " + ex.getErrorCode() + " " + ex.getMessage());
        }

    }

    public static void main(String[] args) {
        /**
         * url de la base de datos -> localhost
         */
        final String urlDb = "jdbc:mysql://localhost:3306/db_reto4?serverTimezone=UTC";
        /**
         * usuario de la base de datos
         */
        final String usuarioDb = "root";
        /**
         * constraseña del usuario de la base de datos
         */
        final String passDb = "1193551867aN";

        try {
            connSql = DriverManager.getConnection(urlDb, usuarioDb, passDb);
            if (!connSql.isClosed()) {
                System.out.println("Conexión establecida");
            }
            consulta1();
            consulta2();
            consulta3();
            consulta4();
            consulta5();
        } catch (SQLException ex) {
            System.out.println("Error en la ejecución " + ex.getErrorCode() + " " + ex.getMessage());
        }

    }
}
