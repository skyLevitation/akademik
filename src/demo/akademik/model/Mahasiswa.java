/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.akademik.model;

import java.io.Serializable;

/**
 *
 * @author sky
 */
public class Mahasiswa implements Serializable {
    
    private int idMhs;
    private String npm;
    private String namaMhs;
    private Jurusan jurusan;

    public int getIdMhs() {
        return idMhs;
    }

    public void setIdMhs(int idMhs) {
        this.idMhs = idMhs;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getNamaMhs() {
        return namaMhs;
    }

    public void setNamaMhs(String namaMhs) {
        this.namaMhs = namaMhs;
    }

    public Jurusan getJurusan() {
        return jurusan;
    }

    public void setJurusan(Jurusan jurusan) {
        this.jurusan = jurusan;
    }
    
    
}
