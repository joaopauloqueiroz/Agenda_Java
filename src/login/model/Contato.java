/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import login.dao.DaoIplements;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Contato extends DaoIplements {

    Validations valid = new Validations();

    public Contato() {
        this.table = "contato";
    }

    String nome, ddd, telefone, email, endereco, title;
    int id;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Cria um novo contato
     * @return 
     */
    public boolean novo() {
        Map<String, String> data = new HashMap<>();
        
        if (valid.isValid(this)) {
            data.put("nome", this.getNome());
            data.put("ddd", this.getDdd());
            data.put("telefone", this.getTelefone());
            data.put("email", this.getEmail());
            data.put("endereco", this.getEndereco());
            
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
    
    /**
     * Atualiza um contato
     * @param contato
     * @return 
     */
    public boolean update(Contato contato){
        ArrayList con = new ArrayList();
        boolean result = false;
        con.add(contato.getNome());
        con.add(contato.getDdd());
        con.add(contato.getTelefone());
        con.add(contato.getEmail());
        con.add(contato.getEndereco());
        
        System.out.println(this.getNome());
        System.out.println(valid.isValid(this));
        if(valid.isValid(this)){
          result = this.update(con, contato.getId());
            
        }else{
            JOptionPane.showMessageDialog(null, "Todos os campos precisam ser preenchidos");
        }
        
        return result;
    }
    /**
     * Busca por um contato especifico
     * @param id
     * @return 
     */
    public ResultSet findOn(int id) {
        ResultSet rs = this.findOne(id);
      
        return rs;
    }
    
}
