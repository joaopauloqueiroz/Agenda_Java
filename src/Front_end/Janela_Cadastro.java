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
import javax.swing.JPanel;
import javax.swing.JTextField;
import login.model.Contato;
import login.model.TableModelContato;
import login.model.Validations;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Janela_Cadastro extends JFrame {
    protected TableModelContato tableModelContato;
    
    JLabel nome, ddd, telefone, email, endereco, title;
    JTextField c_nome, c_ddd, c_telefone, c_email, c_endereco;
    JPanel painel;
    JButton btnSend, btnCancel;
    int widDimension, heDimension, dheight;
    int height = 40;

    public Janela_Cadastro(Principal contato) {
        this.tableModelContato = contato.tableModelContato;
        ButtonHandler handler = new ButtonHandler();
        widDimension = 375;
        heDimension = 25;
        dheight = 70;
        //====================
        painel = new JPanel();
        painel.setSize(getWidth(), getHeight());
        painel.setLayout(null);
        //====================
        title = new JLabel("Formulario de Cadatro");
        title.setBounds(135, 10, 200, 30);
        //====================        
        nome = new JLabel("Nome:");
        nome.setBounds(10, height, 100, 30);
        c_nome = new JTextField();
        c_nome.setBounds(10, dheight, widDimension, heDimension);
        //====================
        ddd = new JLabel("DDD");
        height = height + 70;
        dheight = dheight + dheight;
        ddd.setBounds(10, height, 100, 30);
        c_ddd = new JTextField();
        c_ddd.setBounds(10, dheight, widDimension, heDimension);
        //====================
        height = height + 70;
        dheight = dheight + 70;
        telefone = new JLabel("Telefone");
        telefone.setBounds(10, height, widDimension, heDimension);
        c_telefone = new JTextField();
        c_telefone.setBounds(10, dheight, widDimension, heDimension);
        //====================
        height = height + 70;
        dheight = dheight + 70;
        email = new JLabel("E-mail");
        email.setBounds(10, height, widDimension, heDimension);
        c_email = new JTextField();
        c_email.setBounds(10, dheight, widDimension, heDimension);
        //====================
        dheight = dheight + 70;
        height = height + 70;
        endereco = new JLabel("Endereço");
        endereco.setBounds(10, height, widDimension, heDimension);
        c_endereco = new JTextField();
        c_endereco.setBounds(10, dheight, widDimension, heDimension);

        height = height + 100;
        btnSend = new JButton("Salvar");
        btnSend.setBounds(30, height, 100, 30);
        btnSend.addActionListener(handler);

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
        ddd.setText("");
        nome.setText("");
        telefone.setText("");
        email.setText("");
        endereco.setText("");
    }

    public class ButtonHandler extends Application implements ActionListener {
            
        /**
         * Tratar os eventos de clickes nos botoes 
         * @param event 
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getActionCommand().equals("Salvar")) {

                Contato con = new Contato();
                
                Validations valid = new Validations();

                con.setNome(c_nome.getText());
                con.setDdd(c_ddd.getText());
                con.setTelefone(c_telefone.getText());
                con.setEmail(c_email.getText());
                con.setEndereco(c_endereco.getText());
               
                if (con.novo()) {
                    con.setId(con.findLast());
                    tableModelContato.addContato(con);
                    dispose();
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
