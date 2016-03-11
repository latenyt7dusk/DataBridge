/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.database.h2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import nakpil.database.DBEngine;

/**
 *
 * @author Duskmourne
 */
public class H2Database extends DBEngine {

    public static final String DRIVER = "org.h2.Driver";
    private static String SCRIPT;

    private final H2Settings dbSettings;
    private Connection CONNECTION;
    private PreparedStatement PRESTATEMENT;
    private ResultSet RESULT;
    private int ROW;
    private ResultSetMetaData METADATA;
    private InputStream iStream;
    private OutputStream oStream;
    private FileOutputStream foStream;
    private FileInputStream fiStream;

    private List<Map<String, Object>> tableData;

    public H2Database(H2Settings h2s) {
        this.dbSettings = h2s;
    }

    public void clean() {
        this.tableData = null;
    }

    public boolean checkConnection() throws SQLException {
        try {
            if (CONNECTION == null) {
                Class.forName(DRIVER);
                this.CONNECTION = DriverManager.getConnection(this.getSource(), this.getUsername(), this.getPassword());
                return CONNECTION.isValid(1000);
            }
            return false;
        } catch (ClassNotFoundException | SQLException er) {
            Logger.getLogger(H2Database.class.getName()).log(Level.SEVERE, null, er);
            return false;
        } finally {
            if (CONNECTION != null) {
                CONNECTION.close();
                CONNECTION = null;
                System.gc();
            }
        }
    }
    
    public boolean hasDuplicates(final String schema,final String table,final String val,final String colName) throws SQLException{
        try{
            if (CONNECTION == null) {
                Class.forName(DRIVER);
                this.CONNECTION = DriverManager.getConnection(this.getSource(), this.getUsername(), this.getPassword());
                this.PRESTATEMENT = CONNECTION.prepareStatement("SELECT "+colName+" FROM "+((schema.isEmpty()) ? table : schema + "." + table)+" WHERE "+colName+"='"+val+"'");
                this.RESULT = PRESTATEMENT.executeQuery();
                while (RESULT.next()) {
                    return true;
                }
                return false;
            }
            return true;
        }catch(ClassNotFoundException | SQLException er){
            Logger.getLogger(H2Database.class.getName()).log(Level.SEVERE, null, er);
            return true;
        } finally {
            if (CONNECTION != null) {
                RESULT.close();
                PRESTATEMENT.close();
                CONNECTION.close();
                RESULT = null;
                PRESTATEMENT = null;
                CONNECTION = null;
                System.gc();
            }
        }
    }
    
    public boolean run(final String script) throws SQLException{
        try{
            if (CONNECTION == null) {
                Class.forName(DRIVER);
                this.CONNECTION = DriverManager.getConnection(this.getSource(), this.getUsername(), this.getPassword());
                this.PRESTATEMENT = CONNECTION.prepareStatement(script);
                
                return PRESTATEMENT.execute();
            }
            return false;
        }catch(ClassNotFoundException | SQLException er){
            Logger.getLogger(H2Database.class.getName()).log(Level.SEVERE, null, er);
            return false;
        } finally {
            if (CONNECTION != null) {
                PRESTATEMENT.close();
                CONNECTION.close();
                PRESTATEMENT = null;
                CONNECTION = null;
                System.gc();
            }
        }
    }
    
    public List<Map<String, Object>> parse(final String script) throws SQLException{
        try{
            if (CONNECTION == null) {
                Class.forName(DRIVER);
                this.CONNECTION = DriverManager.getConnection(this.getSource(), this.getUsername(), this.getPassword());
                this.PRESTATEMENT = CONNECTION.prepareStatement(script);
                this.RESULT = PRESTATEMENT.executeQuery();
                this.tableData = new ArrayList();
                this.METADATA = RESULT.getMetaData();
                this.ROW = METADATA.getColumnCount();
                while (RESULT.next()) {
                    Map<String, Object> cData = new HashMap();
                    for (int i = 1; i <= ROW; i++) {
                        cData.put(METADATA.getColumnName(i), RESULT.getObject(i));
                    }
                    this.tableData.add(cData);
                }
                return tableData;
            }
            return null;
        }catch(ClassNotFoundException | SQLException er){
            Logger.getLogger(H2Database.class.getName()).log(Level.SEVERE, null, er);
            return null;
        } finally {
            if (CONNECTION != null) {
                RESULT.close();
                PRESTATEMENT.close();
                CONNECTION.close();
                RESULT = null;
                PRESTATEMENT = null;
                CONNECTION = null;
                System.gc();
            }
        }
    }
    
    public List<Map<String, Object>> searchTable(final String schema,final String table,final String colName,final String likeVal,final List<String> cols,final boolean first) throws SQLException{
        try{
            if (CONNECTION == null) {
                Class.forName(DRIVER);
                this.CONNECTION = DriverManager.getConnection(this.getSource(), this.getUsername(), this.getPassword());
                SCRIPT = "SELECT ";
                if (cols != null) {
                    SCRIPT = SCRIPT.concat(cols.get(0));
                    for (int i = 1; i < cols.size(); i++) {
                        SCRIPT = SCRIPT.concat("," + cols.get(i));
                    }
                    SCRIPT = SCRIPT.concat(" FROM " + ((schema.isEmpty()) ? table : schema + "." + table));
                } else {
                    SCRIPT = SCRIPT.concat("* FROM " + ((schema.isEmpty()) ? table : schema + "." + table));
                }
                SCRIPT = SCRIPT.concat(" WHERE "+colName+" LIKE "+((first)?"'":"'%")+likeVal+"%'");
                this.PRESTATEMENT = CONNECTION.prepareStatement(SCRIPT);
                this.RESULT = PRESTATEMENT.executeQuery();
                this.tableData = new ArrayList();
                this.METADATA = RESULT.getMetaData();
                this.ROW = METADATA.getColumnCount();
                while (RESULT.next()) {
                    Map<String, Object> cData = new HashMap();
                    for (int i = 1; i <= ROW; i++) {
                        cData.put(METADATA.getColumnName(i), RESULT.getObject(i));
                    }
                    this.tableData.add(cData);
                }
                return tableData;
            }
            return null;
        }catch(ClassNotFoundException | SQLException er){
            Logger.getLogger(H2Database.class.getName()).log(Level.SEVERE, null, er);
            return null;
        } finally {
            if (CONNECTION != null) {
                RESULT.close();
                PRESTATEMENT.close();
                CONNECTION.close();
                RESULT = null;
                PRESTATEMENT = null;
                CONNECTION = null;
                System.gc();
            }
        }
        
    }

    public List<Map<String, Object>> getTable(final String schema,final String table,final List<String> cols) throws SQLException {
        try {
            if (CONNECTION == null) {
                Class.forName(DRIVER);
                this.CONNECTION = DriverManager.getConnection(this.getSource(), this.getUsername(), this.getPassword());
                SCRIPT = "SELECT ";
                if (cols != null) {
                    SCRIPT = SCRIPT.concat(cols.get(0));
                    for (int i = 1; i < cols.size(); i++) {
                        SCRIPT = SCRIPT.concat("," + cols.get(i));
                    }
                    SCRIPT = SCRIPT.concat(" FROM " + ((schema.isEmpty()) ? table : schema + "." + table));
                } else {
                    SCRIPT = SCRIPT.concat("* FROM " + ((schema.isEmpty()) ? table : schema + "." + table));
                }
                this.PRESTATEMENT = CONNECTION.prepareStatement(SCRIPT);
                this.RESULT = PRESTATEMENT.executeQuery();
                this.tableData = new ArrayList();
                this.METADATA = RESULT.getMetaData();
                this.ROW = METADATA.getColumnCount();
                while (RESULT.next()) {
                    Map<String, Object> cData = new HashMap();
                    for (int i = 1; i <= ROW; i++) {
                        cData.put(METADATA.getColumnName(i), RESULT.getObject(i));
                    }
                    this.tableData.add(cData);
                }
                return tableData;
            }
            return null;
        } catch (ClassNotFoundException | SQLException er) {
            Logger.getLogger(H2Database.class.getName()).log(Level.SEVERE, null, er);
            return null;
        } finally {
            if (CONNECTION != null) {
                RESULT.close();
                PRESTATEMENT.close();
                CONNECTION.close();
                RESULT = null;
                PRESTATEMENT = null;
                CONNECTION = null;
                System.gc();
            }
        }
    }

    public boolean uploadImage(final String schema,final String table,final File img,final String id,final String name) throws SQLException{
        try {
            if(CONNECTION == null){
                Class.forName(DRIVER);
                this.CONNECTION = DriverManager.getConnection(this.getSource(), this.getUsername(), this.getPassword());
                SCRIPT = "INSERT INTO "+((schema.isEmpty()) ? table : schema + "." + table)+" VALUES(?,?,?)";
                this.PRESTATEMENT = CONNECTION.prepareStatement(SCRIPT);
                this.iStream = new FileInputStream(img);
                PRESTATEMENT.setString(1, id);
                PRESTATEMENT.setString(2, name);
                PRESTATEMENT.setBinaryStream(3, iStream, (int) img.length());
                                
                return PRESTATEMENT.execute();
            }
            return false;
        } catch (ClassNotFoundException | SQLException | FileNotFoundException er) {
            Logger.getLogger(H2Database.class.getName()).log(Level.SEVERE, null, er);
            return false;
        } finally {
            if (CONNECTION != null) {
                PRESTATEMENT.close();
                CONNECTION.close();
                PRESTATEMENT = null;
                CONNECTION = null;
                System.gc();
            }
        }
    }
    
    
}
