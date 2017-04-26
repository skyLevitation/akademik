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
public class Jurusan implements Serializable {
    
    private int idJur;
    private String kdJur;
    private String namaJur;

    public Jurusan() {
    }

    public Jurusan(int idJur, String kdJur) {
        this.idJur = idJur;
        this.kdJur = kdJur;
    }

    public Jurusan(String kdJur) {
        this.kdJur = kdJur;
    }

    public Jurusan(String kdJur, String namaJur) {
        this.kdJur = kdJur;
        this.namaJur = namaJur;
    }
    
    public Jurusan(int idJur, String kdJur, String namaJur) {
        this.idJur = idJur;
        this.kdJur = kdJur;
        this.namaJur = namaJur;
    }

    public int getIdJur() {
        return idJur;
    }

    public void setIdJur(int idJur) {
        this.idJur = idJur;
    }

    public String getKdJur() {
        return kdJur;
    }

    public void setKdJur(String kdJur) {
        this.kdJur = kdJur;
    }

    public String getNamaJur() {
        return namaJur;
    }

    public void setNamaJur(String namaJur) {
        this.namaJur = namaJur;
    }
    
}
