package login.model;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import login.dao.DaoUsuario;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */

public class Usuario extends DaoUsuario{
    private String name;
    private String email;
    private String pass;
    private int id;
   Validations valid = new Validations();
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    /***
     * Verifica se o login e valido ou não
     * @return
     * @throws SQLException
     * @throws NoSuchAlgorithmException 
     */
    public String logins() throws SQLException, NoSuchAlgorithmException{
        return login(this.getEmail(), this.getPass());     
    }
    
    /**
     * Prepara os dados para inserção de um registro
     * @return
     * @throws NoSuchAlgorithmException 
     */
     public boolean novo() throws NoSuchAlgorithmException{
          Map<String, String> data = new HashMap<>();
          data.put("name", this.getName());
          data.put("email", this.getEmail());
          data.put("pass", valid.generatePass(this.getPass()));
          
           if (valid.veriryUser(this)) {
            if (!this.create(this.query(data).get(0).toString(), this.query(data).get(1).toString())) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Ops, Algo deu errado! Verifique o log!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Todos os campos precisam ser preenchidos");
        }
        return false;
     }
}
