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
import javax.swing.JPanel;
import javax.swing.JTextField;
import login.model.Contato;
import login.model.TableModelContato;
import login.model.Validations;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Form_edit extends JFrame {
    protected TableModelContato tableModelContato;
    
    JLabel nome, ddd, telefone, email, endereco, title;
    JTextField c_nome, c_ddd, c_telefone, c_email, c_endereco;
    JPanel painel;
    JButton btnSend, btnDelete;
    int widDimension, heDimension, dheight;
    int height = 40;
    Contato contato;
    Principal principal;
    public Form_edit(Principal principal, Contato contato) {
        this.contato = contato;
        this.principal = principal;
        ButtonHandler handler = new ButtonHandler();
        widDimension = 375;
        heDimension = 25;
        dheight = 70;
        //====================
        painel = new JPanel();
        painel.setSize(getWidth(), getHeight());
        painel.setLayout(null);
        //====================
        title = new JLabel("Editar Contato");
        title.setBounds(135, 10, 200, 30);
        //====================        
        nome = new JLabel("Nome:");
        nome.setBounds(10, height, 100, 30);
        c_nome = new JTextField(contato.getNome());
        c_nome.setBounds(10, dheight, widDimension, heDimension);
        //====================
        ddd = new JLabel("DDD");
        height = height + 70;
        dheight = dheight + dheight;
        ddd.setBounds(10, height, 100, 30);
        c_ddd = new JTextField(contato.getDdd());
        c_ddd.setBounds(10, dheight, widDimension, heDimension);
        //====================
        height = height + 70;
        dheight = dheight + 70;
        telefone = new JLabel("Telefone");
        telefone.setBounds(10, height, widDimension, heDimension);
        c_telefone = new JTextField(contato.getTelefone());
        c_telefone.setBounds(10, dheight, widDimension, heDimension);
        //====================
        height = height + 70;
        dheight = dheight + 70;
        email = new JLabel("E-mail");
        email.setBounds(10, height, widDimension, heDimension);
        c_email = new JTextField(contato.getEmail());
        c_email.setBounds(10, dheight, widDimension, heDimension);
        //====================
        dheight = dheight + 70;
        height = height + 70;
        endereco = new JLabel("Endereço");
        endereco.setBounds(10, height, widDimension, heDimension);
        c_endereco = new JTextField(contato.getEndereco());
        c_endereco.setBounds(10, dheight, widDimension, heDimension);

        height = height + 100;
        btnSend = new JButton("Atualizar");
        btnSend.setBounds(30, height, 100, 30);
        btnSend.addActionListener(handler);
        
        height = height + 100;
        
        painel.add(title);
        painel.add(nome);
        painel.add(c_nome);
        painel.add(ddd);
        painel.add(c_ddd);
        painel.add(telefone);
        painel.add(c_telefone);
        painel.add(email);
        painel.add(c_email);
        painel.add(endereco);
        painel.add(c_endereco);
        painel.add(btnSend);

        setContentPane(painel);
        setSize(400, 550);
        setLocation(500, 100);
        setResizable(false);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    
    public void limpaForm() {
        
    }

    public class ButtonHandler extends Application implements ActionListener {
            
       /**
        * Tratar os eventos de clique nos botões
        * @param event 
        */
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getActionCommand().equals("Atualizar")) {

                Contato con = new Contato();
                
                Validations valid = new Validations();
                
                con.setNome(c_nome.getText());
                con.setDdd(c_ddd.getText());
                con.setTelefone(c_telefone.getText());
                con.setEmail(c_email.getText());
                con.setEndereco(c_endereco.getText());
                con.setId(contato.getId());
                
                if(!con.update(con)){
                    try {
                        principal.tableModelContato.removeAll();
                        principal.listar();
                    } catch (SQLException ex) {
                        Logger.getLogger(Form_edit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   close();
                }
            }
        }

        //pegar o click do x da janela
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

    public void close() {
        dispose();
    }
}
