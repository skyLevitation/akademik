/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.akademik.koneksi;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author sky
 */
public class koneksi {
    
    static Connection conn;
    
    public static Connection connect(){
        if (conn == null) {
            MysqlDataSource data = new MysqlDataSource();
            data.setDatabaseName("demo_akademik");
            data.setUser("root");
            data.setPassword("");
            try {
                conn = data.getConnection();
            } catch (SQLException ex) {
                System.out.println("Gagal Koneksi "+ex);
            }
        }
        return conn;
    }
}
