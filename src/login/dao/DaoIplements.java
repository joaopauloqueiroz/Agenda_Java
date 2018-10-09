/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class DaoIplements implements DaoInterface {

    private Connection conn;
    protected PreparedStatement stmt;
    protected ResultSet rs;
    protected Connections conect;
    protected String table;

    public DaoIplements() {
        conect = new Connections();
    }
    
    /**
     * Buscar todos os registros do banco
     * @return 
     */
    @Override
    public ResultSet getAll() {
        this.conn = this.conect.conexao();
        String sql = "SELECT * FROM " + this.table;
        try {
            this.stmt = this.conn.prepareStatement(sql);
            this.rs = stmt.executeQuery();

        } catch (SQLException e) {
            Logger.getLogger(DaoIplements.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
    
    /**
     * Buscar todos os registros com relacionamento
     * @param id
     * @return 
     */
    @Override
    public ResultSet find(int id) {
        this.conn = this.conect.conexao();
        try {
            String sql = "SELECT * FROM " + this.table + " WHERE user_id = " + id;
            this.stmt = this.conn.prepareStatement(sql);
            this.rs = this.stmt.executeQuery();

        } catch (SQLException e) {
            Logger.getLogger(DaoIplements.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
    
    /*
    *Recebe duas string com os campos e os parametros, que irão ser
    *inseridos
    */
    @Override
    public boolean create(String fields, String parameters) {
        
        this.conn = this.conect.conexao();;
        boolean result = true;

        String sql = "INSERT INTO " + this.table + " (" + fields + ") VALUES (" + parameters + ")";

        try {
            this.stmt = this.conn.prepareStatement(sql);
            result = this.stmt.execute();

        } catch (SQLException ex) {
            return false;
            //Logger.getLogger(DaoIplements.class.getName()).log(Level.SEVERE, null, ex);
            
        }

        return result;
    }

    /**
     * Deletar um registro do banco de dados
     * @param id
     * @return 
     */
    @Override
    public boolean delete(int id) {
        this.conn = this.conect.conexao();
        boolean result = false;
        String sql = "DELETE FROM " + this.table + " WHERE id = " + id;
        try {
            this.stmt = this.conn.prepareStatement(sql);
            result = this.stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoIplements.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    /**
     *Atualizar um registro no banco    
     * @param data
     * @param id
     * @return 
     */
    @Override
    public boolean update(ArrayList data, int id) {
        this.conn = this.conect.conexao();
        boolean result = false;
        String sql = "UPDATE " + this.table + " SET nome = ?, ddd = ?, telefone = ? , email = ? , endereco = ? WHERE id = " + id;
        
        try {
            int position = 1;
            this.stmt = this.conn.prepareStatement(sql);
            for (int i = 0; i < data.size(); i++) {
                this.stmt.setString(position, data.get(i).toString());
                position++;
            }
            position = 1;
            
            result = this.stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoIplements.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    /**
     * Fechar a conexão com o banco de dados
     * @throws SQLException 
     */
    public void close() throws SQLException {
        this.conect.close();
    }
    
    /**
     * Criar a query para inserção de registro no banco
     * @param data
     * @return 
     */
    @Override
    public ArrayList query(Map data) {
        ArrayList form = new ArrayList();
        String query = "";
        String sql = "";
        
        //chaves 
        for (Object key : data.keySet()) {
            query += key + ", ";
        }
        query = query.substring(0, query.length() - 2);
        form.add(query);


        //valores
        for (Object key : data.keySet()) {
            sql += "'" + data.get(key) + "',";
        }
        sql = sql.substring(0, sql.length() - 1);

        form.add(sql);

        return form;
    }
    
    /**
     * Retorna o ultiom id inserido no banco de dados
     * @return 
     */
    @Override
    public int findLast() {
        this.conn = this.conect.conexao();
        int id = 0;
        try {
            String sql = "SELECT * FROM " + this.table + " WHERE id = (SELECT IDENT_CURRENT('"+this.table+"'))";
            this.stmt = this.conn.prepareStatement(sql);
            this.rs = this.stmt.executeQuery();
            rs.next();
             id = (int) rs.getInt("id");
        } catch (SQLException e) {
            Logger.getLogger(DaoIplements.class.getName()).log(Level.SEVERE, null, e);
        }
        return id;
    }
    
    /**
     * Busca um registro especico no banco
     * @param id
     * @return 
     */
    public ResultSet findOne(int id){
        this.conn = this.conect.conexao();
        try {
            String sql = "SELECT * FROM " + this.table + " WHERE id = " + id;
            this.stmt = this.conn.prepareStatement(sql);
            this.rs = this.stmt.executeQuery();

        } catch (SQLException e) {
            Logger.getLogger(DaoIplements.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
}
