/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Front_end;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import login.model.Usuario;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Edit_User extends JFrame{
    JLabel nome, email;
    JPanel painel;
    JButton btnEdit, btnDelete;
    Usuario user;
    Principal principal;
    public Edit_User(Principal principal, Usuario user){
        this.user = user;
        this.principal = principal;
        
        ButtonHandler listener = new ButtonHandler();
        painel = new JPanel();
        painel.setSize(getWidth(), getHeight());
        painel.setLayout(null);

        nome = new JLabel("Nome: " + user.getName());
        nome.setBounds(10, 20, 350, 30);
        painel.add(nome);

        email = new JLabel("E-mail: "+ user.getEmail());
        email.setBounds(10, 50, 350, 30);
        painel.add(email);

        btnEdit = new JButton("Editar");
        btnEdit.setBounds(60, 200, 100, 30);
        btnEdit.addActionListener(listener);
        
        btnDelete = new JButton("Deletar");
        btnDelete.setBounds(220, 200, 100, 30);
        btnDelete.addActionListener(listener);
        
        painel.add(btnDelete);
        
        //painel.add(btnEdit);
        setContentPane(painel);
        setResizable(false);
        setSize(400, 400);
        setLocation(500, 100);

    }
   public class ButtonHandler extends Application implements ActionListener {
        /**
         * Tratar os eventos de cliques nos botões
         * @param event 
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getActionCommand().equals("Editar")) {
              
            }
            
            if(event.getActionCommand().equals("Deletar")){
                 if(!user.delete(user.getId())){
                     JOptionPane.showMessageDialog(null, "Usuario deletado com sucesso \n Estamos fechando a Aplicação");
                     System.exit(0);
                 }
            }
        }

        @Override
        public void start(Stage tela) throws Exception {
            tela.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    t.consume();
                    tela.close();
                }
            });
        }
    }
   /**
    * Fechar a janela
    */
    public void close() {
        dispose();
    }

}
