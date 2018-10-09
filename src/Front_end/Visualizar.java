/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Front_end;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import login.model.Contato;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Visualizar extends JFrame {

    JLabel nome, email, telefone, endereco;
    JPanel painel;
    JButton btnEdit, btnDelete;
    Contato contato;
    Principal principal;
    public Visualizar(Principal principal, Contato con) {
        contato = con;
        this.principal = principal;
        System.out.println(con.getId());
        ButtonHandler listener = new ButtonHandler();
        painel = new JPanel();
        painel.setSize(getWidth(), getHeight());
        painel.setLayout(null);

        nome = new JLabel("Nome: " + con.getNome());
        nome.setBounds(10, 20, 350, 30);
        painel.add(nome);

        email = new JLabel("E-mail: "+ con.getEmail());
        email.setBounds(10, 50, 350, 30);
        painel.add(email);

        telefone = new JLabel("Telefone: " + con.getTelefone());
        telefone.setBounds(10, 80, 350, 30);
        painel.add(telefone);

        endereco = new JLabel("Endereço: " + con.getEndereco());
        endereco.setBounds(10, 110, 350, 30);
        painel.add(endereco);

        btnEdit = new JButton("Editar");
        btnEdit.setBounds(60, 200, 100, 30);
        btnEdit.addActionListener(listener);
        
        btnDelete = new JButton("Deletar");
        btnDelete.setBounds(220, 200, 100, 30);
        btnDelete.addActionListener(listener);
        
        painel.add(btnDelete);
        
        painel.add(btnEdit);
        setContentPane(painel);
        setResizable(false);
        setSize(400, 400);
        setLocation(500, 100);

    }

    public class ButtonHandler extends Application implements ActionListener {
        /**
         * Evento dos botões 
         * @param event 
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getActionCommand().equals("Editar")) {
               Form_edit jan = new Form_edit(principal, contato);
               jan.setVisible(true);
            }
            /**
             * Evento do botão deletar
             */
            if(event.getActionCommand().equals("Deletar")){
                Contato con = new Contato();
                if(!con.delete(contato.getId())){
                    JOptionPane.showMessageDialog(null, "Usúario deletado com sucesso");
                    close();
                    try {
                        principal.tableModelContato.removeAll();
                        principal.listar();
                    } catch (SQLException ex) {
                        Logger.getLogger(Visualizar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        /**
         * Pra quando clicar no X a janela fechar e libear a memoria
         * @param tela
         * @throws Exception 
         */
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
