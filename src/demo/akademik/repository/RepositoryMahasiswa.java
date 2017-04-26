/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.akademik.repository;

import demo.akademik.model.Mahasiswa;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sky
 */
public interface RepositoryMahasiswa {
    
    public Mahasiswa save(Mahasiswa mhs) throws SQLException;
    
    public Mahasiswa update(Mahasiswa mhs) throws SQLException;
    
    public void delete(String NpmMhs) throws SQLException;
    
    public List<Mahasiswa> selectAll() throws SQLException;
    
    public List<Mahasiswa> findByName(String nama) throws SQLException;
    
}
