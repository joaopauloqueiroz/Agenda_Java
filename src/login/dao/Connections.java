
package login.dao;


import java.sql.*;
import java.sql.ResultSet;

/**
 * @author João Paulo <joaopaulo.queiroz.545@gmail.com>
 */

/**
 * Abre uma conexão com o banco de dados
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Connections {
    public ResultSet rs;
    private final String driver = "jdbc:sqlserver://PRES24:1433;" + "databaseName=teste;username=sa;password=180461jp*01";
    public java.sql.Connection conn;
    
   public java.sql.Connection conexao(){
       try {
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           conn = DriverManager.getConnection(driver);
          } catch (ClassNotFoundException | SQLException e) {
           System.out.println("Erro "+ e);
       }
       return this.conn;
   }
   
/**
 * Fecha uma conexão com o banco de dados
 * @throws SQLException 
 */
   public void close() throws SQLException{
       this.conn.close();
   }
}
