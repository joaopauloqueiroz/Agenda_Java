/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package login.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import login.model.Validations;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class DaoUsuario extends DaoIplements{
   
    private final Connection conn;
    private final Validations valid;
    
    public DaoUsuario(){
        this.conn = this.conect.conexao();
        this.valid = new Validations();
        this.table = "users";
     
    }
    /**
     * Executa a verificação do login
     * @param email
     * @param senha
     * @return
     * @throws SQLException
     * @throws NoSuchAlgorithmException 
     */
    public String login(String email, String senha) throws SQLException, NoSuchAlgorithmException{
        
       String res = "";
       String sql = "SELECT * FROM "+this.table+" WHERE email = '"+email+"'";
       this.stmt = this.conn.prepareStatement(sql);
       
       this.rs = this.stmt.executeQuery();

        if (this.rs.next()) {
            if(this.rs.getString(4).equals(valid.generatePass(senha))){
                res = this.rs.getString("id");
            }
        }
        
        return res;
    }
   
}
