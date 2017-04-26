/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.akademik.repository;

import demo.akademik.model.Jurusan;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sky
 */
public interface RepositoryJurusan {
    
    public Jurusan save(Jurusan jurusan) throws SQLException;
    
    public Jurusan update(Jurusan jurusan) throws SQLException;
    
    public void delete(int idJur) throws SQLException;
    
    public List<Jurusan> selectAll() throws SQLException;
    
    public List<Jurusan> findByName(String nama) throws SQLException;
    
}
