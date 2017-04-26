/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.akademik;

import demo.akademik.model.Mahasiswa;
import demo.akademik.repository.RepositoryJurusan;
import demo.akademik.repository.RepositoryMahasiswa;
import demo.akademik.service.ServiceJurusan;
import demo.akademik.service.ServiceMahasiswa;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sky
 */
public class CobaMahasiswa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        RepositoryJurusan repo = new ServiceJurusan();
        RepositoryMahasiswa repoMhs = new ServiceMahasiswa();
        
        List<Mahasiswa> listMhs = repoMhs.selectAll();
        System.out.println("Jumlah Data Mahasiswa : " + listMhs.size());
        for (Mahasiswa mahasiswa : listMhs) {
            System.out.println("NPM : " + mahasiswa.getNpm() + ", Nama : " + mahasiswa.getNamaMhs() + ", Nama Jurusan : " + mahasiswa.getJurusan().getNamaJur());
            
        }
        
    }
    
}
