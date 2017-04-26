/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.akademik.view.table.model;

import demo.akademik.model.Mahasiswa;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sky
 */
public class tableModelMhs extends AbstractTableModel {

    List<Mahasiswa> listMhs;

    public tableModelMhs(List<Mahasiswa> listMhs) {
        this.listMhs = listMhs;
    }
    
    @Override
    public int getRowCount() {
        return listMhs.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "NPM";
            case 1:
                return "NAMA MAHASISWA";
            case 2:
                return "JURUSAN";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0:
                return listMhs.get(row).getNpm();
            case 1:
                return listMhs.get(row).getNamaMhs();
            case 2:
                return listMhs.get(row).getJurusan().getNamaJur();
            default:
                return null;
        }
    }
    
}
