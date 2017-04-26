/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.akademik.view.controller;

import demo.akademik.model.Jurusan;
import demo.akademik.model.Mahasiswa;
import demo.akademik.repository.RepositoryJurusan;
import demo.akademik.repository.RepositoryMahasiswa;
import demo.akademik.service.ServiceJurusan;
import demo.akademik.service.ServiceMahasiswa;
import demo.akademik.view.panelMahasiswa;
import demo.akademik.view.table.model.tableModelJurusan;
import demo.akademik.view.table.model.tableModelMhs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author sky
 */
public class ControllerPanelMhs {
    
    panelMahasiswa PanelMhs;
    RepositoryMahasiswa repoMhs;
    List<Mahasiswa> listMhs;
    
    RepositoryJurusan repoJur;
    List<Jurusan> listJur;
    
    public ControllerPanelMhs(panelMahasiswa PanelMhs) throws SQLException {
        this.PanelMhs = PanelMhs;
        repoMhs = new ServiceMahasiswa();
        listMhs = repoMhs.selectAll();
        repoJur = new ServiceJurusan();
        listJur = repoJur.selectAll();
    }
    
    // menampilkan data Mahasiswa kedalam tableMahasiswa
    public void showTableMahasiswa() throws SQLException{
        repoMhs = new ServiceMahasiswa();
        listMhs = repoMhs.selectAll();
        tableModelMhs tmMhs = new tableModelMhs(listMhs);
        PanelMhs.getTableMahasiswa().setModel(tmMhs);
    }
    
    // menampilkan data Jurusan kedalam tableJurusan
    public void showTableJurusan() throws SQLException{
        repoJur = new ServiceJurusan();
        listJur = repoJur.selectAll();
        tableModelJurusan tmJur = new tableModelJurusan(listJur);
        PanelMhs.getTableJurusan().setModel(tmJur);
    }
    
    // menampilkan data Jurusan kedalam ComboBox
    public void showComboBoxJurusan() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_akademik","root",null);
        Statement st = con.createStatement();
        String sql = "SELECT kd_jurusan FROM jurusan order by kd_jurusan asc";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {            
            Object[] ob  = new Object[10];
            ob[0] = rs.getString(1);
            
            PanelMhs.getComboJurusan().addItem(ob[0]);
        }
    }

    public void insert() throws SQLException {
        if (!PanelMhs.getTxtNPM().getText().isEmpty()) {
            Mahasiswa m = new Mahasiswa();
            //String KdJur = PanelMhs.getTxtKdJurusan().getText();
            String KdJur = PanelMhs.getComboJurusan().getSelectedItem().toString();
            Jurusan jurusan = new Jurusan(KdJur);
            m.setNpm(PanelMhs.getTxtNPM().getText());
            m.setNamaMhs(PanelMhs.getTxtNamaMhs().getText());
            m.setJurusan(jurusan);
            repoMhs.save(m);
        } else {
            JOptionPane.showMessageDialog(PanelMhs, "masukkan NPM!");
        }
    }

    public void reset() {
        PanelMhs.getTxtNPM().setText("");
        PanelMhs.getTxtNamaMhs().setText("");
        PanelMhs.getTxtKdJurusan().setText("");
        PanelMhs.getTxtCari().setText("");
        PanelMhs.getTableMahasiswa().clearSelection();
        PanelMhs.getTableJurusan().clearSelection();
        PanelMhs.getComboJurusan().setSelectedIndex(0);
    }

    public void update() throws SQLException {
        if (!PanelMhs.getTxtNPM().getText().isEmpty()) {
            Mahasiswa m = new Mahasiswa();
            //String KdJur = PanelMhs.getTxtKdJurusan().getText();
            String KdJur = PanelMhs.getComboJurusan().getSelectedItem().toString();
            Jurusan jurusan = new Jurusan(KdJur);
            m.setJurusan(jurusan);
            m.setNamaMhs(PanelMhs.getTxtNamaMhs().getText());
            m.setNpm(PanelMhs.getTxtNPM().getText());

            repoMhs.update(m);
        } else {
            JOptionPane.showMessageDialog(PanelMhs, "Pilih data yang akan di Ubah !");
        }
    }

    public void delete() throws SQLException {
        if (!PanelMhs.getTxtNPM().getText().isEmpty()) {
            String NPM = PanelMhs.getTxtNPM().getText();
            repoMhs.delete(NPM);
        } else {
            JOptionPane.showMessageDialog(PanelMhs, "Pilih data yang akan dihapus !");
        }
    }

    public void findByName() throws SQLException {
        if (!PanelMhs.getTxtCari().getText().isEmpty()) {
            repoMhs.findByName(PanelMhs.getTxtCari().getText());
            filterTableMhs();
        } else {
            JOptionPane.showMessageDialog(PanelMhs, "SILAHKAN PILIH DATA");
        }
    }

    public void clickedTableMhs(int row) {
        PanelMhs.getTxtNPM().setText(listMhs.get(row).getNpm());
        PanelMhs.getTxtNamaMhs().setText(listMhs.get(row).getNamaMhs());
        //PanelMhs.getTxtKdJurusan().setText(listMhs.get(row).getJurusan().getKdJur());
        PanelMhs.getComboJurusan().setSelectedItem(listMhs.get(row).getJurusan().getKdJur());
    }

    private void filterTableMhs() throws SQLException {
        listMhs = repoMhs.findByName(PanelMhs.getTxtCari().getText());
        tableModelMhs tmMhs = new tableModelMhs(listMhs);
        PanelMhs.getTableMahasiswa().setModel(tmMhs);
    }
}
