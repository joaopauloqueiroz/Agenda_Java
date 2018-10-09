/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Front_end;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import login.model.Usuario;
import login.model.Validations;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class New_Usuario extends JFrame {

    JPanel painel;
    JLabel email, senha, login, nome;
    JTextField txtEmail, txtNome;
    JPasswordField txtSenha;
    JButton btnSend;

    public New_Usuario() {
        ButtonHandler ouvir = new ButtonHandler();
        painel = new JPanel();
        painel.setSize(getWidth(), getHeight());
        painel.setLayout(null);

        login = new JLabel("Faça o seu Cadastro");
        login.setBounds(90, 10, 200, 30);

        nome = new JLabel("Nome");
        nome.setBounds(10, 80, 100, 30);

        txtNome = new JTextField();
        txtNome.setBounds(10, 110, 265, 25);

        email = new JLabel("E-mail:");
        email.setBounds(10, 160, 100, 30);

        txtEmail = new JTextField();
        txtEmail.setBounds(10, 190, 265, 25);

        senha = new JLabel("Senha:");
        senha.setBounds(10, 230, 100, 30);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(10, 270, 265, 25);

        btnSend = new JButton("Salvar");
        btnSend.setBounds(10, 320, 100, 25);
        btnSend.addActionListener(ouvir);
        
        painel.add(btnSend);
        painel.add(nome);
        painel.add(txtNome);
        painel.add(login);
        painel.add(email);
        painel.add(senha);
        painel.add(txtEmail);
        painel.add(txtSenha);

        setContentPane(painel);
        setResizable(false);
        setLocation(150, 150);
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public class ButtonHandler implements ActionListener {

        /**
         * Evento do botão salvar
         * @param event 
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getActionCommand().equals("Salvar")) {

                Usuario user = new Usuario();
                Validations valid = new Validations();
                
                user.setName(txtNome.getText());
                user.setEmail(txtEmail.getText());
                user.setPass(txtSenha.getText());
                
                try {
                    if(user.novo()){
                        JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar novo usuario");          
                    }
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(New_Usuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
