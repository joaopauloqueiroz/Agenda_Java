/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import Front_end.Janela_Login;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Login {

    /**
     * @param args the command line arguments
     * @throws java.security.NoSuchAlgorithmException
     */
    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {
        /**
         * Inicia a janela de login
         */
        Janela_Login jan = new Janela_Login();
        jan.setVisible(true);
    }
}
