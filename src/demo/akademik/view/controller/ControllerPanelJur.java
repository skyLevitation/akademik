/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.akademik.view.controller;


import demo.akademik.model.Jurusan;
import demo.akademik.repository.RepositoryJurusan;
import demo.akademik.service.ServiceJurusan;
import demo.akademik.view.panelJurusan;
import demo.akademik.view.panelMahasiswa;
import demo.akademik.view.table.model.tableModelJurusan;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author sky
 */
public class ControllerPanelJur {
    panelMahasiswa PanelMhs;
    panelJurusan PanelJur;
    RepositoryJurusan repoJur;
    List<Jurusan> Jur;

    public ControllerPanelJur(panelJurusan PanelJur) throws SQLException {
        this.PanelJur = PanelJur;
        repoJur = new ServiceJurusan();
        Jur = repoJur.selectAll();
    }

    public void showTableJurusan() throws SQLException {
        Jur = repoJur.selectAll();
        tableModelJurusan tmj = new tableModelJurusan(Jur);
        PanelJur.getTableJurusan().setModel(tmj);
        
    }

    public void insert() throws SQLException {
        if (!PanelJur.getTxtIdJurusan().getText().isEmpty()) {
            Jurusan j = new Jurusan();
            
            j.setIdJur(Integer.parseInt(PanelJur.getTxtIdJurusan().getText()));
            j.setKdJur(PanelJur.getTxtKodeJurusan().getText());
            j.setNamaJur(PanelJur.getTxtNamaJurusan().getText());
            
            repoJur.save(j);
        } else {
            JOptionPane.showMessageDialog(PanelJur, "masukkan ID Jurusan!");
        }
    }

    public void reset() {
        PanelJur.getTxtIdJurusan().setText("");
        PanelJur.getTxtKodeJurusan().setText("");
        PanelJur.getTxtNamaJurusan().setText("");
        PanelJur.getTxtCari().setText("");
        PanelJur.getTableJurusan().clearSelection();
        
    }

    public void update() throws SQLException {
        if (!PanelJur.getTxtIdJurusan().getText().isEmpty()) {
            Jurusan j = new Jurusan();
            
            j.setIdJur(Integer.parseInt(PanelJur.getTxtIdJurusan().getText()));
            j.setKdJur(PanelJur.getTxtKodeJurusan().getText());
            j.setNamaJur(PanelJur.getTxtNamaJurusan().getText());
           
            repoJur.update(j);
        } else {
            JOptionPane.showMessageDialog(PanelJur, "Pilih data yang akan di Ubah !");
        }
    }

    public void delete() throws SQLException {
        if (!PanelJur.getTxtIdJurusan().getText().isEmpty()) {
            int ID = Integer.parseInt(PanelJur.getTxtIdJurusan().getText());
            repoJur.delete(ID);
        } else {
            JOptionPane.showMessageDialog(PanelJur, "Pilih data yang akan dihapus !");
        }
    }

    public void findByName() throws SQLException {
        if (!PanelJur.getTxtCari().getText().isEmpty()) {
            repoJur.findByName(PanelJur.getTxtCari().getText());
            filterTableJur();
        } else {
            JOptionPane.showMessageDialog(PanelJur, "SILAHKAN PILIH DATA");
        }
    }

    public void clickedTableJurusan(int row) {
        PanelJur.getTxtIdJurusan().setText(String.valueOf(Jur.get(row).getIdJur()));
        PanelJur.getTxtKodeJurusan().setText(Jur.get(row).getKdJur());
        PanelJur.getTxtNamaJurusan().setText(Jur.get(row).getNamaJur());
    }

    private void filterTableJur() throws SQLException {
        Jur = repoJur.findByName(PanelJur.getTxtCari().getText());
        tableModelJurusan tmj = new tableModelJurusan(Jur);
        PanelJur.getTableJurusan().setModel(tmj);
    }
    
    
}
