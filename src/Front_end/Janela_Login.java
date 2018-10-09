/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Front_end;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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
public class Janela_Login extends JFrame {

    JPanel painel;
    JLabel email, senha, login;
    public JButton btnSend, btnNovo;
    JTextField txtEmail;
    JPasswordField txtSenha;

    public Janela_Login() {
        ButtonHandler ouvir = new ButtonHandler();
        painel = new JPanel();
        painel.setSize(getWidth(), getHeight());
        painel.setLayout(null);

        login = new JLabel("Faça o seu Login");
        login.setBounds(90, 10, 100, 30);

        email = new JLabel("E-mail:");
        email.setBounds(10, 50, 100, 30);

        txtEmail = new JTextField();
        txtEmail.setBounds(10, 90, 265, 25);

        senha = new JLabel("Senha:");
        senha.setBounds(10, 140, 100, 30);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(10, 180, 265, 25);

        btnSend = new JButton("Login");
        btnSend.setBounds(40, 280, 100, 25);
        btnSend.addActionListener(ouvir);
        
        btnNovo = new JButton("Cadastrar");
        btnNovo.setBounds(150, 280, 100, 25);
        btnNovo.addActionListener(ouvir);
        painel.add(btnNovo);
        
        painel.add(login);
        painel.add(email);
        painel.add(senha);
        painel.add(txtEmail);
        painel.add(txtSenha);
        painel.add(btnSend);

        setContentPane(painel);
        setResizable(false);
        setLocation(150, 150);
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public class ButtonHandler implements ActionListener {

       /**
        * Trata os eventos dos botões 
        * @param event 
        */
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getActionCommand().equals("Login")) {

                Usuario user = new Usuario();
                Validations valid = new Validations();

                if (valid.verify(txtEmail.getText(), txtSenha.getText())) {
                    user.setEmail(txtEmail.getText());
                    user.setPass(txtSenha.getText());
                    try {
                        String id = user.logins();
                        System.out.println(id +" id user gui");
                        if (!id.isEmpty()) {
                            Principal pr = new Principal(id);
                            pr.setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao Fazer Login");
                            txtEmail.setText("");
                            txtSenha.setText("");
                            txtEmail.grabFocus();
                        }
                    } catch (SQLException | NoSuchAlgorithmException ex) {
                        Logger.getLogger(Janela_Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Rodos os campos precisam ser preenchidos!");
                    txtEmail.grabFocus();

                }

            }
            if(event.getActionCommand().equals("Cadastrar")){
                new New_Usuario().setVisible(true);
            }
        }
    }

}
