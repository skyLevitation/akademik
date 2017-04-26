/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.akademik;

import demo.akademik.model.Jurusan;
import demo.akademik.repository.RepositoryJurusan;
import demo.akademik.service.ServiceJurusan;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 *
 * @author sky
 */
public class CobaJurusan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader dataIn = new BufferedReader(is);
        
        RepositoryJurusan repoJ = new ServiceJurusan();
        
         try {
            System.out.println("------- JURUSAN -------");
            System.out.print("ID : ");
            int Id = Integer.parseInt(dataIn.readLine());
            System.out.print("KODE JURUSAN : ");
            String Kode = dataIn.readLine();
            System.out.print("NAMA JURUSAN : ");
            String Nama = dataIn.readLine();
            
            Jurusan jurusan = new Jurusan(Id, Kode, Nama);
            repoJ.save(jurusan);
            
            System.out.println("SELECT ALL -------------------");
            List<Jurusan> daftarJurusan = repoJ.selectAll();
            System.out.println("Jumlah data jurusan  : " + daftarJurusan.size());
            for (Jurusan jur : daftarJurusan) {
                System.out.println("Id  : " + jur.getIdJur() + ", Kode  : " + jur.getKdJur()+ ", Namanya : " + jur.getNamaJur());
            }
        } catch (Exception e) {
            System.out.println("gagal input " + e);
        }
    }
    
}
