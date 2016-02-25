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
public class DBEngine implements ConnectionProfile{
    
    private String Driver,Source,Username,Password,Name;

    @Override
    public void setDriverClass(String s) {
        this.Driver = s;
    }

    @Override
    public String getDriverClass() {
        return Driver;
    }

    @Override
    public void setSource(String s) {
        this.Source = s;
    }

    @Override
    public String getSource() {
        return Source;
    }

    @Override
    public void setUsername(String s) {
        this.Username = s;
    }

    @Override
    public String getUsername() {
        return Username;
    }

    @Override
    public void setPassword(String s) {
        this.Password = s;
    }

    @Override
    public String getPassword() {
        return Password;
    }

    @Override
    public void setName(String s) {
        this.Name = s;
    }

    @Override
    public String getName() {
        return Name;
    }
    
}
