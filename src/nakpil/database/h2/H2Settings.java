/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.database.h2;

import java.util.HashMap;
import java.util.Map;
import nakpil.database.DBSettings;

/**
 *
 * @author Duskmourne
 */
public class H2Settings implements DBSettings{

    private final Map<String,String> Data = new HashMap(){
        {
            put(LOCAL_DB_SOURCE,"");
            put(SERVER_DB_SOURCE,"");
            put(LOCAL_DB_USERNAME,"");
            put(LOCAL_DB_PASSWORD,"");
            put(SERVER_DB_USERNAME,"");
            put(SERVER_DB_PASSWORD,"");
            
            put(LOCAL_DB_PORT,"");
            put(SERVER_DB_PORT,"");
        }
    };
    
    
    @Override
    public void setLocalDatabaseSource(String s) {
        this.Data.put(LOCAL_DB_SOURCE, s);
    }

    @Override
    public String getLocalDatabaseSource() {
        return Data.get(LOCAL_DB_SOURCE);
    }

    @Override
    public void setServerDatabaseSource(String s) {
        this.Data.put(SERVER_DB_SOURCE, s);
    }

    @Override
    public String getServerDatabaseSource() {
        return Data.get(SERVER_DB_SOURCE);
    }

    @Override
    public void setLocalUsername(String s) {
        this.Data.put(LOCAL_DB_USERNAME, s);
    }

    @Override
    public String getLocalUsername() {
        return Data.get(LOCAL_DB_USERNAME);
    }

    @Override
    public void setLocalPassword(String s) {
        this.Data.put(LOCAL_DB_PASSWORD, s);
    }

    @Override
    public String getLocalPassword() {
        return Data.get(LOCAL_DB_PASSWORD);
    }

    @Override
    public void setServerUsername(String s) {
        this.Data.put(SERVER_DB_USERNAME, s);
    }

    @Override
    public String getServerUsername() {
        return Data.get(SERVER_DB_USERNAME);
    }

    @Override
    public void setServerPassword(String s) {
        this.Data.put(SERVER_DB_PASSWORD, s);
    }

    @Override
    public String getServerPassword() {
        return Data.get(SERVER_DB_PASSWORD);
    }

    @Override
    public void setLocalPort(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLocalPort() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setServerPort(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getServerPort() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
