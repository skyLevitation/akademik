/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.akademik.view.table.model;

import demo.akademik.model.Jurusan;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sky
 */
public class tableModelJurusan extends AbstractTableModel {

    List<Jurusan> listJur;

    public tableModelJurusan(List<Jurusan> listJur) {
        this.listJur = listJur;
    }
    
    @Override
    public int getRowCount() {
        return listJur.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "KODE JURUSAN";
            case 1:
                return "NAMA JURUSAN";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0:
                return listJur.get(row).getKdJur();
            case 1:
                return listJur.get(row).getNamaJur();
            default:
                return null;
        }
    }
    
}
