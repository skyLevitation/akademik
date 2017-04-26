/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.akademik.service;

import demo.akademik.koneksi.koneksi;
import demo.akademik.model.Jurusan;
import demo.akademik.repository.RepositoryJurusan;
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
public class ServiceJurusan implements RepositoryJurusan {

    String select = "SELECT * FROM jurusan";
    String insert = "INSERT INTO jurusan(id_jurusan, kd_jurusan, nama_jurusan) VALUES (?,?,?)";
    String update = "UPDATE jurusan SET kd_jurusan = ?, nama_jurusan = ? WHERE id_jurusan = ?";
    String delete = "DELETE FROM jurusan WHERE id_jurusan = ?";
    String cari = "SELECT j.id_jurusan, j.kd_jurusan, j.nama_jurusan FROM jurusan j WHERE j.nama_jurusan LIKE ?";
    
    @Override
    public Jurusan save(Jurusan jurusan) throws SQLException {
        PreparedStatement ps = koneksi.connect().prepareStatement(insert);
        ps.setInt(1, jurusan.getIdJur());
        ps.setString(2, jurusan.getKdJur());
        ps.setString(3, jurusan.getNamaJur());
        
        ps.executeUpdate();
        ps.close();
        return jurusan;
    }

    @Override
    public Jurusan update(Jurusan jurusan) throws SQLException {
        PreparedStatement ps = koneksi.connect().prepareStatement(update);
        
        ps.setString(1, jurusan.getKdJur());
        ps.setString(2, jurusan.getNamaJur());
        ps.setInt(3, jurusan.getIdJur());
        ps.executeUpdate();
        ps.close();
        return jurusan;
    }

    @Override
    public void delete(int idJur) throws SQLException {
        PreparedStatement ps = koneksi.connect().prepareStatement(delete);
        
        ps.setInt(1, idJur);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public List<Jurusan> selectAll() throws SQLException {
        List<Jurusan> daftarJurusan = new ArrayList<>();
        Statement st = koneksi.connect().createStatement();
        ResultSet rs = st.executeQuery(select);
        
        while (rs.next()) {
            Jurusan j = new Jurusan(rs.getInt(1), rs.getString(2), rs.getString(3));
            daftarJurusan.add(j);
        }
        st.close();
        rs.close();
        return daftarJurusan;
    }

    @Override
    public List<Jurusan> findByName(String nama) throws SQLException {
        List<Jurusan> listJur = new ArrayList<>();
        
        PreparedStatement ps = koneksi.connect().prepareStatement(cari);
        ps.setString(1, "%"+ nama +"%");
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Jurusan j = new Jurusan();
            j.setIdJur(rs.getInt("j.id_jurusan"));
            j.setKdJur(rs.getString("j.kd_jurusan"));
            j.setNamaJur(rs.getString("j.nama_jurusan"));
            
            listJur.add(j);
        }
        return listJur;
    }
    
}
