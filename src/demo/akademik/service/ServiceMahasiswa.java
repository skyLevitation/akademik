/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.akademik.service;

import demo.akademik.koneksi.koneksi;
import demo.akademik.model.Jurusan;
import demo.akademik.model.Mahasiswa;
import demo.akademik.repository.RepositoryMahasiswa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sky
 */
public class ServiceMahasiswa implements RepositoryMahasiswa {

    String select = "SELECT m.npm, m.nama_mhs, j.kd_jurusan, j.nama_jurusan FROM mahasiswa m LEFT OUTER JOIN jurusan j ON (m.kd_jurusan = j.kd_jurusan)";
    String insert = "INSERT INTO mahasiswa(id_mhs, npm, nama_mhs, kd_jurusan) VALUES (?,?,?,?)";
    String update = "UPDATE mahasiswa SET nama_mhs = ?, kd_jurusan = ? WHERE npm = ?";
    String delete = "DELETE FROM mahasiswa WHERE npm = ?";
    String cari = "SELECT m.npm, m.nama_mhs, j.kd_jurusan, j.nama_jurusan FROM mahasiswa m LEFT OUTER JOIN jurusan j ON (m.kd_jurusan = j.kd_jurusan) WHERE m.nama_mhs LIKE ?";
    
    @Override
    public Mahasiswa save(Mahasiswa mhs) throws SQLException {
        PreparedStatement ps = koneksi.connect().prepareStatement(insert);
        ps.setInt(1, mhs.getIdMhs());
        ps.setString(2, mhs.getNpm());
        ps.setString(3, mhs.getNamaMhs());
        ps.setString(4, mhs.getJurusan().getKdJur());
        ps.executeUpdate();
        ps.close();
        return mhs;
    }

    @Override
    public Mahasiswa update(Mahasiswa mhs) throws SQLException {
        PreparedStatement ps = koneksi.connect().prepareStatement(update);
        
        ps.setString(1, mhs.getNamaMhs());
        ps.setString(2, mhs.getJurusan().getKdJur());
        ps.setString(3, mhs.getNpm());
        ps.executeUpdate();
        ps.close();
        return mhs;
    }

    @Override
    public void delete(String NpmMhs) throws SQLException {
        PreparedStatement ps = koneksi.connect().prepareStatement(delete);
        ps.setString(1, NpmMhs);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public List<Mahasiswa> selectAll() throws SQLException {
        List<Mahasiswa> listMhs = new ArrayList<>();
        
        Statement st = koneksi.connect().createStatement();
        ResultSet rs = st.executeQuery(select);
        
        while (rs.next()) {
            Mahasiswa m = new Mahasiswa();
            m.setNpm(rs.getString(1));
            m.setNamaMhs(rs.getString(2));
            
            // mendapatkan nilai jurusan dari kolom 3 dan ke 4
            Jurusan j = new Jurusan(rs.getString(3), rs.getString(4));
            m.setJurusan(j);
            listMhs.add(m);
        }
        st.close();
        rs.close();
        return listMhs;
    }

    @Override
    public List<Mahasiswa> findByName(String nama) throws SQLException {
        List<Mahasiswa> listMhs = new ArrayList<>();
        
        PreparedStatement ps = koneksi.connect().prepareStatement(cari);
        ps.setString(1, "%"+ nama + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Mahasiswa m = new Mahasiswa();
            m.setNpm(rs.getString("m.npm"));
            m.setNamaMhs(rs.getString("m.nama_mhs"));
            
            // mendapatkan nilai jurusan dari kolom 3 dan ke 4
            Jurusan j = new Jurusan(rs.getString("j.kd_jurusan"), rs.getString("j.nama_jurusan"));
            m.setJurusan(j);
            listMhs.add(m);
        }
        return listMhs;
    }
    
}
