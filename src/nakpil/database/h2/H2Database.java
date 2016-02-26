/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.database.h2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import nakpil.database.DBEngine;

/**
 *
 * @author Duskmourne
 */
public class H2Database extends DBEngine{
    
    private H2Settings dbSettings;
    private Connection CONNECTION;
    private PreparedStatement PRESTATEMENT;
    private ResultSet RESULT;
    private InputStream iStream;
    private OutputStream oStream;
    private FileOutputStream foStream;
    private FileInputStream fiStream;
    
    private List<Map<String,String>> tableData;
    
    
    public H2Database(H2Settings h2s){
        this.dbSettings = h2s;        
    }
    
    
    
    
}
