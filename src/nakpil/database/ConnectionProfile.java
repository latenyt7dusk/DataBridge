/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.database;

/**
 *
 * @author Duskmourne
 */
public abstract interface ConnectionProfile {
    
    public abstract void setDriverClass(String s);
    public abstract String getDriverClass();
    
    public abstract void setSource(String s);
    public abstract String getSource();
    
    public abstract void setUsername(String s);
    public abstract String getUsername();
    
    public abstract void setPassword(String s);
    public abstract String getPassword();
    
    public abstract void setName(String s);
    public abstract String getName();
    
}
