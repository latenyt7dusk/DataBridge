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
public abstract interface DBSettings {    
    
    public static final String LOCAL_DB_SOURCE = "LOCAL_SOURCE";
    public static final String SERVER_DB_SOURCE = "SERVER_SOURCE";
    public static final String LOCAL_DB_USERNAME = "LOCAL_DB_USERNAME";
    public static final String LOCAL_DB_PASSWORD = "LOCAL_DB_PASSWORD";
    public static final String SERVER_DB_USERNAME = "SERVER_DB_USERNAME";
    public static final String SERVER_DB_PASSWORD = "SERVER_DB_PASSWORD";
    public static final String LOCAL_DB_PORT = "LOCAL_DB_PORT";
    public static final String SERVER_DB_PORT = "SERVER_DB_PORT";
    
    public abstract void setLocalDatabaseSource(String s);
    public abstract String getLocalDatabaseSource();
    public abstract void setServerDatabaseSource(String s);
    public abstract String getServerDatabaseSource();
    public abstract void setLocalUsername(String s);
    public abstract String getLocalUsername();
    public abstract void setLocalPassword(String s);
    public abstract String getLocalPassword();
    public abstract void setServerUsername(String s);
    public abstract String getServerUsername();
    public abstract void setServerPassword(String s);
    public abstract String getServerPassword();
    public abstract void setLocalPort(String s);
    public abstract String getLocalPort();
    public abstract void setServerPort(String s);
    public abstract String getServerPort();
    
}
