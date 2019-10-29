/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Authorities;

/**
 *
 * @author paniagua
 */
public interface AuthoritiesDao {
    
    public List<Authorities> listAllUsers() throws Exception;
    
    public Authorities findbyUserPass(String user, String pass) throws Exception;
    
    
}
