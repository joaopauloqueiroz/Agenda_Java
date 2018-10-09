
package login.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Validations {
    
    
    //retornar a senha criptografada
    public String generatePass(String senha ) throws NoSuchAlgorithmException{
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(senha.getBytes());
 
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
          hexString.append(String.format("%02X", 0xFF & b));
        }
        
        return  hexString.toString();
    }
    
    /**
     * verificar se o email e senha está vazio
     * @param email
     * @param senha
     * @return 
     */
    public boolean verify(String email, String senha){
        return !(email.isEmpty() || senha.isEmpty());
    }
    
    /**
     * Verificar se os campos do contato estão vazios
     * @param contato
     * @return 
     */
    public boolean isValid(Contato contato) {
        return !(contato.getNome().isEmpty() || contato.getEmail().isEmpty() || contato.getTelefone().isEmpty() || contato.getEndereco().isEmpty());
    }
    /**
     * Verifica se os campos do usúario estão vazios
     * @param user
     * @return 
     */
    public boolean veriryUser(Usuario user){
        return !(user.getName().isEmpty() || user.getEmail().isEmpty() || user.getPass().isEmpty());
    }
}
