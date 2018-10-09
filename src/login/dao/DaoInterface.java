/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface DaoInterface {
    /**
     * Interface de implementação
     * @return 
     */
    public ResultSet getAll();
    public ResultSet find(int id);
    public int findLast();
    public boolean create(String data, String prameters);
    public boolean delete(int id);
    public boolean update(ArrayList data, int id);
    public ArrayList query(Map data);
    
}
